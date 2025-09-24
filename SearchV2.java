//window small
//frame large
import java.util.ArrayList;
import java.util.List;

public class SearchV2{
    private int[][] window;
    private int[][] frame;

    public SearchV2(int[][] window, int[][] frame) {
        this.window = window;
        this.frame = frame;
    }

    // SAD between window and sub-block in frame
    private int computeSAD(int startY, int startX) {
        int sad = 0;
        int winSize = window.length;
        System.out.println();
        for (int i = 0; i < winSize; i++) {
            for (int j = 0; j < winSize; j++) {
                sad += Math.abs(window[i][j] - frame[startY + i][startX + j]);
                System.out.print(Math.abs(window[i][j] - frame[startY + i][startX + j]) + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("SAD = " + sad);
        return sad;
    }

    // Generate diagonal offsets

    // Diagonal search
    public int[] diagonalSearch(int frameRow,  int frameCol, int windowRow, int windowCol) {
        int row = 0;
        int col = 0;

        col += 1;

        row += 1; col -= 1;

        row += 1;

        





        return 0;
    }
}
