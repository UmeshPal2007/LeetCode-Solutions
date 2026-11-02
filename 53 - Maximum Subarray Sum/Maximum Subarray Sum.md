# Maximum Subarray
## Problem statement

Given an integer array `nums`, find the contiguous, non-empty subarray with the largest sum and return that sum.

The maximum subarray problem is the task of finding a contiguous (unbroken) part of a one-dimensional array of numbers that has the largest possible sum.

## 1. Brute force approach (Naïve Approach)

Consider every possible starting index. Extend the subarray one element at a time, keeping a running sum, and update the best sum after each extension. This checks every contiguous subarray without recalculating its sum from scratch.

• How it works: Use nested loops to generate every possible start and end point, calculate the sum for each subarray, and find the maximum.


```java
static int maxSubArrayBruteForce(int[] nums) {
    int best = nums[0];

    for (int start = 0; start < nums.length; start++) {
        int currentSum = 0;

        for (int end = start; end < nums.length; end++) {
            currentSum += nums[end];
            best = Math.max(best, currentSum);
        }
    }

    return best;
}
```

- **Time:** `O(n²)`, because there are `O(n²)` contiguous subarrays.
- **Extra space:** `O(1)`.
- **Important:** initialize `best` to `nums[0]`, not `0`. The subarray must be non-empty, and all numbers may be negative.

## 2. Optimized approach: Kadane’s algorithm

Scan from left to right. At each element, decide whether to:

- extend the current subarray by adding this element, or
- start a new subarray at this element.

Track the largest `currentSum` seen anywhere as `bestSum`.

• How it works: Traverse the array from left to right while keeping a running sum (currentSum). At each element, choose whether to:
• Extend the existing subarray by adding the current number to currentSum.
• Start a fresh subarray from the current number if currentSum drops below zero (since a negative sum hurts future totals).
• Track the highest sum seen so far in a maxSum variable.

```java
static int maxSubArray(int[] nums) {
    int currentSum = nums[0];
    int bestSum = nums[0];

    for (int i = 1; i < nums.length; i++) {
        currentSum = Math.max(nums[i], currentSum + nums[i]);
        bestSum = Math.max(bestSum, currentSum);
    }

    return bestSum;
}
```

- **Time:** `O(n)`, one pass through the array.
- **Extra space:** `O(1)`.
- **Use it when:** solving the standard maximum subarray sum problem; this is the preferred solution.

### Why Kadane’s algorithm works

For every index, the best subarray that ends there either begins at that index or extends the best subarray that ended at the previous index. Any earlier subarray with a negative total only reduces the sum if extended, so it is better to restart at the current value. Keeping the largest ending sum at each position is enough to find the global maximum.




## LeetCode Solution

``` java
class Solution {
    public int maxSubArray(int[] nums) {
        int currentSum = 0; // Current subarray sum
        int maxSum = Integer.MIN_VALUE; // Maximum sum found

        for(int val: nums) {
            currentSum += val; // Add current element in the running sum
            maxSum = Math.max(currentSum, maxSum); // Update the maximum subarray BEFORE resetting current sum, So that negative elements are handled correctly. 

            // Kadane's Algorithm : Reset negative running sum to 0 So the next element can start a new subarray. Reset the running sum If it becomes negative.
            if(currentSum < 0) {
                currentSum = 0;
            }
        }
        return maxSum; // Return maximum Sum (largest Contiguous subarray sum found)
    }
}
```