/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;

/**
 *
 * @author LENOVO
 */
public class CheckDriveClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      File[] drives = File.listRoots();
         if (drives != null && drives.length > 0)
	{
         for (File aDrive : drives)
		 {
          System.out.println(aDrive);
           }
     }
	 
	 listFilesAndFilesSubDirectories("F://");
        
    }
    
    public static void listFilesAndFilesSubDirectories(String directoryName){
        

       File directory = new File(directoryName);
        //get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList){
            /*if (file.isFile()){
                System.out.println(file.getAbsolutePath());}
            /* else if (file.isDirectory()){
                listFilesAndFilesSubDirectories(file.getAbsolutePath());
            }*/
             if (file.isDirectory()){
                System.out.println(file.getName());
            }
        }
    }
}
