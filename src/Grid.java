import java.util.ArrayList;
import java.util.List;

/**
 * Main logic, take information with constructor, then call method findHigh() for find all higher point in this grid
 * and fill resultArray(special array for contain information about all possible moves), then call method findPathForAll()
 * that start recursive function to find all possible moves on each step of recursion, and then just call getResult()
 * for getting result.
 *
 */
public class Grid {
    private int[][] arr;
    private int n;
    private int m;
    private Node[][] resultArray;
    private List<Pair> high = new ArrayList<>();
    private List<Integer> result = new ArrayList<>();

    public Grid(int[][] arr, int n, int m) {
        this.arr = arr;
        this.n = n;
        this.m = m;
        this.resultArray =  new Node[n][m];
    }

    public void findSolution() {
        findHigh();
        findPathForAll();
    }

    private void findHigh() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                resultArray[i][j] = new Node();
                boolean isHigh = calc(i, j - 1, i, j, n, m, arr, resultArray)
                        & calc(i, j + 1, i, j, n, m, arr, resultArray)
                        & calc(i + 1, j, i, j, n, m, arr, resultArray)
                        & calc(i - 1, j, i, j, n, m, arr, resultArray);
                if (isHigh) {
                    high.add(new Pair(i, j));
                }
            }
        }
    }

    private void findPathForAll() {
        for (Pair pair : high) {
            findPath(pair.i, pair.j, arr, resultArray, new ArrayList<>());
        }
    }

    public List<Integer> getResult() {
        return result;
    }

    private boolean isValid(int i, int j, int n, int m) {
        return (i >= 0 && i < n && j >= 0 && j < m);
    }

    private boolean calc(int it, int jt, int i, int j, int n, int m, int[][]arr, Node[][] resultArray) {
        boolean isHigh = true;
        if (isValid(it, jt, n, m)) {
            if (arr[it][jt] > arr[i][j]) {
                isHigh = false;
            } else if (arr[it][jt] < arr[i][j]) {
                resultArray[i][j].getLower().add(new Pair(it, jt));
            }
        }
        return isHigh;
    }

    private void findPath(int i, int j, int[][] arr, Node[][] resultArray, List<Integer> res) {
        res.add(arr[i][j]);
        if (resultArray[i][j].getLower().size() == 0) {
            if ((result.size() == 0) || (res.size() > result.size()) ||
                    (res.size() == result.size() && (res.get(0) - res.get(res.size() - 1)) > (result.get(0) - result.get(result.size() - 1)))) {
                result = new ArrayList<>(res);
            }
        } else {
            for (Pair t : resultArray[i][j].getLower()) {
                findPath(t.i, t.j, arr, resultArray, res);
            }
        }

        res.remove(res.size() - 1);
    }
}