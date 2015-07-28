package nl.rudidevries.electronics.components.resistor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Resistor {
	private List<Stripe> stripes;
	
	/**
	 * Constructor 
	 * 
	 * @param stripe1
	 * @param stripe2
	 * @param stripe3
	 * @param stripe4
	 */
	private Resistor(Stripe stripe1, Stripe stripe2, Stripe stripe3, Stripe stripe4) {
		stripes = new ArrayList<>();
		stripes.add(stripe1);
		stripes.add(stripe2);
		stripes.add(stripe3);
		stripes.add(stripe4);
	}
	
	/**
	 * Get new resistor instance.
	 * Beware of all the impossible color combinations!
	 * 
	 * @param color1 Color of the first stripe.
	 * @param color2 Color of the second stripe.
	 * @param color3 Color of the third stripe.
	 * @param color4 Color of the fourth stripe.
	 * @return Resistor.
	 */
	public static Resistor getInstance(Color color1, Color color2, Color color3, Color color4) {
		return new Resistor(
				new Stripe(color1),
				new Stripe(color2),
				new Stripe(color3),
				new Stripe(color4)
			);
	}
	
	/**
	 * Get new resistor instance.
	 * @param value Resistance in Label format (i.e. 4K7)
	 * @return Resistor
	 */
	public static Resistor getInstance(String value) {
		return getInstance(labelToOhms(value));
	}
	
	/**
	 * Get new resistor instance.
	 * @param value Resistance in Label format (i.e. 4K7)
	 * @param toleranceColor color of the stripe indicating tolerance.
	 * @return Resistor
	 */
	public static Resistor getInstance(String value, Color toleranceColor) {
		return getInstance(labelToOhms(value), toleranceColor);
	}
	
	/**
	 * Get a new resistor instance
	 * @param value Resistance in Ohms.
	 * @param tolerance tolerance percentage.
	 * @return Resistor
	 */
	public static Resistor getInstance(int value, int tolerance) {
		return getInstance(value, Tolerance.toColor(tolerance));
	}
	
	/**
	 * Get new resistor instance.
	 * @param value Resistance in Ohms.
	 * @param toleranceColor color of the stripe indicating tolerance.
	 * @return Resistor
	 */
	public static Resistor getInstance(int value, Color toleranceColor) {
		return getInstance((float) value, toleranceColor);
	}
	
	/**
	 * Get a new resistor instance
	 * @param value Resistance in Label format (i.e. 4K7)
	 * @param tolerance tolerance percentage.
	 * @return Resistor
	 */
	public static Resistor getInstance(String value, int tolerance) {
		return getInstance(value, Tolerance.toColor(tolerance));
	}
	
	/**
	 * Get new resistor instance. A default tolerance of 10% will be set.
	 * 
	 * @param value Resistance in Label format (i.e. 4K7)
	 * @return Resistor
	 */
	public static Resistor getInstance(float value) {
		return getInstance(value, 10);
	}
	
	/**
	 * Get new resistor instance.
	 * @param value in Ohms. 
	 * @param tolerance tolerance percentage.
	 * @return Resistor
	 */
	public static Resistor getInstance(float value, int tolerance) {
		return getInstance(value, Tolerance.toColor(tolerance));
	}
	
	/**
	 * Get new resistor instance.
	 * @param value in Ohms. 
	 * @param toleranceColor tolerance color
	 * @return Resistor
	 */
	public static Resistor getInstance(float value, Color toleranceColor) {
		if (value < 0) {
			throw new IllegalArgumentException("Illegal negative value for resistor.");
		}
		
		if (value < 1) {
			throw new IllegalArgumentException("Resistors smaller than one Ohm are not supported.");
		}
		
		
		int d1, d2;
		double multiplierValue;
		
		String strValue = String.valueOf((int) value);
		if (strValue.length() > 1) {
			d1 = Integer.parseInt(strValue.substring(0, 1));
			d2 = Integer.parseInt(strValue.substring(1, 2));
			
			if (strValue.length() > 2) {
				multiplierValue = Math.pow(10, strValue.length() - 2);
			}
			else {
				multiplierValue = 1;
			}
		}
		else {
			// Only a single number.
			d1 = 0;
			d2 = Integer.parseInt(strValue.substring(0, 1));
			multiplierValue = 1;
		}
		
		return new Resistor(
				new Stripe(Digit.toColor(d1)), 
				new Stripe(Digit.toColor(d2)), 
				new Stripe(Multiplier.toColor(multiplierValue)), 
				new Stripe(toleranceColor)
			);
	}
	
	/**
	 * Get value in Ohms
	 * @return ohms
	 */
	public int ohms() {
		return (int) ((float) ((10 * stripes.get(0).getDigit().getValue()) + stripes.get(1).getDigit().getValue())
			* stripes.get(2).getMultiplier().getMultiplier());
	}
	
	/**
	 * Get value in Ohms.
	 * @return
	 */
	public int getOhms() {
		return ohms();
	}
	
	/**
	 * Get tolerance percentage.
	 * @return tolerance percentage
	 */
	public int tolerance() {
		return getTolerance();
	}
	
	/**
	 * Get tolerance percentage.
	 * @return tolerance percentage
	 */
	public int getTolerance() {
		return stripes.get(3).getTolerance().getPercentage();
	}
	
	/**
	 * Get resistor label
	 * @return Resistor label, i.e. 4K7
	 */
	public String getLabel() {
		return toString();
	}
	
	/**
	 * Get factor indicator
	 * @param divisor
	 * @return M or K
	 * @throws IllegalArgumentException to indicate there is no match, 
	 * value should be divided further to get matching factor.
	 */
	private static String getLabelOfDivisor(int divisor) {
		switch(divisor) {
			case 1_000_000:
				return "M";
			case 1000:
				return "K";
			case 1:
				return "";
			default:
				throw new IllegalArgumentException("Divisor unsupported");
		}
	}
	
	/**
	 * Get the divisor for the label.
	 * 
	 * @param label
	 * @return divisor.
	 */
	private static int getDivisorOfLabel(String label) {
		switch (label) {
			case "M":
			case "m":
				return 1_000_000;
			case "K":
			case "k":
				return 1000;
			default:
				throw new IllegalArgumentException("Divisor label unsupported");
		}
	}
	
	/**
	 * Recursive method to convert a int value to a string value. 
	 * 
	 * If the value can't be divided by the divisor, the divisor must be smaller.
	 * If there is a remainder, the divisor must be smaller.
	 * If there is no factor available, the divisor must be smaller.
	 * Else, return the number of times the value can be devided + factor label.
	 * 
	 * @param value
	 * @param divisor
	 * @return
	 */
	private static String recursiveToLabel(int value, int divisor) {
		int divides = value / divisor;
		
		// If it does not divide, try with a lower divisor.
		if (divides == 0) {
			return recursiveToLabel(value, (divisor / 10));
		}
		
		try {
			String factor = getLabelOfDivisor(divisor);
			
			String append = "";
			// If the remainder is divisable by the next divisor, the number of 
			// times it can be devided must be added.
			if (value % divisor > 0 && (value % divisor) % (divisor / 10) == 0) {
				append = String.valueOf((value % divisor) / (divisor / 10));
			}
			
			return divides + factor + append;
		}
		catch (IllegalArgumentException e) {
			// There is no label for this divisor, try with a lower divisor
			return recursiveToLabel(value, (divisor / 10));
		}
	}
	
	/**
	 * Convert an int value to a resistor label.
	 * @param value resistance in Ohms.
	 * @return resistor label (i.e. 10k)
	 */
	public static String ohmsToLabel(int value) {
		return recursiveToLabel(value, 1_000_000);
	}
	
	/**
	 * Convert a resistor value label to a integer value.
	 * @param label Resistance label, i.e. 4K7
	 * @return integer value
	 */
	public static int labelToOhms(String label) {
		Pattern p = Pattern.compile("(\\d+)([kKmM]{0,1})(\\d*)[rR]?$");
		Matcher m = p.matcher(label);
		if (m.matches()) {
			int divisor = 1;
			
			// If divisor char is found, get right divisor value.
			if (!m.group(2).equals("")) {
				divisor = getDivisorOfLabel(m.group(2));
			}
			
			int result = Integer.parseInt(m.group(1)) * divisor;
			
			// If there is a remainder after the divisor, add this.
			if (!m.group(3).equals("")) {
				result += (Integer.parseInt(m.group(3)) * (divisor / 10));
			}
			
			return result;
		}
		
		return 0;
	}
	
	/**
	 * Get value in String format, i.e. 10k
	 */
	public String toString() {
		return ohmsToLabel(ohms());
	}

	/**
	 * Get color codes of stripes in array.
	 * @return Array of colors
	 */
	public Color[] colorCode() {
		Color[] colorCode = new Color[4];
		
		for (int i = 0; i < 4; i++) {
			colorCode[i] = stripes.get(i).getColor();
		}
		
		return colorCode;
	}
	
	/**
	 * Get color codes of stripes in array.
	 * @return Array of colors
	 */
	public Color[] getColorCode() {
		return colorCode();
	}
}
