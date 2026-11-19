# Book Allocation Problem --- Allocate Minimum Pages

## Intuition

Focus on the goal: **minimize the maximum pages assigned to one
student**.

Instead of trying every possible allocation, guess a page limit `X` and
ask:

> Can we allocate all books so that no student receives more than `X`
> pages?

Check this greedily:

-   Start with the first student.
-   Give that student consecutive books while the total stays at or
    below `X`.
-   If the next book would exceed `X`, assign it to the next student.
-   Count how many students are required.

If the allocation needs at most `m` students, `X` is feasible.
Otherwise, `X` is too small.

### Why Binary Search Works

If a limit `X` is feasible, every larger limit is also feasible:
allowing more pages cannot invalidate an existing allocation.

If `X` is impossible, every smaller limit is also impossible.

The answer therefore has a monotonic structure:

-   Too small → not possible.
-   Large enough → possible.

This lets us use **Binary Search on Answer**.

## 3. Search Space

### Lower Bound

`low = maximum value in pages[]`

At least one student must receive the book with the most pages. So the
answer cannot be smaller than the largest individual book.

### Upper Bound

`high = sum of all pages`

The total pages is a valid upper limit: one student could receive all
the books (and the remaining students, if any, can receive books when
`m <= n`).

Therefore, search in:

`[maximum book pages, total pages]`

## 4. Approach - Binary Search on Answer

1.  If there are no books, `m <= 0`, or `m > pages.length`, return `-1`.
2.  Set `low` to the largest book and `high` to the sum of all pages.
3.  While `low <= high`:
    -   Calculate `mid = low + (high - low) / 2`.
    -   Check `isPossible(pages, m, mid)`.
    -   If possible, save `mid` and search smaller: `high = mid - 1`.
    -   If impossible, search larger: `low = mid + 1`.
4.  Return the smallest feasible limit.

## 5. Complete Java Solution

This version uses `long` for totals and binary-search boundaries to
reduce integer-overflow risk.

``` java
class Solution {

    public static long findPages(int[] pages, int m) {

        int n = pages.length;

        if (n == 0 || m <= 0 || m > n) {
            return -1;
        }

        long low = 0;
        long high = 0;

        for (int page : pages) {
            low = Math.max(low, page);
            high += page;
        }

        long answer = high;

        while (low <= high) {

            long mid = low + (high - low) / 2;

            if (isPossible(pages, m, mid)) {
                answer = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return answer;
    }

    private static boolean isPossible(
            int[] pages, int m, long maxPages) {

        int students = 1;
        long currentPages = 0;

        for (int page : pages) {

            if (currentPages + page <= maxPages) {
                currentPages += page;
            } else {
                students++;
                currentPages = page;

                if (students > m) {
                    return false;
                }
            }
        }

        return true;
    }
}
```

## 6. Line-by-Line Explanation

### Main Method

-   `int n = pages.length;` --- Stores the number of books.
-   `if (n == 0 || m <= 0 || m > n)` --- Checks invalid cases. Every
    student must receive at least one book.
-   `return -1;` --- Indicates that a valid allocation does not exist.
-   `long low = 0;` and `long high = 0;` --- Initialize the
    binary-search boundaries.
-   `for (int page : pages)` --- Visits every book to calculate the
    boundaries.
-   `low = Math.max(low, page);` --- Finds the largest individual book.
    This is the minimum possible answer.
-   `high += page;` --- Calculates the total pages. This is a valid
    upper bound.
-   `long answer = high;` --- Stores the best feasible answer found so
    far. The upper bound is feasible when `1 <= m <= n`.
-   `while (low <= high)` --- Continues while the search interval is
    valid.
-   `long mid = low + (high - low) / 2;` --- Calculates the middle limit
    while avoiding possible overflow from `low + high`.
-   `if (isPossible(pages, m, mid))` --- Checks whether the books can be
    allocated within this page limit using at most `m` students.
-   `answer = mid;` --- Saves this feasible limit as a potential
    minimum.
-   `high = mid - 1;` --- Searches smaller limits for a better answer.
-   `low = mid + 1;` --- If the limit is impossible, searches larger
    limits.
-   `return answer;` --- Returns the smallest feasible maximum page
    count.

### Helper Method: `isPossible()`

-   `int students = 1;` --- Starts with the first student.
-   `long currentPages = 0;` --- Tracks the pages assigned to the
    current student.
-   `for (int page : pages)` --- Processes books in their original
    order.
-   `if (currentPages + page <= maxPages)` --- Checks whether the
    current student can receive this book without crossing the guessed
    limit.
-   `currentPages += page;` --- Assigns the book to the current student.
-   `else` --- The current student cannot receive this book within the
    limit.
-   `students++;` --- Moves to the next student.
-   `currentPages = page;` --- Assigns the current book to the new
    student.
-   `if (students > m)` --- Checks whether more than the allowed number
    of students is required.
-   `return false;` --- The guessed limit is too small.
-   `return true;` --- The allocation is possible using at most `m`
    students.

## 7. Complexity Analysis

Let: - `n` = number of books - `S` = sum of all pages - `P` = maximum
pages in one book

  Operation                  Complexity
  -------------------------- -----------------------------
  Find search boundaries     `O(n)`
  One feasibility check      `O(n)`
  Binary-search iterations   `O(log(S - P + 1))`
  **Total time**             **`O(n × log(S - P + 1))`**
  **Auxiliary space**        **`O(1)`**

The array is scanned once per feasibility check, and only a fixed number
of variables are used.

## 8. Important Concepts to Remember

-   Books must be allocated in contiguous order.
-   Every student must receive at least one book.
-   The goal is to minimize the **maximum** pages assigned to any
    student.
-   `low` is the largest single book.
-   `high` is the sum of all pages.
-   `isPossible()` uses a greedy allocation strategy.
-   If a limit is feasible, larger limits are feasible too.
-   If a limit is impossible, smaller limits are impossible too.

## 9. Pattern Recognition

Recognize this pattern when:

-   The problem asks you to minimize a maximum value or maximize a
    minimum value.
-   You can check whether a guessed answer is feasible.
-   Feasibility changes monotonically as the guessed answer changes.
-   A greedy check can validate one guessed answer efficiently.

**Pattern: Minimize the maximum → Binary Search on Answer + Greedy
Feasibility Check.**

## 10. Related Problems

-   Allocate Minimum Pages / Book Allocation
-   Split Array Largest Sum --- LeetCode 410
-   Capacity To Ship Packages Within D Days --- LeetCode 1011
-   Koko Eating Bananas --- LeetCode 875
-   Painter's Partition Problem

The feasibility logic differs between these problems, but the shared
strategy is to binary-search a candidate answer and check whether it is
possible.

```java
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
```