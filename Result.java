import javax.swing.*;
import java.awt.*;
import java.util.*;

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
                        String wordSearched=array[4];
                        
                        
                            
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
                                for(int h=1;h<length;h++)
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
                            String[] split=lines[k].split(wordSearched);
                            String htmlStr="<html><span>"+linenumber[k]+"|"+split[0]+"<b><i>"+wordSearched+"</i></b>"+split[1]+"</span></html>";
                            
                            labels[k]=new JLabel(htmlStr);
                            labels[k].setFont(f);
                            panels[k].add(labels[k]);
                            p1.add(panels[k]);
                        }
                    }
                    
                }
            }
            else
            {
                    System.out.println("bruh");
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
           /* JPanel p1=new JPanel();
            
            JPanel title =new JPanel();
            title.setLayout(new FlowLayout());
            JLabel l=new JLabel("title.java");
            l.setFont(titleFont);
            title.add(l);
            title.setAlignmentX(Component.LEFT_ALIGNMENT);
            title.setBackground( new Color(225, 225, 225) );
            
            JPanel p11=new JPanel();
            JPanel p12=new JPanel();
            JPanel p13=new JPanel();
            JLabel l12=new JLabel("    private String str");
            JLabel l13=new JLabel("    private int INT");
            
            p11.setLayout(new GridLayout(1,1));
            p12.setLayout(new GridLayout(1,1));
            p13.setLayout(new GridLayout(1,1));
            p1.setLayout(new BoxLayout(p1,BoxLayout.Y_AXIS));
            l11.setFont(f);
            l12.setFont(f);
            l13.setFont(f);
            p11.setAlignmentX(Component.LEFT_ALIGNMENT);
            p12.setAlignmentX(Component.LEFT_ALIGNMENT);
            p13.setAlignmentX(Component.LEFT_ALIGNMENT);
            p11.add(l11);
            p12.add(l12);
            p13.add(l13);
            p13.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
            
            p1.add(title);
            
            p1.add(p11);
            p1.add(p12);
            p1.add(p13);
            
            
            
            p12.setBackground(Color.red);
            p.add(p1,c);*/
        
        
    }
}