public class Search {
    private int[][] window; // smaller matrix to search for
    private int[][] frame; // larger matrix to search within

    public Search(int[][] window, int[][] frame) {
        this.window = window;
        this.frame = frame;
        
    }

    public void cornerCheck() {
        // Implement the search algorithm here:
        /*
         * Start -> Right 1 -> Down 1, Left 1 (Until Column 0) -> Down 1 -> Up 1, Right 1 (Until row 0) -> recursive until top right corner (NEXT LINE)
         * 0,0   -> 0,1     -> x,0                             -> x+1,0  -> 0,y                         ->
         * 
         *          Down 1, Left1 (Until Column 0) -> Right 1   -> Up 1, Right 1 (until column max) -> Down 1     -> recursive until bottom right corner (finish)
         * 0, maxY | maxX, y                       -> maxX, y+1 -> x, maxY (until row max)          -> x+1, maxY  -> recursive
         */

        int maxX = frame[0].length - window[0].length;
        int maxY = frame.length - window.length;

        for(int i = 0; i <= maxY; i++) {
            for(int j = 0; j <= maxX; j++) {
                window[0][0] = frame[i][j];
            }
        }



    }
}
