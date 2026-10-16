// LEETCODE 918 : Maximum Sum Circular Subarray

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
