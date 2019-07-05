package example;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class Main {

	public static void main(String[] args) throws Exception {
		
		if(args.length != 1) {
			System.err.println("Usage: TrabLab001 \"text\"");
			System.exit(1);
		}
		System.out.println("plainText: " + args[0]);

		byte[] plainText = args[0].getBytes("UTF8");
		System.out.print("plainText Hexa: ");
		for(int i = 0; i < plainText.length; i++)
			System.out.print(String.format("%02X", plainText[i]));
		
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		keyGen.initialize(1024);
		KeyPair key = keyGen.generateKeyPair();
		
		MySignature mySignature = MySignature.getInstance("MD5withRSA");
		mySignature.initSign(key.getPrivate());
		mySignature.update(plainText);

		byte[] signature = mySignature.sign();
		System.out.print("\nSignature: ");
		for(int i = 0; i != signature.length; i++)
			System.out.print(String.format("%02x", signature[i]));
		
		mySignature.initVerify(key.getPublic());
		mySignature.update(plainText);
		
		if(mySignature.verify(signature))
			System.out.println("\nAssinatura válida");
		else
			System.err.println("\nAssinatura inválida");
	}

}
