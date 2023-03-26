

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
class passenger extends connect implements ActionListener
{
JFrame f;
JLabel l1,l2,l3,l4,l5,l6;
JTextField t1,t2,t3;
JButton b1,b2,b3;
Checkbox c1,c2,c3,c4,c5;
CheckboxGroup cbg;
JTextArea ta;
PreparedStatement ps;
Statement st;
ResultSet rs;
int pno;
JLabel imgL;
ImageIcon img;
passenger(int p)
{
pno=p;
img=new ImageIcon("LOGO2.jpg");
imgL=new JLabel(img);
imgL.setBounds(0,400,400,200);
f=new JFrame("Passenger");
f.getContentPane().setLayout(null);
f.getContentPane().setBackground(Color.yellow);
l1=new JLabel("PId");
l1.setBounds(50,50,100,30);
t1=new JTextField(10);
t1.setBounds(170,50,100,30);
l2=new JLabel("Name of Passenger");
l2.setBounds(50,80,120,30);
t2=new JTextField(10);
t2.setBounds(170,80,100,30);
l3=new JLabel("Age");
l3.setBounds(50,130,100,30);
t3=new JTextField(10);
t3.setBounds(150,130,50,30);
l4=new JLabel("Gender");
l4.setBounds(250,130,50,30);
ta=new JTextArea(5,10);
ta.setBounds(150,170,130,70);
l5=new JLabel("Address");
l5.setBounds(50,170,100,30);
l6=new JLabel("Catagory");
l6.setBounds(50,240,100,30);
b1=new JButton("More");
b1.setBackground(Color.white);
b1.addActionListener(this);
b1.setBounds(50,330,100,30);
b2=new JButton("Save");
b2.setBackground(Color.white);
b2.addActionListener(this);
b2.setBounds(170,330,100,30);
b3=new JButton("Back");
b3.setBackground(Color.white);
b3.addActionListener(this);
b3.setBounds(290,330,100,30);

b1.setMnemonic('M');
b2.setMnemonic('S');
b3.setMnemonic('B');

cbg=new CheckboxGroup();
c1=new Checkbox("Male",cbg,true);
c1.setBounds(300,130,100,30);
c2=new Checkbox("Female",cbg,false); 
c2.setBounds(300,160,100,30);
c3=new Checkbox("General");
c3.setBounds(150,240,100,30);
c4=new Checkbox("Senior Citizen");
c4.setBounds(150,270,100,30);
c5=new Checkbox("Ex-serviceman");
c5.setBounds(150,300,100,30);
f.getContentPane().add(l1);
f.getContentPane().add(l2);
f.getContentPane().add(l3);
f.getContentPane().add(l4);
f.getContentPane().add(l5);
f.getContentPane().add(l6);
f.getContentPane().add(t1);
f.getContentPane().add(t2);
f.getContentPane().add(t3);
f.getContentPane().add(ta);
f.getContentPane().add(b1);
f.getContentPane().add(b2);
f.getContentPane().add(b3);
f.getContentPane().add(c1);
f.getContentPane().add(c2);
f.getContentPane().add(c3);
f.getContentPane().add(c4);
f.getContentPane().add(c5);
f.getContentPane().add(imgL);
f.setSize(1300,1000);
f.setVisible(true);
}
public void actionPerformed(ActionEvent e)
{
if(e.getSource()==b1)
{
try
{
//saving existing record
String cat="";
ps=con.prepareStatement("insert into TempPassenger values(?,?,?,?,?,?,?)");
ps.setString(1,t1.getText());
ps.setString(2,t2.getText());
ps.setString(3,t3.getText());
ps.setString(4,cbg.getSelectedCheckbox().getLabel());
ps.setString(5,ta.getText());
if(c3.getState())
cat+=c3.getLabel()+",";
if(c4.getState())
cat+=c4.getLabel()+",";
if(c5.getState())
cat+=c5.getLabel()+",";
ps.setString(6,cat);
ps.setInt(7,pno);
ps.executeUpdate();
ps.close();

//opening new form
Passenger P=new Passenger(pno);
st=con.createStatement();
rs=st.executeQuery("select * from PassengerID");
rs.next();
int x=rs.getInt(1);
P.t1.setText(String.valueOf(x));
st.close();
//update passenger id
ps=con.prepareStatement("update PassengerID set PID=? where PID=?");
ps.setInt(1,(x+1));
ps.setInt(2,x);
ps.executeUpdate();
ps.close();
}
catch(Exception e1)
{
System.out.println("Connection failed:"+e1);
}
}
if(e.getSource()==b2)
{

try
{
String cat="";
ps=con.prepareStatement("insert into TempPassenger values(?,?,?,?,?,?,?)");
ps.setString(1,t1.getText());
ps.setString(2,t2.getText());
ps.setString(3,t3.getText());
ps.setString(4,cbg.getSelectedCheckbox().getLabel());
ps.setString(5,ta.getText());
if(c3.getState())
cat+=c3.getLabel()+",";
if(c4.getState())
cat+=c4.getLabel()+",";
if(c5.getState())
cat+=c5.getLabel()+",";
ps.setString(6,cat);
ps.setInt(7,pno);
ps.executeUpdate();
ps.close();

st=con.createStatement();
rs=st.executeQuery("select * from TempPassenger");
while(rs.next())
{
ps=con.prepareStatement("insert into Passenger values(?,?,?,?,?,?,?)");
ps.setString(1,rs.getString(1));
ps.setString(2,rs.getString(2));
ps.setString(3,rs.getString(3));
ps.setString(4,rs.getString(4));
ps.setString(5,rs.getString(5));
ps.setString(6,rs.getString(6));
ps.setString(7,rs.getString(7));
ps.executeUpdate();
ps.close();
}
st=con.createStatement();
st.executeUpdate("delete from TempPassenger");
JOptionPane.showMessageDialog(null,"Record Saved");
b2.setEnabled(false);
b1.setEnabled(false);
st.close();
}
catch(Exception e1)
{
System.out.println("Connection failed:"+e1);
}
}

if(e.getSource()==b3)
{
f.setVisible(false);
new reservation();
}
}
public static void main(String args[])
{
new passenger(0);
}
}
