# Product of Array Except Self

Given an integer array `nums`, return an array `answer` such that `answer[i]` is the product of every element in `nums` except `nums[i]`.

The solution must run in **O(n)** time. The usual challenge also asks for no division and O(1) extra space, excluding the output array.

## Example

```text
Input:  nums = [1, 2, 3, 4]
Output: [24, 12, 8, 6]
```

For example, at index `2`, the product of all values other than `nums[2]` is `1 × 2 × 4 = 8`.

## Key observation

For any index `i`, split the product into two independent parts:

```text
answer[i] = product of values before i × product of values after i
```

This lets us avoid recomputing the product from scratch for each index. It also works when the array contains zeroes, without division.

## Approach 1: Brute force

### Intuition

For each position, visit every array position and multiply all values except the one at the current position. This directly follows the definition of the problem.

### Algorithm

1. Create an output array of the same length.
2. For each index `i`, initialize a product to `1`.
3. Scan all indices `j`. Multiply `nums[j]` into the product if `j != i`.
4. Store the product at `answer[i]`.

### Java

```java
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];

        for (int i = 0; i < n; i++) {
            int product = 1;

            for (int j = 0; j < n; j++) {
                if (j != i) {
                    product *= nums[j];
                }
            }

            answer[i] = product;
        }

        return answer;
    }
}
```

### Complexity

- **Time:** O(n²), since each of `n` positions scans the array.
- **Extra space:** O(1), excluding the output array.

This is easy to understand, but it is too slow for large inputs.

## Approach 2: Optimized prefix and suffix products

### Intuition

At every index, the answer is the product of the prefix to its left and the suffix to its right. Compute these parts with running products:

- First pass, left to right: put the product of all earlier elements into `answer[i]`.
- Second pass, right to left: multiply by the product of all later elements.

A running product starts at `1`, which naturally represents the empty prefix before index `0` and the empty suffix after the last index. No special zero handling is needed.

### Walkthrough

For `nums = [1, 2, 3, 4]`:

**After the left-to-right pass**, `answer` holds products to the left:

```text
answer = [1, 1, 2, 6]
```

**During the right-to-left pass**, multiply each value by the running product to its right:

```text
index 3: 6 × 1  = 6
index 2: 2 × 4  = 8
index 1: 1 × 12 = 12
index 0: 1 × 24 = 24
```

Result: `[24, 12, 8, 6]`.

### Algorithm

1. Create `answer` and set `prefix = 1`.
2. Traverse left to right. At each `i`, store `prefix` in `answer[i]`, then multiply `prefix` by `nums[i]`.
3. Set `suffix = 1`.
4. Traverse right to left. Multiply `answer[i]` by `suffix`, then multiply `suffix` by `nums[i]`.
5. Return `answer`.

### Java (most optimized)

```java
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];

        // Store the product of all elements to the left of each index.
        int prefix = 1;
        for (int i = 0; i < n; i++) {
            answer[i] = prefix;
            prefix *= nums[i];
        }

        // Multiply by the product of all elements to the right.
        int suffix = 1;
        for (int i = n - 1; i >= 0; i--) {
            answer[i] *= suffix;
            suffix *= nums[i];
        }

        return answer;
    }
}
```

### Complexity

- **Time:** O(n), using two linear passes.
- **Extra space:** O(1), excluding the output array. The two running products are the only extra variables.

## LeetCode Solutions
``` java
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];

        // Initialize the first element
        ans[0] = 1;

        // prefix => ans
        for (int i = 1; i < n; i++) {
            ans[i] = ans[i - 1] * nums[i - 1];
        }

        int suffix = 1;
        // Suffix
        for(int i = n-2; i>=0; i--) {
            suffix *= nums[i+1];
            ans[i] *= suffix;
        }
        return ans;       
    }
}
```