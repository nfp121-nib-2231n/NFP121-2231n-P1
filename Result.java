import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.*;

public class Result
{ 
    private JScrollPane scroll;
    private JFrame frame;
    private JPanel panel;
    private JPanel mainPanel;
    
    private Color grey=new Color(225, 225, 225);
    private Color darkgrey=new Color(127,127,127);
    private Font f = new Font("Lucida Sans Typewriter", Font.PLAIN,16);
    private Font titleFont = new Font("Lucida Sans Typewriter", Font.BOLD,20);
    
    private StringDoctor se;
    public Result(SearchInfoComponent data)
    {
        se=new StringDoctor();
        frame=new JFrame();
        mainPanel = new JPanel();
        panel=new JPanel();
        mainPanel.setLayout(new BorderLayout());
        panel.setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.weightx = 1;
        cons.gridx = 0;
        panel.setBackground(Color.black);
        mainPanel.add(panel,BorderLayout.NORTH);
        scroll=new JScrollPane(mainPanel);
        frame.add(scroll);
        frame.setSize(700,500);
        
        addPanels(panel,cons,data);
        frame.setVisible(true);
        
        
    }
    private void addPanels(JPanel p,GridBagConstraints c,SearchInfoComponent d)
    {
        Iterator iAll=d.createIterator();
        while(iAll.hasNext())
        {
            SearchInfoComponent group=(SearchInfoComponent)iAll.next();
            Iterator iGroup=group.createIterator();
            
            JPanel queryPanel = new JPanel();
            //specify the index of the query
            int queryIndex=group.getIndex();
            JLabel queryLabel = new JLabel("Query:"+queryIndex);
            queryLabel.setFont(titleFont);
            queryPanel.add(queryLabel);
            queryPanel.setLayout(new GridLayout(1,1));
            p.add(queryPanel,c);
            JPanel p1 = new JPanel();
            //Check if its a group or not
            //if its null its a search info and not a search info group
            if(group.getSize()!=0){
            if(iGroup!=null)
            {
                while(iGroup.hasNext())
                {
                    //get each search info in the group
                    SearchInfoComponent leaf=(SearchInfoComponent)iGroup.next();
                    String filename=leaf.getFilename();
                    int count = leaf.getCount();
                    if(count>0)
                    {
                        ArrayList<String> linenumber=leaf.getLinenumber();
                        ArrayList<String> lines=leaf.getLines();
                        ArrayList<ArrayList<String>> wordSearched = leaf.getWords();
                        boolean containsJoker=leaf.getCJ();
                        
                        if(containsJoker==false)
                        {
                            JPanel[] panels = new JPanel[lines.size()];
                            JLabel[] labels = new JLabel[lines.size()];
                            
                            JPanel title =new JPanel();
                            title.setLayout(new FlowLayout());
                            JLabel l=new JLabel(filename);
                            l.setFont(titleFont);
                            title.add(l);
                            title.setAlignmentX(Component.LEFT_ALIGNMENT);
                            title.setBackground(darkgrey);
                            p1.add(title);
                        
                            //Change the style of the linenumber to be the same length
                            int length=0;
                            for(String s:linenumber)
                            {
                                if(s.length()>length)
                                {
                                    length=s.length();
                                }
                            }
                            for(int x=0;x<linenumber.size();x++)
                            {
                                if(linenumber.get(x).length()<length)
                                {
                                    String lasts=linenumber.get(x);
                                    String ln="";
                                    for(int h=lasts.length();h<length;h++)
                                    {
                                        ln+="0";
                                    }
                                    ln+=lasts;
                                    linenumber.set(x,ln);
                                }
                            }
                            for(int k=0;k<panels.length;k++)
                            {
                                panels[k]=new JPanel();
                                panels[k].setLayout(new GridLayout(1,1));
                                panels[k].setAlignmentX(Component.LEFT_ALIGNMENT);
                                if(k%2==0)
                                {
                                    panels[k].setBackground(grey);
                                }
                                if(k==panels.length-1)
                                {
                                    panels[k].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
                                }
                                //Make the word found bold
                                String[] split=lines.get(k).split(se.EscapeString(wordSearched.get(k).get(0)));
                                String htmlStr="<html><span>"+linenumber.get(k)+"|";
                                    for(int y=0;y<split.length;y++)
                                    {
                                        if(y==split.length-1)
                                        {
                                            htmlStr+=split[y]+"</span></html>";
                                        }else{
                                            htmlStr+=split[y]+"<b><i>"+wordSearched.get(k).get(0).replaceAll("\\\\","")+"</i></b>";
                                        }
                                    }
                                
                                labels[k]=new JLabel(htmlStr);
                                labels[k].setFont(f);
                                panels[k].add(labels[k]);
                                p1.add(panels[k]);
                            }
                        }
                        else
                        {
                            JPanel[] panels = new JPanel[lines.size()];
                            JLabel[] labels = new JLabel[lines.size()];
                            
                            JPanel title =new JPanel();
                            title.setLayout(new FlowLayout());
                            JLabel l=new JLabel(filename);
                            l.setFont(titleFont);
                            title.add(l);
                            title.setAlignmentX(Component.LEFT_ALIGNMENT);
                            title.setBackground(darkgrey);
                            p1.add(title);
                            //Change the style of the linenumber to be the same length
                            int length=0;
                        
                            for(String s:linenumber)
                            {
                                if(s.length()>length)
                                {
                                    length=s.length();
                                }
                            }
                            for(int x=0;x<linenumber.size();x++)
                            {
                                if(linenumber.get(x).length()<length)
                                {
                                    String lasts=linenumber.get(x);
                                    String ln="";
                                    for(int h=lasts.length();h<length;h++)
                                    {
                                        ln+="0";
                                    }
                                    ln+=lasts;
                                    linenumber.set(x,ln);
                                }
                            }
                            for(int k=0;k<panels.length;k++)
                            {
                                panels[k]=new JPanel();
                                panels[k].setLayout(new GridLayout(1,1));
                                panels[k].setAlignmentX(Component.LEFT_ALIGNMENT);
                                if(k%2==0)
                                {
                                    panels[k].setBackground(grey);
                                }
                                if(k==panels.length-1)
                                {
                                    panels[k].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
                                }
                                
                                //Make the word found bold
                                ArrayList<String> wordParts=wordSearched.get(k);
                                String htmlStr="";
                                String newWord="";
                                String rest=lines.get(k);
                                for(int it=0;it<wordParts.size();it++)
                                {
                                    
                                    String[] split=rest.split(wordParts.get(it));
                                    
                                    newWord=wordParts.get(it).replaceAll("\\\\","");
                                    
                                    for(int y=0;y<split.length;y++)
                                    {   
                                        if(y==split.length-1)
                                        {
                                            rest=split[split.length-1];
                                            if(it==wordParts.size()-1)
                                            {
                                                htmlStr+=split[y];
                                            }
                                        }else
                                        {
                                            htmlStr+=split[y]+"<b><i>"+newWord+"</i></b>";
                                        }
                                    }
                                    
                                }
                                //adding missing line numbers and fixing their length
                                int endIndex=htmlStr.lastIndexOf("\n");
                                htmlStr=htmlStr.substring(0,endIndex);
                                Reader reader=new StringReader(htmlStr);
                                BufferedReader br=new BufferedReader(reader);
                                String htmlStr2="<html>";
                                String line="";
                                try
                                {
                                    int num=Integer.valueOf(linenumber.get(k));
                                    while((line = br.readLine()) != null)
                                    {
                                        //Parsing to String
                                        String strnum=num+"";
                                        //Same function as before
                                        for(int x=0;x<linenumber.get(k).length();x++)
                                        {
                                            if(strnum.length()<linenumber.get(k).length())
                                            {
                                                String lasts=strnum;
                                                strnum="";
                                                for(int h=lasts.length();h<linenumber.get(k).length();h++)
                                                {
                                                    strnum+="0";
                                                }
                                                strnum+=lasts;  
                                            }
                                        }
                                        htmlStr2+=strnum+"|"+line+"<br/>";
                                        num++;
                                    }
                                    br.close();
                                }catch(IOException e)
                                {
                                    e.printStackTrace();
                                }
                                
                                htmlStr2+="</html>";
                                labels[k]=new JLabel(htmlStr2);
                                labels[k].setFont(f);
                                panels[k].add(labels[k]);
                                p1.add(panels[k]);
                            }
                        }
                    }
                    p1.setLayout(new BoxLayout(p1,BoxLayout.Y_AXIS));
                    p.add(p1,c);
                }
            }
            }else
            {
                JPanel emptyPanel=new JPanel();
                String emptyString="<html><span style=\"font-size:16px;color:rgb(125,125,125)\">No files were found.</span></html>";
                JLabel emptyLabel=new JLabel(emptyString);
                emptyLabel.setFont(f);
                emptyPanel.add(emptyLabel);
                emptyPanel.setLayout(new GridLayout(1,1));
                emptyPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
                p1.add(emptyPanel);
                p1.setLayout(new BoxLayout(p1,BoxLayout.Y_AXIS));
                p.add(p1,c);
            }
        }
    }
}