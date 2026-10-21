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