# Best Time to Buy and Sell Stock

Given the stock price on each day, choose one day to buy and a later day to sell. Return the largest possible profit; return `0` if no profitable transaction is possible.

## Approach

Stock Buy & Sell Problem
Problem Statement: Given an array of stock prices, find the maximum profit by buying on one day and selling on a future day.
Optimized Solution: Rather than using nested loops (
), this is solved in 
 time by:
Maintaining a best_buy variable to track the minimum price seen so far.
Calculating potential profit at each step (
).
Updating the max_profit if a higher profit is found.
Updating best_buy if the current day's price is lower than the previous minimum.

To solve the Stock Buy and Sell problem efficiently (in 
 time), the video recommends treating each day as a potential selling day. The algorithm maintains two variables: 'maxProfit' (initialized to 0) and 'bestBuy' (initialized to the price on the first day). As you iterate through the prices starting from the second day, you calculate the profit if you were to sell on that current day by subtracting the 'bestBuy' from the current price. If this value is greater than your current 'maxProfit', you update it. Simultaneously, you update 'bestBuy' if the current day's price is lower than the previous minimum, ensuring you always track the cheapest possible entry point.

Scan the prices once. Keep the lowest price seen so far as the best buying price. For each day, calculate the profit from selling at that day's price and update the maximum profit. Because the minimum is updated as we scan from left to right, the buy day always comes before the sell day.


## Java solution

```java
class Solution {
    public int maxProfit(int[] prices) {
        int lowestPrice = Integer.MAX_VALUE;
        int bestProfit = 0;

        for (int price : prices) {
            lowestPrice = Math.min(lowestPrice, price);
            bestProfit = Math.max(bestProfit, price - lowestPrice);
        }

        return bestProfit;
    }
}
```

## Complexity
Time Complexity = O(n)
Space Complexity = O(1)