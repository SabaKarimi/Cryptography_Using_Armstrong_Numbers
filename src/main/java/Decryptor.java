import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Decryptor {
    public static void decrypt(String encrypted,String decrypted,String key) throws IOException {
        FileInputStream fin = new FileInputStream(encrypted);
        FileOutputStream fout = new FileOutputStream(decrypted);
        int len;
        byte[] buffer = new byte[2048];
        while ((len = fin.read(buffer))!= -1){
            levelTwoDecryption(buffer,len,key);
            levelOneDecryption(buffer,len,key);
            fout.write(buffer,0,len);
        }
        fout.flush();
        fin.close();
        fout.close();
    }
    private static void levelOneDecryption(byte[] buffer, int len, String key){
        int keyLength = key.length();
        for(int i = 0;i<len;i++){
            int data = (int)buffer[i] & 255;
            data = data ^ (int)key.charAt(i%keyLength);
            buffer[i] = (byte)data;
        }
    }
    private static void levelTwoDecryption(byte[] buffer, int len, String key){
        int[] color = ColorHandler.getColor(key);
        for(int i = 0;i<len;i++) {
            int data = (int) buffer[i] & 255;
            data = (data - color[i % color.length] + 256) % 256;
            int r = data / 16;
            int c = data % 16;
            buffer[i] = (byte) merge(r, c);
        }
    }
    private static int merge(int r , int c){
        return (r << 4) | (c);
    }
}
