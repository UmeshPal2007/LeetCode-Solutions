# Remove Duplicates from Sorted Array 
## Problem

Given an integer array `nums` sorted in non-decreasing order, remove the
duplicates **in-place** so that each unique element appears only once.

Return the number of unique elements, `k`. The first `k` positions of
`nums` must contain the unique elements in their original order.

The elements after index `k - 1` do not matter.

## Intuition

Because the array is already sorted, duplicate values are always next to
each other.

We can use two pointers:

-   `i` keeps track of the position of the last unique element.
-   `j` scans the array to find the next new value.

When `nums[j]` is different from `nums[i]`, we have found a new unique
element. Move `i` one step forward and copy `nums[j]` to `nums[i]`.

At the end, the number of unique elements is `i + 1`.

## Approach --- Two Pointers

1.  If the array is empty, return `0`.
2.  Start `i` at index `0`. It represents the last unique element's
    position.
3.  Start `j` at index `1` and scan the array.
4.  If `nums[j] != nums[i]`:
    -   Increment `i`.
    -   Place `nums[j]` at `nums[i]`.
5.  Return `i + 1`.

## Java Solution

``` java
class Solution {
    public int removeDuplicates(int[] nums) {

        if (nums.length == 0) {
            return 0;
        }

        int i = 0;

        for (int j = 1; j < nums.length; j++) {

            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }

        return i + 1;
    }
}
```

## Line-by-Line Explanation

-   `if (nums.length == 0)` checks whether the array is empty.
-   `return 0` handles the empty array because it contains no unique
    elements.
-   `int i = 0` marks the position of the first unique element.
-   `for (int j = 1; j < nums.length; j++)` scans the array from the
    second element.
-   `if (nums[j] != nums[i])` checks whether the current value is
    different from the last unique value.
-   `i++` moves the unique-element position forward.
-   `nums[i] = nums[j]` writes the newly found unique value into the
    next available position.
-   `return i + 1` returns the count of unique elements. Indexing starts
    at `0`, so the count is one more than the final index.

## Complexity Analysis

  -----------------------------------------------------------------------
  Complexity              Result                  Reason
  ----------------------- ----------------------- -----------------------
  Time                    `O(n)`                  The array is scanned
                                                  once.

  Space                   `O(1)`                  Only two pointers are
                                                  used; no extra array is
                                                  created.
  -----------------------------------------------------------------------

## Key Takeaways

-   Sorted arrays place duplicates next to each other.
-   Use two pointers when one pointer tracks the result and another
    scans the input.
-   The solution modifies the original array in-place.
-   Return the count `k`, not the modified array.

**Pattern to remember:** Sorted Array + In-place Modification → Two
Pointers.

``` java
class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length < 2) {
            return nums.length;
        }

        int j = 0; //Slow Pointer
        int i = 1; //Fast Pointer


        while (i < nums.length) {
            if (nums[j] == nums[i]) { //means a duplicate is found
                i++;
            } else {
                j++;
                nums[j] = nums[i]; //Copies the new unique value into position j
                i++;
            }
        }

        return j + 1; //as the final answer. Because j is an index (starting at 0), adding 1 gives the exact count of unique elements in the array.
    }
}
```