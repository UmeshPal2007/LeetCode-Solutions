# Reverse String

## Intuition

The string can be reversed in place by swapping its outermost characters, then moving inward. Once the left and right pointers meet or cross, every pair has been swapped.

## Approach

1. Convert the string to a `char[]`, since Java strings are immutable.
2. Set `left` to the first index and `right` to the last.
3. While `left < right`, swap the characters and move both pointers toward the center.
4. Return the character array as a string.

```java
class Solution {
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;

        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }
}
```

The method changes the input array directly, as required by the common Reverse String problem.

## Complexity

- **Time:** `O(n)` — each character is processed at most once.
- **Extra space:** `O(1)` — reversal uses only two pointers and one temporary variable.
