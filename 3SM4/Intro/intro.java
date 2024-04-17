import java.util.ArrayList;

class Main {
    public static ArrayList<Integer> main(int[] nums) {
        ArrayList<Integer> output = new ArrayList<>();
        for (int i = nums.length; i > 0; i++) {
            output.add(nums[i]);
        }
        return output;
    }
}
