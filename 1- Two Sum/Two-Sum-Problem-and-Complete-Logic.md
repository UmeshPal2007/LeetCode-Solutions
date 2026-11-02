# Two Sum Problem: Complete Logic

## Problem statement

Given an array of integers `nums` and an integer `target`, find the **indices of two distinct elements** whose values add up to `target`.
You may assume that each input would have exactly one solution, and you may not use the same element twice.

For example:
```text
nums   = [2, 7, 11, 15]
target = 9
```

The answer is `[0, 1]`, because `nums[0] + nums[1] = 2 + 7 = 9`.

### 1. Brute force approach

Check every pair of indices. The inner loop starts at `i + 1`, so an element is never paired with itself and each pair is checked only once.

```java
static int[] twoSumBruteForce(int[] nums, int target) {
    for (int i = 0; i < nums.length; i++) {
        for (int j = i + 1; j < nums.length; j++) {
            if(nums[i] + nums[j] == target) {
                return new int[] {i, j};
            }
        }
    }
    return new int[0]; // No pair found
}
```

- **Time:** `O(n²)` because it may check every pair.
- **Extra space:** `O(1)`.
- **Use it when:** the input is small or you want the simplest solution.

### 2. Better approach: sort and use two pointers

Pair every number with its original index, then sort those pairs by number. Start one pointer at each end of the sorted array. If their sum is too small, move the left pointer right; if too large, move the right pointer left. Preserve original indices so the answer refers to the input array.

```java
import java.util.Arrays;

public class TwoSumTwoPointer {
    public static boolean hasTwoSum(int[] arr, int target) {
        // Step 1: Sort the array (skip if already sorted)
        Arrays.sort(arr); 
        
        // Step 2: Initialize two pointers at opposite ends
        int left = 0;
        int right = arr.length - 1;
        
        // Step 3: Shrink the window toward the center
        while (left < right) {
            int currentSum = arr[left] + arr[right];
            
            if (currentSum == target) {
                return true; // Pair found
            } else if (currentSum < target) {
                left++; // Sum is too small, move right to get a larger value
            } else {
                right--; // Sum is too large, move left to get a smaller value
            }
        }
        return false; // No pair found
    }
}
```

- **Time:** `O(n log n)` for sorting, followed by an `O(n)` scan.
- **Extra space:** typically `O(n)` to retain original indices (sorting space also depends on the language).
- **Use it when:** the input is already sorted, or when applying the two-pointer pattern. For an unsorted array, the hash map approach is generally faster.

### 3. Optimized approach: Hashing
Hashing means using maps & sets in Programming

Scan the original array once. For each value, look for its complement among values seen earlier. If it is present, return the earlier index and the current index; otherwise, record the current value and index. This is the approach detailed below.

The optimized Two Sum approach transforms a time-consuming search into a fast, single-pass operation by trading a small amount of memory for maximum speed. As you traverse the array from left to right, you calculate the exact complement (the target minus the current number) needed to complete the sum, and instantly check a HashMap to see if that complement was recorded in a previous step. If the complement is found, the problem is solved immediately because the map provides the past index while the loop provides the current one; if it is not found, the current number and its index are saved into the map for future numbers to find. Because looking up a value in a HashMap takes constant time (\(O(1)\)), this technique allows you to find the matching pair in a single sweep through the array, reducing the time complexity from a slow \(O(n^2)\) brute-force search to an efficient \(O(n)\) linear run time.

``` java
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        // Create a hash map to store elements and their corresponding indices
        Map<Integer, Integer> map = new HashMap<>();
        
        // Traverse the array once
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            
            // Check if the complement already exists in the map
            if (map.containsKey(complement)) {
                // Return the index of the complement and the current index
                return new int[] { map.get(complement), i };
            }
            
            // If not found, store the current number and its index in the map
            map.put(nums[i], i);
        }
        
        // Return an empty array if no solution is found (per problem constraints)
        return new int[] {};
    }
}

```

- **Time:** `O(n)` on average.
- **Extra space:** `O(n)` in the worst case.
- **When useful:** the standard choice for an unsorted array when fast lookup is the priority.


Keep a hash map that records values already seen and their indices. If the complement is already in the map, the current value and that earlier value form the answer. Otherwise, save the current value and its index for later.
`None` is returned if no pair exists. If the problem guarantees a solution, that case will not occur.

## LeetCode Solution
``` java
import java.util.HashMap;
import java.util.Arrays;

class Twosum {
    // Function to find the indices of Two Numbers
    public static int[] twoSum(int[] arr, int tar) {
        // Created a HashMap to store numbers & their indexes
        HashMap<Integer, Integer> Hashmap = new HashMap<>();

        // Loop to traverse through each element of the array one by one
        for (int i = 0; i < arr.length; i++) {
            int first = arr[i]; // Current Number
            int second = tar - first; // Second Number we are looking for

            // if the second number already exists in the HashMap
            if (Hashmap.containsKey(second)) {
                // then return the current index and the index of that second number
                return new int[] { i, Hashmap.get(second) };
            }

            // If the number is not found, put the current number & its index into the HashMap
            Hashmap.put(first, i);
        }
        // If no such Pair is found
        System.out.println("No Pair Found which equals to the Target " + tar);
        return arr;
    }

    public static void main(String[] args) {
        // Input array and target value to test
        int nums[] = { 2, 7, 11, 15 };
        int target = 9;

        // Calling the function & storing the result
        int[] result = twoSum(nums, target);

        // Print Result to Console
        System.out.println("Indices of two Numbers whose sum is equals to the Target " + Arrays.toString(result));
    }
}
```