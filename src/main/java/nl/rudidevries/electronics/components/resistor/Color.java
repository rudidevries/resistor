package nl.rudidevries.electronics.components.resistor;

/**
 * Colors use in resistor color coding
 * 
 * @author Rudi de Vries
 *
 */
public enum Color {
	BLACK("Black"),
	BROWN("Brown"),
	RED("Red"),
	ORANGE("Orange"),
	YELLOW("Yellow"),
	GREEN("Green"),
	BLUE("Blue"),
	VIOLET("Violet"),
	GREY("Grey"),
	WHITE("White"),
	SILVER("Silver"),
	GOLD("Gold");
	
	private String name;
	
	private Color(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
}
