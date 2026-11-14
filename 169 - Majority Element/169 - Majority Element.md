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


Majority Element Problem (14:42 - 38:53)

Objective: Find the element that appears more than 
 times in an array.
Brute Force: Counts the frequency of each element using nested loops (
) (14:42).
Better Approach: Sorts the array first (
) to easily count consecutive identical elements (21:48).
Optimal Approach (Moore's Voting Algorithm): Maintains a frequency count and candidate element. By incrementing for the candidate and decrementing for others, it efficiently identifies the majority element in 
 time complexity (29:03).


 Moore's Voting Algorithm is an efficient approach used to find the majority element in an array, which is defined as an element that appears more than 
 times (29:03 - 29:21).

The algorithm works based on the following logic:

Candidate Selection: You maintain a candidate element and a frequency count initialized to zero. As you iterate through the array, if the current count is zero, you assign the current element as the candidate (31:37 - 32:02).
Voting: If the current element matches your candidate, you increment the count. If it does not match, you decrement the count. By treating the count like 'power' or 'votes', the majority element—which occupies more than half the array—will remain as the candidate at the end of the process because its frequency is high enough to offset all other elements (30:03 - 31:36).
Result: Once the loop finishes, the remaining candidate is the majority element (32:32 - 32:51).
This algorithm is highly optimal with a time complexity of 
 because it only requires a single pass through the data, unlike brute-force methods (32:53 - 33:02).


 Moore's Voting Algorithm is an efficient approach used to find the majority element in an array, which is defined as an element that appears more than 
 times (29:03 - 29:21).

The algorithm works based on the following logic:

Candidate Selection: You maintain a candidate element and a frequency count initialized to zero. As you iterate through the array, if the current count is zero, you assign the current element as the candidate (31:37 - 32:02).
Voting: If the current element matches your candidate, you increment the count. If it does not match, you decrement the count. By treating the count like 'power' or 'votes', the majority element—which occupies more than half the array—will remain as the candidate at the end of the process because its frequency is high enough to offset all other elements (30:03 - 31:36).
Result: Once the loop finishes, the remaining candidate is the majority element (32:32 - 32:51).
This algorithm is highly optimal with a time complexity of 
 because it only requires a single pass through the data, unlike brute-force methods (32:53 - 33:02).