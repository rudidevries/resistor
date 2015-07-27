package nl.rudidevries.electronics.components.resistor;

/**
 * Multiplier 
 * 
 * @author Rudi de Vries
 *
 */
class Multiplier {
	
	private Color color;
	private float multiplier;
	
	/**
	 * Constructor
	 * Create by constructor value.
	 * @param color Color code
	 */
	Multiplier(Color color) {
		this.color = color;
		multiplier = toFloat(color);
	}
	
	/**
	 * Constructor
	 * Create by multiplier value.
	 * @param multiplier Multiplier value.
	 */
	Multiplier(float multiplier) {
		this.multiplier = multiplier;
		color = toColor(multiplier);
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
	 * @return multiplier
	 */
	float getMultiplier() {
		return multiplier;
	}

	/**
	 * Convert multiplier to color
	 * @param multiplier
	 * @return Color
	 */
	static Color toColor(double multiplier) {
		return toColor((float) multiplier);
	}
	
	/**
	 * Convert multiplier to color
	 * @param multiplier
	 * @return Color
	 */
	static Color toColor(float multiplier) {
		switch ((int) multiplier) {
			case 1:
				return Color.BLACK;
			case 10:
				return Color.BROWN;
			case 100: 
				return Color.RED;
			case 1000:
				return Color.ORANGE;
			case 10_000:
				return Color.YELLOW;
			case 100_000:
				return Color.GREEN;
			case 1_000_000:
				return Color.BLUE;
		}
		
		if (multiplier == 0.1f) {
			return Color.SILVER;
		}
		if (multiplier == 0.01f) {
			return Color.GOLD;
		}
		
		throw new IllegalArgumentException("");
	}
	
	/**
	 * Convert color to multiplier
	 * @param color
	 * @return Multiplier
	 */
	static float toFloat(Color color) {
		switch (color) {
			case BLACK:
				return 1f;
			case BROWN:
				return 10f;
			case RED:
				return 100f;
			case ORANGE:
				return 1000f;
			case YELLOW:
				return 10_000f;
			case GREEN:
				return 100_000f;
			case BLUE:
				return 1_000_000f;
			case SILVER:
				return 0.1f;
			case GOLD:
				return 0.01f;
			default:
				throw new IllegalArgumentException("");
		}
		
	}
}
