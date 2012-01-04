package model;

import java.util.List;

public interface PairManagerable {
	public void reset();
	public void addPair(Pair pair);
	public void removePair(Pair pair);
	public boolean findPair(Pair pair);
	public List<Pair> getPairs();
}
