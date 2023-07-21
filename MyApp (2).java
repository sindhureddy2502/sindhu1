import java.sql.*;
import java.awt.*;
import java.awt.event.*;
class MyApp implements WindowListener,ActionListener{
	TextField deptno,dname,loc,msg;
	Connection con=null;
	ResultSet rs=null;
MyApp() throws Exception
{
	Frame frame=new Frame();
	Label deptnol=new Label("deptno:  ");
	Label dnamel=new Label("deptname:");
	Label locl=new Label("	location:   ");
	 deptno=new TextField(20);
	dname=new TextField(20);
	 loc=new TextField(20);
	msg=new TextField(20);
	frame.setVisible(true);
	frame.setBackground(Color.CYAN);
	frame.setSize(300,300);
	Button next=new Button("next");
	next.addActionListener(this);
	Button last=new Button("last");
	last.addActionListener(this);
	Button first=new Button("first");
	first.addActionListener(this);
	Button previous=new Button("previous");
	previous.addActionListener(this);
	frame.setLayout(new FlowLayout());
	frame.add(deptnol);
	frame.add(deptno);
	frame.add(dnamel);
	frame.add(dname);
	frame.add(locl);
	frame.add(loc);
	frame.add(first);
	frame.add(next);
	frame.add(previous);
	frame.add(last);
	frame.add(msg);
	frame.addWindowListener(this);
	Class.forName("oracle.jdbc.driver.OracleDriver");
	con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
	Statement st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);		
	rs=st.executeQuery("select * from dept");
	
}
public void actionPerformed(ActionEvent ae) 
{
	String s=ae.getActionCommand();
	switch(s)
	{
	case "first":	try{rs.first();
				deptno.setText(""+rs.getInt(1));
				dname.setText(rs.getString(2));
				loc.setText(rs.getString(3));
				msg.setText("First record..!");
				}
			catch(Exception e)
			{}
			break;	
	case "next": try{
			if(!(rs.isLast()))
			{
			rs.next();
			deptno.setText(""+rs.getInt(1));
			dname.setText(rs.getString(2));
			loc.setText(rs.getString(3));
			msg.setText("");
			}
			else{msg.setText(" Last record..");}
			}
			catch(Exception e)
			{}
			break;
	case "previous": try
			{
			
			if(!(rs.isFirst()))
			{
			rs.previous();
			deptno.setText(""+rs.getInt(1));
			dname.setText(rs.getString(2));
			loc.setText(rs.getString(3));
			msg.setText("");
			}
			else{msg.setText(" First record..");}
			}
			catch(Exception e)
			{}
		break;
	case "last":  try
			{
			rs.last();
			deptno.setText(""+rs.getInt(1));
			dname.setText(rs.getString(2));
			loc.setText(rs.getString(3));
			msg.setText("Last record!");
			}
			catch(Exception e)
			{}
		break;
	}
}
public  void windowOpened(WindowEvent w)
{ }
public  void windowClosing(WindowEvent w)
{
System.exit(0);
}
public  void windowClosed(WindowEvent w)
{
System.exit(1);
}
public  void windowIconified(WindowEvent w)
{ }
public  void windowDeiconified(WindowEvent w)
{ }
public  void windowActivated(WindowEvent w)
{ }
public  void windowDeactivated(WindowEvent w)
{ }

public static void main(String args[]) throws Exception
{
	MyApp m=new MyApp();
	
}

}