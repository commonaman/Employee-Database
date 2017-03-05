
package javaapplication5;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import org.hibernate.*;
import org.hibernate.cfg.*;

public class JavaGUI extends Frame implements ActionListener
{
    TextField txteno;
    TextField txtename;
    TextField txtesal;
    
    Button btnSave;  
    Button btnClear;
    Button btnUpdate;
    Button btnDelete;
    Button btnSelect;
    Button btnExit;
    
    Configuration cfg;
    SessionFactory sf;
    Session s;
    Transaction t;
    Emp e1;

public JavaGUI()
    {
        cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        sf = cfg.buildSessionFactory();
       
        setSize(350,130);
        setVisible(true);
        setLocation(100,100);
        setLayout(new FlowLayout());
        
        txteno = new TextField(10);
        txtesal = new TextField(10);
        txtename = new TextField(10);
        
        btnSave = new Button("Save");
        btnUpdate = new Button("Update");
        btnDelete = new Button("Delete");
        btnExit = new Button("Exit");
        btnClear = new Button("Clear");
        btnSelect = new Button("Select");
        
        add(txteno);
        add(txtename);
        add(txtesal);
        add(btnSave);
        add(btnDelete);
        add(btnUpdate);
        add(btnClear);
        add(btnSelect);
        add(btnExit);
        
        btnSave.addActionListener(this);
        btnDelete.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnClear.addActionListener(this);
        btnSelect.addActionListener(this);
        btnExit.addActionListener(this);
    }
    
public static void main(String... args) 
        {
            new JavaGUI();
        }

    public void actionPerformed(ActionEvent e)
{
    Button b=(Button)e.getSource();
    if(b==btnSave)
    {
        s=sf.openSession();
        e1=new Emp();
        e1.setEno(Integer.parseInt(txteno.getText()));
        e1.setEname(txtename.getText());
        e1.setEsal(Integer.parseInt(txtesal.getText()));
        s.save(e1);
        t = s.beginTransaction();
        t.commit();
        s.close();
        JOptionPane.showMessageDialog(null,"Save");
        
    }
    if(b==btnUpdate)
    { 
        s=sf.openSession();
        e1=new Emp();
        int n1=Integer.parseInt(txteno.getText());
        Object o=s.load(Emp.class, n1);
        e1=(Emp)o;
        e1.setEname(txtename.getText());
        e1.setEsal(Integer.parseInt(txtesal.getText()));
        s.update(e1);
        s.save(e1);
        t=s.beginTransaction();
        t.commit();
        s.close();
        JOptionPane.showMessageDialog(null,"Update");
    }
    if(b==btnDelete)
    {
        s=sf.openSession();
        System.out.println("Delete");
        int n2= Integer.parseInt(txteno.getText());
        Object o=s.load(Emp.class, n2);
        s.delete(o);
        t=s.beginTransaction();
        t.commit();
        s.close();
        JOptionPane.showMessageDialog(null,"Delete");
    }
    if(b==btnSelect)
    {
        s=sf.openSession();
        System.out.println("Select");
        int n3=Integer.parseInt(txteno.getText());
        Object o=s.load(Emp.class, n3);
        e1=(Emp)o;        
        txtename.setText(e1.getEname());
        txtesal.setText(Integer.toString(e1.getEsal()));
        s.close();
        JOptionPane.showMessageDialog(null,"Select");        
    }
    if(b==btnClear)
    {
        System.out.println("Clear");
        txteno.setText(" ");
        txtename.setText(" ");
        txtesal.setText(" ");
        txteno.requestFocus();
        JOptionPane.showMessageDialog(null,"Clear");
    }
    if(b==btnExit)
    {
        JOptionPane.showMessageDialog(null,"Exit");
        System.exit(0);
    }
}
    
}