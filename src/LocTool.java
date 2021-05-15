import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

	public class LocTool {
	    private static MessageDigest mD;
	    static {
	        try {
	            mD = MessageDigest.getInstance("SHA-512");
	        	} 
	        catch (NoSuchAlgorithmException e) 
	        {
	            throw new RuntimeException("initialization of SHA-512 failed", e);
	        }
	    }

	    public static void DistinctFiles(Map<String, List<String>> ListofFiles, File directory) 
	    {
	        for (File subDir : directory.listFiles()) {
	            // Loop through all the sub-directories
	            if (!subDir.isDirectory()) 
	            {
	            	try {
	            		int LengthofFile=(int) subDir.length();
	                    FileInputStream fip =new FileInputStream(subDir);
	                    byte fileData[] = new byte[LengthofFile];
	                    fip.read(fileData);
	                    fip.close();
	                    
	                    // Generate hash that is unique
	                    
	                    String hash = new BigInteger(1, mD.digest(fileData)).toString(16);
	                    List<String> dupFiles = ListofFiles.get(hash);
	                    if (dupFiles == null) 
	                    {
	                        dupFiles = new LinkedList<String>();
	                    }
	                    
	                    
	                    // Store hash with files in list
	                    dupFiles.add(subDir.getAbsolutePath());
	                    // push updated list to Hash table
	                    ListofFiles.put(hash, dupFiles);
	                } 
	            	catch (IOException e) 
	            	{
	                    throw new RuntimeException(" Read error File" + subDir.getAbsolutePath(), e);
	                }
	            }
	            else
	            {
	            	   DistinctFiles(ListofFiles, subDir);
	            }
	        }
	    }
	    
	    public static void LineCount(List<String> dlist)
	    {
	    	int totalLine=0,blankLine=0,commentLine=0,codeLine=0;
	    	for(String file : dlist)
	    	{
	    		BufferedReader reader;
	    		try {
	    			reader = new BufferedReader(new FileReader(file));
	    			String line = reader.readLine();
	    			while (line != null) {
	    				totalLine++;
	    				if (line.trim().isEmpty())
	    					blankLine++;
	    				 else if(line.trim().startsWith("//"))
	    				 {
	    					 commentLine++;
	    				 }
	    				 else if(line.trim().startsWith("/*"))
	    				 { 
	    				//	 commentLine++;
							while(!line.trim().endsWith("*/"))
							{
								totalLine++;
								commentLine++;
								line = reader.readLine();
							}
						//	totalLine++;
							commentLine++;
						}
	    					 
	    				line = reader.readLine();
	    			}
	    			reader.close();
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		}
	    	}
	    	System.out.println(blankLine);
	    	System.out.println(commentLine);
	       	System.out.println(totalLine-commentLine-blankLine);
	    	
	    }

	    public static void main(String[] args) {
	        if (args.length < 1) {
	            System.out.println("Please supply a directory path");
	            return;
	        }
	        File dir = new File(args[0]);
	        if (!dir.isDirectory()) {
	            System.out.println("Supplied directory does not exist.");
	            return;
	        }
	        int fcount=0;
	        List<String> dlist = new ArrayList<String>();
	        Map<String, List<String>> lists = new HashMap<String, List<String>>();
	        LocTool.DistinctFiles(lists, dir);
	        for (List<String> list : lists.values()) {
	            if (list.size() == 1) 
	            {
	            	if(list.get(0).endsWith(".java")) 
	            	{
	                dlist.addAll(list);
	            	}
	               // for (String file : list) {
	               //   System.out.println(file);
	                }
	            else if(list.size() > 1)
	            {
	            	if(list.get(0).endsWith(".java")) 
	            	{
	            	dlist.add(list.get(0));
	            	}
	            }
	        }
	        
	    //	for(String file : dlist) {
            System.out.println(dlist.size());
            
         LineCount(dlist);
               //}
        
	    }   
	}

	        
	  

