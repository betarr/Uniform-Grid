package model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class HashPairManagementTest {

	private static HashPairManagement hashPairManagement;
	
	@BeforeClass
	public static void initialization() {
		HashPairManagementTest.hashPairManagement = new HashPairManagement();
		HashPairManagementTest.hashPairManagement.addPair(new Pair(1, 3));
		HashPairManagementTest.hashPairManagement.addPair(new Pair(2, 2));
		HashPairManagementTest.hashPairManagement.addPair(new Pair(4, 3));
	}
	
	@Test
	public void findPair() {
		assertThat(HashPairManagementTest.hashPairManagement.findPair(new Pair(1, 3)), is(true));
	}
	
	@Test
	public void testAddPair() {
		HashPairManagementTest.hashPairManagement.addPair(new Pair(7, 8));
		assertThat(HashPairManagementTest.hashPairManagement.findPair(new Pair(7, 8)), is(true));
	}
	
	@Test
	public void testAddPair2() {
		HashPairManagementTest.hashPairManagement.addPair(new Pair(9, 11));
		assertThat(HashPairManagementTest.hashPairManagement.findPair(new Pair(11, 9)), is(true));
	}
	
	@Test
	public void testRemovePair() {
		HashPairManagementTest.hashPairManagement.removePair(new Pair(7, 8));
		assertThat(HashPairManagementTest.hashPairManagement.findPair(new Pair(7, 8)), is(false));
	}
	
	@Test
	public void testRemovePair2() {
		HashPairManagementTest.hashPairManagement.removePair(new Pair(11, 9));
		assertThat(HashPairManagementTest.hashPairManagement.findPair(new Pair(9, 11)), is(false));
	}
	
	@Ignore
	@Test
	public void getPairs() {
		Pair[] pairs = new Pair[]{
				new Pair(1, 3),
				new Pair(2, 2),
				new Pair(4, 3)
		};
		Arrays.sort(pairs);
		Pair[] pairs2 = this.fromListToSortedPairsArray(HashPairManagementTest.hashPairManagement.getPairs());
		assertThat(pairs2, is(pairs));
	}
	
	@Ignore
	@Test
	public void getPairs2() {
		Pair[] pairs = new Pair[]{
				new Pair(3, 1),
				new Pair(2, 2),
				new Pair(3, 4)
		};
		Arrays.sort(pairs);
		Pair[] pairs2 = this.fromListToSortedPairsArray(HashPairManagementTest.hashPairManagement.getPairs());
		assertThat(pairs2, is(pairs));
	}
	
	private Pair[] fromListToSortedPairsArray(List<Pair> pairs) {
		Pair[] pairsArray = new Pair[pairs.size()];
		for ( int i = 0; i < pairs.size(); i++ ) {
			pairsArray[i] = pairs.get(i);
		}
		Arrays.sort(pairsArray);
		return pairsArray;
	}
}
