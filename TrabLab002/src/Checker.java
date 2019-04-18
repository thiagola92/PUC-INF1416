
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;

public class Checker {
	
	private Path informationPath;
	private MessageDigest messageDigest;
	
	private ArrayList<String> filesPath;					// Caminhos passados por argumentos
	private ArrayList<String[]> filesInformationFromList;	// Informação dos arquivos na lista
	
	private enum Status {
		UNKNOW,
		OK,
		NOT_OK,
		NOT_FOUND,
		COLLISION,
	}
	
	public void setDigestType(String type) throws Exception {
		messageDigest = MessageDigest.getInstance(type);
		
		System.out.println("Tipo do digest: " + type);
		System.out.println("Provider: " + messageDigest.getProvider());
		System.out.println("//////////////////////////////////");
	}
	
	public void setFilePath(String path) throws Exception {
		informationPath = Paths.get(path);
		filesInformationFromList = new ArrayList<String[]>();
		
		Files.readAllLines(informationPath).forEach(lineInformation -> {
			filesInformationFromList.add(lineInformation.split(" "));
		});
		
		System.out.println("Arquivo com as informações dos digests: " + path);
		System.out.println("Caminho com do arquivo: " + informationPath.toAbsolutePath());
		System.out.println("\nCada linha do arquivo como Array: ");
		filesInformationFromList.forEach(lineInformation -> {
			System.out.println(Arrays.toString(lineInformation));
		});
		System.out.println("//////////////////////////////////");
	}
	
	public void setFilesList(ArrayList<String> fileList) {
		this.filesPath = fileList;
		
		System.out.println("Arquivos passados como argumentos: ");
		fileList.forEach(fileName -> {
			System.out.println(fileName);
		});
		System.out.println("//////////////////////////////////");
	}
	
	private String convertByteToString(byte[] fileDigest) {
		String digest = "";
		
		for(int i = 0; i < fileDigest.length; i++)
			digest = digest + String.format("%02X", fileDigest[i]);
		
		return digest;
	}
	
	private String calculateDigestFromFile(Path filePath) throws Exception {
		
		if(Files.exists(filePath) == false) {
			System.err.print("FILE DOESN'T EXIST, EXITING \n");
			System.exit(2);
		}
		
		byte[] fileBytes = Files.readAllBytes(filePath);
		messageDigest.update(fileBytes, 0, fileBytes.length);
		fileBytes = messageDigest.digest();
		
		return convertByteToString(fileBytes);
	}
	
	private String getDigestFromInformationFile(int index) throws Exception {
		String[] information = filesInformationFromList.get(index);
		for(int i = 1; i < information.length; i=i+2) {
			String type = information[i];
			
			if(type.equalsIgnoreCase(messageDigest.getAlgorithm()))
				return information[i+1];
		}
		
		return null;
	}
	
	private Status findOutFileStatus(String fileName, String fileDigest) throws Exception {	
		Status status = Status.UNKNOW;
		
		// Confere se algum dos arquivos passados como argumentos tem o digest igual a esse arquivo passado como argumento
		for(int i = 0; i < filesPath.size(); i++) {
			Path path = Paths.get(filesPath.get(i));
			String name = path.getFileName().toString();
			
			if(fileName.equals(name))
				continue;
			
			String digest = calculateDigestFromFile(path);
			
			if(fileDigest.equalsIgnoreCase(digest))
				status = Status.COLLISION;
		}
		
		// Confere se o digest da lista dos digest está igual ao digest atual do arquivo
		// Confere se outro arquivo da lista de digest tem o digest igual ao digest atual do arquivo
		for(int i = 0; i < filesInformationFromList.size(); i++) {
			String name = filesInformationFromList.get(i)[0];
			String digest = getDigestFromInformationFile(i);
			
			if(fileName.equals(name) && status == Status.UNKNOW) {
				if(fileDigest.equalsIgnoreCase(digest))
					status = Status.OK;
				else if(digest != null)
					status = Status.NOT_OK;
			} else if(fileDigest.equalsIgnoreCase(digest))
				status = Status.COLLISION;
		}
		
		if(status == Status.UNKNOW)
			status = Status.NOT_FOUND;
		
		return status;
	}
	
	private void addNewDigestToFileInList(String fileName, String fileDigest) throws Exception {
		
		// Procura o arquivo na lista e adiciona no final das informações dele um novo algoritmo e digest		
		for(int i = 0; i < filesInformationFromList.size(); i++) {
			String name = filesInformationFromList.get(i)[0];
			
			if(name.equals(fileName) == false)
				continue;
			
			String[] oldFileInformation = filesInformationFromList.get(i);
			String[] newFileInformation = new String[oldFileInformation.length + 2];
			
			for(int j = 0; j < oldFileInformation.length; j++)
				newFileInformation[j] = oldFileInformation[j];
			
			newFileInformation[oldFileInformation.length] = messageDigest.getAlgorithm();
			newFileInformation[oldFileInformation.length + 1] = fileDigest;
			
			filesInformationFromList.set(i, newFileInformation);
			
			return;
		}
		
		// Se não encontrou já presente na lista, adiciona na lista
		String[] newFileInformation = new String[3];
		newFileInformation[0] = fileName;
		newFileInformation[1] = messageDigest.getAlgorithm();
		newFileInformation[2] = fileDigest;
		
		filesInformationFromList.add(newFileInformation);
	}
	
	// If new information was add, rewrite the file with the new information
	private void rewriteFileAddingNewInformation() throws Exception {
		StringBuilder text = new StringBuilder();
		
		for(int i = 0; i < filesInformationFromList.size(); i++) {
			String[] info = filesInformationFromList.get(i);
			
			for(int j = 0; j < info.length; j++)
				text.append(info[j] + " ");
			
			text.append("\n");
		}
		
		Files.write(informationPath, text.toString().getBytes(),StandardOpenOption.WRITE);
	}
	
	public void check( ) throws Exception {
		boolean notFound = true;
		
		for(int i = 0; i < filesPath.size(); i++) {
			Path filePath = Paths.get(filesPath.get(i));
			
			String fileName = filePath.getFileName().toString();
			String fileDigest = calculateDigestFromFile(filePath);
			Status status = findOutFileStatus(fileName, fileDigest);
			
			System.out.print(fileName + " ");
			System.out.print(messageDigest.getAlgorithm() + " ");
			System.out.print(fileDigest + " ");
			System.out.print(status + " ");
			
			if(status == Status.NOT_FOUND) {
				addNewDigestToFileInList(fileName, fileDigest);
				notFound = true;
			}
			
			System.out.println();
		}
		
		if(notFound == true)
			rewriteFileAddingNewInformation();
	}

}
