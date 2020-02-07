/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.swing.JScrollPane;
import javax.swing.JTextPane;

/**
 *
 * @author siddarth
 */
public class ReturnComponent {
    private JTextPane panel;
       private JScrollPane jk;
       
    public ReturnComponent()
    {
    panel=new JTextPane();
 jk  = new JScrollPane(panel);
    }
    public void setName(String name)
    {
    this.jk.setName(name); 
    }
    public String getName()
    {
    return this.jk.getName();
    }
public  JScrollPane getComponent()
{
return jk;
}
}


