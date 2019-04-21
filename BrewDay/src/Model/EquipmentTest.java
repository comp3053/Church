package Model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EquipmentTest {

	private static Equipment equip = new Equipment(10,"pipe");
	
	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testGetCapacity() {
		assertEquals(10,equip.getCapacity());
	}

	@Test
	public void testGetType() {
		assertEquals("pipe",equip.getType());
	}

	@Test
	public void testGetAvaliable() {
		assertEquals(true,equip.getAvaliable());
	}

	@Test
	public void testUpdateEquipmentType() {
		assertEquals(false, equip.updateEquipmentType("line"));
	}

	@Test
	public void testUpdateEquipmentCapacity() {
		assertEquals(true, equip.updateEquipmentCapacity("line", 20));
	}

}
