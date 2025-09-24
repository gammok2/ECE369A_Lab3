import java.util.ArrayList;
import java.util.List;

public class Search {
    private int[][] window;
    private int[][] frame;

    public Search(int[][] window, int[][] frame) {
        this.window = window;
        this.frame = frame;
    }

    // SAD between window and sub-block in frame
    private int computeSAD(int startY, int startX) {
        int sad = 0;
        int winSize = window.length;
        for (int i = 0; i < winSize; i++) {
            for (int j = 0; j < winSize; j++) {
                sad += Math.abs(window[i][j] - frame[startY + i][startX + j]);
            }
        }
        return sad;
    }

    // Generate diagonal offsets
    private List<int[]> generateDiagonalOffsets(int radius) {
        List<int[]> offsets = new ArrayList<>();
        int size = 2 * radius + 1;
        int maxSum = 2 * (size - 1);

        for (int s = 0; s <= maxSum; s++) {
            int uMin = Math.max(0, s - (size - 1));
            int uMax = Math.min(size - 1, s);
            for (int u = uMin; u <= uMax; u++) {
                int v = s - u;
                int dx = u - radius;
                int dy = v - radius;
                offsets.add(new int[]{dx, dy});
            }
        }
        return offsets;
    }

    // Diagonal search
    public int[] diagonalSearch(int searchRadius) {
        int bestSAD = Integer.MAX_VALUE;
        int[] bestOffset = {0, 0};

        List<int[]> offsets = generateDiagonalOffsets(searchRadius);

        for (int[] offset : offsets) {
            int dx = offset[0];
            int dy = offset[1];

            if (dy < 0 || dx < 0 ||
                dy + window.length > frame.length ||
                dx + window[0].length > frame[0].length) {
                continue;
            }

            int sad = computeSAD(dy, dx);
            if (sad < bestSAD) {
                bestSAD = sad;
                bestOffset[0] = dx;
                bestOffset[1] = dy;
            }
        }

        System.out.println("Best match at offset (" + bestOffset[0] + ", " + bestOffset[1] + ") with SAD = " + bestSAD);
        return bestOffset;
    }
}
