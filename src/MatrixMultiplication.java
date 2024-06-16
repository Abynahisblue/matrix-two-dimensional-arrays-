import java.util.InputMismatchException;
import java.util.Scanner;

public class MatrixMultiplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = 0, m = 0, p = 0;
        boolean validInput = false;

        // Input dimensions for matrix A
        while (!validInput) {
            try {
                System.out.println("Enter the dimensions for matrix A (n, m): ");
                String input = scanner.nextLine();
                String[] dimensions = input.split(",");

                n = Integer.parseInt(dimensions[0].trim());
                m = Integer.parseInt(dimensions[1].trim());
                validInput = true;
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid input. Please enter two integers separated by a comma (e.g., 2, 3).");
            }
        }

        int[][] A = new int[n][m];

        // Input elements for matrix A
        try {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    A[i][j] = scanner.nextInt();
                }
            }
        } catch (InputMismatchException e) {
            throw new RuntimeException("Invalid input. Please enter exactly " + (n * m) + " integers for matrix A.");
        }

        validInput = false;

        // Input dimensions for matrix B
        while (!validInput) {
            try {
                System.out.println("Enter the number of columns for matrix B (p): ");
                p = scanner.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next(); // clear the invalid input
            }
        }

        int[][] B = new int[m][p];

        // Input elements for matrix B
        try {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < p; j++) {
                    B[i][j] = scanner.nextInt();
                }
            }
        } catch (InputMismatchException e) {
            throw new RuntimeException("Invalid input. Please enter exactly " + (m * p) + " integers for matrix B.");
        }

        // Perform matrix multiplication
        int[][] C = multiplyMatrix(A, B);

        // Output the result matrix C
        System.out.println("Matrix C:");
        printResult(C);

        scanner.close();
    }

    public static int[][] multiplyMatrix(int[][] A, int[][] B) {
        int n = A.length;
        int m = A[0].length;
        int p = B[0].length;

        int[][] C = new int[n][p];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < p; j++) {
                for (int k = 0; k < m; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }

    public static void printResult(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.print("| ");
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println("|");
        }
    }
}
