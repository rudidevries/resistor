package nl.rudidevries.electronics.components.resistor;

class Digit {

	private Color color;
	private int value;
	
	Digit(Color color) {
		this.color = color;
		this.value = toInt(color);
	}
	
	Digit(int value) {
		this.value = value;
		this.color = toColor(value);
	}
	
	Color getColor() {
		return color;
	}

	int getValue() {
		return value;
	}

	static Color toColor(int value) {
		Color[] colors = Color.values();
		if (colors.length < (value + 1)) {
			throw new IllegalArgumentException("");
		}
		
		return colors[value];
	}
	
	static int toInt(Color color) {
		int value = color.ordinal();
		
		if (value > 9) {
			throw new IllegalArgumentException("");
		}
		
		return value;
	}
	
}
