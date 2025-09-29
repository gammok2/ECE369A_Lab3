import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MatrixReader {
    public static void main(String[] args) {
        int frameRow = 0;
        int frameCol = 0;
        int windowRow = 0;
        int windowCol = 0;

        File file = new File("test.txt");
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Failed to open test.txt");
            return;
        }

        // Read sizes
        frameRow = scanner.nextInt();
        frameCol = scanner.nextInt();
        windowRow = scanner.nextInt();
        windowCol = scanner.nextInt();

        int[][] frame = new int[frameRow][frameCol];
        int[][] window = new int[windowRow][windowCol];

        int maxWidth = 0;

        // Read frame
        for (int i = 0; i < frameRow; i++) {
            for (int j = 0; j < frameCol; j++) {
                frame[i][j] = scanner.nextInt();
                int width = String.valueOf(frame[i][j]).length();
                if (width > maxWidth) maxWidth = width;
            }
        }

        // Print frame (aligned nicely)
        for (int i = 0; i < frameRow; i++) {
            for (int j = 0; j < frameCol; j++) {
                System.out.printf("%" + (maxWidth + 1) + "d", frame[i][j]);
            }
            System.out.println();
        }

        System.out.println();

        // Read window
        for (int i = 0; i < windowRow; i++) {
            for (int j = 0; j < windowCol; j++) {
                window[i][j] = scanner.nextInt();
            }
        }

        scanner.close();

        // Print window
        System.out.println("Window:");
        for (int i = 0; i < windowRow; i++) {
            for (int j = 0; j < windowCol; j++) {
                System.out.print(window[i][j] + " ");
            }
            System.out.println();
        }

        // Run diagonal search
        Search.diagonalSearch(frame, window);
    }
}
