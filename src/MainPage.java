import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JTable;
import javax.swing.JList;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;


public class MainPage extends JFrame {

	private JPanel contentPane;
	JLabel lbl_user_id;
	static String str7,str2,str_main;
	JLabel lbl_bal;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage frame = new MainPage();
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
	public MainPage(String str) {
		
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
			System.out.println("main page user id :" + str + " "+  rs.getString(7));
			str_main = str;
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
	}
	
	
	public MainPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 421, 497);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.desktop);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		
		JLabel lblHi = new JLabel("User Id :-");
		lblHi.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblHi.setBounds(75, 148, 118, 28);
		contentPane.add(lblHi);
		
		lbl_user_id = new JLabel("User Id");
		lbl_user_id.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lbl_user_id.setBounds(194, 148, 143, 28);
		contentPane.add(lbl_user_id);
		
		JButton btnBookACab = new JButton("Book a Cab");
		btnBookACab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(Integer.parseInt(lbl_bal.getText()) >= 300)
				{
					Book_Cab bc = new Book_Cab();
					bc.lbl_bal.setText(str7);
					bc.lbl_user.setText(str2);
					Book_Cab mp2 = new Book_Cab(str_main);
					bc.setVisible(true);
					dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Your balance is less than 300. Please add money to  book a cab.Click Ok to Proceed");
					AddMoney am = new AddMoney();
					am.lbl_bal.setText(str7);
					am.lbl_user.setText(str2);
					am.setVisible(true);
					AddMoney mp2 = new AddMoney(str_main);
					dispose();
				}
					
			}
		});
		btnBookACab.setForeground(Color.MAGENTA);
		btnBookACab.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		btnBookACab.setBounds(94, 255, 176, 40);
		contentPane.add(btnBookACab);
		
		JButton btnAddMone = new JButton("Add Money");
		btnAddMone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				AddMoney am = new AddMoney();
				am.lbl_bal.setText(str7);
				am.lbl_user.setText(str2);
				am.setVisible(true);
				AddMoney mp2 = new AddMoney(str_main);
				dispose();
				
			}
		});
		btnAddMone.setForeground(Color.MAGENTA);
		btnAddMone.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		btnAddMone.setBounds(94, 325, 176, 40);
		contentPane.add(btnAddMone);
		
		JLabel lblBbalance = new JLabel("Balance :-");
		lblBbalance.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblBbalance.setBounds(75, 189, 118, 28);
		contentPane.add(lblBbalance);
		
		lbl_bal = new JLabel("0");
		lbl_bal.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lbl_bal.setBounds(194, 189, 56, 28);
		contentPane.add(lbl_bal);
		
		JLabel lblRs_1 = new JLabel("Rs");
		lblRs_1.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblRs_1.setBounds(256, 189, 56, 28);
		contentPane.add(lblRs_1);
		
		JLabel lblQuickRide = new JLabel("Quick Ride");
		lblQuickRide.setForeground(Color.WHITE);
		lblQuickRide.setFont(new Font("Montserrat", Font.BOLD, 29));
		lblQuickRide.setBounds(105, 77, 176, 47);
		contentPane.add(lblQuickRide);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.LIGHT_GRAY);
		menuBar.setBounds(0, 0, 403, 40);
		contentPane.add(menuBar);
		
		JMenuItem mntmQuickCab = new JMenuItem("Home");
		mntmQuickCab.setFont(new Font("Poppins Medium", Font.PLAIN, 17));
		mntmQuickCab.setHorizontalAlignment(SwingConstants.CENTER);
		mntmQuickCab.setBackground(Color.LIGHT_GRAY);
		mntmQuickCab.setIcon(new ImageIcon("C:\\Users\\srini\\Desktop\\iconfinder_65_171424.png"));
		menuBar.add(mntmQuickCab);
		
		JMenuItem mntmHome = new JMenuItem("Ride");
		mntmHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(Integer.parseInt(lbl_bal.getText()) >= 300)
				{
					Book_Cab bc = new Book_Cab();
					bc.lbl_bal.setText(str7);
					bc.lbl_user.setText(str2);
					Book_Cab mp2 = new Book_Cab(str_main);
					bc.setVisible(true);
					dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Your balance is less than 300. Please add money to  book a cab.Click Ok to Proceed");
					AddMoney am = new AddMoney();
					am.lbl_bal.setText(str7);
					am.lbl_user.setText(str2);
					am.setVisible(true);
					AddMoney mp2 = new AddMoney(str_main);
					dispose();
				}
				
			}
		});
		mntmHome.setFont(new Font("Poppins Medium", Font.PLAIN, 18));
		mntmHome.setIcon(new ImageIcon("C:\\Users\\srini\\Desktop\\iconfinder_aiga_taxi_134116.png"));
		mntmHome.setHorizontalAlignment(SwingConstants.CENTER);
		mntmHome.setBackground(Color.LIGHT_GRAY);
		menuBar.add(mntmHome);
		
		JMenuItem mntmSignOut = new JMenuItem("Sign out");
		mntmSignOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				WelcomePage wp = new WelcomePage();
				wp.setVisible(true);
				dispose();
				
			}
		});
		mntmSignOut.setFont(new Font("Poppins Medium", Font.PLAIN, 17));
		mntmSignOut.setHorizontalAlignment(SwingConstants.CENTER);
		mntmSignOut.setBackground(Color.LIGHT_GRAY);
		mntmSignOut.setIcon(new ImageIcon("C:\\Users\\srini\\Desktop\\iconfinder_common-logout-signout-exit-glyph_763291 (1).png"));
		menuBar.add(mntmSignOut);
		
	}
}
