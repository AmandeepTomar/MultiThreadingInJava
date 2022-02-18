package finderproblem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FinderUtility {

    public List<Integer> findPattern(File file,String pattern) throws InterruptedException {
        int lineNo=1;
        String line;
        List<Integer> lineList=new ArrayList<>();
        try(BufferedReader br=new BufferedReader(new FileReader(file))) {
            while ((line=br.readLine())!=null){
                if (!line.isEmpty()){
                    lineList.add(lineNo);
                }
                lineNo++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Thread.sleep(1000); // for demo purpose.

        return lineList;
    }
}
