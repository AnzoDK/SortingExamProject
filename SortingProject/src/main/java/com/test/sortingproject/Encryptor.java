
package com.test.sortingproject;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
/**
 *
 * @author anzo
 */
public class Encryptor {
    public static String EncryptString(String strToEnc)
    {
        return Encryptor.EncryptString(strToEnc, "SHA-512");
    }
    public static String EncryptString(String strToEnc, String algorithm)
    {
        try{
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(strToEnc.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest();
            String hash="";
            StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) 
            {
                String hex = Integer.toHexString(0xff & bytes[i]);
                if(hex.length() == 1)
                {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            hash = hexString.toString();
            return hash;
        }
        catch(NoSuchAlgorithmException e)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Unknown Algorithm Requested!");
            alert.setContentText("The Encryptor failed to use the requested algorithm. Error: " + e.getMessage());
            alert.showAndWait();
            System.exit(1);
            return "";
        }
    }
}
