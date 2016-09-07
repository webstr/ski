import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //read input information from stdin
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = in.nextInt();
            }
        }
        //create class for calculate result and call needed methods
        Grid grid = new Grid(arr, n, m);

        grid.findSolution();
        List<Integer> result = grid.getResult();

        //print result into stdout
        for (int i = 0; i < result.size() - 1; i++) {
            System.out.print(result.get(i) + "-");
        }
        System.out.println(result.get(result.size() - 1));
    }
}
