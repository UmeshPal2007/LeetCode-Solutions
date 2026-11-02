# Search in a Rotated Sorted Array

## Problem statement
A sorted array of distinct integers has been rotated at an unknown pivot. Given the rotated array `nums` and a `target`, return the target's index, or `-1` if it is not present.

## 1. Brute force approach: Linear search

Check each element from left to right. Return its index if it equals the target; return `-1` after checking the entire array.

```java
static int searchLinear(int[] nums, int target) {
    for (int i = 0; i < nums.length; i++) {
        if (nums[i] == target) {
            return i;
        }
    }
    return -1;
}
```

- **Time:** `O(n)`.
- **Extra space:** `O(1)`.
- **Use it when:** the array is small, or a straightforward solution is enough.

## 2. Optimized approach: Binary search

At each step, one half of the current search range must be sorted. Check which half is sorted, then determine whether the target falls within that half's value range. Keep only the half that can contain the target.

To search for a target element in a rotated sorted array in O(log n) time, you must use a modified binary search.

You are given an integer array nums with distinct elements sorted in ascending order And then rotated. The Array was originally sorted but then rotated at some pivot. You must return the index of target, or -1 if the target doesn't exist.

Important observation is that on every Binary search step at least one half of the current search range is sorted.

The core insight is that whenever you split a rotated sorted array in half, at least one of the halves will always be perfectly sorted.

💡 The Algorithm Steps
1. Find the Midpoint.
2. Check for Target:
3. Identify the Sorted Half:  If left half is sorted; Otherwise: The right half is sorted.
4. • If the Left Half is Sorted: Check if the target falls within the left range.
If the Right Half is Sorted: Check if the target falls within the right range.
5. Repeat until.if the target isn't found, return -1.


```java
static int search(int[] nums, int target) {
    int low = 0;
    int high = nums.length - 1;

    while (low <= high) {
        int mid = low + (high - low) / 2;

        if (nums[mid] == target) {
            return mid;
        }

        // Left half [low, mid] is sorted.
        if (nums[low] <= nums[mid]) {
            if (nums[low] <= target && target < nums[mid]) {
                high = mid - 1; // Target is in the sorted left half.
            } else {
                low = mid + 1;  // Target must be in the other half.
            }
        }
        // Otherwise, right half [mid, high] is sorted.
        else {
            if (nums[mid] < target && target <= nums[high]) {
                low = mid + 1;  // Target is in the sorted right half.
            } else {
                high = mid - 1; // Target must be in the other half.
            }
        }
    }

    return -1;
}
```

- **Time:** `O(log n)` - Each iteration eliminates roughly half of the remaining search range..
- **Extra space:** `O(1)`.
- **Use it when:** you want the standard efficient solution for a rotated sorted array with distinct values.

## Leetcode Solution
``` java
class Solution {
    public int search(int[] nums, int target) {
        int start = 0; // 📍 Pointer at start
        int end = nums.length - 1; // 📍 Pointer at end

        // As long as (start <= end) there are still elements to examine. When (start > end), The search range is empty And the target was not found.

        while (start <= end) { // 🎯 Find middle index
            int mid = start + (end - start) / 2;

            if (nums[mid] == target) { // If the middle element equals to the Target. 🎉 Target found!.
                return mid;
            }

            // 🔍 Check if the left half is sorted
            if (nums[start] <= nums[mid]) {
                // Check if target lies within the sorted left half
                if (nums[start] <= target && target < nums[mid]) {
                    end = mid - 1; //⬅️ Search left
                } else {
                    start = mid + 1; // ➡️ Search right
                }
            } 
            // Otherwise, the right half must be sorted
            else {
                // Check if target lies within the sorted right half
                if (nums[mid] < target && target <= nums[end]) {
                    start = mid + 1; // ➡️ Search right
                } else {
                    end = mid - 1; // ⬅️ Search left
                }
            }
        }
        return -1; // ❌ Target not found
    }
}
```