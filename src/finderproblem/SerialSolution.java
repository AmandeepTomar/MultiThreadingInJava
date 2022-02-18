package finderproblem;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SerialSolution {

    public void findPattersOnLineNo() throws IOException, InterruptedException {

        String path="sample/";
        String pattern="public";
        File file=new File(path);
        File []files=file.listFiles();
        FinderUtility finder=new FinderUtility();

        for (File file1:files){
           List<Integer> list = finder.findPattern(file1,pattern);
           if (!list.isEmpty()){
               System.out.println(pattern + "; found at " +list+ " in the file - " + file.getName());
           }
        }


    }

}
