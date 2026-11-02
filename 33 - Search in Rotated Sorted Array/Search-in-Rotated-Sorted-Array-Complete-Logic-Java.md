# Search in a Rotated Sorted Array

## Problem statement
A sorted array of distinct integers has been rotated at an unknown pivot. Given the rotated array `nums` and a `target`, return the target's index, or `-1` if it is not present.

Example:

```text
nums   = [4, 5, 6, 7, 0, 1, 2]
target = 0
answer = 4
```

The rotation preserves sorted order within two sections: `[4, 5, 6, 7]` and `[0, 1, 2]`.

## 1. Brute force approach: linear search

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

## 2. Better approach: Binary search

First find the index of the smallest element using binary search. This index is the rotation pivot and divides the array into two sorted sections. Then binary search each section, stopping if the target is found.

```java
static int searchTwoPass(int[] nums, int target) {
    if (nums.length == 0) {
        return -1;
    }

    // Find the index of the smallest element (the rotation pivot).
    int low = 0;
    int high = nums.length - 1;
    while (low < high) {
        int mid = low + (high - low) / 2;
        if (nums[mid] > nums[high]) {
            low = mid + 1;
        } else {
            high = mid;
        }
    }
    int pivot = low;

    // Search the left sorted section: [0, pivot - 1].
    int result = binarySearch(nums, 0, pivot - 1, target);
    if (result != -1) {
        return result;
    }

    // Search the right sorted section: [pivot, nums.length - 1].
    return binarySearch(nums, pivot, nums.length - 1, target);
}

static int binarySearch(int[] nums, int low, int high, int target) {
    while (low <= high) {
        int mid = low + (high - low) / 2;
        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] < target) {
            low = mid + 1;
        } else {
            high = mid - 1;
        }
    }
    return -1;
}
```

- **Time:** `O(log n)` to find the pivot plus up to `O(log n)` for each binary search, which is `O(log n)` overall.
- **Extra space:** `O(1)`.
- **Use it when:** you want a clear separation between locating the sorted sections and searching them.

This pivot-finding logic assumes all values are distinct, matching the standard version of this problem. With duplicates, identifying the pivot can require `O(n)` time in the worst case.

## 3. Optimized approach: Binary search

At each step, one half of the current search range must be sorted. Check which half is sorted, then determine whether the target falls within that half's value range. Keep only the half that can contain the target.

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

- **Time:** `O(log n)`.
- **Extra space:** `O(1)`.
- **Use it when:** you want the standard efficient solution for a rotated sorted array with distinct values.


``` java
// LeetCode 33 : Search in Rotated Sorted Array

// To search for a target element in a rotated sorted array in O(log n) time, you must use a modified binary search.

// You are given an integer array nums with distinct elements sorted in ascending order And then rotated. The Array was originally sorted but then rotated at some pivot. You must return the index of target, or -1 if the target doesn't exist.
// Important observation is that on every Binary search step at least one half of the current search range is sorted.
// The core insight is that whenever you split a rotated sorted array in half, at least one of the halves will always be perfectly sorted.

// 💡 The Algorithm Steps
// 1. Find the Midpoint.
// 2. Check for Target:
// 3. Identify the Sorted Half:  If left half is sorted; Otherwise: The right half is sorted.
// 4. • If the Left Half is Sorted: Check if the target falls within the left range.
//     If the Right Half is Sorted: Check if the target falls within the right range.
// 5. Repeat until.if the target isn't found, return -1.

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

// Time Complexity : O(log n)- Each iteration eliminates roughly half of the remaining search range.
// Space complexity : O(1)- We use only a few variables without creating another array.
```