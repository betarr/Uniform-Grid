package model;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

public class GridSquereTest {

	@Test
	public void testContainsId() {
		GridSquere gridSquere = this.createAndFillGridSquere(new int[]{34, 5, 71, 16});
		assertThat(gridSquere.containsId(71), is(true));
	}
	
	@Test
	public void testNotContainsId() {
		GridSquere gridSquere = this.createAndFillGridSquere(new int[]{34, 5, 71, 16});
		assertThat(gridSquere.containsId(72), is(false));
	}
	
	@Test
	public void testGetNumberOfIds() {
		GridSquere gridSquere = this.createAndFillGridSquere(new int[]{34, 5, 71, 16});
		assertThat(gridSquere.getNumberOfIds(), is(4));
	}
	
	@Test
	public void testAddId() {
		GridSquere gridSquere = this.createAndFillGridSquere(new int[]{34, 5, 71, 16});
		gridSquere.addId(84);
		assertThat(gridSquere.containsId(84), is(true));
	}
	
	@Test
	public void testRemoveId() {
		GridSquere gridSquere = this.createAndFillGridSquere(new int[]{34, 5, 71, 16});
		gridSquere.removeId(71);
		assertThat(gridSquere.containsId(71), is(false));
	}
	
	private GridSquere createAndFillGridSquere(int[] ids) {
		GridSquere gridSquere = new GridSquere();
		for (int id : ids) {
			gridSquere.addId(id);
		}
		return gridSquere;
	}
}
