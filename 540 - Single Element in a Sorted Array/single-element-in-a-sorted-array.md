# Single Element in a Sorted Array

## Intuition

Every value appears twice except one. Before the single value, pairs start at even indices (`0, 1`, `2, 3`, ...). After it, that alignment shifts: pairs start at odd indices. Binary search can find where this pattern changes.

## Approach

Keep a search range `[left, right]`. At each step, make `mid` even so it points to the expected first element of a pair. If `nums[mid] == nums[mid + 1]`, the single value is to the right; otherwise it is at `mid` or to the left. Continue until the range contains one index.

```java
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (mid % 2 == 1) {
                mid--; // align with the start of a pair
            }

            if (nums[mid] == nums[mid + 1]) {
                left = mid + 2; // this pair is intact; single is later
            } else {
                right = mid; // pair is broken here or before
            }
        }

        return nums[left];
    }
}
```

## Complexity

- **Time:** `O(log n)` — the search range is halved each iteration.
- **Extra space:** `O(1)`.

# Leetcode Solution

```java
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int start = 0;
        int end = nums.length - 1;

        while (start < end) {
            int mid = start + (end - start) / 2;
            if (mid % 2 == 1) {
                mid--;
            }
            if (nums[mid] == nums[mid + 1]) {
                start = mid + 2;
            } else {
                end = mid;
            }
        }
        return nums[start];
    }
}
```