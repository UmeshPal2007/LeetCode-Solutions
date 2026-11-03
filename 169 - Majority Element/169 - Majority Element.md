# LeetCode 169: Majority Element

A **majority element** is a value that appears more than `n / 2` times in an array of length `n`.

## 1. Brute Force Approach

### Intuition

For each array element, scan the entire array and count how many times it occurs. If its count is greater than `n / 2`, return it. Recounting duplicate values is fine for this simple approach.

### Java

```java
class Solution {
    public int majorityElement(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int count = 0;

            for (int j = 0; j < n; j++) {
                if (nums[j] == nums[i]) {
                    count++;
                }
            }

            if (count > n / 2) {
                return nums[i];
            }
        }

        // LeetCode guarantees a majority element exists.
        throw new IllegalArgumentException("No majority element found");
    }
}
```

### Complexity

- Time: `O(n^2)` — each of the `n` elements scans the array.
- Extra space: `O(1)`.

## 2. Better approach — Sorting

### Intuition

After sorting, equal values sit next to each other. A value occurring more than half the time must cover the middle index `n / 2`: there are not enough positions on either side for it to stay entirely before or after the middle. So the element at index `n / 2` is the majority element.

### Java

```java
import java.util.Arrays;

class Solution {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
}
```

`Arrays.sort` changes the input array. If preserving the original array matters, sort a copy instead:

```java
int[] copy = nums.clone();
Arrays.sort(copy);
return copy[copy.length / 2];
```

### Complexity

- Time: `O(n log n)`.
- Extra space: depends on the sorting implementation; Java's primitive-array sort uses an in-place dual-pivot quicksort implementation, with stack space for recursion.

## 3. Most Optimized Approach — Boyer–Moore Voting Algorithm

### Intuition

Imagine pairing each occurrence of the majority value with an occurrence of a different value and cancelling both. Because the majority value occurs more than all other values combined, it cannot be fully cancelled. It remains as the candidate.

Maintain:

- `candidate`: the current possible majority value.
- `count`: the balance of votes for that candidate.

For each number:

1. If `count` is zero, make the current number the candidate.
2. If it equals the candidate, add one vote; otherwise subtract one.

The candidate is the majority element when one is guaranteed to exist, as in LeetCode 169.

### Java

```java
class Solution {
    public int majorityElement(int[] nums) {
        int candidate = 0;
        int count = 0;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }

            if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }

        return candidate;
    }
}
```

### Why cancellation works

Each decrement corresponds to pairing one candidate occurrence with one different value. A majority element has more occurrences than all non-majority elements combined, so even if every non-majority occurrence cancels one majority occurrence, at least one majority occurrence remains. The algorithm's running candidate tracks that survivor.

### Complexity

- Time: `O(n)` — one pass.
- Extra space: `O(1)`.

## LeetCode Solution

``` java
class Solution {
    public int majorityElement(int[] nums) {
        int freq = 0;
        int ans = 0;

        for (int i = 0; i < nums.length; i++) {
            if (freq == 0) {
                ans = nums[i];
            }

            if (ans == nums[i]) {
                freq++;
            } else {
                freq--;
            }
        }
        return ans;
    }
}

```