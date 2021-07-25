/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.ugb.ipsl.exercice1;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource.PSpecified;


/**
 *
 * @author celo
 */

public class Question3 {
    
    //Ici il s'agit de donner un chiffrement RSA-OAEP et le déchiffrement correspondant
    
    //Le chiffement correspondant
    
     public static String encrypt(String publicKeyString, String message, boolean usingOAEP)
      throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
    X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(
        Base64.getDecoder().decode(publicKeyString));
    RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA")
        .generatePublic(publicKeySpec);
    Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-256ANDMGF1PADDING");
    if (usingOAEP) {
      OAEPParameterSpec oaepParameterSpec = new OAEPParameterSpec("SHA-256", "MGF1",
          MGF1ParameterSpec.SHA256, PSpecified.DEFAULT);
      cipher.init(Cipher.ENCRYPT_MODE, pubKey, oaepParameterSpec);
    } else {
      cipher.init(Cipher.ENCRYPT_MODE, pubKey);
    }
    byte[] encrypted = cipher.doFinal(message.getBytes());
    return new String(Base64.getEncoder().encode(encrypted));
    }


    public static String decrypt(String privateKeyString, String cipherText, boolean usingOAEP)
      throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(
            Base64.getDecoder().decode(privateKeyString));
            PrivateKey priKey = KeyFactory.getInstance("RSA").generatePrivate(privateKeySpec);
            Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-256ANDMGF1PADDING");
            if (usingOAEP) {
            OAEPParameterSpec oaepParameterSpec = new OAEPParameterSpec("SHA-256", "MGF1",
            MGF1ParameterSpec.SHA256, PSpecified.DEFAULT);
            cipher.init(Cipher.DECRYPT_MODE, priKey, oaepParameterSpec);
            } else {
            cipher.init(Cipher.DECRYPT_MODE, priKey);
            }

            byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(cipherText.getBytes()));
            return new String(decrypted);
        }

    public static KeyPair generateKeyPair(int keySize) throws NoSuchAlgorithmException {
    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
    keyPairGenerator.initialize(keySize);
    KeyPair keyPair = keyPairGenerator.generateKeyPair();
    return keyPair;
  }
  
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        KeyPair kp = kpg.genKeyPair();
        Key publicKey = kp.getPublic();
        Key privateKey = kp.getPrivate();
        String publicKeyString = new String(Base64.getEncoder().encode(publicKey.getEncoded()));
        String privateKeyString = new String(Base64.getEncoder().encode(privateKey.getEncoded()));
        
        //Exemple de message à crypter
        
        String message = "Hello";
        
        //Chiffrement du message
        String cipherText = encrypt(publicKeyString, message, false);
        //Déchiffrement du message
        String plainText = decrypt(privateKeyString, cipherText, false);
        
        //Affichage du résultat 
    
        System.out.println("le message crypthe"+cipherText);
        System.out.println("le message decrypthe"+plainText );
    }
}



    
    


        