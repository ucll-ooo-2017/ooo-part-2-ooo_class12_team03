package controller;

import model.Categories;
import model.Player;
import view.OtherPlayerDicePane;
import view.YahtzeePane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

public class PlayerController extends Observable {

	private Player model;
	private YahtzeePane view;

	private ArrayList<DieController> dieControllers;
	private ArrayList<DieAsideController> dieAsideControllers;

	private OtherPlayerDicePane otherPlayerDicePane;

	private HashMap<String, Integer> rolls;

	private int diceRolled = 0;
	public static int DICE_ROLLED_MAX = 3;

	public PlayerController(String name) {
		this.model = new Player(name);
		this.otherPlayerDicePane = new OtherPlayerDicePane();
		rolls = new HashMap<>();

		dieControllers = new ArrayList<>(5);
		for (int i = 0; i < 5; i++) {
			dieControllers.add(new DieController(this));
		}
		dieAsideControllers = new ArrayList<>(5);
		for (int i = 0; i < 5; i++) {
			dieAsideControllers.add(new DieAsideController(this));
		}

		this.view = new YahtzeePane(this);
		addObserver(view.getScoreTable());
	}

	public void rollDice() {
		diceRolled++;
		for (DieController dieController : dieControllers) {
			dieController.roll();
		}

		if (diceRolled >= DICE_ROLLED_MAX) {
			endTurn();
		}
	}

	public void setDieAside(DieController dieController) {
		for (DieAsideController dieAsideController : dieAsideControllers) {
			if (dieAsideController.getModel().getValue() == 0) {
				dieAsideController.setAside(dieController);
				dieController.setAside();
				break;
			}
		}
	}

	public void endTurn() {
		// Set all die still on the board aside, disallowing any further rolling of the dice
		for (DieController dieController : dieControllers) {
			if (dieController.getModel().getValue() != 0) {
				setDieAside(dieController);
			}
		}
		// Will disable rolling button
		diceRolled = DICE_ROLLED_MAX;
		view.getDicePane().update(null, this); // We'll need to update the view
	}

	public void resetBoard() {
		diceRolled = 0;
		view.getDicePane().update(null, this); // We'll need to update the view
		for (DieAsideController dieAsideControllerController : dieAsideControllers) {
			dieAsideControllerController.reset();
		}
		for (DieController dieController : dieControllers) {
			dieController.reset();
		}
	}

	public void setCategory(Categories category) {
		int points = category.getPoints();
		int scoreToGrant;
		switch (category) {
		case ACES: case TWOS: case THREES: case FOURS: case FIVES: case SIXES:
			scoreToGrant = points * getDiceCount(points);
			break;

		case THREE_OF_A_KIND: case FOUR_OF_A_KIND:
			scoreToGrant = getDiceCount(points) >= points ? getDiceSum() : 0;
			break;

		case SMALL_STRAIGHT:
			scoreToGrant = hasStraight(4) ? points : 0;
			break;

		case LARGE_STRAIGHT:
			scoreToGrant = hasStraight(5) ? points : 0;
			break;

		case CHANCE:
			scoreToGrant = getDiceSum();
			break;

		case YAHTZEE:
			scoreToGrant = getDiceCount(dieAsideControllers.get(0).getModel().getValue()) == dieAsideControllers.size() ? 50 : 0;
			break;

		default:
			return;
		}
		if (!incrementRollList(category.getName(), scoreToGrant)) {
			return;
		}

		// Update totals and bonus
		int numbersSum = getNumbersSum();
		int bonus = numbersSum < 63 ? 0 : 35;
		int upperTotal = numbersSum + bonus;
		int lowerTotal = getLowerSum();
		rolls.put("TOTAL_SCORE", numbersSum);
		rolls.put("BONUS", bonus);
		rolls.put("TOTAL", upperTotal);
		rolls.put("UPPER_SECTION_TOTAL", upperTotal);
		rolls.put("LOWER_SECTION_TOTAL", lowerTotal);
		rolls.put("GRAND_TOTAL", upperTotal + lowerTotal);

		setChanged();
		notifyObservers(rolls);
		view.getDicePane().update(this, rolls);

		YahtzeeController.getInstance().nextPlayer();
	}

	private int getNumbersSum() {
		int sum = 0;
		sum += rolls.getOrDefault(Categories.ACES.getName(), 0);
		sum += rolls.getOrDefault(Categories.TWOS.getName(), 0);
		sum += rolls.getOrDefault(Categories.THREES.getName(), 0);
		sum += rolls.getOrDefault(Categories.FOURS.getName(), 0);
		sum += rolls.getOrDefault(Categories.FIVES.getName(), 0);
		sum += rolls.getOrDefault(Categories.SIXES.getName(), 0);
		return sum;
	}

	private int getLowerSum() {
		int sum = 0;
		sum += rolls.getOrDefault(Categories.THREE_OF_A_KIND.getName(), 0);
		sum += rolls.getOrDefault(Categories.FOUR_OF_A_KIND.getName(), 0);
		sum += rolls.getOrDefault(Categories.FULL_HOUSE.getName(), 0);
		sum += rolls.getOrDefault(Categories.SMALL_STRAIGHT.getName(), 0);
		sum += rolls.getOrDefault(Categories.LARGE_STRAIGHT.getName(), 0);
		sum += rolls.getOrDefault(Categories.YAHTZEE.getName(), 0);
		sum += rolls.getOrDefault(Categories.CHANCE.getName(), 0);
		return sum;
	}

	/*
	private void incrementRollList(String category, int amount) {
		if (rolls.containsKey(category)) {
			rolls.replace(category, rolls.get(category) + amount);
		} else {
			rolls.put(category, amount);
		}
	}
	*/
	private boolean incrementRollList(String category, int amount) {
		if (rolls.containsKey(category)) {
			return false;
		} else {
			rolls.put(category, amount);
			return true;
		}
	}

	private int getDiceCount(int value) {
		int count = 0;
		for (DieAsideController dieAsideController : dieAsideControllers) {
			if (dieAsideController.getModel().getValue() == value) {
				++count;
			}
		}
		return count;
	}

	private int getDiceSum() {
		int sum = 0;
		for (DieAsideController dieAsideController : dieAsideControllers) {
			sum += dieAsideController.getModel().getValue();
		}
		return sum;
	}

	private boolean hasStraight(int size) {
		if (size > 5) {
			throw new IllegalArgumentException("Straight cannot be larger than 5");
		}
		for (int i = 1; i <= 7 - size; ++i) {
			boolean straight = true;
			for (int j = i; j < i + size; ++j) {
				if (getDiceCount(j) < 1) {
					straight = false;
				}
			}
			if (straight) {
				return true;
			}
		}
		return false;
	}

	public boolean canRoll() {
		return diceRolled < DICE_ROLLED_MAX;
	}

	public Player getModel() {
		return model;
	}
	public YahtzeePane getView() {
		return view;
	}
	public ArrayList<DieController> getDieControllers() {
		return dieControllers;
	}
	public ArrayList<DieAsideController> getDieAsideControllers() {
		return dieAsideControllers;
	}
	public OtherPlayerDicePane getOtherPlayerDicePane() {
		return otherPlayerDicePane;
	}
}
