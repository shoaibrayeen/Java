package encryption;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.springframework.stereotype.Component;

@Component
public class RSAUtil {

  protected static String DEFAULT_ENCRYPTION_ALGORITHM = "RSA";

  protected static int DEFAULT_ENCRYPTION_KEY_LENGTH = 1024;

  protected static String DEFAULT_TRANSFORMATION = "RSA/ECB/PKCS1Padding";

  protected PrivateKey mPrivateKey;

  protected PublicKey mPublicKey;

  public static PublicKey getPublicKey(String base64PublicKey) {
    PublicKey publicKey = null;
    try {
      X509EncodedKeySpec keySpec =
          new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey.getBytes()));
      KeyFactory keyFactory = KeyFactory.getInstance(DEFAULT_ENCRYPTION_ALGORITHM);
      publicKey = keyFactory.generatePublic(keySpec);
      return publicKey;
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (InvalidKeySpecException e) {
      e.printStackTrace();
    }
    return publicKey;
  }

  public static PrivateKey getPrivateKey(String base64PrivateKey) {
    PrivateKey privateKey = null;
    PKCS8EncodedKeySpec keySpec =
        new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64PrivateKey.getBytes()));
    KeyFactory keyFactory = null;
    try {
      keyFactory = KeyFactory.getInstance(DEFAULT_ENCRYPTION_ALGORITHM);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    try {
      privateKey = keyFactory.generatePrivate(keySpec);
    } catch (InvalidKeySpecException e) {
      e.printStackTrace();
    }
    return privateKey;
  }

  public static String encrypt(String data, PrivateKey privateKey)
      throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
          IllegalBlockSizeException, BadPaddingException {
    Cipher cipher = Cipher.getInstance(DEFAULT_TRANSFORMATION);
    cipher.init(Cipher.ENCRYPT_MODE, privateKey);
    return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
  }

  public static String encrypt(String data, PublicKey publicKey)
      throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
          IllegalBlockSizeException, BadPaddingException {
    Cipher cipher = Cipher.getInstance(DEFAULT_TRANSFORMATION);
    cipher.init(Cipher.ENCRYPT_MODE, publicKey);
    return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
  }

  public static String decrypt(String data, PrivateKey privateKey)
      throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
          IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
    Cipher cipher = Cipher.getInstance(DEFAULT_TRANSFORMATION);
    cipher.init(Cipher.DECRYPT_MODE, privateKey);

    return new String(cipher.doFinal(Base64.getDecoder().decode(data)), "UTF-8");
  }

  public static String decrypt(String data, PublicKey publicKey)
      throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
          IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
    Cipher cipher = Cipher.getInstance(DEFAULT_TRANSFORMATION);
    cipher.init(Cipher.DECRYPT_MODE, publicKey);
    return new String(cipher.doFinal(Base64.getDecoder().decode(data)), "UTF-8");
  }

  public static String encrypt(String data, String publicKey)
      throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException,
          NoSuchPaddingException, NoSuchAlgorithmException {
    Cipher cipher = Cipher.getInstance(DEFAULT_TRANSFORMATION);
    cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
    return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
  }

  public static String decrypt(byte[] data, PrivateKey privateKey)
      throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException,
          BadPaddingException, IllegalBlockSizeException {
    Cipher cipher = Cipher.getInstance(DEFAULT_TRANSFORMATION);
    cipher.init(Cipher.DECRYPT_MODE, privateKey);
    return new String(cipher.doFinal(data));
  }

  public static String decrypt(String data, String base64PrivateKey)
      throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException,
          NoSuchAlgorithmException, NoSuchPaddingException {
    return decrypt(Base64.getDecoder().decode(data.getBytes()), getPrivateKey(base64PrivateKey));
  }

  public KeyPair generateKeyPair(String algorithm, int encryptionKeyLengh)
      throws NoSuchAlgorithmException {

    KeyPairGenerator kpg = KeyPairGenerator.getInstance(algorithm);
    kpg.initialize(encryptionKeyLengh);

    return kpg.generateKeyPair();
  }

  public KeyPair generateKeyPair() throws NoSuchAlgorithmException {
    return generateKeyPair(DEFAULT_ENCRYPTION_ALGORITHM, DEFAULT_ENCRYPTION_KEY_LENGTH);
  }

  public byte[] getPrivateKeyAsByteArray(KeyPair keyPair) {
    return keyPair.getPrivate().getEncoded();
  }

  public String getEncodedPrivateKey(KeyPair keyPair) {

    return Base64.getEncoder().encodeToString(getPrivateKeyAsByteArray(keyPair));
  }

  public byte[] getPublicKeyAsByteArray(KeyPair keyPair) {
    return keyPair.getPublic().getEncoded();
  }

  public String getEncodedPublicKey(KeyPair keyPair) {

    return Base64.getEncoder().encodeToString(getPublicKeyAsByteArray(keyPair));
  }

  public static void main(String[] args)
      throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException,
          BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException,
          UnsupportedEncodingException {
    RSAUtil rsaUtil = new RSAUtil();
    KeyPair keyPair = rsaUtil.generateKeyPair();

    String privateKey = rsaUtil.getEncodedPrivateKey(keyPair);
    String publicKey = rsaUtil.getEncodedPublicKey(keyPair);
    System.out.println("Private Key\t:\t" + privateKey);
    System.out.println("Public Key\t:\t" + publicKey);

    // using encoded public key for encryption and encoded private key for decryption
    String data = rsaUtil.encrypt("Testing Encryption - 1", publicKey);
    System.out.println("\n\nEncrypted String\t:\t" + data);
    System.out.println("Decrypted String\t:\t" + rsaUtil.decrypt(data, privateKey));

    // using public key for decryption and private key for encryption
    data = rsaUtil.encrypt("Testing Encryption - 2", getPrivateKey(privateKey));
    System.out.println("\n\nEncrypted String\t:\t" + data);
    System.out.println("Decrypted String\t:\t" + rsaUtil.decrypt(data, getPublicKey(publicKey)));

    // using public key for encryption and private key for decryption
    data = rsaUtil.encrypt("Testing Encryption - 3", getPublicKey(publicKey));
    System.out.println("\n\nEncrypted String\t:\t" + data);
    System.out.println("Decrypted String\t:\t" + rsaUtil.decrypt(data, getPrivateKey(privateKey)));
  }
}
