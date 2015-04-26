package main;

public class Interaction {

	public static Interaction[][] interactions = new Interaction[9][9];

	public Action action;
	public Object[] args;

	public Interaction(Interactive active, Interactive passive, Action action, Object[] args) {

	}

	public void act(Action action, Object[] args) {

		switch (action) {
		case PICKUP:

			break;
		case CRAFT:

			break;
		case THINK:

			break;
		case TALK:

			break;
		case THROWSTONE:

			break;
		}
	}

	public static void load() {

		// new Interaction(Item.stone, Item.fruit, Action.CRAFT, new Object[] { Item.brokenFruit });
	}

	public static void act(Interactive active, Interactive passive) {

	}
}
