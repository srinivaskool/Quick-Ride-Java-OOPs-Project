import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JMenuBar;


public class AddMoney extends JFrame {

	private JPanel contentPane;
	private JTextField tf_amount;
	
	JLabel lbl_bal;
	JLabel lbl_user;
	static String str7,str2,str_main;
	/**
	 * Launch the application.
	 */
	static AddMoney frame = new AddMoney();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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
	
	public AddMoney(String str) {
		
		str_main = str;
		
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
	
	public AddMoney() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 421, 497);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.desktop);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblCurrentBalnce = new JLabel("Current Balance :-");
		lblCurrentBalnce.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCurrentBalnce.setBounds(68, 169, 151, 63);
		contentPane.add(lblCurrentBalnce);
		
		lbl_bal = new JLabel("0 RS");
		lbl_bal.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_bal.setBounds(239, 174, 89, 53);
		contentPane.add(lbl_bal);
		
		tf_amount = new JTextField();
		tf_amount.setBounds(118, 286, 160, 36);
		contentPane.add(tf_amount);
		tf_amount.setColumns(10);
		
		JLabel lblAmountToBe = new JLabel("Enter Amount");
		lblAmountToBe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAmountToBe.setBounds(118, 268, 151, 16);
		contentPane.add(lblAmountToBe);
		
		JLabel lblAddMoney = new JLabel("Add Money");
		lblAddMoney.setForeground(Color.WHITE);
		lblAddMoney.setBackground(Color.WHITE);
		lblAddMoney.setFont(new Font("SansSerif", Font.BOLD, 24));
		lblAddMoney.setBounds(127, 82, 151, 53);
		contentPane.add(lblAddMoney);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.setBackground(Color.BLACK);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String str = tf_amount.getText();
				int x = Integer.parseInt(str);
				int bal= Integer.parseInt(str7);
				int new_balance = x +  bal;
				System.out.println(new_balance);
				
				if(tf_amount.getText() == null)
				{
					JOptionPane.showMessageDialog(null, "Please Enter Amount");
				}
				else
				{
					Connection conn = null;
					try 
					{
						Class.forName("com.mysql.cj.jdbc.Driver");
						conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root@123");
						Statement stmt =  conn.createStatement();
						String sql = "update users " + " set wallet = '" + new_balance +"' " +"where user_id='"+ str_main +"' "; 		
						stmt.executeUpdate(sql);
						System.out.println("Update done");
						JOptionPane.showMessageDialog(null, "Amount added succesfully");
						MainPage mp = new MainPage();
						mp.lbl_user_id.setText(str_main);
						mp.lbl_bal.setText(String.valueOf(new_balance));
						MainPage mp2 = new MainPage(str_main);
						mp.setVisible(true);
						dispose();
						conn.close();
					} 
					catch (Exception e) 
					{
						System.out.println(e);
					}
				}
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setBounds(147, 357, 106, 36);
		contentPane.add(btnAdd);
		
		JLabel lblUserName = new JLabel("User Name :-");
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUserName.setBounds(68, 135, 151, 63);
		contentPane.add(lblUserName);
		
		lbl_user = new JLabel("username");
		lbl_user.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_user.setBounds(239, 140, 89, 53);
		contentPane.add(lbl_user);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.LIGHT_GRAY);
		menuBar.setBounds(0, 0, 403, 40);
		contentPane.add(menuBar);
		
		JMenuItem mntmQuickCab = new JMenuItem("Home");
		mntmQuickCab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainPage mp = new MainPage();
				mp.lbl_user_id.setText(str_main);
				mp.lbl_bal.setText(str7);
				MainPage mp2 = new MainPage(str_main);
				mp.setVisible(true);
				dispose();
			}
		});
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
