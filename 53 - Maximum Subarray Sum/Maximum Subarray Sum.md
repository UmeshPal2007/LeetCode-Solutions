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

Subarray Basics: The video defines a subarray as a continuous part of an array. It explains how to calculate the total number of possible subarrays for an array of size 
, using the formula 
, and demonstrates how to print them using nested loops (1:02-6:18).
Brute Force Approach: This method involves iterating through all possible subarrays to calculate their individual sums and tracking the maximum. The video discusses how to optimize this from a triple-nested loop to a double-nested loop, resulting in a time complexity of 
 (6:18-12:40).
Kadane's Algorithm: This is the most efficient approach presented, with a linear time complexity of 
. The core logic is to maintain a currentSum of the subarray and reset it to zero whenever it becomes negative, as negative sums decrease the potential maximum. It also discusses handling edge cases, such as arrays consisting entirely of negative numbers (12:40-22:06).

Kadane's Algorithm is an optimized, linear-time approach used to find the maximum subarray sum within an array (12:40).

Its core logic is based on the intuition that if a subarray's running sum becomes negative, it will only decrease the sum of any subsequent elements added to it. Therefore, if the currentSum drops below zero, the algorithm resets it to zero and effectively starts a fresh subarray (13:42-15:07).

Key steps of the algorithm:

Maintain a currentSum variable (initialized to 0) and a maxSum variable (often initialized to the smallest possible integer) to track the highest sum found (13:56-14:08).
Iterate through each element of the array, adding it to the currentSum (15:31-15:42).
After adding each element, update maxSum by taking the maximum of currentSum and the existing maxSum (15:47-15:55).
If currentSum falls below 0, reset it to 0 before continuing to the next element (15:56-16:02).
This approach achieves a time complexity of 
, making it significantly more efficient than the brute force 
 approach (20:25-20:47).

 A subarray is a continuous part of a given array (1:02-1:25). To calculate the sum of a subarray, you identify its starting index and ending index, and then iterate through every element within that range to add them together (2:32-3:00, 4:48-5:36).

There are a few ways to approach this:

Brute Force Approach: This involves using nested loops. An outer loop tracks the start, a second loop tracks the end, and a third loop iterates from start to end to calculate the sum. This results in a time complexity of 
 (6:18-8:05).
Optimized Brute Force: You can improve the efficiency by removing the third loop. Once you have a fixed starting point, as you expand the subarray by moving the end pointer, you simply add the new element to your existing currentSum, keeping the complexity to 
 (8:19-11:25).
Kadane's Algorithm: This is the most optimized approach, achieving linear time complexity 
. It maintains a currentSum and updates a maxSum. If currentSum ever becomes negative, it resets to zero, as adding a negative value would only decrease the potential maximum subarray sum (12:40-15:07, 20:25-20:47).

Kadane's Algorithm is an optimized approach to find the maximum subarray sum with linear time complexity, O(n) (20:25-20:47).

Its core logic is based on the idea that if a running subarray sum becomes negative, it is better to reset that sum to 0 because adding a negative value will only decrease the sum of any subsequent elements (13:42-15:07).

How it works:

Initialization: Maintain two variables: currentSum (initially 0) to track the running sum and maxSum (initially INT_MIN) to store the highest sum found so far (13:56-14:08).
Iteration: Loop through each element of the array (15:31-15:42).
Summation & Comparison:
Add the current element to currentSum (15:38-15:42).
Update maxSum by taking the maximum of currentSum and the existing maxSum (15:47-15:55).
Reset: If currentSum becomes less than 0, reset currentSum to 0 (15:56-16:02). This effectively restarts the subarray search from the next element, discarding the negative contribution (14:43-15:25).