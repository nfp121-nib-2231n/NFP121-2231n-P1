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
    
    private StringEscaper se;
    public Result(LinkedHashMap data)
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
        se=new StringEscaper();
        
    }
    private void addPanels(JPanel p,GridBagConstraints c,LinkedHashMap<Integer,ArrayList<String[]>> d)
    {
        for(int i=0;i<d.size();i++)
        {
            JPanel querypanel= new JPanel();
            int numq=i+1;
            JLabel queryNumber= new JLabel("Query:"+numq);
            queryNumber.setFont(titleFont);
            querypanel.add(queryNumber);
            querypanel.setLayout(new GridLayout(1,1));
            p.add(querypanel,c);
            ArrayList<String[]> list=d.get(i+1);
            JPanel p1=new JPanel();
            
            if(list.size()!=0)
            {
                for(int j=0;j<list.size();j++)
                {
                    String[] array=list.get(j);
                    String filename=array[0];
                    int count=Integer.parseInt(array[1]);
                    
                    if(count>0)
                    {
                        String[] linenumber=array[2].split(",");
                        String[] lines=array[3].split("-_-");
                        //System.out.println(array[3]);
                        String containsJoker=array[5];
 
                        if(containsJoker.equals("false"))
                        {
                           String[] wordSearched=array[4].split("-_-");
                           //System.out.println(wordSearched);
                        
                            
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
                        }else //if there is a joker
                        {
                        String[] wordSearched=array[4].split("-_-");
                        //System.out.println(array[4]);
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
                                    }else{
                                        htmlStr+=split[y]+"<b><i>"+newWord+"</i></b>";
                                    }
                                }
                            }
                            
                            //adding missing line numbers and fixing their length
                            Reader reader=new StringReader(htmlStr);
                            BufferedReader br=new BufferedReader(reader);
                            String htmlStr2="<html>";
                            String line="";
                            try{
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
                    
                }
            }
            else //if count=0
            {
                    JPanel emptyPanel=new JPanel();
                    String emptyString="<html><span style=\"font-size:16px;color:rgb(125,125,125)\">No files were found.</span></html>";
                    JLabel emptyLabel=new JLabel(emptyString);
                    emptyLabel.setFont(f);
                    emptyPanel.add(emptyLabel);
                    emptyPanel.setLayout(new GridLayout(1,1));
                    emptyPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
                    p1.add(emptyPanel);
            }
            p1.setLayout(new BoxLayout(p1,BoxLayout.Y_AXIS));
            p.add(p1,c);
        }
    }
    class c
    {
        //text
    }
}