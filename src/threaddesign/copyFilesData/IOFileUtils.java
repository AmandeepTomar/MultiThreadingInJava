package threaddesign.copyFilesData;

import java.io.*;

public  class IOFileUtils {

    static void copyFile(String sourceFile,String destFile) throws IOException {
        FileInputStream fin=new FileInputStream(sourceFile);
        FileOutputStream fout=new FileOutputStream(destFile);

        copy(fin,fout);

        fin.close();
        fout.close();

    }

     static void copy(FileInputStream sourceFile, FileOutputStream destFile) throws IOException {
        int value=0;
        while ((value=sourceFile.read())!=-1){
            destFile.write(value);
        }
    }

}
