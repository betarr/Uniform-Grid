package model;

public class Pair implements Comparable<Pair> {

	private int id1;
	private int id2;
	
	public Pair(int id1, int id2) {
		this.id1 = id1;
		this.id2 = id2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id1;
		result = prime * result + id2;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pair other = (Pair) obj;
		if ( this.id1 == other.id1 && this.id2 == other.id2 ) {
			return true;
		}
		if ( this.id1 == other.id2 && this.id2 == other.id1 ) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "[" + this.id1 + ", " + this.id2 + "]";
	}

	public int getId1() {
		return id1;
	}

	public void setId1(int id1) {
		this.id1 = id1;
	}

	public int getId2() {
		return id2;
	}

	public void setId2(int id2) {
		this.id2 = id2;
	}

	@Override
	public int compareTo(Pair pair) {
		Integer thisPair = this.id1 + this.id2;
		Integer paramPair = pair.id1 + pair.id2;
		if ( thisPair == paramPair ) {
			return new Integer(this.id1).compareTo(new Integer(pair.id1));
		}
		return thisPair.compareTo(paramPair);
	}
}
