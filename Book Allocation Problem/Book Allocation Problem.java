import java.util.*;

public class Main {

    public static int allocateBooks(List<Integer> arr, int n, int m) {
        int sum = 0;

        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }

        // Range of Possible Answers
        int start = 0;
        int end = sum;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if(isValid (arr, n, m, mid)) {
                end = mid-1;
            } else {

            }

        }
    }

    public static void main(String[] args) {
        List<Integer> arr = Arrays.asList(2, 1, 3, 4);
        int n = 4, m = 2;

        System.out.println(allocateBooks(arr, n, m));
    }
}
