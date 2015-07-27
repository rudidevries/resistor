package nl.rudidevries.electronics.components.resistor;

class Stripe {
	private Color color;
	private Digit digit;
	private Multiplier multiplier;
	private Tolerance tolerance;
	
	Stripe(Color color) {
		this.color = color;
		
		try {
			digit = new Digit(color); 
		}
		catch (IllegalArgumentException e) {
			digit = null;
		}
		
		try {
			multiplier = new Multiplier(color); 
		}
		catch (IllegalArgumentException e) {
			multiplier = null;
		}
		
		try {
			tolerance = new Tolerance(color); 
		}
		catch (IllegalArgumentException e) {
			tolerance = null;
		}
	}

	Color getColor() {
		return color;
	}

	Digit getDigit() {
		return digit;
	}

	Multiplier getMultiplier() {
		return multiplier;
	}

	Tolerance getTolerance() {
		return tolerance;
	}

	
}
