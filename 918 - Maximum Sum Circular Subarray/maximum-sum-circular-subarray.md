# Maximum Sum Circular Subarray (LeetCode 918)

## Intuition

The best subarray either does not wrap around, or wraps from the end of the array to the beginning. The non-wrapping answer is the usual maximum-subarray sum (Kadane's algorithm). For a wrapping answer, exclude one minimum-sum contiguous subarray: the remaining elements form the best prefix-plus-suffix, with sum `totalSum - minSubarraySum`.

Run Kadane's algorithm for both the maximum and minimum subarray sums in one pass. If every value is negative, the complement formula would choose an empty subarray, so return the ordinary maximum subarray sum instead.

## Approach

- Track the best subarray ending at the current position for both maximum and minimum sums.
- Accumulate the total array sum and the best values seen so far.
- Return the larger of the non-wrapping maximum and `totalSum - minSubarraySum`, except when `maxSubarraySum < 0`.

```java
class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int totalSum = 0;
        int maxEnding = 0, minEnding = 0;
        int maxSubarraySum = nums[0], minSubarraySum = nums[0];

        for (int num : nums) {
            maxEnding = Math.max(num, maxEnding + num);
            maxSubarraySum = Math.max(maxSubarraySum, maxEnding);

            minEnding = Math.min(num, minEnding + num);
            minSubarraySum = Math.min(minSubarraySum, minEnding);

            totalSum += num;
        }

        if (maxSubarraySum < 0) {
            return maxSubarraySum; // all values are negative
        }
        return Math.max(maxSubarraySum, totalSum - minSubarraySum);
    }
}
```

## Complexity

- **Time:** `O(n)` — one pass through the array.
- **Extra space:** `O(1)`.

# LEETCODE 918 : Maximum Sum Circular Subarray

```java

// Maximum Sum Circular Subarray problem asks for the largest possible sum of a non-empty subarray in a circular integer array, where the end of the array connects back to the beginning.

class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int totalSum = 0; // Stores the sum of all elements in the array

        int currentMax = 0; // Running max sum : Current element par khatam hone wale subarray ka maximum sum
        int maxSum = nums[0]; // Stores the overall maximum subarray sum found so far

        int currentMin = 0; // Running min sum : Current element par khatam hone wale subarray ka minimum sum
        int minSum = nums[0]; // Stores the overall minimum subarray sum found so far

        for (int num : nums) { // Loop through each element in the array

            // Kadane's Algorithm for Maximum Subarray Sum
            currentMax = Math.max(num, currentMax + num); // Either start a new subarray or extend the previous one.
                                                          // Math.max() dono mein se badi value deta hai
            maxSum = Math.max(maxSum, currentMax); // Update the maximum subarray sum if current is larger

            // Kadane's Algorithm for Minimum Subarray Sum
            currentMin = Math.min(num, currentMin + num); // Either start a new min subarray or extend the previous one.
                                                          // Math.min() dono mein se chhoti value deta hai
            minSum = Math.min(minSum, currentMin); // Update the minimum subarray sum if current is smaller

            totalSum += num; // Add current element to the total sum accumulator
        }

        // If all elements are negative, Return the max single element immediately
        if (maxSum < 0) {
            return maxSum;
        }

        // Return the max of normal subarray sum or circular subarray sum (Total - Min)
        return Math.max(maxSum, totalSum - minSum);
    }
}

// Kadane's algorithm runs in O(n) time and O(1) space.
```