

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.Robot;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import javax.swing.*;
import java.util.StringTokenizer;
import java.util.Random;


import java.applet.*;
import java.beans.*;
import java.net.*;
import java.io.*;
/**
 * The test class UITest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class UITest
{
    /**
     * Default constructor for test class UITest
     */
     private JFrame f;
    private Robot robot;
    private static Random random= new Random();
    public UITest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    protected void setUp() throws java.lang.Exception{
        try{
            f = new UI();
            f.pack();
            f.setVisible(true);
            while(!(f.isShowing())){}
            //f.setAlwaysOnTop(true);
            f.setLocation(random.nextInt(500), random.nextInt(500));
            robot = new Robot();
            robot.delay(10);

        }catch(Exception e){
            fail("exception inattendue !, " + e.getClass().getName());
        }
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
        f.dispose();
    }
    @Test
    public void test_s() throws Exception{
        try{
            Container panel = f.getContentPane();
            Component[] components = panel.getComponents();
            assertEquals(2, components.length);
            
            assertTrue(components[0] instanceof JPanel);
            Component[] TitleParentPanel = ((JPanel)components[0]).getComponents();
            Component[] TablePane =((JScrollPane)components[1]).getComponents();
            assertEquals(2,TitleParentPanel.length);
            
            assertTrue(TitleParentPanel[0] instanceof JPanel);
            Component[] TitlePanel=((JPanel)TitleParentPanel[0]).getComponents();
            
            assertTrue(TitleParentPanel[1] instanceof JPanel);
            Component[] FlowPanel=((JPanel)TitleParentPanel[1]).getComponents();
            
            JButton plus = ((JButton)FlowPanel[1]);
            JButton search = ((JButton)FlowPanel[3]);
            
            JButton browse=((JButton)TitlePanel[1]);
            browse.doClick();
            
            Component[] view = ((JViewport)TablePane[0]).getComponents();
            JTable t=((JTable)view[0]);
            
            DefaultTableModel model = (DefaultTableModel) t.getModel();
            
            String[][] data={{"1","Classe","class","","","Faux","Code"},
                             {"2","Interface","","","","Faux","Code"},
                             {"3","Commentaires","//,/*","","","Faux","Code"},
                             {"4","Sous-Classe","class%\n{\n%\nclass\n%}","%","","Vrai","Code"},
                             {"5","Liste étudiants","Etudiant: %","%","","Vrai","Texte"},
                             {"6","Liste Taches","Tache#%: %","%","","Vrai","Texte"}};
            for(int i=0;i<data.length;i++)
            {
                String[] d=data[i];
                model.addRow(d);
            }
            search.doClick();
            /*Point location = browse.getLocationOnScreen();
            mouseMoveAndClick(location.x+(browse.getWidth()/2),location.y+(browse.getHeight()/2));*/

            
            //System.out.println();
            /*JLabel etatPile = ((JLabel)vue[0]);

            assertTrue(components[1] instanceof JPanel);
            Component[] controle = ((JPanel)components[1]).getComponents();
            assertEquals(2, controle.length);*/

            /*assertTrue(" ce n'est pas l'IHM attendue ?", controle[0] instanceof JTextField);
            Component[] subComponents = ((JPanel)controle[1]).getComponents();
            assertTrue(" ce n'est pas l'IHM attendue ?", subComponents[0] instanceof JButton);// push
            assertTrue(" ce n'est pas l'IHM attendue ?", subComponents[1] instanceof JButton);// +
            assertTrue(" ce n'est pas l'IHM attendue ?", subComponents[2] instanceof JButton);// -
            assertTrue(" ce n'est pas l'IHM attendue ?", subComponents[3] instanceof JButton);// *
            assertTrue(" ce n'est pas l'IHM attendue ?", subComponents[4] instanceof JButton);// /

            empiler("15");
            assertTrue("empiler(15), en sortie != [15]","[15]".equals(etatPile.getText()));
            empiler("12");
            assertTrue("empiler(15),empiler(12) en sortie != [12, 15]","[12, 15]".equals(etatPile.getText()));
            assertTrue("15+12 != 27 ???","[27]".equals(add()));
            empiler("12");
            assertTrue("[12, 27]".equals(etatPile.getText()));
            assertTrue("27 + 12 != 39 ??? ","[39]".equals(add()));*/
        }catch(Exception e){
            fail("exception inattendue ! " + e.getClass().getName());
        }
    }
    public void mouseMoveAndClick(int x, int y){
        robot.mouseMove( x,y);
        robot.delay(20);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.delay(20);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.delay(20);
    }//end mouseMoveAndClick


}
