/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.ugb.ipsl.exercice1;

import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.Scanner;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
/**
 *
 * @author celo
 */
public class Question2 {
    public static void main(String[] args) throws FileNotFoundException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        KeyPairGenerator Gen = KeyPairGenerator.getInstance("RSA");
        //Initialisation 
        Gen.initialize(2048);
        //Génération des clés
        KeyPair pair = Gen.generateKeyPair();
        //Recupération de la clé privée
        PrivateKey privateKey = pair.getPrivate();
        //Recupération de la clé publique
        PublicKey publicKey = pair.getPublic();
        
        System.out.println("La clé publique est :"+publicKey);
        System.out.println("La clé privée est :"+privateKey);
        
        //Début du chiffrement
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir le message à crypter");
        String secretMessage = sc.nextLine();
        
        System.out.println("Le message initial est:"+secretMessage);
        //Chiffement de la clé publique
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        
       
        byte[] secretMessageBytes = secretMessage.getBytes(StandardCharsets.UTF_8);
        byte[] encryptedMessageBytes = encryptCipher.doFinal(secretMessageBytes);
        System.out.println("Message secret en byte:" + secretMessageBytes);
        System.out.println("Message crypté en byte:"+ encryptedMessageBytes);
        
        //Encodage
        String encodedMessage = Base64.getEncoder().encodeToString(encryptedMessageBytes);
        System.out.println("Encodage dans la base 64 :"+ encodedMessage);
        //Fin du chiffrement
        
        //Début de d"chiffrement
        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        
        byte[] decryptedMessageBytes = decryptCipher.doFinal(encryptedMessageBytes);
        String decryptedMessage = new String(decryptedMessageBytes, StandardCharsets.UTF_8);
        //Résultat
        System.out.println("Le message déchiffré est :"+decryptedMessage);
    }
}