package baysensors;

import static org.junit.Assert.*;

import org.junit.Test;

public class IntegralMatrixTest {

	@Test
	public void test() {
		double[][] in;
		double[][] result;
		
		// One row, one column
		in = new double[1][1];
		in[0][0] = -1.0;
		result = IntegralMatrix.integralMatrix(in);
		assertEquals(result[0][0], -1.0, 0.0);
		
		// One row, two columns
		in = new double[1][2];
		in[0][0] = -1.0;
		in[0][1] = 3.0;
		result = IntegralMatrix.integralMatrix(in);
		assertEquals(result[0][0], -1.0, 0.0);
		assertEquals(result[0][1], 2.0, 0.0);
		
		// One row, three columns
		in = new double[1][3];
		in[0][0] = -1.0;
		in[0][1] = 3.0;
		in[0][2] = 6.0;
		result = IntegralMatrix.integralMatrix(in);
		assertEquals(result[0][0], -1.0, 0.0);
		assertEquals(result[0][1], 2.0, 0.0);
		assertEquals(result[0][2], 8.0, 0.0);
		
		// Two rows, one column
		in = new double[2][1];
		in[0][0] = -1.0;
		in[1][0] = 3.0;
		result = IntegralMatrix.integralMatrix(in);
		assertEquals(result[0][0], -1.0, 0.0);
		assertEquals(result[1][0], 2.0, 0.0);
		
		// Two rows, two columns
		in = new double[2][2];
		in[0][0] = -1.0;
		in[1][0] = 3.0;
		in[0][1] = -11.0;
		in[1][1] = -2.5;		
		result = IntegralMatrix.integralMatrix(in);
		assertEquals(result[0][0], -1.0, 0.0);
		assertEquals(result[1][0], 2.0, 0.0);
		assertEquals(result[0][1], -12.0, 0.0);
		assertEquals(result[1][1], -11.5, 0.0);
		
		// Two rows, three columns
		in = new double[2][3];
		in[0][0] = -1.0;
		in[1][0] = 3.0;
		in[0][1] = -11.0;
		in[1][1] = -2.5;
		in[0][2] = -1.0;
		in[1][2] = -2.5;
		result = IntegralMatrix.integralMatrix(in);
		assertEquals(result[0][0], -1.0, 0.0);
		assertEquals(result[1][0], 2.0, 0.0);
		assertEquals(result[0][1], -12.0, 0.0);
		assertEquals(result[1][1], -11.5, 0.0);
		assertEquals(result[0][2], -13.0, 0.0);
		assertEquals(result[1][2], -15.0, 0.0);
		
		// Three rows, one column
		in = new double[3][1];
		in[0][0] = -1.0;
		in[1][0] = 3.0;
		in[2][0] = -11.0;
		result = IntegralMatrix.integralMatrix(in);
		assertEquals(result[0][0], -1.0, 0.0);
		assertEquals(result[1][0], 2.0, 0.0);
		assertEquals(result[2][0], -9.0, 0.0);
		
		// Three rows, two columns
		in = new double[3][2];
		in[0][0] = -1.0;
		in[1][0] = 3.0;
		in[2][0] = -11.0;
		in[0][1] = -2.5;
		in[1][1] = -1.0;
		in[2][1] = -2.5;
		result = IntegralMatrix.integralMatrix(in);
		assertEquals(result[0][0], -1.0, 0.0);
		assertEquals(result[1][0], 2.0, 0.0);
		assertEquals(result[2][0], -9.0, 0.0);
		assertEquals(result[0][1], -3.5, 0.0);
		assertEquals(result[1][1], -1.5, 0.0);
		assertEquals(result[2][1], -15.0, 0.0);
		
		// Three rows, three columns
		in = new double[3][3];
		in[0][0] = -1.0;
		in[1][0] = 3.0;
		in[2][0] = -11.0;
		in[0][1] = -2.5;
		in[1][1] = -1.0;
		in[2][1] = -2.5;
		in[0][2] = 4.0;
		in[1][2] = 2.0;
		in[2][2] = 3.0;
		result = IntegralMatrix.integralMatrix(in);
		assertEquals(result[0][0], -1.0, 0.0);
		assertEquals(result[1][0], 2.0, 0.0);
		assertEquals(result[2][0], -9.0, 0.0);
		assertEquals(result[0][1], -3.5, 0.0);
		assertEquals(result[1][1], -1.5, 0.0);
		assertEquals(result[2][1], -15.0, 0.0);
		assertEquals(result[0][2], 0.5, 0.0);
		assertEquals(result[1][2], 4.5, 0.0);
		assertEquals(result[2][2], -6.0, 0.0);
	}

}
