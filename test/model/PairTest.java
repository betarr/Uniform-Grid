package model;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

import org.junit.Before;
import org.junit.Test;

public class PairTest {
	
	private Pair pair1;
	private Pair pair2;
	private Pair pair3;
	private Pair pair4;
	private Pair pair5;
	
	@Before
	public void initialization() {
		this.pair1 = new Pair(1, 2);
		this.pair2 = new Pair(1, 2);
		this.pair3 = new Pair(2, 1);
		this.pair4 = new Pair(2, 3);
		this.pair5 = new Pair(2, 2);
	}
	
	@Test
	public void testEquals() {
		assertThat(this.pair1, is(this.pair2));
	}

	@Test
	public void testEquals2() {
		assertThat(this.pair2, is(this.pair3));
	}
	
	@Test
	public void testNotEquals() {
		assertThat(this.pair3, is(not(this.pair4)));
	}
	
	@Test
	public void testNotEquals2() {
		assertThat(this.pair1, is(not(this.pair5)));
	}
}
