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
		
		assertEquals(20, Resistor.labelToOhms("20"));
		assertEquals("20", Resistor.ohmsToLabel(20));
		
		assertEquals(2, Resistor.labelToOhms("2"));
		assertEquals("2", Resistor.ohmsToLabel(2));
		
	}

}
