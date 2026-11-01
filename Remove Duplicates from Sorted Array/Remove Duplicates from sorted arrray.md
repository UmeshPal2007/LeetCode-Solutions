``` java
class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length < 2) {
            return nums.length;
        }

        int j = 0; //Slow Pointer
        int i = 1; //Fast Pointer


        while (i < nums.length) {
            if (nums[j] == nums[i]) { //means a duplicate is found
                i++;
            } else {
                j++;
                nums[j] = nums[i]; //Copies the new unique value into position j
                i++;
            }
        }

        return j + 1; //as the final answer. Because j is an index (starting at 0), adding 1 gives the exact count of unique elements in the array.
    }
}
```