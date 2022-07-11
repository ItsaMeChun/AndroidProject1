package MD5;

import java.math.BigInteger;
import java.security.MessageDigest;

public class hashmd5 {
    public static String md5hashing(String text)
    {   String hashtext = null;
        try
        {
            String plaintext = text;
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(plaintext.getBytes());
            byte[] digest = m.digest();
            BigInteger bigInt = new BigInteger(1,digest);
            hashtext = bigInt.toString(16);
            while(hashtext.length() < 32 ){
                hashtext = "0"+hashtext;
            }
        } catch (Exception e1)
        {
            //JOptionPane.showMessageDialog(null,e1.getClass().getName() + ": " + e1.getMessage());
            System.out.println(e1.getMessage());
        }
        return hashtext;
    }
}
