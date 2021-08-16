import java.util.*;
import javax.swing.table.*;
import javax.swing.*;
import java.awt.*;
//this is for aesthetics purposes only 
//just to make the los text wrap
public class TextAreaRenderer extends JTextArea implements TableCellRenderer 
{
    TextAreaRenderer() {
        setLineWrap(true);
        setWrapStyleWord(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText(value.toString());
        setSize(table.getColumnModel().getColumn(column).getWidth(), getPreferredSize().height);
        if (table.getRowHeight(row) != getPreferredSize().height) {
            table.setRowHeight(row, getPreferredSize().height+10);
        }
        return this;
    }
}