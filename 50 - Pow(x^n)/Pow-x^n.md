# Pow(x^n)

## Problem statement

Implement `myPow(x, n)`, which calculates `x` raised to the integer power `n` (`xⁿ`). Return the result as a `double`.
The important challenge is to avoid multiplying `x` by itself `|n|` times for large exponents.

## 1. Brute force: multiply repeatedly

Start with `result = 1`. Multiply by `x` once for every unit of the exponent's magnitude. For a negative exponent, return the reciprocal of the result.

```java
static double myPowBruteForce(double x, int n) {
    long exponent = n;
    long count = Math.abs(exponent); // long handles Integer.MIN_VALUE

    double result = 1.0;
    for (long i = 0; i < count; i++) {
        result *= x;
    }

    return exponent < 0 ? 1.0 / result : result;
}
```

- **Time:** `O(|n|)`.
- **Extra space:** `O(1)`.
- **Limitation:** this can take too long for a very large exponent, so it may time out.
  
## 2. Optimized approach: Iterative Binary exponentiation

The iterative approach uses the same halving idea, without recursion. Track:

- `exponent`: the remaining nonnegative exponent.
- `currentBase`: the current power of the base, squared each round.
- `result`: the product of powers corresponding to set bits in the exponent.

On every iteration, the least significant bit tells us whether the current power belongs in the result. Then shift the exponent right by one bit and square the base for the next bit.

```java
static double myPow(double x, int n) {
    long exponent = n;

    if (exponent < 0) {
        x = 1.0 / x;
        exponent = -exponent;
    }

    double result = 1.0;
    double currentBase = x;

    while (exponent > 0) {
        if ((exponent & 1L) == 1L) {
            result *= currentBase;
        }
        currentBase *= currentBase;
        exponent >>= 1;
    }

    return result;
}
```

- **Time:** `O(log |n|)`.
- **Extra space:** `O(1)`.
- **Why preferred:** it is fast and avoids recursion and its stack usage.

## LeetCode Solution
``` java
class Solution {
    public double myPow(double x, int n) {
        // Corner Cases
        if (n == 0)
            return 1.0;
        if (x == 0)
            return 0.0;
        if (x == 1)
            return 1.0;
        if (x == -1 && n % 2 == 0)
            return 1.0;
        if (x == -1 && n % 2 != 0)
            return -1.0;

        long binForm = n;
        if (binForm < 0) {
            binForm = -binForm;
        }

        double ans = 1.0;

        // Binary Exponentiation
        while (binForm > 0) {
            if (binForm % 2 == 1) {
                ans *= x;
            }
            x *= x;
            binForm /= 2;
        }

        if (n < 0) {
            return 1.0 / ans;
        }

        return ans;
    }
}
```