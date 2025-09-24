import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MatrixReader {
    public static void main(String[] args) {
        int framesize = 0;
        int windowsize = 0;
        int empty1 = 0;
        int empty2 = 0;


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
            if(i == 0) framesize = scanner.nextInt();
            if(i == 1) empty1 = scanner.nextInt();
            if(i == 2) windowsize = scanner.nextInt();
            if(i == 3) empty2 = scanner.nextInt();
        }

        //System.out.println("Frame Size: " + framesize + " Window Size: " + windowsize);
        //System.out.println("Empty 1: " + empty1 + " Empty 2: " + empty2);

        int[][] frame = new int[framesize][framesize];
        int[][] window = new int[windowsize][windowsize];


        // Read values into frame and find max width
        for (int i = 0; i < framesize; i++) {
            for (int j = 0; j < framesize; j++) {
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
        for (int i = 0; i < framesize; i++) {
            for (int j = 0; j < framesize; j++) {
                System.out.printf("%" + (maxWidth + 1) + "d", frame[i][j]);
            }
            System.out.println();
        }

        System.out.println("\n\n");

        // Fill window with values from test.txt (continue reading)
        for (int i = 0; i < windowsize; i++) {
            for (int j = 0; j < windowsize; j++) {
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


        Search problem = new Search(window, frame);
        
    }
}
