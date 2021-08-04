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
    
    public Table()
    {
        SetHeader();
        tab=new JTable(model);
        scroll=new JScrollPane(tab);
        tab.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tab.getColumnModel().getColumn(0).setMaxWidth(20);
        r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment( JLabel.CENTER );
        tab.getColumnModel().getColumn(0).setCellRenderer(r);
    }
    public JTable get()
    {
        return tab;
    }
    public JScrollPane getPane()
    {
        return scroll;
    }
    public DefaultTableModel getModel()
    {
        return model;
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
        model.addRow(new String[]{Integer.toString(count)," "," "," "," "," ",""});
        tab.setRowHeight(count-1,60);
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
    public void actionPerformed(ActionEvent e)
    {
        insertEmpty();
    }
    public ArrayList<String[]> fetchData()
    {
        ArrayList<String[]> list = new ArrayList<String[]>();
        for(int i=0;i<model.getRowCount();i++)
        {
            String[] current=new String[7];
            for(int j=0;j<model.getColumnCount();j++)
            {
                current[j]=model.getValueAt(i,j).toString();
            }
            list.add(current);
        }
        for(int k=0;k<list.size();k++)
        {
            String[] a =list.get(k);
            for(int s=0;s<a.length;s++)
            {
                System.out.println("col"+s+" : "+a[s]);
            }
            
        }
        System.out.println("SIZE : "+list.size());
        return list;
    }
}
