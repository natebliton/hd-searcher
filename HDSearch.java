import java.io.File;
import static java.lang.System.*;

public class HDSearch {
   public static void main(String[] args) {      

      File f = null;
      String[] paths;
            
      try {    
        
        String mainPath = "/Users/blitonn/Documents/GVSU/streams";
         // create new file
         f = new File(mainPath);
                                 
         // array of files and directory
         paths = f.list();
            
         // for each name in the path array
         for(String path:paths) {
            
            if (path.contains(".")) {
                System.out.println("File:\t" + mainPath + "/" + path);
            } else if (isProjectFolder(path)) {
                System.out.println("Project Directory:\t" + mainPath + "/" + path);
                String newPath = mainPath + "/" + path;
                checkFolder(newPath);
            }  
            else {
                System.out.println("Directory:\t" + mainPath + "/" + path);
            }

         }
         
      } catch(Exception e) {
         // if any error occurs
         e.printStackTrace();
      }
   }

   public static void checkFolder(String mainPath) {
    File f = null;
    String[] paths;
          
    try {    
      
      //
       // create new file
       f = new File(mainPath);
                               
       // array of files and directory
       paths = f.list();
          
       // for each name in the path array
       for(String path:paths) {
          
          if (path.contains(".")) {
              System.out.println("File:\t" + mainPath + "/" + path);
          } else if (isProjectFolder(path)) {
              System.out.println("Project Directory:\t" + mainPath + "/" + path);
              String newPath = mainPath + "/" + path;
              checkFolder(newPath);
          }  
          else {
              System.out.println("Directory:\t" + mainPath + "/" + path);
          }

       }
       
    } catch(Exception e) {
       // if any error occurs
       e.printStackTrace();
    }
   }

   private static boolean isProjectFolder(String str) {

        if (countChar(str, '-') > 1) {
            return true;
        } else {
            return false;
        }

   }

   private static int countChar(String str, char c) {
       int count = 0;

       for (int i=0; i < str.length(); i++) {
           if(str.charAt(i) == c) {
               count++;
           }
       }

       return count;

   }
}