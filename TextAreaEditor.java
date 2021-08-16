import java.awt.event.*;
import java.text.*;
import java.awt.*;
import java.awt.font.*;
import java.awt.geom.AffineTransform;

 import javax.swing.*;

public class TextAreaEditor extends DefaultCellEditor {
    private Table connectedTable;
    int lastLineCount=1;
    int LineCount=1;
    
    
    public TextAreaEditor(Table t) {
    
    super(new JTextField());
    connectedTable=t;
    final JTextArea textArea = new JTextArea();
    textArea.setWrapStyleWord(true);
    textArea.setLineWrap(true);
    textArea.addKeyListener(new KeyListener()
    {
    public void keyTyped(KeyEvent e)
    {
    
    }
    public void keyPressed(KeyEvent e)
    {
    }
    
    public void keyReleased(KeyEvent e)
    {
       t.getRenderer().getTableCellRendererComponent(t.get(),textArea.getText(),true,true,t.get().getSelectedRow(),t.get().getSelectedColumn());
    }
    });
    JScrollPane scrollPane = new JScrollPane(textArea);
    scrollPane.setBorder(null);
    editorComponent = scrollPane;

    delegate = new DefaultCellEditor.EditorDelegate() {
      public void setValue(Object value) {
        textArea.setText((value != null) ? value.toString() : "");
      }
      public Object getCellEditorValue() {
        return textArea.getText();
      }
    };
  }
}