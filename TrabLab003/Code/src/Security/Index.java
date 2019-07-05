package Security;

import Database.Database;

import java.security.Key;

public class Index {

    public static String[][] getIndex(String folderPath) throws Exception {
        String indexPath = folderPath + "/index";
        String indexString = new String(SecurityFile.readFile(indexPath));
        String[] indexLines = indexString.split("\n");
        String[][] indexTable = new String[indexLines.length][];

        for(int i = 0; i < indexLines.length; i++)
            indexTable[i] = indexLines[i].split(" ");

        return indexTable;
    }

}
