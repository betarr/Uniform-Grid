package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatrixPairManagement implements PairManagerable {

	private boolean[][] pairsMatrix;

	public MatrixPairManagement() {
		this.pairsMatrix = new boolean[5][5];
		initMatrix();
	}
	
	public void reset() {
		for (boolean[] row : this.pairsMatrix) {
			Arrays.fill(row, false);
		}
	}

	@Override
	public void addPair(Pair pair) {
		while (pair.getId1() >= this.pairsMatrix.length
				|| pair.getId2() >= this.pairsMatrix[0].length) {
			this.extendMatrix();
		}
		this.pairsMatrix[pair.getId1()][pair.getId2()] = true;
		this.pairsMatrix[pair.getId2()][pair.getId1()] = true;
	}

	@Override
	public void removePair(Pair pair) {
		if (pair.getId1() < this.pairsMatrix.length
				&& pair.getId2() < this.pairsMatrix[0].length) {
			this.pairsMatrix[pair.getId1()][pair.getId2()] = false;
			this.pairsMatrix[pair.getId2()][pair.getId1()] = false;
		}
	}

	@Override
	public boolean findPair(Pair pair) {
		return this.pairsMatrix[pair.getId1()][pair.getId2()];
	}

	@Override
	public List<Pair> getPairs() {
		List<Pair> result = new ArrayList<Pair>();
		for (int i = 0; i < this.pairsMatrix.length; i++) {
			for (int j = 0; j < this.pairsMatrix[i].length; j++) {
				if (this.pairsMatrix[i][j]) {
					Pair pair = new Pair(i, j);
					if (!result.contains(pair)) {
						result.add(pair);
					}
				}
			}
		}
		return result;
	}

	private void initMatrix() {
		for (int i = 0; i < this.pairsMatrix.length; i++) {
			Arrays.fill(this.pairsMatrix[i], false);
		}
	}

	private void extendMatrix() {
		int oldMatrixRowsNumber = this.pairsMatrix.length;
		int oldMatrixColumnsNumber = this.pairsMatrix[0].length;
		boolean[][] newPairsMatrix = new boolean[2 * oldMatrixRowsNumber][2 * oldMatrixColumnsNumber];
		for (int i = 0; i < newPairsMatrix.length; i++) {
			for (int j = 0; j < newPairsMatrix[i].length; j++) {
				if (i < oldMatrixRowsNumber && j < oldMatrixColumnsNumber) {
					newPairsMatrix[i][j] = this.pairsMatrix[i][j];
				} else {
					newPairsMatrix[i][j] = false;
				}
			}
		}
		this.pairsMatrix = newPairsMatrix;
	}

	@Override
	public String toString() {
		String result = "";
		for (boolean[] arr : this.pairsMatrix) {
			for (boolean isPair : arr) {
				result += isPair ? "1 " : "0 ";
			}
			result += "\n";
		}
		return result;
	}

}
