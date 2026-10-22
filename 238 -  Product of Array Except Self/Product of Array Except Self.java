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