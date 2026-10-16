import java.util.HashMap;
import java.util.Arrays;

class Twosum {
    // Function to find the indices of Two Numbers
    public static int[] twoSum(int[] arr, int tar) {
        // Created a HashMap to store numbers & their indexes
        HashMap<Integer, Integer> Hashmap = new HashMap<>();

        // Loop to traverse through each element of the array one by one
        for (int i = 0; i < arr.length; i++) {
            int first = arr[i]; // Current Number
            int second = tar - first; // Second Number we are looking for

            // if the second number already exists in the HashMap
            if (Hashmap.containsKey(second)) {
                // then return the current index and the index of that second number
                return new int[] { i, Hashmap.get(second) };
            }

            // If the number is not found, put the current number & its index into the HashMap
            Hashmap.put(first, i);
        }
        // If no such Pair is found
        System.out.println("No Pair Found which equals to the Target " + tar);
        return arr;
    }

    public static void main(String[] args) {
        // Input array and target value to test
        int nums[] = { 2, 7, 11, 15 };
        int target = 9;

        // Calling the function & storing the result
        int[] result = twoSum(nums, target);

        // Print Result to Console
        System.out.println("Indices of two Numbers whose sum is equals to the Target " + Arrays.toString(result));
    }
}

