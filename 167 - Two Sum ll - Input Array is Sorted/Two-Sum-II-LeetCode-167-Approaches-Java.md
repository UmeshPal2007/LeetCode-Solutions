# Two Sum II

## Problem statement

Given a **1-indexed**, non-decreasing sorted integer array `numbers` and a target value, find two numbers whose sum equals the target. Return their indices as a two-element array `[index1, index2]` using **1-based indices**, with `index1 < index2`.

The problem guarantees exactly one solution and that the same element cannot be used twice.

Example:

```text
numbers = [2, 7, 11, 15]
target = 9
answer = [1, 2]
```

The values at 1-based positions 1 and 2 add up to 9.

## 1. Brute force approach

Check every pair of indices. For each `i`, try every `j` after it and return the pair if the values add to the target.

```
static int[] twoSumBruteForce(int[] numbers, int target) {
    for (int i = 0; i < numbers.length; i++) {
        for (int j = i + 1; j < numbers.length; j++) {
            if ((long) numbers[i] + numbers[j] == target) {
                // Convert Java's 0-based indices to the required 1-based indices.
                return new int[] {i + 1, j + 1};
            }
        }
    }

    return new int[0]; // The problem guarantees a solution.
}
```

- **Time:** `O(n²)`.
- **Extra space:** `O(1)`.
- **Use it when:** the input is tiny or you want the most direct baseline.

## 2. Better approach: Binary Search

Because the array is sorted, for each value at index `i`, calculate the needed complement:

```text
complement = target - numbers[i]
```

Then binary search for that complement only in the part of the array after `i`. Searching after `i` ensures the same element is not reused.

```java
static int[] twoSumBinarySearch(int[] numbers, int target) {
    for (int i = 0; i < numbers.length - 1; i++) {
        long needed = (long) target - numbers[i];
        int low = i + 1;
        int high = numbers.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            long value = numbers[mid];

            if (value == needed) {
                return new int[] {i + 1, mid + 1};
            } else if (value < needed) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
    }

    return new int[0]; // The problem guarantees a solution.
}
```

- **Time:** `O(n log n)`: binary search takes `O(log n)` for each of up to `n` elements.
- **Extra space:** `O(1)`.
- **Use it when:** you want to make use of sorted order, though two pointers are more efficient for this task.

## 3. Optimized approach: Two Pointers

Start one pointer at the beginning and one at the end of the sorted array. Their values give the current sum:

- If the sum equals the target, return their 1-based indices.
- If the sum is too small, move the left pointer right to get a larger value.
- If the sum is too large, move the right pointer left to get a smaller value.

```java
static int[] twoSum(int[] numbers, int target) {
    int left = 0;
    int right = numbers.length - 1;

    while (left < right) {
        long sum = (long) numbers[left] + numbers[right];

        if (sum == target) {
            return new int[] {left + 1, right + 1};
        } else if (sum < target) {
            left++;
        } else {
            right--;
        }
    }

    return new int[0]; // The problem guarantees a solution.
}
```

- **Time:** `O(n)`, because each pointer moves only inward.
- **Extra space:** `O(1)`.
- **Use it when:** solving the standard Two Sum II problem; this is the preferred approach.

## Leetcode Solution
``` java
class Solution {
    public int[] twoSum(int[] numbers, int target) {

        int left = 0;
        int right = numbers.length-1;

        while(left < right) {
            
            int sum = numbers[left] + numbers[right];

            if(sum == target) {
                return new int[]{left + 1,right + 1};
            } else if(sum > target) {
                right--;
            } else {
                left++;
            }
        }
        return new int[]{-1, -1};
    }
}
```