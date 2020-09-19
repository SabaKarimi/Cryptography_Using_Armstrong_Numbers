public class ArmstrongHandler {
    private static final String[] armstrongNumbers = {"153" , "370" , "371" , "407"};
    private static int[] permutations;
    private static void next_permutation(int[] arr){
        int ptr1 = -1;
        for(int i = arr.length -2; i>=0; i--){
            if(arr[i] < arr[i+1]){
                ptr1 = i;
                break;
            }
        }
        if(ptr1 == -1) return;
        int ptr2 = -1;
        for(int i = arr.length -1; i>ptr1; i--){
            if(arr[i] > arr[ptr1]){
                ptr2 = i;
                break;
            }
        }
        int temp = arr[ptr1];
        arr[ptr1] = arr[ptr2];
        arr[ptr2] = temp;
        int i = ptr1 + 1;
        int j = arr.length - 1;
        while(i < j){
            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }
    private static int getNumber(int[] arr){
        int number = 0;
        for (int j : arr) {
            number = (number * 10) + j;
        }
        return number;
    }
    private static void generatePermutation(){
        permutations = new int[24];
        int[] arr = {1 , 2, 3, 4};
        permutations[0] = getNumber(arr);
        for(int i = 1;i<=23;i++){
            next_permutation(arr);
            permutations[i] = getNumber(arr);
        }
    }
    static int getPermutation(int keyValue){
        generatePermutation();
        return permutations[keyValue%24];
    }
    static String getArmstrongNumber(int idx){
        return armstrongNumbers[idx-1];
    }
}
