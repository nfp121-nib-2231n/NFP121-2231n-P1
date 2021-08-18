import java.util.*;
import java.util.regex.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Fetcher implements ActionListener
{
    // this is the class that compares the table data
    // with the file data and saves the results in a list;
    private BrowseButton b;
    private Table t;
    private ArrayList<String[]> tableData;
    private LinkedHashMap<String,String> fileData;
    private StringEscaper se;
    
    public Fetcher(BrowseButton bb,Table table)
    {
        b=bb;
        t=table;
        se=new StringEscaper();
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
            
            if(los.equals("")||los==null)
            {
                los=desc.toLowerCase();
            }
            
            //Make new empty arraylist;
            ArrayList<String[]> a =new ArrayList<String[]>();
            
            boolean multiple=false;
            //Check los
            
            //Check joker
            String[] losParts=los.split(joker);
            String ptn="";
            if(joker.equals("")||joker==null)
            {
                
            }else
            {
                if(los.contains(joker))
                {
                    String[] parts=los.split(joker);
                    
                    if(parts!=null)
                    {
                        for(int x=0;x<parts.length;x++)
                        {
                            if(x==parts.length-1)
                            {
                                ptn+=se.EscapeString(parts[x]);
                            }else
                            {
                                ptn+=se.EscapeString(parts[x])+"[^"+se.EscapeString(parts[parts.length-1])+"]*";
                            }
                        }
                    }
                }
            }
            joker=ptn;

            //Check oper
            //Check rech
            if(rech.toLowerCase().equals("faux"))
            {
                multiple=false;
            }else if(rech.toLowerCase().equals("vrai"))
            {
                multiple=true;
            }
            //Check type
            if(type.toLowerCase().equals("code"))
            {
                type=".java";
            }else if(type.toLowerCase().equals("texte"))
            {
                type=".txt";
            }else
            {
                type="";
            }
            
            Set<String> keys=fData.keySet();
            for (String key : keys) 
            {
                String str=fData.get(key);
                if(key.contains(type))
                {
                    String[] ar=FindString(los,str,multiple,key,joker,losParts);
                    if(ar!=null)
                        a.add(ar);
                }
            }
            data.put(index,a);
        }
        Result r = new Result(data);
    }
    private String[] FindString(String inputSearch,String file,boolean rech,String filename,String joker,String[] losParts)
    {
    int count = 0,countBuffer=0,countLine=0;
    String lineNumber = "";
    Reader inputString = new StringReader(file);
    
    String[] result= new String[6];
    
    String line = "";
    String lines="";
    String linesEnd="";
    String containsJoker="false";
    
    String wordSearched="";
    BufferedReader br= new BufferedReader(inputString);
    
        try {
            String[] splitLos= inputSearch.split(",");
            for(int i=0;i<splitLos.length;i++)
            {
                inputString.reset();
                br = new BufferedReader(inputString);
                if(joker.equals(""))
                {
                    while((line = br.readLine()) != null)
                    {
                        countLine++;
                        String[] words = line.split(" ");
                        for (String word : words) 
                        {
                              
                                  if (word.contains(splitLos[i])) 
                                  {
                                    wordSearched+=splitLos[i]+"-_-";
                                    count++;
                                    countBuffer++;
                                    if(lines.contains(line))
                                    {
                                    
                                    }else
                                    {
                                    lines+=line+" "+"-_-";
                                    }  
                                  
                        }
                    }
        
                        if(countBuffer > 0)
                        {
                            countBuffer = 0;
                            lineNumber += countLine + ",";
                        }
    
                    }
                }
                else
                {
                    Pattern p =Pattern.compile(joker,Pattern.DOTALL);  
                    System.out.println(joker);
                    String currentText="";
                    containsJoker="true";
                    while((line=br.readLine())!=null)
                    {
                        currentText+=line+"\n";
                        countLine++;
                        if(losParts!=null)
                        {
                            String firstPart=losParts[0];
                            if(firstPart!="")
                            {
                                if(currentText.contains(firstPart))
                                {
                                    Matcher m=p.matcher(currentText);
                                    //System.out.println(currentText);
                                    if(m.find()){
                                        //this will reset the matcher to start again from 0
                                        m.reset();
                                        while(m.find())
                                        {
                                            if(lines.contains(currentText))
                                            {
                                            }else
                                            {
                                                lines+=currentText+" "+"-_-";
                                            }
                                            lineNumber+=countLine+",";
                                            count++;
                                            String word=currentText.substring(m.start(),m.end());
                                            word=se.EscapeString(word);
                                            wordSearched+=word+"-";
                                        }
                                        wordSearched+="-_-";
                                        currentText="";  
                                    }else
                                    {
                                    
                                    }
                                     
                                }else
                                {
                                    currentText="";
                                }
                            }
                        }
                    }
            }
                /*jkjnkn
                while((line = br.readLine()) != null)
                {
                    currentText+=line+"\n";
                    Matcher m=p.matcher(currentText);
                    countLine++;
                    while(m.find())
                    {
                        lines+=currentText+" "+"-_-";
                        lineNumber+=m.start();
                        linesEnd+=countLine+",";
                        wordSearched=currentText.substring(m.start(),m.end());
                        currentText="";
                        count++;
                        countBuffer++;
                    }
                    
                    if(countBuffer > 0)
                    {
                        countBuffer = 0;
                        //lineNumber += countLine + ",";
                    }
                }*/
            }
            
        
    
    br.close();
    } catch (IOException e) 
    {
        e.printStackTrace();
        
    }
    
    //System.out.println("count "+count);
    if(count!=0)
    {
        result[0]=filename;
        result[1]=count+"";
        result[2]=lineNumber;
        result[3]=lines;
        result[4]=wordSearched;
        result[5]=containsJoker;
        
        if(rech==true)
        {
            if(count>1)
                return result;
        }else
        {
            if(count==1)
                return result;
        }
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
    }
}
