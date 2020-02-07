


import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.DeleteResult;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.FullAccount;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
public class DemoIntegration {
   private static final String ACCESS_TOKEN = "8G9J9oHzfPAAAAAAAAAAQO4J6Sq3MNBlCtLuI7truuGhxqKnaxkZbaaU5eDJqOYy";
private static DbxClientV2 client;
private static DbxRequestConfig config;
public DemoIntegration() throws DbxException
{
      config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
 client = new DbxClientV2(config, ACCESS_TOKEN);// Get current account info  
        FullAccount account = client.users().getCurrentAccount();
        System.out.println(account.getName().getDisplayName());

}
    public  void  crap(String args[]) throws DbxException, IOException {
        // Create Dropbox client
      
       

        // Get files and folder metadata from Dropbox root directory
        ListFolderResult result = client.files().listFolder("/some docs");
        while (true) {
            for (Metadata metadata : result.getEntries()) {
                System.out.println(metadata.getPathLower());
            }

            if (!result.getHasMore()) {
                break;
            }

            result = client.files().listFolderContinue(result.getCursor());
        }

        // Upload "test.txt" to Dropbox
        try (InputStream in = new FileInputStream("Hidemo.txt")) {
            FileMetadata metadata = client.files().uploadBuilder("/Hidemo.txt")
                .uploadAndFinish(in);
            JOptionPane.showMessageDialog(null, "Done Successfully");
        }catch(Exception e){e.getMessage();}
    
    /* -------------------------------------------------------------------------------------------------------------------------------------- */
    /*----------------------------------------------------------- File Download Code Below-------------------------------------------------*/
 
    
    
    } 
    
    public ArrayList listfiles() throws DbxException
    {ArrayList al = new ArrayList();
      ListFolderResult result = client.files().listFolder("/CodeSolve");
      if(result == null){return al;}
      else{
        while (true) {
            for (Metadata metadata : result.getEntries()) {
              // System.out.println(metadata.getPathLower());
                al.add(metadata.getPathLower());
            }

            if (!result.getHasMore()) {
                break;
            }

            result = client.files().listFolderContinue(result.getCursor());
        }
       return al;
      }
    
    }
    void download(String s)
    {
        try
            {
                //output file for download --> storage location on local system to download file
                OutputStream downloadFile = new FileOutputStream("F:\\CoretoCrustdata\\"+s);
                System.out.println("F:\\CoretoCrustdata"+s);
                try
                {
                FileMetadata metadata = client.files().downloadBuilder("/CodeSolve/"+s)
                        .download(downloadFile);
                if(metadata==null){JOptionPane.showMessageDialog(null,"NO Such File in the Cloud");}
                // 
                else{ JOptionPane.showMessageDialog(null,"Succesfully downloaded Opening...");}
                }
                finally
                {
                    downloadFile.close();
                }
            }
            //exception handled
            catch (DbxException e)
            {
                //error downloading file
                JOptionPane.showMessageDialog(null, "Unable to download file to local system\n Error: " + e);
            }
            catch (IOException e)
            {
                //error downloading file
                JOptionPane.showMessageDialog(null, "Unable to download file to local system\n Error: " + e);
            }
    
    
    
    }
   public void openFile(String filename) throws IOException
    {StringBuilder sb = null;
        String st =filename.substring(filename.lastIndexOf('.'));
        System.out.println(st);
    if(st.equalsIgnoreCase(".java")==false)
    {
        download(filename);
    Desktop dk = Desktop.getDesktop();
      if(Desktop.isDesktopSupported())
      {
          dk.open(new File("F:\\CoretoCrustdata\\"+filename));
      }
      else{JOptionPane.showMessageDialog(null,"Unable to Open this File");}
    }else{
    download(filename);
    Desktop dk = Desktop.getDesktop();
      if(Desktop.isDesktopSupported())
      {
          dk.open(new File("F:\\CoretoCrustdata\\"+filename));
      }
      else{JOptionPane.showMessageDialog(null,"Unable to Open this File");}
    
    }
    
    }
   public void makeDir()
   {
		File dir = new File("F:/CoretoCrustdata");
		
		//method 'mkdir' to create directroy and result
		//is getting stored in 'isDirectoryCreated'
		//which will be either 'true' or 'false'
		boolean isDirectoryCreated = dir.mkdir();

		//displaying results
		if(isDirectoryCreated)
			System.out.println("Directory successfully created: " + dir);
		else
			System.out.println("Directory was not created successfully: " + dir);   
   }
   void fuckinUpload(File f)
   {
   try (InputStream in = new FileInputStream(f)) {
            FileMetadata metadata = client.files().uploadBuilder("/CodeSolve/"+f.getName())
                .uploadAndFinish(in);
               if(metadata==null){JOptionPane.showMessageDialog(null,"NO Such File in the Directory");}
                // 
                else{ JOptionPane.showMessageDialog(null,"Succesfully Uploaded click view to see");}
        }catch(Exception e){e.getMessage();}
   
   }
   void delete(String name) throws DbxException
   {
   DeleteResult ds = client.files().deleteV2("/CodeSolve/"+name);
      if(ds==null){JOptionPane.showMessageDialog(null,"NO Such File in the Cloud");}
                // 
                else{ JOptionPane.showMessageDialog(null,"Succesfully deleted from the cloud");}
   }

   
}
