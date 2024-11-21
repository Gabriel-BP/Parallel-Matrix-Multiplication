package matrix;

import jdk.incubator.vector.*;

public class VectorizedMatrixMultiplication {
    public static double[][] multiply(double[][] matrixA, double[][] matrixB) {
        int rowsA = matrixA.length;
        int colsA = matrixA[0].length;
        int colsB = matrixB[0].length;

        double[][] result = new double[rowsA][colsB];

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                double[] colB = new double[colsA];
                for (int k = 0; k < colsA; k++) {
                    colB[k] = matrixB[k][j];
                }

                VectorSpecies<Double> species = DoubleVector.SPECIES_PREFERRED;
                int loopBound = species.loopBound(colsA);

                for (int k = 0; k < loopBound; k += species.length()) {
                    DoubleVector vectorA = DoubleVector.fromArray(species, matrixA[i], k);
                    DoubleVector vectorB = DoubleVector.fromArray(species, colB, k);
                    result[i][j] += vectorA.mul(vectorB).reduceLanes(VectorOperators.ADD);
                }
            }
        }
        return result;
    }
}
