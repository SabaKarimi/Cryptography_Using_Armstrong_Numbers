import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Encryptor {
    public static void encrypt(String original,String encrypted,String key) throws IOException {
        FileInputStream fin = new FileInputStream(original);
        FileOutputStream fout = new FileOutputStream(encrypted);
        byte buffer[] = new byte[2048];
        int len;
        while((len = fin.read(buffer))!= -1){
            levelOneEncryption(buffer,len,key);
            levelTwoEncryption(buffer,len,key);
            fout.write(buffer,0,len);
        }
        fout.flush();
        fin.close();
        fout.close();
    }
    private static void levelOneEncryption(byte buffer[],int len,String key){
        int keyLength = key.length();
        for(int i = 0;i<len;i++){
            int data = (int)buffer[i] & 255;
            data = data ^ (int)key.charAt(i%keyLength);
            buffer[i] = (byte)data;
        }
    }
    private static void levelTwoEncryption(byte buffer[],int len,String key){
        int color[] = ColorHandler.getColor(key);
        for(int i = 0;i<len;i++){
            int data = (int)buffer[i] & 255;
            int r = getHigherNibble(data);
            int c = getLowerNibble(data);
            data = (color[i% color.length] + r * 16 + c)%256;
            buffer[i] = (byte)data;
        }
    }
    private static int getHigherNibble(int data){
        return (data & 240) >> 4;
    }
    private static int getLowerNibble(int data){
        return (data & 15);
    }
}
