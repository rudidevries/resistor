package nl.rudidevries.electronics.components.resistor;

/**
 * Tolerance 
 * 
 * @author Rudi de Vries
 *
 */
class Tolerance {
	/**
	 * Tolerance color code.
	 */
	private Color color;
	
	/**
	 * Tolerance percentage percentage.
	 */
	private int percentage;
	
	/**
	 * Constructor
	 * Create by color code. 
	 * 
	 * @param color Color
	 */
	Tolerance(Color color) {
		this.color = color;
		this.percentage = toInt(color);
	}
	
	/**
	 * Constructor
	 * Create by percentage.
	 * 
	 * @param percentage
	 */
	Tolerance(int percentage) {
		this.percentage = percentage;
		this.color = toColor(percentage);
	}
	
	/**
	 * Getter
	 * @return color
	 */
	Color getColor() {
		return color;
	}

	/**
	 * Getter
	 * @return percentage
	 */
	int getPercentage() {
		return percentage;
	}

	/**
	 * Convert percentage to color code
	 * @param percentage
	 * @return Color
	 */
	static Color toColor(int percentage) {
		switch (percentage) {
			case 1:
				return Color.BROWN;
			case 2: 
				return Color.RED;
			case 3:
				return Color.ORANGE;
			case 4:
				return Color.YELLOW;
			case 5:
				return Color.SILVER;
			case 10:
				return Color.GOLD;
		}
		
		throw new IllegalArgumentException("");
	}
	
	/**
	 * Convert color code to percentage 
	 * @param color
	 * @return Percentage
	 */
	static int toInt(Color color) {
		switch (color) {
			case BROWN:
				return 1;
			case RED:
				return 2;
			case ORANGE:
				return 3;
			case YELLOW:
				return 4;
			case SILVER:
				return 5;
			case GOLD:
				return 10;
			default:
				throw new IllegalArgumentException("");
		}
	}
}
