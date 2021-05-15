import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.print.attribute.standard.MediaSize.Other;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Font;


public class Trip extends JFrame {

	private JPanel contentPane;
	JLabel lbl_bal;
	JLabel lbl_user;
	JLabel lbl_pick;
	JLabel lbl_drop;
	JLabel lbl_journey_time;
	JLabel lbl_fare;
	static String str7,str2,str_main,pup,dop,driver_name;
	static String place;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Trip frame = new Trip();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public boolean no_drivers()
	{
		try {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root@123");
		Statement stmt =  conn.createStatement();
		String sql = "Select * from drivers where status='"+ '0' +"' ";
		ResultSet rs = stmt.executeQuery(sql); 
		while(rs.next())
		{
			String string = rs.getString(2);
			System.out.println("string is " +string);
			if(string != null)
			{
				return false;	
			}
		}
		conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	
	public String calc_wait_time(String str1,String str2)
	{
		String string = null;
		if(str1.equals(str2))
		{
			System.out.println("0 min");
		}
		else
		{
			try {
				Connection conn = null;
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root@123");
				Statement stmt =  conn.createStatement();
				String sql = "Select * from points where pick='"+ str1 +"' and drop_point= '"+ str2 +"' ";
				ResultSet rs = stmt.executeQuery(sql); 
				if(rs.next())
				{
					System.out.println("time for coming" + rs.getString(4) );
					string =  rs.getString(4);
				}
				else
				{
					System.out.println("0 min");
					string = "0 min";
				}
				conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return string;
	}
	
	public void get_shortest()
	{
		try {
			Connection conn = null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root@123");
			Statement stmt =  conn.createStatement();
			String sql4 = "SELECT * FROM project.points where pick='"+ pup +"' order by distance Asc";
			ResultSet res = stmt.executeQuery(sql4); 
			while(res.next())
			{
				place = res.getString(3);
				System.out.println("Shortest is :" + place);
				
				try {
					Connection connec = null;
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				    connec = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root@123");
					Statement stmt2 =  connec.createStatement();
				String sql2 = "select * from drivers where def_location='"+ place +"' and status= '"+ '0' +"' ORDER BY rating DESC Limit 1";
				ResultSet result = stmt2.executeQuery(sql2); 
				if(result.next())
				{
					if(	Integer.parseInt(result.getString(6))==0)
					{
						System.out.println("driver name "+result.getString(2));
						Ongoing_Trip ot = new Ongoing_Trip();
						ot.setVisible(true);
						dispose();
						ot.lblWaittime.setText(calc_wait_time(pup,place));
						ot.lbl_to.setText(dop);
						ot.lblFrom.setText(pup);
						ot.lblDrivername.setText(result.getString(2));
						ot.lblDriverrating.setText(result.getString(3));
						ot.lblDrivercontact.setText(result.getString(4));
						ot.lbl_driver_loc.setText(place);
						
						driver_name =  result.getString(2);
						Ongoing_Trip ot2 = new Ongoing_Trip(str_main,pup,dop,driver_name);
						System.out.println("trip page  " + str_main + "  " +pup + "  " + dop + "  " +driver_name);
							String sql3 = "update drivers " + " set status = '" + 1 +"' " +"where name='"+ result.getString(2) +"' "; 		
							stmt2.executeUpdate(sql3);
							break;
						
					}
				}
				connec.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				

			}
			conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

	}

	/**
	 * Create the frame.
	 */
	
		public Trip(String str,String pick_up,String drop_point) {
		
		str_main = str;
		pup = pick_up;
		dop = drop_point;
		
		try {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root@123");
		Statement stmt =  conn.createStatement();
		String sql = "Select * from users where user_id=" + str;
		ResultSet rs = stmt.executeQuery(sql); 
		if(rs.next())
		{
			System.out.println("confirm trip page user id :" + str + " "+  rs.getString(7) + " " + pup + " " + dop);
			str7 = rs.getString(7);
			str2 = rs.getString(2);
		}
		else
		{
			System.out.println("Some error");
		}
		conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		try {
			Connection connec = null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		    connec = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root@123");
			Statement stmt2 =  connec.createStatement();
		String sql2 = "select * from drivers where def_location='"+ pup +"' and status= '"+ '0' +"' ORDER BY rating DESC Limit 1";
		ResultSet result = stmt2.executeQuery(sql2); 
		if(result.next())
		{
			if(	Integer.parseInt(result.getString(6))==0)
			{
				System.out.println("driver name "+result.getString(2));
				calc_wait_time(pup,place);
				Ongoing_Trip ot = new Ongoing_Trip();
				ot.setVisible(true);
				dispose();
				ot.lbl_to.setText(drop_point);
				ot.lblFrom.setText(pick_up);
				ot.lblDrivername.setText(result.getString(2));
				ot.lblDriverrating.setText(result.getString(3));
				ot.lblDrivercontact.setText(result.getString(4));
				ot.lbl_driver_loc.setText(pup);
				ot.lbl_vechile_id.setText(result.getString(7));
				
				driver_name = result.getString(2);
				Ongoing_Trip ot2 = new Ongoing_Trip(str_main,pup,dop,driver_name);
					String sql3 = "update drivers " + " set status = '" + 1 +"' " +"where name='"+ result.getString(2) +"' "; 		
					stmt2.executeUpdate(sql3);
			
			}
		}
		else 
		{
			boolean b = no_drivers();
			System.out.println("b is " + b);
			if(b)
			{
				JOptionPane.showMessageDialog(null, "Sorry, No Cabs are available.");
				MainPage mp = new MainPage();
				mp.lbl_user_id.setText(str_main);
				mp.lbl_bal.setText(str7);
				MainPage mp2 = new MainPage(str_main);
				mp.setVisible(true);
				dispose();
			}
			else
			{
				get_shortest();
			}
			
		}
		
		connec.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
	public Trip() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 421, 497);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFindingYourTrip = new JLabel("Finding your trip");
		lblFindingYourTrip.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		lblFindingYourTrip.setBounds(123, 25, 157, 49);
		contentPane.add(lblFindingYourTrip);
	}

}
