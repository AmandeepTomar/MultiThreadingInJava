package threaddesign.copyFilesData;

import java.io.IOException;

public class CopyFilesDataInParallel {

    public void copyDataInParallel(){

        String srcFile1 = "files/a.txt";
        String sourceFile2 = "files/b.txt";

        String destFile1 = "files/identicaltoa.txt";
        String destFile2 = "files/identicaltob.txt";
        Thread t1=new Thread(new MyCopyFilesThread(srcFile1,destFile1));
        Thread t2=new Thread(new MyCopyFilesThread(sourceFile2,destFile2));

        t1.start();
        t2.start();

    }
}


class MyCopyFilesThread implements Runnable{
    private String srcFile;
    private String destFile;

    MyCopyFilesThread(String srcFile,String destFile){
        this.srcFile=srcFile;
        this.destFile=destFile;
    }

    @Override
    public void run() {
        try {
            IOFileUtils.copyFile(srcFile, destFile);
            System.out.println("Done on thread "+Thread.currentThread().getName()+" source "+srcFile+" Dest "+destFile);
        }catch (IOException e){
            System.out.println(e);
        }
    }
}



