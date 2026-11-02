# Subsets : Recursion and Backtracking

## Problem statement

Given an integer array `nums` with distinct values, return every possible subset (the power set). The result must include the empty subset and the original set. The order of subsets does not matter.

There are `2ⁿ` subsets because each element has two choices: include it or exclude (leave it out).

## Approach : Recursion and Backtracking
Build one subset at a time. At each recursive call:

1. Add a copy of the current subset to the answer. Every partial choice is itself a valid subset.
2. Consider each remaining element, starting at `start`.
3. Choose the element by adding it to the current subset.
4. Recurse from the next index so the same element is not reused and combinations are not repeated in a different order.
5. Backtrack by removing the chosen element, then try the next candidate.

The `start` index ensures each input element is considered at most once along a path and subsets are built in input order.

## Code
```java
import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        backtrack(0, nums, current, result);
        return result;
    }

    private void backtrack(
            int start,
            int[] nums,
            List<Integer> current,
            List<List<Integer>> result) {
        // Save a snapshot; current will change during backtracking.
        result.add(new ArrayList<>(current));

        for (int i = start; i < nums.length; i++) {
            current.add(nums[i]);       // Choose
            backtrack(i + 1, nums, current, result); // Explore
            current.remove(current.size() - 1);      // Undo choice
        }
    }
}
```

## LeetCode Solution

``` java
import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        result.add(new ArrayList<>());

        for (int num : nums) {
            int currentSize = result.size();
            for (int i = 0; i < currentSize; i++) {
                List<Integer> newSubset = new ArrayList<>(result.get(i));
                newSubset.add(num);
                result.add(newSubset);
            }
        }

        return result;
    }
}
```