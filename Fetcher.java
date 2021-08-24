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
    private Info<TableInfo> tableData;
    private Info<FileInfo> fileData;
    private StringDoctor se;
    
    public Fetcher(BrowseButton bb,Table table)
    {
        b=bb;
        t=table;
        se=new StringDoctor();
    }
     
    private void Compare(Info<TableInfo> tData,Info<FileInfo> fData)
    {
        if(tData!=null)
        {
        int index=0;
        Iterator tIter=tData.createIterator();
        
        //Top level group that holds everything
        SearchInfoComponent all = new SearchInfoGroup(index);
        while(tIter.hasNext())
        {
            index++;
            //Mid level groups that holds multiple SearchInfos related to a passed index
            SearchInfoComponent group = new SearchInfoGroup(index);
            TableInfo currentCell=(TableInfo)tIter.next();
            Iterator fIter=fData.createIterator();
            while(fIter.hasNext())
            {
                FileInfo currentFile=(FileInfo)fIter.next();
                if(currentFile.getName().contains(currentCell.getType()))
                {
                    SearchInfo s=FindString(currentCell,currentFile);
                    if(s!=null)
                        group.add(s);
                }
            }
            all.add(group);
        }
        Result r = new Result(all);
        }
    }
    private SearchInfo FindString(TableInfo ti,FileInfo fi)
    {
        int count = 0,countBuffer=0,countLine=0;
        //Arraylist to store at what lines the words were found
        ArrayList<String> lineNumber=new ArrayList<String>();
        Reader reader = new StringReader(fi.getData());
        
        SearchInfo result;
        //Empty string to store each line in
        String line = "";
        //ArrayList to store found lines in
        ArrayList<String> lines = new ArrayList<String>();
        //boolean that checks if the search contains joker
        boolean containsJoker=false;
        //Arraylist to store the words found in
        ArrayList<ArrayList<String>> wordSearched = new ArrayList<ArrayList<String>>();
        
        String filename=fi.getName();
        
        String[] losParts=ti.getLosParts();
        
        String joker=ti.getJoker();
        
        BufferedReader br= new BufferedReader(reader);
    
        try {
            String[] splitLos= ti.getLos().split(",");
            for(int i=0;i<splitLos.length;i++)
            {
                reader.reset();
                br = new BufferedReader(reader);
                if(joker.equals(""))
                {
                    while((line = br.readLine()) != null)
                    {
                        countLine++;
                        Pattern p = Pattern.compile(se.EscapeString(splitLos[i]));
                        Matcher m = p.matcher(line);
                        
                        if(m.find())
                        {
                            ArrayList<String> temp=new ArrayList<String>();
                            m.reset();
                            while(m.find())
                            {
                                if(lineNumber.contains(countLine+""))
                                {
                                
                                }else
                                {
                                    lines.add(line+" ");
                                    lineNumber.add(countLine+"");
                                }
                                String word=line.substring(m.start(),m.end());
                                temp.add(word);
                                count++;
                            }
                            wordSearched.add(temp);
                        }
                    }
                }
                else
                {
                    Pattern p =Pattern.compile(joker,Pattern.DOTALL);  
                    String currentText="";
                    containsJoker=true;
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
                                    if(m.find())
                                    {
                                        ArrayList<String> temp=new ArrayList<String>();
                                        //this will reset the matcher to start again from 0
                                        m.reset();
                                        while(m.find())
                                        {
                                            if(lineNumber.contains(countLine+""))
                                            {
                                            }else
                                            {
                                                lines.add(se.EscapeHtml(" "+currentText+" "));
                                                lineNumber.add(countLine+"");
                                            }
                                            
                                            count++;
                                            String word=currentText.substring(m.start(),m.end());
                                            word=se.EscapeHtml(se.EscapeString(word));
                                            temp.add(word);
                                        }
                                        wordSearched.add(temp);
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
            }
    br.close();
    } catch (IOException e) 
    {
        e.printStackTrace();
    }
    if(count!=0)
    {
        result=new SearchInfo(filename,count,lineNumber,lines,wordSearched,containsJoker);   
        if(ti.getRech()==true)
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
            ArrayList<FileInfo> fileDataList=new ArrayList<FileInfo>();
            fileData = new Info<FileInfo>(fileDataList);
            File selected=b.getFile();
            b.getFiles(selected,"",fileDataList);
            Compare(tableData,fileData);
        }catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
    }
}
