package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HashPairManagement implements PairManagerable {
	
	private static int p1 = 15485941;
	private static int p2 = 15486907;
	private static int N = 500;
	
	private Map<Integer, Pair> pairsHash;
	
	public HashPairManagement() {
		this.pairsHash = new HashMap<Integer, Pair>();
	}
	
	public void reset() {
		
	}

	@Override
	public void addPair(Pair pair) {
		pair = this.unifyPair(pair);
		Integer hash = this.getHash(pair);
		this.pairsHash.put(hash, pair);
	}

	@Override
	public void removePair(Pair pair) {
		pair = this.unifyPair(pair);
		Integer hash = this.getHash(pair);
		this.pairsHash.remove(hash);
	}

	@Override
	public boolean findPair(Pair pair) {
		pair = unifyPair(pair);
		Integer hash = this.getHash(pair);
		Pair p = this.pairsHash.get(hash);
		return p != null;
	}

	@Override
	public List<Pair> getPairs() {
		List<Pair> pairs = new ArrayList<Pair>();
		Iterator<Integer> it = this.pairsHash.keySet().iterator();
		while ( it.hasNext() ) {
			Pair p = this.pairsHash.get(it.next());
			if ( !pairs.contains(p) ) {
				pairs.add(p);
			}
		}
		return pairs;
	}
	
	private Integer getHash(Pair pair) {
		return (pair.getId1() * HashPairManagement.p1 + pair.getId2() * HashPairManagement.p2) % HashPairManagement.N;
	}
	
	private Pair unifyPair(Pair pair) {
		if ( pair.getId1() > pair.getId2() ) {
			int tmp = pair.getId1();
			pair.setId1(pair.getId2());
			pair.setId2(tmp);
		}
		return pair;
	}

	@Override
	public String toString() {
		List<Pair> pairs = this.getPairs();
		String result = "";
		for (Pair pair : pairs) {
			result += pair;
		}
		return result;
	}
	
	

}
