import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import jdk.internal.dynalink.beans.StaticClass;


public class Ongoing_Trip extends JFrame {

	private JPanel contentPane;
	JLabel lblWaittime;
	JLabel lblDriverrating;
	JLabel lblDrivername;
	JLabel lblDrivercontact;
	JLabel lblFrom;
	JLabel lbl_to;
	JLabel lbl_driver_loc;
	JButton btnNewButton;
	
	static String str_user,str2,str7,pick_point,drop_point,dri_name;
	static String fare_sting;
	static int new_balance;
	JLabel lblVechileId;
	JLabel lbl_vechile_id;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ongoing_Trip frame = new Ongoing_Trip();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	
	public Ongoing_Trip(String str_main,String pup,String dop,String driver_name)
	{
		str_user = str_main;
		pick_point = pup;
		drop_point = dop;
		dri_name = driver_name;
		
		try {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root@123");
		Statement stmt =  conn.createStatement();
		String sql = "Select * from users where user_id=" + str_user;
		ResultSet rs = stmt.executeQuery(sql); 
		if(rs.next())
		{
			System.out.println("confirm trip page user id :" + str_user + " "+  rs.getString(7) + " " + pup + " " + dop);
			str7 = rs.getString(7);
			str2 = rs.getString(2);
		}
		else
		{
			System.out.println("Some user error");
		}
		conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update_balance()
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
			String sql = "Select * from points where pick='"+ pick_point +"' and drop_point='"+ drop_point +"'";
			ResultSet rs2 = stmt.executeQuery(sql); 
			if(rs2.next())
			{
				fare_sting = rs2.getString(5);
			}
			else
			{
				System.out.println("Some error");
			}
			conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		int x = Integer.parseInt(fare_sting);
		int bal= Integer.parseInt(str7);
		new_balance = bal - x;
		
		Connection connec = null;
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			connec = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root@123");
			Statement stmt =  connec.createStatement();
			String sql = "update users " + " set wallet = '" + new_balance +"' " +"where user_id='"+ str_user +"' "; 		
			stmt.executeUpdate(sql);
			System.out.println("Update done");
			connec.close();
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
		
		System.out.println(new_balance);
		
		
		System.out.println(str_user + "  " +pick_point + "  " + str7 +"  " + drop_point + "  " +dri_name);
	}
	
	public void update_driver()
	{
		Connection conn = null;
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root@123");
			Statement stmt =  conn.createStatement();
			String sql = "update drivers " + " set def_location = '" + drop_point +"' ,status= '" + '0' +"' " +"where name='"+ dri_name +"' "; 		
			stmt.executeUpdate(sql);
			System.out.println("Update done");
			conn.close();
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}
	
	public void driver_distribution()
	{
		int count=0;
		int num_lin=0,num_sec=0,num_kom=0,num_nam=0,num_bphc=0;
		int arr[] = null;
		try {
			Connection conn = null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root@123");
			Statement stmt =  conn.createStatement();
			String sql = "Select * from drivers where def_location='"+ drop_point +"' ";
			ResultSet rs2 = stmt.executeQuery(sql); 
			while(rs2.next())
			{
				count++;
			}
			System.out.println("The number is " + count);
			if(count > 4)
			{
				System.out.println("in the count >4");
				String sql7 = "Select * from drivers where def_location='"+ "Lingampally" +"' ";
				ResultSet rs7 = stmt.executeQuery(sql7); 
				while(rs7.next())
				{
					num_lin++;
				}
				
				String sql8 = "Select * from drivers where def_location='"+ "Nampally" +"' ";
				ResultSet rs8 = stmt.executeQuery(sql8); 
				while(rs8.next())
				{
					num_nam++;
				}
				
				String sql9 = "Select * from drivers where def_location='"+ "Secunderabad" +"' ";
				ResultSet rs9 = stmt.executeQuery(sql9); 
				while(rs9.next())
				{
					num_sec++;
				}
				
				
				String sql6 = "Select * from drivers where def_location='"+ "BPHC" +"' ";
				ResultSet rs6 = stmt.executeQuery(sql6); 
				while(rs6.next())
				{
					num_bphc++;
				}
				
				String sql5 = "Select * from drivers where def_location='"+ "Kompally" +"' ";
				ResultSet rs5 = stmt.executeQuery(sql5); 
				while(rs5.next())
				{
					num_kom++;
				}
				
				System.out.println("starting if");
				
				if(num_kom<=num_bphc&&num_kom<=num_sec&&num_kom<=num_lin&&num_kom<=num_nam)
				{
					String sql_kom = "update drivers " + " set def_location = '" + "Kompally" +"' " +"where name='"+ dri_name +"' "; 	
					stmt.executeUpdate(sql_kom);
				}
				else if(num_lin<=num_bphc&&num_lin<=num_sec&&num_lin<=num_kom&&num_lin<=num_nam)
				{
					String sql_lin = "update drivers " + " set def_location = '" + "Lingampally" +"' "  +"where name='"+ dri_name +"' "; 		
					stmt.executeUpdate(sql_lin);
				}
				else if(num_sec<=num_bphc&&num_sec<=num_lin&&num_sec<=num_kom&&num_sec<=num_nam)
				{
					String sql_sec = "update drivers " + " set def_location = '" + "Secunderabad" +"' " +"where name='"+ dri_name +"' "; 		
					stmt.executeUpdate(sql_sec);
				}
				else if(num_nam<=num_bphc&&num_nam<=num_lin&&num_nam<=num_kom&&num_nam<=num_sec) 
				{
					String sql_nam = "update drivers " + " set def_location = '" + "Nampally" +"' " +"where name='"+ dri_name +"' "; 		
					stmt.executeUpdate(sql_nam);
				}
				else
				{
					String sql_bphc = "update drivers " + " set def_location = '" + "BPHC" +"' " +"where name='"+ dri_name +"' "; 		
					stmt.executeUpdate(sql_bphc);
				}
				
				
				
			}
			
			

			System.out.println(num_bphc + "  " + num_kom +"  "+ num_nam +"  " + num_lin+"  "+ num_sec);
			
			conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
	}
	
	public Ongoing_Trip() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 421, 497);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.desktop);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		lblFrom = new JLabel("FROM");
		lblFrom.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblFrom.setBounds(27, 26, 146, 35);
		contentPane.add(lblFrom);
		
		lbl_to = new JLabel("TO");
		lbl_to.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lbl_to.setBounds(233, 26, 158, 35);
		contentPane.add(lbl_to);
		
		JLabel lblName = new JLabel("Name ");
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblName.setBounds(62, 121, 99, 35);
		contentPane.add(lblName);
		
		JLabel lblTo = new JLabel(">>");
		lblTo.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblTo.setBounds(185, 27, 36, 35);
		contentPane.add(lblTo);
		
		JLabel lblDriverDetails = new JLabel("Driver Details");
		lblDriverDetails.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblDriverDetails.setBounds(117, 73, 158, 35);
		contentPane.add(lblDriverDetails);
		
		JLabel lblCotact = new JLabel("Contact");
		lblCotact.setForeground(Color.WHITE);
		lblCotact.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblCotact.setBounds(62, 158, 99, 35);
		contentPane.add(lblCotact);
		
		JLabel lblRating = new JLabel("Rating");
		lblRating.setForeground(Color.WHITE);
		lblRating.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblRating.setBounds(62, 231, 99, 35);
		contentPane.add(lblRating);
		
		lblDrivername = new JLabel("driver-name");
		lblDrivername.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblDrivername.setBounds(191, 121, 158, 35);
		contentPane.add(lblDrivername);
		
		lblDrivercontact = new JLabel("driver-contact");
		lblDrivercontact.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblDrivercontact.setBounds(191, 158, 158, 35);
		contentPane.add(lblDrivercontact);
		
		lblDriverrating = new JLabel("driver-rating");
		lblDriverrating.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblDriverrating.setBounds(191, 231, 158, 35);
		contentPane.add(lblDriverrating);
		
		JLabel lblWaitTime = new JLabel("Wait Time");
		lblWaitTime.setForeground(Color.WHITE);
		lblWaitTime.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblWaitTime.setBounds(62, 264, 99, 35);
		contentPane.add(lblWaitTime);
		
		lblWaittime = new JLabel("1 min ");
		lblWaittime.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblWaittime.setBounds(191, 264, 158, 35);
		contentPane.add(lblWaittime);
		
		JLabel lblDriverLocation = new JLabel("Driver At");
		lblDriverLocation.setForeground(Color.WHITE);
		lblDriverLocation.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblDriverLocation.setBounds(62, 297, 99, 35);
		contentPane.add(lblDriverLocation);
		
		lbl_driver_loc = new JLabel("driver-location");
		lbl_driver_loc.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lbl_driver_loc.setBounds(191, 297, 158, 35);
		contentPane.add(lbl_driver_loc);
		
		btnNewButton = new JButton("Done Trip");
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Connection conn = null;
				try 
				{
					Class.forName("com.mysql.cj.jdbc.Driver");
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root@123");
					Statement stmt =  conn.createStatement();
					String sql = "update users " + " set status = '" + 0 +"' " +"where user_id='"+ str_user +"' "; 		
					stmt.executeUpdate(sql);
					System.out.println("Update done");
					conn.close();
				} 
				catch (Exception e) 
				{
					System.out.println(e);
				}
				
				update_balance();
				update_driver();
				driver_distribution();
				Rating_Driver rd = new Rating_Driver();
				Rating_Driver rd2 = new Rating_Driver(str_user,pick_point,drop_point,dri_name,new_balance);
				rd.driv_name.setText(dri_name);
				rd.setVisible(true);
				dispose();

			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 21));
		btnNewButton.setBounds(117, 357, 130, 44);
		contentPane.add(btnNewButton);
		
		lblVechileId = new JLabel("Vehicle Id");
		lblVechileId.setForeground(Color.WHITE);
		lblVechileId.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblVechileId.setBounds(62, 194, 99, 35);
		contentPane.add(lblVechileId);
		
		lbl_vechile_id = new JLabel("vehicle-id");
		lbl_vechile_id.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lbl_vechile_id.setBounds(191, 194, 158, 35);
		contentPane.add(lbl_vechile_id);

	}

}
