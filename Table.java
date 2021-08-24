import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Table implements ActionListener
{
    private JTable tab;
    private DefaultTableModel model;
    private JScrollPane scroll;
    private DefaultTableCellRenderer r;
    private int count=1;
    
    private JComboBox multipleSelection;
    private JComboBox fileType;
    private JTextArea textArea;
    private JScrollPane sp;
    
    private TableColumn multipleSelectionColumn;
    private TableColumn fileTypeColumn;
    private TableColumn losColumn;
    
    private TextAreaRenderer tar;
    
    private int size;
    
    private StringDoctor sd;
    public Table()
    {
        size=20;
        SetHeader();
        tab=new JTable(model);
        scroll=new JScrollPane(tab);
        tab.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tab.getColumnModel().getColumn(0).setMaxWidth(20);
        r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment( JLabel.CENTER );
        
        textArea=new JTextArea();
        multipleSelection=new JComboBox();
        fileType=new JComboBox();
        sp=new JScrollPane(textArea);
        multipleSelection.addItem("Vrai");
        multipleSelection.addItem("Faux");
        fileType.addItem("Code");
        fileType.addItem("Texte");
        fileType.addItem("All");
        
        multipleSelectionColumn=tab.getColumnModel().getColumn(5);
        fileTypeColumn=tab.getColumnModel().getColumn(6);
        losColumn=tab.getColumnModel().getColumn(2);
        
        tar=new TextAreaRenderer();
        
        multipleSelectionColumn.setCellEditor(new DefaultCellEditor(multipleSelection));
        fileTypeColumn.setCellEditor(new DefaultCellEditor(fileType));
        losColumn.setCellEditor(new TextAreaEditor(this));
        losColumn.setCellRenderer(tar);
        
        tab.getColumnModel().getColumn(0).setCellRenderer(r);
        sd=new StringDoctor();
    }
    public JTable get()
    {
        return tab;
    }
    public JScrollPane getPane()
    {
        return scroll;
    }
    public TextAreaRenderer getRenderer()
    {
        return tar;
    }
    public DefaultTableModel getModel()
    {
        return model;
    }
    public int rowCount()
    {
        return tab.getRowCount();
    }
    private void SetHeader()
    {
        String[] header={"","Desciption","List de séquences","Joker","Opérateur","Recherche Multiple","Type de Fichiers"}; 
        model=new DefaultTableModel(header,0){
        @Override
        public boolean isCellEditable(int row, int column) {
           return column!=0;
        }
        };
    }
    public void insertEmpty()
    {
        model.addRow(new String[]{Integer.toString(count),"","","","","",""});
        tab.setRowHeight(count-1,30);
        count++;
    }
    public void remove()
    {
        if(tab.getSelectedRow() != -1) {
               model.removeRow(tab.getSelectedRow());
               count--;
            }else
            {
                JOptionPane.showMessageDialog(null, "Select the row you want to remove",
                "No rows deleted", JOptionPane.ERROR_MESSAGE);
            }
    }
    public void addSize(int lines)
    {
        if(tab.getSelectedRow() != -1)
        {
            size=lines*20;
            tab.setRowHeight(tab.getSelectedRow(),size);
        }
    }
    public void actionPerformed(ActionEvent e)
    {
        insertEmpty();
    }
    public Info<TableInfo> fetchData()
    {
        ArrayList<TableInfo> list = new ArrayList<TableInfo>();
        for(int i=0;i<model.getRowCount();i++)
        {
            String[] row=new String[7];
            for(int j=0;j<model.getColumnCount();j++)
            {
                row[j]=model.getValueAt(i,j).toString();
            }
            if(sd.isEmpty(row[1])&sd.isEmpty(row[2]))
            {
                 JOptionPane.showMessageDialog(null, "You cant have both Description & List of sequence empty",
                "Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            if(sd.isEmpty(row[5]))
            {
                JOptionPane.showMessageDialog(null, "You need to choose multiple",
                "Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            if(sd.isEmpty(row[6]))
            {
            JOptionPane.showMessageDialog(null, "You need to choose file type",
                "Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            list.add(new TableInfo(row[0],row[1],row[2],row[3],row[4],row[5],row[6]));
        }
        return(new Info<TableInfo>(list));
    }
}
