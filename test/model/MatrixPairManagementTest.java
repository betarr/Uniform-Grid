package model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.Test;

public class MatrixPairManagementTest {

	private static MatrixPairManagement matrixPairManagement;
	
	@BeforeClass
	public static void initialization() {
		MatrixPairManagementTest.matrixPairManagement = new MatrixPairManagement();
		MatrixPairManagementTest.matrixPairManagement.addPair(new Pair(1, 3));
		MatrixPairManagementTest.matrixPairManagement.addPair(new Pair(2, 2));
		MatrixPairManagementTest.matrixPairManagement.addPair(new Pair(4, 3));
	}
	
	@Test
	public void findPair() {
		assertThat(MatrixPairManagementTest.matrixPairManagement.findPair(new Pair(1, 3)), is(true));
	}
	
	@Test
	public void testAddPair() {
		MatrixPairManagementTest.matrixPairManagement.addPair(new Pair(7, 8));
		assertThat(MatrixPairManagementTest.matrixPairManagement.findPair(new Pair(7, 8)), is(true));
		this.testRemovePair();
	}
	
	@Test
	public void testAddPair2() {
		MatrixPairManagementTest.matrixPairManagement.addPair(new Pair(9, 11));
		assertThat(MatrixPairManagementTest.matrixPairManagement.findPair(new Pair(11, 9)), is(true));
		this.testRemovePair2();
	}
	
	//@Test
	public void testRemovePair() {
		MatrixPairManagementTest.matrixPairManagement.removePair(new Pair(7, 8));
		assertThat(MatrixPairManagementTest.matrixPairManagement.findPair(new Pair(7, 8)), is(false));
	}
	
	//@Test
	public void testRemovePair2() {
		MatrixPairManagementTest.matrixPairManagement.removePair(new Pair(11, 9));
		assertThat(MatrixPairManagementTest.matrixPairManagement.findPair(new Pair(9, 11)), is(false));
	}
	
	@Test
	public void getPairs() {
		Pair[] pairs = new Pair[]{
				new Pair(1, 3),
				new Pair(2, 2),
				new Pair(4, 3)
		};
		assertThat(MatrixPairManagementTest.matrixPairManagement.getPairs(), is(Arrays.asList(pairs)));
	}
	
	@Test
	public void getPairs2() {
		Pair[] pairs = new Pair[]{
				new Pair(3, 1),
				new Pair(2, 2),
				new Pair(3, 4)
		};
		assertThat(MatrixPairManagementTest.matrixPairManagement.getPairs(), is(Arrays.asList(pairs)));
	}
}
