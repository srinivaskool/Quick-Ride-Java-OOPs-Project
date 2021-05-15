import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Book_Cab extends JFrame {

	private JPanel contentPane;

	JLabel lbl_bal;
	JLabel lbl_user;
	static String str7,str2,str_main;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Book_Cab frame = new Book_Cab();
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
	
public Book_Cab(String str) {
		
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
	
	
	public Book_Cab() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 421, 497);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.desktop);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblBookACab = new JLabel("Book a cab");
		lblBookACab.setForeground(Color.WHITE);
		lblBookACab.setFont(new Font("SansSerif", Font.BOLD, 24));
		lblBookACab.setBounds(124, 48, 143, 45);
		contentPane.add(lblBookACab);
		
		JLabel lblHi = new JLabel("User Name :-");
		lblHi.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblHi.setBounds(59, 113, 133, 28);
		contentPane.add(lblHi);
		
		lbl_user = new JLabel("User Id");
		lbl_user.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lbl_user.setBounds(205, 113, 143, 28);
		contentPane.add(lbl_user);
		
		JLabel lblBbalance = new JLabel("Balance :-");
		lblBbalance.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblBbalance.setBounds(59, 151, 118, 28);
		contentPane.add(lblBbalance);
		
		lbl_bal = new JLabel("0");
		lbl_bal.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lbl_bal.setBounds(205, 154, 56, 28);
		contentPane.add(lbl_bal);
		
		JLabel lblRs_1 = new JLabel("Rs");
		lblRs_1.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblRs_1.setBounds(267, 151, 56, 28);
		contentPane.add(lblRs_1);
		
		final JComboBox pick = new JComboBox();
		pick.setBackground(Color.LIGHT_GRAY);
		pick.setBounds(107, 234, 181, 33);
		pick.addItem("Secunderabad");
		pick.addItem("BPHC");
		pick.addItem("Nampally");
		pick.addItem("Lingampally");
		pick.addItem("Kompally");
		pick.setSelectedItem(null);;
		contentPane.add(pick);
		
		JLabel lblNewLabel = new JLabel("Pick up");
		lblNewLabel.setBounds(107, 214, 56, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblDropPoint = new JLabel("Drop point");
		lblDropPoint.setBounds(107, 280, 71, 16);
		contentPane.add(lblDropPoint);
		
		final JComboBox drop = new JComboBox();
		drop.setBackground(Color.LIGHT_GRAY);
		drop.setBounds(107, 300, 181, 33);
		drop.addItem("Secunderabad");
		drop.addItem("BPHC");
		drop.addItem("Nampally");
		drop.addItem("Lingampally");
		drop.addItem("Kompally");
		drop.setSelectedItem(null);;
		contentPane.add(drop);
		
		JButton btnNewButton = new JButton("Ride Now");
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String pick_up = pick.getSelectedItem().toString();
				String drop_point =  drop.getSelectedItem().toString();
				
				if(drop_point == null || pick_up == null)
				{
					JOptionPane.showMessageDialog(null, "There cant be empty pick up or drop point");
				}
				else if(pick_up.equals(drop_point))
				{
					JOptionPane.showMessageDialog(null, "Pick up an drop points are same please check.");
				}
				else
				{
					System.out.println("pick up : " + pick_up + " drop off " + drop_point);
					
					Confirm_Cab cc = new Confirm_Cab();
					cc.lbl_bal.setText(str7);
					cc.lbl_user.setText(str2);

					cc.lbl_pick.setText(pick_up);
					cc.lbl_drop.setText(drop_point);
					Confirm_Cab cc2 = new Confirm_Cab(str_main,pick_up,drop_point);
					
					try {
						Connection conn = null;
						try {
							Class.forName("com.mysql.cj.jdbc.Driver");
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
					    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root@123");
						Statement stmt =  conn.createStatement();
						String sql = "Select * from points where pick='"+ pick_up +"' and drop_point='"+ drop_point +"'";
						ResultSet rs2 = stmt.executeQuery(sql); 
						if(rs2.next())
						{
							cc.lbl_journey_time.setText(rs2.getString(4));
							cc.lbl_fare.setText(rs2.getString(5));
							System.out.println("main page drop and pick up :" + " "+  rs2.getString(4) + " " + rs2.getString(5));
						}
						else
						{
							System.out.println("Some error");
						}
						conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					
					
					
					cc.setVisible(true);
					dispose();
					
				}
			}
		});
		btnNewButton.setBounds(124, 367, 143, 45);
		contentPane.add(btnNewButton);
		
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
