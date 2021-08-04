import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UI
{
    private JFrame f;
    private BrowseButton b;
    private JPanel titlePanelParent;
    private JPanel titlePanel;
    private JPanel tablePanel;
    private JLabel selectLabel;
    private JFileChooser fc;
    private JLabel pathLabel;
    private Table t;
    private JButton plusRow;
    private JButton minusRow;
    private JPanel FlowPanel;
    private JButton print;
    
    public UI()
    {
        f=new JFrame();
        titlePanel=new JPanel();
        titlePanelParent=new JPanel();
        FlowPanel=new JPanel();
        pathLabel=new JLabel("");
        fc=new JFileChooser();
        b=new BrowseButton("Browse",pathLabel,fc);
        t=new Table();
        plusRow=new JButton("+");
        minusRow=new JButton("-");
        print=new JButton("Print");
        plusRow.addActionListener(t);
        minusRow.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent ae) {
            t.remove();
         }
      });
      print.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent ae) {
            t.fetchData();
         }
      });
        b.get().addActionListener(b);
        selectLabel=new JLabel("Select Project Directory");
        FlowPanel.setLayout(new FlowLayout());
        FlowPanel.add(pathLabel);
        FlowPanel.add(plusRow);
        FlowPanel.add(minusRow);
        FlowPanel.add(print);
        titlePanel.setLayout(new GridLayout(1,2));
        titlePanel.add(selectLabel);
        titlePanel.add(b.get());
        titlePanelParent.setLayout(new GridLayout(2,1));
        titlePanelParent.add(titlePanel);
        titlePanelParent.add(FlowPanel);
        f.setLayout(new BorderLayout());
        f.add(titlePanelParent,BorderLayout.NORTH);
        f.add(t.getPane(),BorderLayout.CENTER);       
        f.setSize(700,500);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}