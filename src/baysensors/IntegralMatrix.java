package baysensors;
import java.util.Map;
import java.util.HashMap;

public class IntegralMatrix {
	
	/**
	 * Dynamic programming algorithm to compute integral matrix.
	 * Runs in O(n) time (where n is number of elements in matrix).
	 * Takes O(n) space.
	 * Update Equation: Integral[i][j] = Input[i][j] + Integral[i][j-1] + ColumnSum[i-1][j]  
	 * @param in
	 * @return
	 */
	public static double[][] integralMatrix (double[][] in) {
		int rows = in.length;
		int cols = in[0].length;
		
		
		// store submatrix sums, indexed by bottom-right corner of sub-matrix.
		double[][] integral = new double[rows][cols];
		
		// store column sums, indexed by bottom entry
		double[][] columnSums = new double[rows][cols];
		
		// initialize in[0][0]
		integral[0][0] = in[0][0];
		columnSums[0][0] = in[0][0];
		
		// initialize left edge
		for (int i = 1; i < rows; i++) {
			integral[i][0] = in[i][0] + integral[i-1][0];
			columnSums[i][0] = columnSums[i-1][0];
		}
		
		// initialize top edge
		for (int j = 1; j < cols; j++) {
			integral[0][j] = in[0][j] + integral[0][j-1];
			columnSums[0][j] = in[0][j];
		}

		for (int i = 1; i < rows; i++) {
			for (int j = 1; j < cols; j++) {
				integral[i][j] = in[i][j] + integral[i][j-1] + columnSums[i-1][j];
				columnSums[i][j] = in[i][j] + columnSums[i-1][j];
			}
		}	
		return integral;
	}
	
	public static void printMatrix (double[][] mat) {
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				System.out.print(mat[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void main (String [] args) {
		double[][] in = new double[2][2];
		in[0][0] = -1;
		in[0][1] = 3;
		in[1][0] = 0;
		in[1][1] = 5;
		double[][] result = integralMatrix(in);
		printMatrix(result);
		
	}
}