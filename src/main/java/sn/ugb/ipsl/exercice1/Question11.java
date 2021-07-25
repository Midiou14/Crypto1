/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.ugb.ipsl.exercice1;

import java.util.*;
import java.math.BigInteger;

/**
 *
 * @author celo
 */
public class Question11 {
    public static void main(String[] args) {
        
        //Génération de deux nombres premiers aléatoires.
        Random rand1 = new Random(System.currentTimeMillis());
        Random rand2 = new Random(System.currentTimeMillis()*10);
    
        int pubKey = 5;
        //renvoie un BigInteger qui n'est pas premier
        
        BigInteger bigB_p = BigInteger.probablePrime(2048,rand1);
        BigInteger bigB_q = BigInteger.probablePrime(2048,rand2);
    
        BigInteger bigB_n = bigB_p.multiply(bigB_q);
    
        BigInteger bigB_p_1 = bigB_p.subtract(new BigInteger("1")); //p-1
        BigInteger bigB_q_1 = bigB_q.subtract(new BigInteger("1")); //q-1
        BigInteger bigB_p_1_q_1 = bigB_p_1.multiply(bigB_q_1);      //(p-1)*(q-1)
    
        //Génération d'une clé publique
    
        while (true){
        BigInteger BigB_GCD = bigB_p_1_q_1.gcd(new BigInteger(""+pubKey));
        if (BigB_GCD.equals(BigInteger.ONE)){
            break;
        }
        
        pubKey++;
    } 
     
    BigInteger bigB_pubKey = new BigInteger(""+pubKey);
    BigInteger bigB_prvKey = bigB_pubKey.modInverse(bigB_p_1_q_1);
   
    //Affichage de la clé publique et de la clé privée.
    
    System.out.println(" public key : "+bigB_pubKey);
    System.out.println("private key : "+bigB_prvKey);
    
    }
}
   