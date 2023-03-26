import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
class reservation extends connect implements ActionListener,FocusListener
{
JFrame f;
JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13;
JTextField t1,t2,t3,t4,t5,t6,t7;
Choice h;
JTextArea ta;
JButton b1,b2,b3;
PreparedStatement ps;
Statement st;
ResultSet rs;
int x;
JLabel imgL;
ImageIcon img;
void disable()
{
t1.setEnabled(false);
t2.setEnabled(false);
t3.setEnabled(false);
t4.setEnabled(false);
t5.setEnabled(false);
t6.setEnabled(false);
t7.setEnabled(false);
h.setEnabled(false);
b2.setEnabled(false);
}
void enable()
{
t1.setEnabled(true);
t2.setEnabled(true);
t3.setEnabled(true);
t4.setEnabled(true);
t5.setEnabled(true);
t6.setEnabled(true);
t7.setEnabled(true);
h.setEnabled(true);
b2.setEnabled(true);
}
reservation()
{
img=new ImageIcon("LOGO1.jpg");
imgL=new JLabel(img);
imgL.setBounds(0,320,500,250);
f=new JFrame("Reservation");
f.getContentPane().setLayout(null);
f.getContentPane().setBackground(Color.white);
l1=new JLabel("PNR Number:");
l1.setBounds(130,50,100,30);
l2=new JLabel("Train Number:");
l2.setBounds(50,80,100,30);
l3=new JLabel("Train Name:");
l3.setBounds(50,110,100,30);
l4=new JLabel("Class:");
l4.setBounds(50,140,100,30);
l5=new JLabel("Date of Journey:");
l5.setBounds(50,170,100,30);
l6=new JLabel("From");
l6.setBounds(50,200,100,30);
l7=new JLabel("To");
l7.setBounds(250,200,50,30);
l8=new JLabel("Boarding at:");
l8.setBounds(50,230,100,30);
t1=new JTextField(10);
t1.setBounds(230,50,120,30);
t2=new JTextField(10);
t2.addFocusListener(this);
t2.setBounds(150,80,100,30);
t3=new JTextField(10);
t3.setBounds(150,110,100,30);
t4=new JTextField(10);
t4.setBounds(150,170,100,30);
t5=new JTextField(10);
t5.setBounds(150,200,100,30);
t6=new JTextField(10);
t6.setBounds(300,200,100,30);
t7=new JTextField(10);
t7.setBounds(150,230,100,30);
h=new Choice();
h.setBounds(150,140,100,30);
h.add("AC");
h.add("SL");
b1=new JButton("Insert");
b1.setBackground(Color.blue);
b1.setBounds(50,270,100,30);
b1.addActionListener(this);
b2=new JButton("Next");
b2.setBackground(Color.blue);
b2.setBounds(160,270,100,30);
b2.addActionListener(this);
b3=new JButton("Main");
b3.setBackground(Color.blue);
b3.setBounds(270,270,100,30);
b3.addActionListener(this);

b1.setMnemonic('I');
b2.setMnemonic('N');
b3.setMnemonic('M');

f.getContentPane().add(l1);
f.getContentPane().add(l2);
f.getContentPane().add(l3);
f.getContentPane().add(l4);
f.getContentPane().add(l5);
f.getContentPane().add(l6);
f.getContentPane().add(l7);
f.getContentPane().add(l8);
f.getContentPane().add(t1);
f.getContentPane().add(t2);
f.getContentPane().add(t3);
f.getContentPane().add(t4);
f.getContentPane().add(t5);
f.getContentPane().add(t6);
f.getContentPane().add(t7);
f.getContentPane().add(h);
f.getContentPane().add(b1);
f.getContentPane().add(b2);
f.getContentPane().add(b3);
f.getContentPane().add(imgL);
f.setSize(1300,1000);
f.setVisible(true);
disable();
}
public void focusLost(FocusEvent e)
{
if(t2.getText().length()!=0)
{
try
{
ps=con.prepareStatement("select Train_Name from Train where Train_No=?");
ps.setString(1,t2.getText());
rs=ps.executeQuery();
if(rs.next())
{
t3.setText(rs.getString(1));
}
}
catch(Exception e1)
{
System.out.println("Connection failed:"+e1);
}
}
}
public void focusGained(FocusEvent e)
{}
public void actionPerformed(ActionEvent e)
{
if(e.getSource()==b1)
{
enable();
b1.setEnabled(false);
try
{
st=con.createStatement();
rs=st.executeQuery("select * from PNR");
rs.next();
x=rs.getInt(1);
t1.setText(String.valueOf(x));

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
ps=con.prepareStatement("insert into Reservation values(?,?,?,?,?,?,?,?)");
ps.setString(1,t1.getText());
ps.setString(2,t2.getText());
ps.setString(3,t3.getText());
ps.setString(4,h.getSelectedItem());
ps.setString(5,t4.getText());
ps.setString(6,t5.getText());
ps.setString(7,t6.getText());
ps.setString(8,t7.getText());
ps.executeUpdate();


//update PNR no.
ps=con.prepareStatement("update PNR set PNR_No=? where PNR_No=?");
ps.setInt(1,(x+1));
ps.setInt(2,x);
ps.executeUpdate();
JOptionPane.showMessageDialog(null, "Record Saved");

b1.setEnabled(true);
b2.setEnabled(false);
f.setVisible(false);
}
catch(Exception e1)
{
System.out.println("Connection failed:"+e1);
}
try
{
st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
rs=st.executeQuery("select * from Reservation");
rs.last();
x=rs.getInt(1);
passenger P=new passenger(x);
st=con.createStatement();
rs=st.executeQuery("select * from PassengerID");
rs.next();
x=rs.getInt(1);
P.t1.setText(String.valueOf(x));
ps=con.prepareStatement("update PassengerID set PID=? where PID=?");
ps.setInt(1,(x+1));
ps.setInt(2,x);
ps.executeUpdate();
}
catch(Exception e1)
{
System.out.println("Connection failed:"+e1);
}
}
if(e.getSource()==b3)
{
f.setVisible(false);
new main();
}
}
public static void main(String args[])
{
new reservation();
}
}

