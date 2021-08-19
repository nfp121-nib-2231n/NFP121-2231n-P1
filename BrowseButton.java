 import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import java.util.*;
import java.io.IOException;

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
    public void getFiles(File f,String parent,ArrayList<FileInfo> data) throws IOException{
        // Get the name and the data of all the files inside a folder.
        File[] listOfFiles = f.listFiles();
        if(listOfFiles != null)
        {
            for (File file : listOfFiles) 
            {
                if(file.isHidden()==false)
                {
                    if (file.isFile()) 
                    {
                        String name=parent+file.getName();
                        data.add(new FileInfo(name,readFile(file)));
                    } else if (file.isDirectory()) 
                    {
                        //Made it recursive to get any subfolder containing files too.
                        String p=parent+file.getName()+" > ";
                        getFiles(file,p,data);
                    }
                }
            }
        }
    }
    public File getFile()
    {
        return fc.getSelectedFile();
    }
    private String readFile(File f) throws IOException 
    {
        String filedata="";
        try (Scanner scanner =  new Scanner(f)){
          while (scanner.hasNextLine()){
            filedata+=scanner.nextLine();
            filedata+="\n";
          }      
        }
        return filedata;
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
