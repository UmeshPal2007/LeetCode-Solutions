// LEETCODE 53 : Maximum Subarray Sum using Kadane's Algorithm
//The maximum subarray problem is the task of finding a contiguous (unbroken) part of a one-dimensional array of numbers that has the largest possible sum.

// Kadane’s Algorithm (Optimal Approach)
// • How it works: Traverse the array from left to right while keeping a running sum (currentSum). At each element, choose whether to:
// • Extend the existing subarray by adding the current number to currentSum.
// • Start a fresh subarray from the current number if currentSum drops below zero (since a negative sum hurts future totals).
// • Track the highest sum seen so far in a maxSum variable.

// Brute Force (Naïve Approach)
// • How it works: Use nested loops to generate every possible start and end point, calculate the sum for each subarray, and find the maximum.
// • Time Complexity: O(n²) (or O(n³) if recalculating sums from scratch)
// • Space Complexity: O(1)

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

// • Time Complexity: O(n)
// • Space Complexity: O(1)