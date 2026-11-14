# Peak Index in a Mountain Array (LeetCode 852)

## Intuition

A mountain array strictly increases up to one peak, then strictly decreases. At any index, comparing `arr[mid]` with `arr[mid + 1]` tells us which side of the peak we are on: if the next value is larger, the peak is to the right; otherwise, the peak is at `mid` or to its left. This lets us use binary search instead of scanning the array.

## Approach

Search within `[left, right]`. While more than one index remains, compute `mid`:

- If `arr[mid] < arr[mid + 1]`, we are on the rising slope, so set `left = mid + 1`.
- Otherwise, we are on the falling slope or at the peak, so set `right = mid`.

When `left == right`, that index is the peak. Since `left < right`, `mid` is always less than `right`, so `mid + 1` is a valid index.

```java
class Solution {
    public int peakIndexInMountainArray(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] < arr[mid + 1]) {
                left = mid + 1; // still rising; peak is later
            } else {
                right = mid; // at or past the peak; keep mid
            }
        }

        return left;
    }
}
```

## Complexity

- **Time:** `O(log n)` — the search range is roughly halved each iteration.
- **Extra space:** `O(1)`.

# Leetcode Solution

```java
class Solution {
    public int peakIndexInMountainArray(int[] arr) {
        int start = 1;
        int end = arr.length - 2;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (arr[mid - 1] < arr[mid] && arr[mid] > arr[mid + 1]) {
                return mid;
            } else if (arr[mid - 1] < arr[mid]) { //right
                start = mid + 1;
            } else { //left
                end = mid -1;
            }
        }
        return -1;
    }
}
```