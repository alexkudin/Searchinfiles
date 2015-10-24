package searchinfiles;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Iterator;

public class SearchInFiles 
{
    
    public static String getString()
    {        
        String              S   = "";
        try
        {
            LineNumberReader    L = new LineNumberReader(new InputStreamReader(System.in, "CP1251"));
            S   = L.readLine();
        }
        catch (Exception ioe) 
        {
            S   = "";
        }
        return  S;
    }
    public static int getInt()
    {
        try
        {
            return Integer.parseInt(getString());
        }
        catch (Exception ie) 
        {
            return 0;
        }
    }
    public static void main(String[] args)
    {
        
        //=== Enter SOURSE PATH Of Files =======================================
        
        //System.out.println("Enter Sourse Path of Files : ");
        //String  soursePath  = getString();
        String  soursePath  = "D:/1/" ; 
        File    srcDir      = new File (soursePath);
        if(srcDir.exists())
        {
            if (srcDir.isDirectory() == false)
            {
                System.out.println("This Path not To Directory - this Path to File ! ");
                System.exit(0);
            }
        }
        
        //=== Enter DESTINATION PATH Of Files ==================================
        
//        System.out.println("Enter Destination directory : ");
//        String  dstDirName  = getString();
        String  dstDirName  = "E:/2/";
        File    dstDir      = new File (dstDirName);
        if(dstDir.exists())
        {
            if (dstDir.isDirectory() == false)
            {
                System.out.println("This Path not To Directory - this Path to File ! ");
                System.exit(0);
            }
        }
        else
        {
            if (dstDir.mkdirs() == false)
            {
                System.out.println("Not Possible Create Destination Directory ");
                System.exit(0);
            }
        }
        
        //=== Sort Files In Sourse Directory for Extension =====================
        
        File        Sort   = new File ("D:/1/");
        String[]    N   = Sort.list(
                                    new FilenameFilter() 
                                    {
                                        @Override
                                        public boolean accept (File dir, String name)
                                        {
                                            if (name.endsWith(".txt"))
                                            {
                                               return true;
                                            }
                                            else
                                            {
                                                return false;
                                             }
                                        }
                                    }    
                                           );
        for (String n : N)    // Create Array N of Text files Sorting by FileFilter
        {
            System.out.println(n);
        }
        
        //== Copyng File from Sourse To Destination Directory ==================
        
//        for(int i = 0 ; i < N.length ; i++)
//        {
//            String F = N[i];
//            try
//            {
//                FileInputStream srcFIS    = new FileInputStream(soursePath.concat(F));
//                FileOutputStream dstFOS = new FileOutputStream(dstDirName.concat(F));
//                byte []                        a = new byte [ 4 * 1024 ];
//                while(srcFIS.available()>0)
//                {
//                    int cnt = srcFIS.read(a , 0 , a.length);
//                    if(cnt == -1)  
//                    {
//                        break;
//                    }
//                    dstFOS.write(a, 0, cnt);
//                }
//                srcFIS.close();
//                dstFOS.close();
//                System.out.println("Well Done !!! Coping executed !!!");
//            }
//            catch(Exception e)
//            {
//                System.out.println("Coping Error : " + e.getMessage());
//            }
//        }
        
        
        //=== Enter KeyWord which You Want to Substitute ==================================
        
//        System.out.println("Enter KeyWord in Files Which must be Substitute : ");
//        String oldWord = getString();
        String KeyWord = "asdfg";
        
        //=== Enter SubsWord For Substitution =============================================
        
//        System.out.println("Enter Word For Substitution : ");
//        String newWord = getString();
        String SubsWord = "java rules!";
        
       //=== SUBSTITUTION =====================================================
        
        File Log = new File("log.txt");
        String log = "Log File :  \r\n";
        
        for(int i = 0; i < N.length; i++)
        {
            File oldFile = new File(soursePath.concat(N[i]));  
            File newFile = new File(dstDirName.concat(N[i]));           
            
        try
        {  
            LineNumberReader LNR = new LineNumberReader(new InputStreamReader (new FileInputStream(oldFile) , "CP1251"));
            
            FileWriter FW = new FileWriter(newFile);            
            FileWriter logWr = new FileWriter(Log);            
            
            int subsCount = 0;            
            
            while(true)
                {          
                    String line = LNR.readLine();
                    
                    if (line == null)
                    {
                        break;
                    }
                    
                    if(line.contains(KeyWord))
                    {
                        line = line.replace(KeyWord,SubsWord);
                        subsCount++;                   
                    }
                    
                    FW.flush();
                    FW.write(line);
                    FW.write("\r\n");
                }
            
            FW.close();
            
            LNR.close();
            
            
             log = log.concat("in file : " + dstDirName.concat(N[i]) + " word " + KeyWord + " was replaced " + subsCount + " times. \r\n" );
             
             logWr.write(log);
             
             logWr.close();                
        }
        
        catch (Exception e) 
        {
            System.out.println("Error345 : " + e.getMessage());
        }
        
        }               // end of cicle FOR
    }    
}
