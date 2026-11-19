# LeetCode 229: Majority Element II

Given an integer array `nums` of length `n`, return every value that appears **more than** `⌊n / 3⌋` times. The answer can contain at most two values.

## Why can there be at most two answers?

Three distinct values would each need to occur at least `⌊n / 3⌋ + 1` times. Their combined count would exceed `n`, which is impossible.

## Approach 1: Brute force

For each array value, count its occurrences by scanning the whole array. If its count is greater than `n / 3`, add it to the result once. A set prevents duplicate output when the same value appears at multiple indices.

### Java implementation

```java
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> checked = new HashSet<>();
        int threshold = nums.length / 3;

        for (int value : nums) {
            if (!checked.add(value)) {
                continue; // Already counted this distinct value.
            }

            int count = 0;
            for (int candidate : nums) {
                if (candidate == value) {
                    count++;
                }
            }

            if (count > threshold) {
                result.add(value);
            }
        }

        return result;
    }
}
```

### Complexity

- **Time:** `O(n · d)`, where `d` is the number of distinct values; worst case `O(n²)`.
- **Extra space:** `O(d)` for the set, excluding the returned list.

This is straightforward, but repeatedly scanning the array is slow for many distinct values.

## Approach 2: Extended Boyer–Moore Voting (optimized)

### Intuition

For the usual majority-element problem, one candidate survives pairwise cancellation because a value occurring more than half the time cannot be fully canceled by all other values. Here the cutoff is one third, so there may be two qualifying values. Keep **two candidate slots** and their counts.

Think of each round as canceling a group of three different values. Any value that appears more than `n / 3` times cannot be completely removed by these cancellations. The first pass identifies the only possible survivors; it does **not** establish that either candidate truly passes the threshold. A second pass must count and verify them.

### Candidate selection rules

For each value `x`:

1. If `x` matches the first candidate, increase its count.
2. Otherwise, if it matches the second candidate, increase that count.
3. Otherwise, fill an empty candidate slot if one exists.
4. If both slots are occupied by different values, decrement both counts. This cancels one occurrence of three distinct values.

The order matters: check matches before empty slots so a candidate with a zero count can be replaced correctly without accidentally giving a matching value a second slot.

After the first pass, count occurrences of both surviving candidates from scratch. Add each candidate whose actual count is greater than `nums.length / 3`, taking care not to add the same value twice.

### Java implementation

```java
import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int candidate1 = 0;
        int candidate2 = 0;
        int count1 = 0;
        int count2 = 0;

        // Pass 1: find up to two possible candidates.
        for (int value : nums) {
            if (count1 > 0 && value == candidate1) {
                count1++;
            } else if (count2 > 0 && value == candidate2) {
                count2++;
            } else if (count1 == 0) {
                candidate1 = value;
                count1 = 1;
            } else if (count2 == 0) {
                candidate2 = value;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }

        // Pass 2: candidate status is only provisional; verify real frequencies.
        count1 = 0;
        count2 = 0;
        for (int value : nums) {
            if (value == candidate1) {
                count1++;
            } else if (value == candidate2) {
                count2++;
            }
        }

        List<Integer> result = new ArrayList<>();
        int threshold = nums.length / 3;

        if (count1 > threshold) {
            result.add(candidate1);
        }
        if (count2 > threshold && candidate2 != candidate1) {
            result.add(candidate2);
        }

        return result;
    }
}
```

### Why it works

There can be at most two values occurring more than `n / 3` times. During candidate selection, whenever both counters are positive and the current value matches neither candidate, decrementing both counters removes one occurrence of each candidate and one occurrence of a third, distinct value. This cancellation cannot eliminate every occurrence of a value that appears more than one third of the time: each cancellation involving it also consumes two occurrences from other values, and no qualifying value can be outweighed by all the remaining values combined in this three-way cancellation process. Thus every qualifying value remains among the final candidates. The verification pass then removes any candidates that do not actually meet the strict threshold.

### Complexity

- **Time:** `O(n)` — two passes.
- **Extra space:** `O(1)`, excluding the returned list.

## Quick walkthrough

For `nums = [1, 2, 1, 2, 1, 3, 2]`, `n / 3` is `2`. The first pass leaves possible candidates `1` and `2`. The verification pass counts three `1`s and three `2`s, so both exceed `2`; return `[1, 2]`.

## Common mistakes

- Returning candidates immediately after the first pass without verifying their actual counts.
- Using `>= n / 3`; the requirement is **strictly more than** `⌊n / 3⌋`.
- Checking for empty counters before checking whether the value matches a current candidate.
- Forgetting that the result may contain zero, one, or two values.
- Allowing duplicate candidates into the result (the implementation checks they differ).

## Which approach to use

Use the brute-force version to understand the counting requirement. For LeetCode 229, the extended Boyer–Moore version is the preferred solution because it runs in linear time and uses constant extra space.

## LeetCode Solution

``` java
import java.util.ArrayList;
import java.util.List;; 


class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new ArrayList<>();

        if (nums == null || nums.length == 0) {
            return result;
        }

        //Step 1 : Find potential candidates
        int candidate1 = 0; int candidate2 = 0;
        int count1 = 0; int count2 = 0;

        for(int num : nums) {
            if(num == candidate1) {
                count1++;
            } else if(num == candidate2) {
                count2++;
            } else if(count1 == 0) {
                candidate1 = num;
                count1 = 1;
            } else if(count2 == 0) {
                candidate2 = num;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }

        // Step : 2: Verify candidates
        count1 = 0;
        count2 = 0;
        for(int num:nums) {
            if(num == candidate1) {
                count1++;
            } else if (num == candidate2) {
                count2++;
            }
        }

        int threshold = nums.length/3;
        if(count1 > threshold) {
            result.add(candidate1);
        }

        if(count2 > threshold) {
            result.add(candidate2);
        }

        return result;
    }
}
```