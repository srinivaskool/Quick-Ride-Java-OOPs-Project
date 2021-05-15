import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.SystemColor;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.ImageIcon;

import org.omg.CORBA.INTERNAL;

import com.sun.org.apache.bcel.internal.generic.BALOAD;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;


public class Rating_Driver extends JFrame {

	private JPanel contentPane;
	public JLabel driv_name;
	static int rate;
	
	static String str_user,str2,str7,pick_point,drop_point,dri_name;
	static String fare_sting;
	static int new_balance;
	
	static String prev_rating;
	static String prev_trips;
	
	static double new_rating;
	static int new_trips;
	static int bal;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Rating_Driver frame = new Rating_Driver();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void update_rating()
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
			String sql = "Select * from drivers where name='"+ dri_name +"' ";
			ResultSet rs2 = stmt.executeQuery(sql); 
			if(rs2.next())
			{
				prev_rating = rs2.getString(3);
				prev_trips = rs2.getString(8);
				int pt = Integer.parseInt(prev_trips);
				new_trips = pt + 1;
				double pr = Double.parseDouble(prev_rating);
				new_rating = (pr*pt + rate)/new_trips;
				new_rating = Double.parseDouble(new DecimalFormat("#.#").format(new_rating));
				System.out.println(new_rating + "  " + new_trips);
			}
			else
			{
				System.out.println("Some error");
			}
			conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		
		Connection conn = null;
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root@123");
			Statement stmt =  conn.createStatement();
			String sql = "update drivers " + " set trips = '" + new_trips +"' ,rating= '" + new_rating +"' " +"where name='"+ dri_name +"' "; 		
			stmt.executeUpdate(sql);
			System.out.println("Update done");
			conn.close();
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}

	/**
	 * Create the frame.
	 */
	
	public Rating_Driver(String str_main,String pup,String dop,String driver_name,int new_balance)
	{
		str_user = str_main;
		pick_point = pup;
		drop_point = dop;
		dri_name = driver_name;
		bal = new_balance;
	}
	
	
	public Rating_Driver() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 421, 497);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.desktop);
		contentPane.setFocusCycleRoot(true);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblRateUs = new JLabel("Rate Us");
		lblRateUs.setFont(new Font("Roboto Medium", Font.PLAIN, 26));
		lblRateUs.setBounds(141, 40, 95, 32);
		contentPane.add(lblRateUs);
		
		JLabel lblRateYourTrip = new JLabel("Rate your trip with");
		lblRateYourTrip.setFont(new Font("Poppins Medium", Font.PLAIN, 20));
		lblRateYourTrip.setBounds(33, 105, 358, 66);
		contentPane.add(lblRateYourTrip);
		
		driv_name = new JLabel("Name");
		driv_name.setFont(new Font("Poppins SemiBold", Font.PLAIN, 18));
		driv_name.setBounds(232, 105, 159, 66);
		contentPane.add(driv_name);
		
		JButton star_1 = new JButton("");
		star_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				rate = 1;
				update_rating();
				MainPage mp = new MainPage();
				mp.lbl_user_id.setText(str_user);
				mp.lbl_bal.setText(String.valueOf(bal));
				MainPage mp2 = new MainPage(str_user);
				mp.setVisible(true);
				dispose();
			}
		});
		star_1.setMargin(new Insets(0, 0, 0, 0));
		star_1.setForeground(SystemColor.desktop);
		star_1.setBackground(SystemColor.desktop);
		star_1.setIcon(new ImageIcon("C:\\Users\\srini\\Desktop\\star.png"));
		star_1.setBounds(51, 197, 49, 52);
		contentPane.add(star_1);
		
		JButton star_2 = new JButton("");
		star_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rate = 2;
				update_rating();
				MainPage mp = new MainPage();
				mp.lbl_user_id.setText(str_user);
				mp.lbl_bal.setText(String.valueOf(bal));
				MainPage mp2 = new MainPage(str_user);
				mp.setVisible(true);
				dispose();
			}
		});
		star_2.setIcon(new ImageIcon("C:\\Users\\srini\\Desktop\\star.png"));
		star_2.setMargin(new Insets(0, 0, 0, 0));
		star_2.setForeground(SystemColor.desktop);
		star_2.setBackground(SystemColor.desktop);
		star_2.setBounds(112, 197, 49, 52);
		contentPane.add(star_2);
		
		JButton star_3 = new JButton("");
		star_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rate = 3;
				update_rating();
				MainPage mp = new MainPage();
				mp.lbl_user_id.setText(str_user);
				mp.lbl_bal.setText(String.valueOf(bal));
				MainPage mp2 = new MainPage(str_user);
				mp.setVisible(true);
				dispose();
				
			}
		});
		star_3.setIcon(new ImageIcon("C:\\Users\\srini\\Desktop\\star.png"));
		star_3.setMargin(new Insets(0, 0, 0, 0));
		star_3.setForeground(SystemColor.desktop);
		star_3.setBackground(SystemColor.desktop);
		star_3.setBounds(173, 197, 49, 52);
		contentPane.add(star_3);
		
		JButton star_4 = new JButton("");
		star_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rate = 4;
				update_rating();
				MainPage mp = new MainPage();
				mp.lbl_user_id.setText(str_user);
				mp.lbl_bal.setText(String.valueOf(bal));
				MainPage mp2 = new MainPage(str_user);
				mp.setVisible(true);
				dispose();
			}
		});
		star_4.setIcon(new ImageIcon("C:\\Users\\srini\\Desktop\\star.png"));
		star_4.setMargin(new Insets(0, 0, 0, 0));
		star_4.setForeground(SystemColor.desktop);
		star_4.setBackground(SystemColor.desktop);
		star_4.setBounds(234, 197, 49, 52);
		contentPane.add(star_4);
		
		JButton star_5 = new JButton("");
		star_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rate = 5;
				update_rating();
				MainPage mp = new MainPage();
				mp.lbl_user_id.setText(str_user);
				mp.lbl_bal.setText(String.valueOf(bal));
				MainPage mp2 = new MainPage(str_user);
				mp.setVisible(true);
				dispose();
			}
		});
		star_5.setIcon(new ImageIcon("C:\\Users\\srini\\Desktop\\star.png"));
		star_5.setMargin(new Insets(0, 0, 0, 0));
		star_5.setForeground(SystemColor.desktop);
		star_5.setBackground(SystemColor.desktop);
		star_5.setBounds(295, 197, 49, 52);
		contentPane.add(star_5);
		
		JLabel lblThankYouFor = new JLabel(" Thank You For Choosing Quick Ride");
		lblThankYouFor.setForeground(Color.WHITE);
		lblThankYouFor.setFont(new Font("Work Sans Medium", Font.PLAIN, 20));
		lblThankYouFor.setBounds(12, 319, 379, 52);
		contentPane.add(lblThankYouFor);;
	}
}