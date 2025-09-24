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

        int maxWidth = 0;

        for(int i = 0; i <= 3; i++){
            if(i == 0) frameRow = scanner.nextInt();
            if(i == 1) frameCol = scanner.nextInt();
            if(i == 2) windowRow = scanner.nextInt();
            if(i == 3) windowCol = scanner.nextInt();
        }

        //System.out.println("Frame Size: " + framesize + " Window Size: " + windowsize);
        //System.out.println("Empty 1: " + empty1 + " Empty 2: " + empty2);

        int[][] frame = new int[frameRow][frameCol];
        int[][] window = new int[windowRow][windowCol];


        // Read values into frame and find max width
        for (int i = 0; i < frameRow; i++) {
            for (int j = 0; j < frameCol; j++) {
                if (!scanner.hasNextInt()) {
                    System.out.println("Not enough values in test.txt");
                    scanner.close();
                    return;
                }
                frame[i][j] = scanner.nextInt();
                int width = String.valueOf(frame[i][j]).length();
                if (width > maxWidth) maxWidth = width;
            }
        }

        // Print frame with aligned columns
        for (int i = 0; i < frameRow; i++) {
            for (int j = 0; j < frameCol; j++) {
                System.out.printf("%" + (maxWidth + 1) + "d", frame[i][j]);
            }
            System.out.println();
        }

        System.out.println("\n\n");

        // Fill window with values from test.txt (continue reading)
        for (int i = 0; i < windowRow; i++) {
            for (int j = 0; j < windowCol; j++) {
                if (!scanner.hasNextInt()) {
                    System.out.println("Not enough values in test.txt for window");
                    scanner.close();
                    return;
                }
                window[i][j] = scanner.nextInt();
                System.out.print(window[i][j] + " ");
            }
            System.out.println();
        }

        scanner.close();


        SearchV2 problem = new SearchV2(window, frame);
        problem.diagonalSearch(windowCol);
        
    }
}
