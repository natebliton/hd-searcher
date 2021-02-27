import java.io.File;
import static java.lang.System.*;

public class TestStrings {

    public static void main(String[] args) {      

        File f = null;
        String[] paths;
            
        try {    
            //String mainPath = "/Users/blitonn/Documents/GVSU/streams";
            String mainPath = args[0];

            // create new file
            f = new File(mainPath);

            // array of files and directories in root directory
            paths = f.list();

            // for each name in the path array
            for(String path:paths) {
                System.out.println(path + " (original)");
                path = path.replace('"', '\"');
                System.out.println(path + " (updated)");
            }
        } catch(Exception e) {
            // if any error occurs
            e.printStackTrace();
        }
    }
}