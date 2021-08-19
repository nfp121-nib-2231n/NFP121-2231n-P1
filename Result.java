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
        se=new StringDoctor();
        
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
                        String[] linenumber=leaf.getLinenumber();
                        String[] lines=leaf.getLines();
                        boolean containsJoker=leaf.getCJ();
                        
                        if(containsJoker==false)
                        {
                            String[] wordSearched = leaf.getWords();
                            for(String l:lines)
                            {
                                System.out.println("LINE "+l);
                            }
                            for(String s:wordSearched)
                            {
                                System.out.println("WORD "+s);
                            }
                            JPanel[] panels = new JPanel[lines.length];
                            JLabel[] labels = new JLabel[lines.length];
                            
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
                            for(int x=0;x<linenumber.length;x++)
                            {
                                if(linenumber[x].length()<length)
                                {
                                    String lasts=linenumber[x];
                                    linenumber[x]="";
                                    for(int h=lasts.length();h<length;h++)
                                    {
                                        linenumber[x]+="0";
                                    }
                                    linenumber[x]+=lasts;  
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
                                
                                String[] split=lines[k].split(wordSearched[k]);
                                String htmlStr="<html><span>"+linenumber[k]+"|";
                                    for(int y=0;y<split.length;y++)
                                    {
                                        if(y==split.length-1)
                                        {
                                            htmlStr+=split[y]+"</span></html>";
                                        }else{
                                            htmlStr+=split[y]+"<b><i>"+wordSearched[k]+"</i></b>";
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
                            String[] wordSearched=leaf.getWords();
                            
                            JPanel[] panels = new JPanel[lines.length];
                            JLabel[] labels = new JLabel[lines.length];
                            
                            JPanel title =new JPanel();
                            title.setLayout(new FlowLayout());
                            JLabel l=new JLabel(filename);
                            l.setFont(titleFont);
                            title.add(l);
                            title.setAlignmentX(Component.LEFT_ALIGNMENT);
                            title.setBackground(darkgrey);
                            p1.add(title);
                            
                            int length=0;
                        
                            for(String s:linenumber)
                            {
                                if(s.length()>length)
                                {
                                    length=s.length();
                                }
                            }
                            for(int x=0;x<linenumber.length;x++)
                            {
                                if(linenumber[x].length()<length)
                                {
                                    String lasts=linenumber[x];
                                    linenumber[x]="";
                                    for(int h=lasts.length();h<length;h++)
                                    {
                                        linenumber[x]+="0";
                                    }
                                    linenumber[x]+=lasts;  
                                }
                            }
                            //Change the style of the linenumber to be the same length
                        
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
                                String [] wordParts=wordSearched[k].split("-");
                                String htmlStr="";
                                String newWord="";
                                String rest=lines[k];
                                for(int it=0;it<wordParts.length;it++)
                                {
                                    String[] split=rest.split(wordParts[it]);
                                    
                                    newWord=wordParts[it].replaceAll("\\\\","");
                                    
                                    for(int y=0;y<split.length;y++)
                                    {
                                        
                                        if(y==split.length-1)
                                        {
                                            rest=split[split.length-1];
                                            if(it==wordParts.length-1)
                                            {
                                                htmlStr+=split[y];
                                            }
                                        }else
                                        {
                                            htmlStr+=split[y]+"<b><i>"+newWord+"</i></b>";
                                        }
                                    }
                                }
                                //remove the line break
                                htmlStr=htmlStr.replaceAll("\n","");
                                //adding missing line numbers and fixing their length
                                Reader reader=new StringReader(htmlStr);
                                BufferedReader br=new BufferedReader(reader);
                                String htmlStr2="<html>";
                                String line="";
                                try
                                {
                                    int num=Integer.valueOf(linenumber[k]);
                                    while((line = br.readLine()) != null)
                                    {
                                        //Parsing to String
                                        String strnum=num+"";
                                        //Same function as before
                                        for(int x=0;x<linenumber[k].length();x++)
                                        {
                                            if(strnum.length()<linenumber[k].length())
                                            {
                                                String lasts=strnum;
                                                strnum="";
                                                for(int h=lasts.length();h<linenumber[k].length();h++)
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