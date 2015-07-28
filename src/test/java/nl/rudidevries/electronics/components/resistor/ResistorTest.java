package nl.rudidevries.electronics.components.resistor;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import nl.rudidevries.electronics.components.resistor.Resistor;

public class ResistorTest {

	@Test
	public void testValueConversion() {
		assertEquals(470, Resistor.labelToOhms("470R"));
		assertEquals("470", Resistor.ohmsToLabel(470));
		
		assertEquals(4700, Resistor.labelToOhms("4K7"));
		assertEquals("4K7", Resistor.ohmsToLabel(4700));
		
		assertEquals(2200, Resistor.labelToOhms("2K2"));
		assertEquals("2K2", Resistor.ohmsToLabel(2200));
		
		assertEquals(2000, Resistor.labelToOhms("2K"));
		assertEquals("2K", Resistor.ohmsToLabel(2000));
		
		assertEquals(200, Resistor.labelToOhms("200"));
		assertEquals("200", Resistor.ohmsToLabel(200));
		
		assertEquals(220, Resistor.labelToOhms("220"));
		assertEquals("220", Resistor.ohmsToLabel(220));
		
		assertEquals(20, Resistor.labelToOhms("20"));
		assertEquals("20", Resistor.ohmsToLabel(20));
		
		assertEquals(2, Resistor.labelToOhms("2"));
		assertEquals("2", Resistor.ohmsToLabel(2));
	}

	@Test
	public void testInstanceCreation() {
		Resistor r1;
		
		r1 = Resistor.getInstance(Color.YELLOW, Color.VIOLET, Color.RED, Color.BROWN);
		assertEquals(1, r1.getTolerance());
		assertEquals("4K7", r1.toString());
		r1 = Resistor.getInstance(Color.RED, Color.RED, Color.BLACK, Color.RED);
		assertEquals(2, r1.getTolerance());
		assertEquals("22", r1.toString());
		
		r1 = Resistor.getInstance("4K7");
		assertEquals("4K7", r1.toString());
		r1 = Resistor.getInstance("4K7", Color.ORANGE);
		assertEquals(3, r1.getTolerance());
		assertEquals("4K7", r1.toString());
		
		r1 = Resistor.getInstance(4700);
		assertEquals("4K7", r1.toString());
		r1 = Resistor.getInstance(4700, Color.YELLOW);
		assertEquals(4, r1.getTolerance());
		assertEquals("4K7", r1.toString());
		r1 = Resistor.getInstance(4700, 5);
		assertEquals("4K7", r1.toString());
		
		r1 = Resistor.getInstance(4700f, Color.SILVER);
		assertEquals(5, r1.getTolerance());
		assertEquals("4K7", r1.toString());
		r1 = Resistor.getInstance(4700f);
		assertEquals("4K7", r1.toString());
		r1 = Resistor.getInstance(4700f, Color.GOLD);
		assertEquals(10, r1.getTolerance());
		assertEquals("4K7", r1.toString());
	}
}
