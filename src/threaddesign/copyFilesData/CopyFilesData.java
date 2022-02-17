package threaddesign.copyFilesData;

import java.io.IOException;

public class CopyFilesData {

    public static void main(String[] args) throws IOException {
        CopyFilesDataInSerialProcess obj1 = new CopyFilesDataInSerialProcess();
        obj1.copyFileInSerialProcess();
        obj1.copyTwoFilesUsingSerialProcess();


        CopyFilesDataInParallel obj2=new CopyFilesDataInParallel();
        // this will execute on Thread
        obj2.copyDataInParallel();


        // here we are using ExecutorService
        UsingExecutorService obj3=new UsingExecutorService();
        obj3.executeTaskUsingExecutorService();


    }


}



