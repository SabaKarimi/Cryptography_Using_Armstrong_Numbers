import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        try{
            String original = "src\\main\\resources\\Original\\Taj_Mahal.jpeg";
            String encrypted = "src\\main\\resources\\Encrypted\\Taj_Mahal.jpeg";
            String decrypted = "src\\main\\resources\\Decrypted\\Taj_Mahal.jpeg";
            Scanner sc = new Scanner(System.in);
            String password = sc.nextLine();
            String key = KeyHandler.generateKey(password);
            Encryptor.encrypt(original,encrypted,key);
            Decryptor.decrypt(encrypted,decrypted,key);
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }

}
