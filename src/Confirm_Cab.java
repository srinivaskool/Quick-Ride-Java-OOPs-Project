import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.print.DocFlavor.STRING;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Confirm_Cab extends JFrame {

	private JPanel contentPane;
	JLabel lbl_bal;
	JLabel lbl_user;
	JLabel lbl_pick;
	JLabel lbl_drop;
	JLabel lbl_journey_time;
	JLabel lbl_fare;
	static String str7,str2,str_main,pick_up_point,drop_off_point,user_status;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Confirm_Cab frame = new Confirm_Cab();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
public Confirm_Cab(String str,String pick_up,String drop_point) {
		
		str_main = str;
		pick_up_point = pick_up;
		drop_off_point = drop_point;
		
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
			System.out.println("confirm page user id :" + str + " "+  rs.getString(7));
			str7 = rs.getString(7);
			str2 = rs.getString(2);
			user_status = rs.getString(8);
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
	
	public Confirm_Cab() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 421, 497);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBackground(SystemColor.desktop);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblHi = new JLabel("User Id :-");
		lblHi.setForeground(Color.WHITE);
		lblHi.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblHi.setBounds(42, 110, 118, 28);
		contentPane.add(lblHi);
		
		lbl_user = new JLabel("User Id");
		lbl_user.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lbl_user.setBounds(161, 110, 143, 28);
		contentPane.add(lbl_user);
		
		JLabel lblBbalance = new JLabel("Balance :-");
		lblBbalance.setForeground(Color.WHITE);
		lblBbalance.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblBbalance.setBounds(42, 148, 118, 28);
		contentPane.add(lblBbalance);
		
		lbl_bal = new JLabel("0");
		lbl_bal.setBackground(Color.CYAN);
		lbl_bal.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lbl_bal.setBounds(161, 148, 56, 28);
		contentPane.add(lbl_bal);
		
		JLabel lblNewLabel = new JLabel("Rs");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNewLabel.setBounds(221, 150, 56, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Confirm Cab");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 23));
		lblNewLabel_1.setBounds(110, 53, 153, 34);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblPickup = new JLabel("Pickup :-");
		lblPickup.setForeground(Color.WHITE);
		lblPickup.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblPickup.setBounds(42, 204, 118, 28);
		contentPane.add(lblPickup);
		
		lbl_pick = new JLabel("pick point");
		lbl_pick.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lbl_pick.setBounds(161, 204, 230, 28);
		contentPane.add(lbl_pick);
		
		JLabel lblDrop = new JLabel("Drop :-");
		lblDrop.setForeground(Color.WHITE);
		lblDrop.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblDrop.setBounds(42, 245, 118, 28);
		contentPane.add(lblDrop);
		
		lbl_drop = new JLabel("drop point");
		lbl_drop.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lbl_drop.setBounds(161, 245, 230, 28);
		contentPane.add(lbl_drop);
		
		JLabel label = new JLabel("");
		label.setForeground(Color.WHITE);
		label.setBounds(42, 274, 56, 16);
		contentPane.add(label);
		
		JLabel lblJourneyTime = new JLabel("Journey Time :-");
		lblJourneyTime.setForeground(Color.WHITE);
		lblJourneyTime.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblJourneyTime.setBounds(42, 300, 175, 28);
		contentPane.add(lblJourneyTime);
		
		lbl_journey_time = new JLabel("0 min");
		lbl_journey_time.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lbl_journey_time.setBounds(221, 300, 108, 28);
		contentPane.add(lbl_journey_time);
		
		lbl_fare = new JLabel("0 Rs");
		lbl_fare.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lbl_fare.setBounds(161, 341, 143, 28);
		contentPane.add(lbl_fare);
		
		JLabel lblFare = new JLabel("Fare :-");
		lblFare.setForeground(Color.WHITE);
		lblFare.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblFare.setBounds(42, 341, 118, 28);
		contentPane.add(lblFare);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setBackground(Color.BLACK);
		btnConfirm.setForeground(Color.WHITE);
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("status is " + user_status);
				if(Integer.parseInt(user_status) != 0)
				{
					JOptionPane.showMessageDialog(null, "You are already on trip.");
				}
				else
				{
					Trip tr = new Trip();
					Trip tr2 = new Trip(str_main,pick_up_point,drop_off_point);
					dispose();
					
					Connection conn = null;
					try 
					{
						Class.forName("com.mysql.cj.jdbc.Driver");
						conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root@123");
						Statement stmt =  conn.createStatement();
						String sql = "update users " + " set status = '" + 1 +"' " +"where user_id='"+ str_main +"' "; 		
						stmt.executeUpdate(sql);
						System.out.println("Update done");
						conn.close();
					} 
					catch (Exception e) 
					{
						System.out.println(e);
					}
				}
			}
		});
		btnConfirm.setFont(new Font("Tahoma", Font.PLAIN, 23));
		btnConfirm.setBounds(133, 382, 133, 42);
		contentPane.add(btnConfirm);
		
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
