package threaddesign.copyFilesData;

import java.io.IOException;

public class CopyFilesDataInSerialProcess {

    public   void copyTwoFilesUsingSerialProcess() throws IOException {

        String srcFile1 = "files/a.txt";
        String sourceFile2 = "files/b.txt";

        String destFile1 = "files/identicaltoa.txt";
        String destFile2 = "files/identicaltob.txt";
        IOFileUtils.copyFile(srcFile1,destFile1);
        System.out.println("copyTwoFilesUsingSerialProcess() : Done");
        IOFileUtils.copyFile(sourceFile2,destFile2);
        System.out.println("copyTwoFilesUsingSerialProcess() : Done");


    }


    public   void copyFileInSerialProcess() throws IOException {
        String srcFile = "files/a.txt";
        String destFile = "files/b.txt";
        IOFileUtils.copyFile(srcFile,destFile);
        System.out.println("copyFileInSerialProcess() : Done");
    }
}
