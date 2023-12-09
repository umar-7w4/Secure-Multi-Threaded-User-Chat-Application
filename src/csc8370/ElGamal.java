package csc8370;
import java.math.BigInteger;
import java.security.SecureRandom;
    

public class ElGamal {
   private final static int len = 4096; // key length
   private BigInteger priKey;
   private BigInteger pubKey;
   private BigInteger mod;

   public ElGamal() {
	  SecureRandom random = new SecureRandom();
      BigInteger i = BigInteger.probablePrime(len/2, random);
      BigInteger j = BigInteger.probablePrime(len/2, random);
      BigInteger phi = (i.subtract(BigInteger.ONE)).multiply(j.subtract(BigInteger.ONE));
      mod = i.multiply(j);                                  
      pubKey  = new BigInteger("65537");
      priKey = pubKey.modInverse(phi);
   }


   public String encrypt(String msg, String mod, String pubKey){
	   BigInteger original = new BigInteger(msg.getBytes());
	   BigInteger encrypted = original.modPow(new BigInteger(pubKey), new BigInteger(mod));
	   return new String(encrypted.toString());
   }

   public String decrypt(String msg){
	   BigInteger encrypted = new BigInteger(msg);
	   BigInteger original = encrypted.modPow(priKey, mod);
	   return new String(original.toByteArray()); 
   }

   public String getPublicKey(){
	   return pubKey.toString();
   }
   
   public String getModulus(){
	   return mod.toString();
   }
}
