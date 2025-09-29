public class Search {

    // Compute SAD between window and sub-block in frame at (row, col)
    private static int computeSAD(int[][] frame, int[][] window, int row, int col) {
        int sad = 0;
        int winRows = window.length;
        int winCols = window[0].length;

        for (int i = 0; i < winRows; i++) {
            for (int j = 0; j < winCols; j++) {
                sad += Math.abs(window[i][j] - frame[row + i][col + j]);
            }
        }
        return sad;
    }

    // Zig-zag diagonal search
    public static void diagonalSearch(int[][] frame, int[][] window) {
        int frameRows = frame.length;
        int frameCols = frame[0].length;
        int winRows = window.length;
        int winCols = window[0].length;
    
        int bestRow = -1;
        int bestCol = -1;
        int bestSAD = Integer.MAX_VALUE;
    
        int row = 0, col = 0;
    
        // ----------------------------
        // FIRST PART (top → top-right)
        // ----------------------------
        while (col < frameCols - winCols) {
            // move right
            col++;
            if (col > frameCols - winCols) break;
            int sad = computeSAD(frame, window, row, col);
            if (sad == 0) { bestRow = row; bestCol = col; bestSAD = 0; break; }
    
            // diagonal down-left
            while (row + 1 <= frameRows - winRows && col - 1 >= 0) {
                row++; col--;
                sad = computeSAD(frame, window, row, col);
                if (sad == 0) { bestRow = row; bestCol = col; bestSAD = 0; break; }
            }
            if (bestSAD == 0) break;
    
            // down
            if (row + 1 <= frameRows - winRows) {
                row++;
                sad = computeSAD(frame, window, row, col);
                if (sad == 0) { bestRow = row; bestCol = col; bestSAD = 0; break; }
            }
    
            // diagonal up-right
            while (row - 1 >= 0 && col + 1 <= frameCols - winCols) {
                row--; col++;
                sad = computeSAD(frame, window, row, col);
                if (sad == 0) { bestRow = row; bestCol = col; bestSAD = 0; break; }
            }
            if (bestSAD == 0) break;
        }
    
        // ----------------------------
        // MIDDLE PART (diagonal left)
        // ----------------------------
        while (row + 1 <= frameRows - winRows && col - 1 >= 0) {
            row++; col--;
            int sad = computeSAD(frame, window, row, col);
            if (sad == 0) { bestRow = row; bestCol = col; bestSAD = 0; break; }
        }
    
        // ----------------------------
        // SECOND PART (bottom-left → bottom-right)
        // ----------------------------
        while (col < frameCols - winCols && bestSAD != 0) {
            // move right
            col++;
            if (col > frameCols - winCols) break;
            int sad = computeSAD(frame, window, row, col);
            if (sad == 0) { bestRow = row; bestCol = col; bestSAD = 0; break; }
    
            // diagonal up-right
            while (row - 1 >= 0 && col + 1 <= frameCols - winCols) {
                row--; col++;
                sad = computeSAD(frame, window, row, col);
                if (sad == 0) { bestRow = row; bestCol = col; bestSAD = 0; break; }
            }
            if (bestSAD == 0) break;
    
            // down
            if (row + 1 <= frameRows - winRows) {
                row++;
                sad = computeSAD(frame, window, row, col);
                if (sad == 0) { bestRow = row; bestCol = col; bestSAD = 0; break; }
            }
    
            // diagonal down-left
            while (row + 1 <= frameRows - winRows && col - 1 >= 0) {
                row++; col--;
                sad = computeSAD(frame, window, row, col);
                if (sad == 0) { bestRow = row; bestCol = col; bestSAD = 0; break; }
            }
            if (bestSAD == 0) break;
        }
    
        // ----------------------------
        // Final result
        // ----------------------------
        if (bestSAD == 0) {
            System.out.println("Best match at offset (" + bestRow + ", " + bestCol + ") with SAD = 0");
        } else {
            System.out.println("No perfect match found");
        }
    }
}
