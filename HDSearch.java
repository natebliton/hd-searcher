import java.io.File;

public class HDSearch {
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
            
               if (path.contains(".")) {
                  // System.out.println("File:\t" + mainPath + "/" + path);
               } else if (isProjectFolder(path)) 
                // look in project folder 
                {
                   //System.out.println("Project Directory:\t" + mainPath + "/" + path);
                    String newPath = mainPath + "/" + path;
                    checkProjectFolder(newPath);
                }
                else 
                // check folder in case there are project folders inside
                {
                    //System.out.println("Directory:\t" + mainPath + "/" + path);
                    String newPath = mainPath + "/" + path;
                    boolean[] projectFilesPresent = {false, false, false, false, false};
                    checkFolder(newPath, projectFilesPresent);
                }

            }
         
        } catch(Exception e) {
            // if any error occurs
            e.printStackTrace();
        }
    }


    // checks specifically a project folder
    public static void checkProjectFolder(String projectFolderPath) {
        
        // boolean array for project folder: mp3s, bounce folder, pdf, video, upload
        boolean[] projectFilesPresent = {false, false, false, false, false};

        File f = null;
        String[] paths;

        try {    
      
            //
            // create new file
            f = new File(projectFolderPath);
                                     
            // array of files and directory
            paths = f.list();
                
            if(paths != null) {
                // for each name in the path array
                for(String path:paths) {
                    
                    if (path.contains(".")) {

                        // this is a file, so make file checks: mp3s, bounce folder, pdf, video, upload
                        projectFilesPresent = checkFileType(path, projectFilesPresent);
                        
                    } else if (isProjectFolder(path)) 
                    // look in project folder 
                    {
                        //System.out.println("Project Directory:\t" + mainPath + "/" + path);
                        String newPath = projectFolderPath + "/" + path;
                        checkProjectFolder(newPath);
                    }
                    else 
                    // check folder in case there are project folders inside
                    {
                        // this is a folder, so check folder checks: 0 mp3s, 1 bounce folder, 2 pdf, 3 video, 4 upload
                        if (hasBounce(path)) {
                            projectFilesPresent[1] = true; 
                        }
                        if (hasUpload(path)) {
                            projectFilesPresent[4] = true; 
                        }
                        
                        //System.out.println("Directory:\t" + mainPath + "/" + path);
                        String newPath = projectFolderPath + "/" + path;
                        checkFolder(newPath, projectFilesPresent);
                    }
        
                }
            }
            // get string of just project name
            String projectName = "";
            if (projectFolderPath.lastIndexOf("/") > 1) {
                projectName += projectFolderPath.substring(projectFolderPath.lastIndexOf("/") + 1);
            }

            // print report of project folder: directory name, mp3s, bounce folder, pdf, video, upload
            System.out.print(projectName);
            System.out.print('\t');
            System.out.print("mp3: " + booleanToYesNo(projectFilesPresent[0]));
            System.out.print('\t');
            System.out.print("bounce folder: " + booleanToYesNo(projectFilesPresent[1]));
            System.out.print('\t');
            System.out.print("pdf: " + booleanToYesNo(projectFilesPresent[2]));
            System.out.print('\t');
            System.out.print("video file: " + booleanToYesNo(projectFilesPresent[3]));
            System.out.print('\t');
            System.out.print("upload folder: " + booleanToYesNo(projectFilesPresent[4]));
            System.out.print('\t');
            System.out.print("Project Directory: " + projectFolderPath);
            System.out.print('\n');

        } catch(Exception e) {
            // if any error occurs
            e.printStackTrace();
        }


    }

    // checks if a string has the .mp3 extension
    private static boolean hasMp3(String str) {
       if (str.toLowerCase().contains(".mp3")) {
           return true;
       } else {
           return false;
       }
    }

    // checks if this might be a bounce folder
    private static boolean hasBounce(String str) {
        if (str.toLowerCase().contains("bounce")) {
            return true;
        } else {
            return false;
        }
    }

    // checks if this might be an "upload" folder, likely containing tagged mp3s
    private static boolean hasUpload(String str) {
        if (str.toLowerCase().contains("upload")) {
            return true;
        } else {
            return false;
        }
    }
 
    // checks if a string has the .pdf extension
    private static boolean hasPdf(String str) {
        if (str.toLowerCase().contains(".pdf")) {
            return true;
        } else {
            return false;
        }
    }
    
    // checks if this might be a video file
    private static boolean hasVideo(String str) {
        str = str.toLowerCase();
        if (str.contains(".mov") || str.contains(".mp4") || str.contains("avchd") || str.contains("mts")) {
            return true;
        } else {
            return false;
        }
    }

    // check file type and update boolean array
    private static boolean[] checkFileType(String tempPath, boolean[] tempProjectFilesPresent){
        if (hasMp3(tempPath)) {
            tempProjectFilesPresent[0] = true; 
        }
        if (hasPdf(tempPath)) {
            tempProjectFilesPresent[2] = true; 
        }
        if (hasVideo(tempPath)) {
            tempProjectFilesPresent[3] = true; 
        }
        return tempProjectFilesPresent;
    }

    // checks a non-project folder
    public static boolean[] checkFolder(String mainPath, boolean[] projectFilesPresent) {
        File f = null;
        String[] paths;
        
        try {    
        
        
            // create new file
            f = new File(mainPath);
                                
            // array of files and directory
            paths = f.list();
            
            if(paths != null){
                            
                // for each name in the path array
                for(String path:paths) {
                
                    if (path.contains(".")) {
                        // this is a file, so make file checks:
                        // mp3s, bounce folder, pdf, video, upload
                        projectFilesPresent = checkFileType(path, projectFilesPresent);
                        
                        
                    } else if (isProjectFolder(path)) 
                    // look in project folder 
                    {
                        // System.out.println("Project Directory:\t" + mainPath + "/" + path);
                        String newPath = mainPath + "/" + path;
                        checkProjectFolder(newPath);
                    }
                    else 
                    // check folder in case there are project folders inside
                    {
                        // this is a folder, so check folder checks: 
                        // 0 mp3s, 1 bounce folder, 2 pdf, 3 video, 4 upload
                        if (hasBounce(path)) {
                            projectFilesPresent[1] = true; 
                        }
                        if (hasUpload(path)) {
                            projectFilesPresent[4] = true; 
                        }

                        // System.out.println("Directory:\t" + mainPath + "/" + path);
                        String newPath = mainPath + "/" + path;
                        checkFolder(newPath, projectFilesPresent);
                    }

                }
            }
        } catch(Exception e) {
            // if any error occurs
            e.printStackTrace();
        }

        return projectFilesPresent;
    }

    // returns true if folder has 2+ '-' characters
    // as in my nameing scheme "2000-01-14-ThisProjectFolder"
    private static boolean isProjectFolder(String str) {

        if (countChar(str, '-') > 1) {
            return true;
        } else {
            return false;
        }

    }

    // translates boolean into yes or no string
    private static String booleanToYesNo(boolean booleanInput) {
        if(booleanInput) {
            return "yes";
        } else {
            return "";
        }

    }

    // returns the number of times a character occurs in a string
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