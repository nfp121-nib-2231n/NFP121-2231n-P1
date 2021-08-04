 import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;

public class BrowseButton implements ActionListener
{
    private JButton b;
    private JFileChooser fc;
    private JLabel l;
    
    public JButton get()
    {
        return b;
    }
    public BrowseButton(String s,JLabel label,JFileChooser f)
    {
        b=new JButton(s);
        l=label;
        fc=f;
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
    }
    
    public void actionPerformed(ActionEvent e)
    {
        int r = fc.showOpenDialog(null);

        if (r == JFileChooser.APPROVE_OPTION)
        {
            l.setText("Selected Directory : "+fc.getSelectedFile().getAbsolutePath());
    }
    else
        {
            l.setText("No Directories Selected");
        }

    }
}
