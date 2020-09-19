public class KeyHandler {
    private static int getKeyValue(String key){
        int sum = 0;
        int len = key.length();
        for(int i = 0;i < len; i++){
            sum += (int)key.charAt(i);
        }
        return sum;
    }
    public static String generateKey(String password){
        String key = "";
        int keyValue = getKeyValue(password);
        int permutation = ArmstrongHandler.getPermutation(keyValue);
        while(permutation > 0){
            key = ArmstrongHandler.getArmstrongNumber(permutation%10) + key;
            permutation/=10;
        }
        key = key + keyValue;
        return key;
    }
}
