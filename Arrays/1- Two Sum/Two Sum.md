# LeetCode 1: Two Sum

## Problem Statement

Given an array of integers `nums` and an integer `target`, return the **indices of the two numbers** such that they add up to `target`.

- You may assume that each input has exactly one solution.
- You may not use the same element twice.

## Approach 1: Brute Force
The brute-force approach checks every possible pair of numbers in the array to see whether their sum equals the target.

### How it works
- Use nested loops to test all possible pairs.
- For each pair, check whether `nums[i] + nums[j] == target`.
- If the sum equals the target, return the two indices.
- **Time complexity:** `O(n²)` — two nested loops may examine every pair.
- **Space complexity:** `O(1)` — only a constant amount of extra space is used, excluding the returned answer.

## Approach 2: Sorting + Two Pointers
This approach sorts the numbers and uses two pointers, one at each end, moving toward the middle.

### How it works
- Sort the array in ascending order.
- Place one pointer at the beginning and the other at the end.
- If their sum is equal to the target, the pair has been found.
- If the sum is smaller than the target, move the left pointer to the right to increase the sum.
- If the sum is larger than the target, move the right pointer to the left to decrease the sum.
- **Time complexity:** `O(n log n)` for sorting, followed by `O(n)` for the two-pointer scan; overall `O(n log n)`.
- **Space complexity:** Depends on the sorting implementation and whether extra storage is used.

## Approach 3: Hash Map (Expected / Optimized Approach)
In programming, the primary purpose of using Maps and Sets in practical scenarios is to achieve a constant time complexity—O(1)—for lookup, insertion, and deletion operations.
A hash map stores numbers already visited, along with their indices. This lets us look up the number needed to complete the target sum.

For each number `current`, calculate its complement:

`complement = target - current`

If the complement is already in the map, the two indices have been found. Otherwise, store the current number and its index in the map.

### Step-by-step implementation
1. Create an empty hash map (`HashMap<Integer, Integer>` in Java).
2. Iterate through the array.
3. For each number, calculate `complement = target - current`.
4. Check whether the map contains the complement.
5. If it does, return the stored index of the complement and the current index.
6. Otherwise, add the current number and its index to the map.
7. If no pair is found after the loop, return an empty result (though the problem guarantees one solution).

- **Time complexity:** `O(n)` on average — each hash-map lookup and insertion is expected to take constant time.
- **Space complexity:** `O(n)` — in the worst case, the map stores nearly every array element.
