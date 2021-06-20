package processor;

import java.util.Locale;
import java.util.Scanner;
import java.util.Arrays;

public class Main {
    
    static final Scanner sc = new Scanner(System.in).useLocale(Locale.ENGLISH);
    
    public static void main(String[] args) {
        boolean flag = true;
        while (flag) {
            printMenu();
            System.out.print("Your choice: ");
            int num = sc.nextInt();
            switch (num) {
                case 1:
                    double[][] matrixA = fillMatrix("first");
                    double[][] matrixB = fillMatrix("second");
                    displayMatrix(add(matrixA, matrixB));
                    break;
                case 2:
                    matrixA = fillMatrix("a");
                    System.out.print("Enter constant: ");
                    double constant = sc.nextDouble();
                    displayMatrix(multiplyByConstant(matrixA, constant));
                    break;
                case 3:
                    matrixA = fillMatrix("first");
                    matrixB = fillMatrix("second");
                    displayMatrix(multiply(matrixA, matrixB));
                    break;
                case 4:
                    System.out.println("1. Main diagonal");
                    System.out.println("2. Side diagonal");
                    System.out.println("3. Vertical line");
                    System.out.println("4. Horizontal line");
                    System.out.println();
                    System.out.print("Your choice: ");
                    int mode = sc.nextInt();
                    switch (mode) {
                        case 1:
                            matrixA = fillMatrix("a");
                            displayMatrix(transposeMain(matrixA));
                            break;
                        case 2:
                            matrixA = fillMatrix("a");
                            displayMatrix(transposeSide(matrixA));
                            break;
                        case 3:
                            matrixA = fillMatrix("a");
                            displayMatrix(transposeVertical(matrixA));
                            break;
                        case 4:
                            matrixA = fillMatrix("a");
                            displayMatrix(transposeHorizontal(matrixA));
                            break;
                        default:
                            errorMsg();
                            break;
                    }
                    break;
                case 5:
                    matrixA = fillMatrix("a");
                    System.out.println("The result is: ");
                    System.out.println(determinant(matrixA));
                    System.out.println();
                    break;
                case 6:
                    matrixA = fillMatrix("a");
                    displayMatrix(inverse(matrixA));
                    break;
                case 0:
                    flag = false;
                    break;
                default:
                    errorMsg();
                    break;
            }
        }
    }

    private static void printMenu() {
        System.out.println("1. Add matrices");
        System.out.println("2. Multiply matrix by a constant");
        System.out.println("3. Multiply matrices");
        System.out.println("4. Transpose matrix");
        System.out.println("5. Calculate a determinant");
        System.out.println("6. Inverse matrix");
        System.out.println("0. Exit");
        System.out.println();
    }

    private static double[][] fillMatrix(String number) {
        System.out.print("Enter size of " + number + " matrix: ");
        int row = sc.nextInt();
        int col = sc.nextInt();
        String[][] matrix = new String[row][col];
        System.out.println("Enter " + number + " matrix: ");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = sc.next();
            }
        }
        
        double[][] filledMatrix = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                filledMatrix[i][j] = Double.parseDouble(matrix[i][j]);
            }
        }
        return filledMatrix;  
    }

    private static void displayMatrix(double[][] matrix) {
        if (Arrays.deepEquals(matrix, new double[0][])) {
            errorMsg();
        } else {
            System.out.println("The result is: ");
            for (double[] nums : matrix) {
                for (double num : nums) {
                    System.out.printf("%5.2f" + " ", num);
                }
                System.out.println();
            }
        }
        System.out.println();
    }

    private static void errorMsg() {
        System.out.println("The operation cannot be performed.");
        System.out.println();
    }

    /* Operations */

    private static double[][] add(double[][] matrixA, double[][] matrixB) {
        double[][] result = new double[matrixA.length][matrixA[0].length];
        if (matrixA.length != matrixB.length && matrixA[0].length != matrixB[0].length) {
            errorMsg();
            return new double[0][0];
        }
        for (int row = 0; row < matrixA.length; row++) {
            for (int col = 0; col < matrixA[row].length; col++) {
                result[row][col] = matrixA[row][col] + matrixB[row][col];
            }
        } 
        return result;
    }

    private static double[][] multiplyByConstant(double[][] matrixA, double constant) {
        double[][] result = new double[matrixA.length][matrixA[0].length];
        for (int row = 0; row < matrixA.length; row++) {
            for (int col = 0; col < matrixA[row].length; col++) {
                result[row][col] = matrixA[row][col] * constant;
            }
        }
        return result;
    }

    private static double[][] multiply(double[][] matrixA, double[][] matrixB) {
        double[][] result = new double[matrixA.length][matrixB[0].length];
        if (matrixA[0].length != matrixB.length) {
            errorMsg();
            return new double[0][0];
        }
        for (int row = 0; row < matrixA.length; row++) {
            for (int col = 0; col < matrixB[row].length; col++) {
                for (int i = 0; i < matrixA[row].length; i++) {
                    result[row][col] += (matrixA[row][i] * matrixB[i][col]);
                }
            }
        }
        return result;
    }
    
    private static double[][] transposeMain(double[][] matrixA) {
        double[][] result = new double[matrixA.length][matrixA[0].length];
		for (int row = 0; row < matrixA.length; row++) {
            for (int col = 0; col < matrixA[row].length; col++) {
                result[row][col] = matrixA[col][row];
            }
        }
        return result;
    }

    private static double[][] transposeSide(double[][] matrixA) {
        double[][] result = new double[matrixA.length][matrixA[0].length];
        for (int row = 0; row < matrixA.length; row++) {
			for (int col = 0; col < matrixA.length; col++) {
				result[row][col] = (matrixA[matrixA.length-1-col][matrixA.length-1-row]);
			}
        }
        return result;
    }

    private static double[][] transposeVertical(double[][] matrixA) {
        double[][] result = new double[matrixA.length][matrixA[0].length];
        for (int row = 0; row < matrixA.length; row++) {
			for (int col = 0; col < matrixA[0].length; col++) {
				result[row][col] = matrixA[row][matrixA[0].length-1-col];
			}
        }
        return result;
    }
    
    private static double[][] transposeHorizontal(double[][] matrixA) {
        double[][] result = new double[matrixA.length][matrixA[0].length];
        for (int row = 0; row < matrixA.length; row++) {
			for (int col = 0; col < matrixA[0].length; col++) {
				result[row][col] = matrixA[matrixA.length-1-row][col];
			}
        }
        return result;
    }

    private static double determinant(double[][] matrixA) {
        if (matrixA.length == 2 && matrixA[0].length == 2) {
            return matrixA[0][0] * matrixA[1][1] - matrixA[0][1] * matrixA[1][0];
        } else if (matrixA.length == 1) {
            errorMsg();
            return matrixA[0][0];
        } else {
            double determinant = 0.0;
            for (int i = 0; i < matrixA.length; i++) {
                double[][] reducedMatrix = new double[matrixA.length - 1][matrixA.length - 1];
                for (int j = 0; j < reducedMatrix.length; j++) {
                    for (int k = 0; k < reducedMatrix.length; k++) {
                        reducedMatrix[j][k] = matrixA[j + 1][k + (k >= i ? 1 : 0)];
                    }
                }
                int cofactor = (int) Math.pow(-1, i + (double) 2);
                determinant += matrixA[0][i] * cofactor * determinant(reducedMatrix);
            }
            return determinant;
        }
    } 

    private static double[][] inverse(double[][] matrixA) {
        double[][] result = new double[matrixA.length][matrixA[0].length];
        double determinant = determinant(matrixA);

        if (determinant == 0) {
            System.out.println("This matrix doesn't have an inverse.");
            errorMsg();
        } else {
            double[][] cofactor = new double[matrixA.length][matrixA.length];
            for (int row = 0; row < matrixA.length; row++) { 
                for (int col = 0; col < matrixA.length; col++) { 
                    double[][] reducedMatrix = new double[matrixA.length - 1][matrixA.length - 1];
                    for (int i = 0; i < reducedMatrix.length; i++) {
                        for (int j = 0; j < reducedMatrix.length; j++) {
                            reducedMatrix[i][j] = matrixA[i + (i >= row ? 1 : 0)][j + (j >= col ? 1 : 0)];
                        }
                    }
                    cofactor[row][col] = (int) Math.pow(-1, row + col + (double) 2) * determinant(reducedMatrix);
                }
            }
            
            double[][] transpose = new double[cofactor[0].length][cofactor.length];
            for (int col = 0; col < cofactor[0].length; col++) {
                for (int row = 0; row < cofactor.length; row++) {
                    transpose[col][row] = cofactor[row][col];
                }
            }
            result = multiplyByConstant(transpose, 1 / determinant(matrixA));
        }
        return result;
    }
}