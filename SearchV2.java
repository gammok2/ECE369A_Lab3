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

    //Check Sad
    public boolean checkSAD(int sad) {
        return (sad == 0);
    }

    // Diagonal search
    public int diagonalSearch(int frameRow,  int frameCol, int windowRow, int windowCol) {
        int row = 0;
        int col = 0;
        int colMax = frameCol - windowCol;
        int rowMax = frameRow - windowRow;
        int sad = -1;

        sad = computeSAD(col, row);
        if(checkSAD(sad)) return 0; //need to return coords not 0.

        while(  col != colMax && row != 0 ){ //First Half
            col += 1; //Right
            sad = computeSAD(col, row);
            
            while(col != 0){ //Diagonal down left
                col -= 1;
                row += 1;
                sad = computeSAD(col, row);
            }

            row +=1;    //Down
            sad = computeSAD(col, row);

            while(row != 0){    //Diagonal up right
                row -= 1;
                col += 1;
                sad = computeSAD(col, row);
            } 

            col += 1; //Right
            sad = computeSAD(col, row);
        }

        // Middle
        while(col != 0){
            col -= 1;
            row += 1;
            sad = computeSAD(col, row);
        }

        // Second Half
        while( row != rowMax && col != colMax ){
            col += 1; //Right
            sad = computeSAD(col, row);

            while(col != colMax){  //Diagonal Up Right
                col += 1;
                row -= 1;
                sad = computeSAD(col, row);
            }

            row += 1; //Down
            sad = computeSAD(col, row);

            while(row != rowMax){  //Diagonal Down Left
                row += 1;
                col -= 1;
                sad = computeSAD(col, row);
            }

            col += 1; //Right
            sad = computeSAD(col, row);
            
        }

        return -1;
    }
}
