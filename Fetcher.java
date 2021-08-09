import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Fetcher implements ActionListener
{
    private BrowseButton b;
    private Table t;
    private ArrayList<String[]> tableData;
    private LinkedHashMap<String,String> fileData;
    public Fetcher(BrowseButton bb,Table table)
    {
        b=bb;
        t=table;
    }
     
    private void Compare(ArrayList<String[]> tData,LinkedHashMap<String,String> fData)
    {
        LinkedHashMap<Integer,ArrayList<String[]>> data=new LinkedHashMap<Integer,ArrayList<String[]>>();
        int index=0;
        for(String[] s : tData)
        {
            index++;
            String[] tableContent=s;
            String desc=tableContent[1];
            String los=tableContent[2];
            String joker=tableContent[3];
            String oper=tableContent[4];
            String rech=tableContent[5];
            String type=tableContent[6];
    
            //Make new empty arraylist;
            ArrayList<String[]> a =new ArrayList<String[]>();
            
            boolean multiple=false;
            //Check los
            if(los.equals("")||los==null)
            {
                los=desc.toLowerCase();
            }
            if(rech.toLowerCase().equals("faux"))
            {
                multiple=false;
            }else if(rech.toLowerCase().equals("vrai"))
            {
                multiple=true;
            }
            //Check joker
            //Check oper
            //Check rech
            //Check type
            if(type.toLowerCase().equals("code"))
            {
                type=".java";
            }else if(type.toLowerCase().equals("texte")||type.toLowerCase().equals("text"))
            {
                type=".txt";
            }
            
            Set<String> keys=fData.keySet();
            for (String key : keys) 
            {
                String str=fData.get(key);
                if(key.contains(type))
                {
                    String[] ar=FindString(los,str,multiple,key);
                    if(ar!=null)
                        a.add(ar);
                }
            }
            data.put(index,a);
        }
        Result r = new Result(data);
    }
    private String[] FindString(String inputSearch,String file,boolean rech,String filename)
    {
    int count = 0,countBuffer=0,countLine=0;
    String lineNumber = "";
    Reader inputString = new StringReader(file);
    
    String[] result= new String[5];
    
    String line = "";
    String lines="";
    
    String wordSearched="";
    
        BufferedReader br = new BufferedReader(inputString);
        try {
            while((line = br.readLine()) != null)
            {
                countLine++;
                String[] words = line.split(" ");

                for (String word : words) {
                  if (word.equals(inputSearch)) {
                    if(wordSearched=="")
                    {
                        wordSearched=word;
                    }
                    count++;
                    countBuffer++;
                    lines+=line+"-_-";
                  }
                }

                if(countBuffer > 0)
                {
                    countBuffer = 0;
                    lineNumber += countLine + ",";
                }

            }
            br.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        
        }
    if(count!=0){
    result[0]=filename;
    result[1]=count+"";
    result[2]=lineNumber;
    result[3]=lines;
    result[4]=wordSearched;
    
    return result;
    }
    return null;
}
    public void actionPerformed(ActionEvent ae) {
        try
        {
            tableData=t.fetchData();
            fileData = new LinkedHashMap<String,String>();
            File selected=b.getFile();
            b.getFiles(selected,"",fileData);
            Compare(tableData,fileData);
        }catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
            /*try
            {
                LinkedHashMap<String,String> Data=new LinkedHashMap<String,String>();
                File f=b.getFile();
                b.getFiles(f,"",Data);
                Set<String> keys=Data.keySet();
                for (String key : keys) {
                    System.out.println(key);
                    //System.out.println(Data.get(key));
                    System.out.println("-------------------------------------------");
                }
            }
            catch (java.io.IOException ioe)
            {
                ioe.printStackTrace();
            }*/
         }
}
