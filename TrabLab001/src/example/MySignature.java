package example;

import java.security.*;
import java.util.Arrays;

import javax.crypto.Cipher;

public class MySignature {
	
	private MessageDigest messageDigest;
	private Cipher cipher;
	
	protected MySignature(String algorithm) throws Exception {
		String[] algorithms = algorithm.toLowerCase().split("with");
		
		if(algorithms.length != 2) {
			System.err.print("\nYou need to inform the algorithm from Digest and Asymetric Key Cypher, example: \"MD5WithRSA\"");
			System.err.print("\nThis way you use MD5 and RSA algorithm");
			System.exit(1);
		}
		
		System.out.print("\nAlgorithm 0: " + algorithms[0]);
		System.out.print("\nAlgorithm 1: " + algorithms[1]);
		
		messageDigest = MessageDigest.getInstance(algorithms[0]);
		cipher = Cipher.getInstance(algorithms[1] + "/ECB/PKCS1Padding");
		
		System.out.print("\nProvider: " + messageDigest.getProvider().getInfo());
	}
	
	public static MySignature getInstance(String algorithm) throws Exception {
		MySignature mySignature = new MySignature(algorithm);
		
		return mySignature;
	}

	public void initSign(PrivateKey privateKey) throws Exception {
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
	}

	public void initVerify(PublicKey publicKey) throws Exception {
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
	}

	public void update(byte[] data) {
		messageDigest.update(data);
	}

	public boolean verify(byte[] signature) throws Exception {
		byte[] digest = messageDigest.digest();
		byte[] signatureDigest = cipher.doFinal(signature);

		System.out.print("\nDigest obtained from Signature: ");
		for(int i = 0; i != digest.length; i++)
			System.out.print(String.format("%02X", digest[i]));
		
		if(Arrays.equals(signatureDigest, digest))
			return true;
		
		return false;
	}

	public byte[] sign() throws Exception {
		byte[] digest = messageDigest.digest();
		
		System.out.print("\nDigest: ");
		for(int i = 0; i != digest.length; i++)
			System.out.print(String.format("%02X", digest[i]));
		
		byte[] cipherDigest = cipher.doFinal(digest);
		return cipherDigest;
	}
}
