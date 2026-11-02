# Container With Most Water: Complete Logic in Java

## Problem statement

Given an integer array `height`, where `height[i]` is the height of a vertical line at position `i`, choose two lines that, together with the x-axis, hold the most water. Return the maximum area.

Find two lines that together with the x-axis form a container, such that the container contains the most water.
Return the maximum amount of water a container can store.
Notice that you may not slant the container.

The area between lines at indices `left` and `right` is:

```text
width × height of the shorter line
= (right - left) × min(height[left], height[right])
```

Example:

```text
height = [1, 8, 6, 2, 5, 4, 8, 3, 7]
maximum area = 49
```

The lines at indices `1` and `8` have width `7` and shorter height `7`, so their area is `7 × 7 = 49`.

## 1. Brute force approach

Try every pair of lines. For each pair, calculate the width, take the shorter line's height, and calculate the area. Track the largest area found.

```java
static int maxAreaBruteForce(int[] height) {
    int maxArea = 0;

    for (int left = 0; left < height.length; left++) {
        for (int right = left + 1; right < height.length; right++) {
            int width = right - left;
            int limitingHeight = Math.min(height[left], height[right]);
            int area = width * limitingHeight;
            maxArea = Math.max(maxArea, area);
        }
    }

    return maxArea;
}
```

- **Time:** `O(n²)`, because every pair is checked.
- **Extra space:** `O(1)`.
- **Use it when:** the input is small or as a straightforward baseline.

## 2. Optimized approach: Two pointers

Start with one pointer at the first line and one at the last. At each step:

1. Compute the area using the current width and the shorter line.
2. Update the maximum area.
3. Move the pointer at the shorter line inward.

Why move the shorter line? The current area is limited by that shorter height. Moving the taller line inward would reduce the width while keeping the limiting height no larger, so it cannot produce a better area with the same shorter line. The only chance to improve is to move the shorter side and look for a taller line.

```java
static int maxArea(int[] height) {
    int left = 0;
    int right = height.length - 1;
    int maxArea = 0;

    while (left < right) {
        int width = right - left;
        int limitingHeight = Math.min(height[left], height[right]);
        int area = width * limitingHeight;
        maxArea = Math.max(maxArea, area);

        if (height[left] < height[right]) {
            left++;
        } else {
            right--;
        }
    }

    return maxArea;
}
```

- **Time:** `O(n)`, because each pointer moves inward at most `n - 1` times total.
- **Extra space:** `O(1)`.
- **Use it when:** solving the standard problem; this is the preferred approach.

If the two heights are equal, moving either pointer is valid. The code above moves the right pointer.

## LeetCode Solution

``` java
class Solution {
    public int maxArea(int[] height) {
        int maxWater = 0;
        int leftPointer = 0;
        int rightPointer = height.length - 1;

        while(leftPointer < rightPointer) {
            int width = rightPointer - leftPointer;
            int Height = Math.min(height[leftPointer], height[rightPointer]);
            int currWater = width * Height;
            maxWater = Math.max(maxWater, currWater);

            if(height[leftPointer] < height[rightPointer]) {
                leftPointer++;
            } else {
                rightPointer--;
            }
        }
        return maxWater;
    }
}
```