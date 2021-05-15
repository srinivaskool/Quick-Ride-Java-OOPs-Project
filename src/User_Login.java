import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTextField;


public class User_Login extends JFrame {

	private JPanel contentPane;
	private JPasswordField pass;
	private JTextField tf_user_id;
	String str7;
	/**
	 * Launch the application.
	 */
	
	static User_Login frame = new User_Login();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
						frame.setVisible(true);
						frame.setTitle("Quick Ride");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public User_Login() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 421, 497);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.desktop);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		pass = new JPasswordField();
		pass.setBounds(103, 177, 164, 32);
		contentPane.add(pass);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Connection conn = null;
				
				try 
				{
					  Class.forName("com.mysql.cj.jdbc.Driver");
					  conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root@123");

    				Statement stmt =  conn.createStatement();
					String sql = "Select * from users where user_id='"+ tf_user_id.getText() +"' and password= '"+ pass.getText().toString() +"' ";
					ResultSet rs = stmt.executeQuery(sql);
					if(rs.next())
					{
						JOptionPane.showMessageDialog(null, "Login Sucessfully");
						String str = tf_user_id.getText();
						MainPage mp = new MainPage();
						mp.lbl_user_id.setText(tf_user_id.getText());
						str7 = rs.getString(7);
						mp.lbl_bal.setText(str7);
						MainPage mp2 = new MainPage(tf_user_id.getText());
						mp.setVisible(true);
						dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Incorrect login details");
					}
					conn.close();
					
				} catch (Exception e) {
					System.out.println(e);
				}
				
			}
		});
		btnLogin.setFont(new Font("Work Sans Medium", Font.PLAIN, 16));
		btnLogin.setBounds(139, 264, 103, 32);
		contentPane.add(btnLogin);
		
		tf_user_id = new JTextField();
		tf_user_id.setColumns(10);
		tf_user_id.setBounds(103, 102, 164, 32);
		contentPane.add(tf_user_id);
		
		JLabel label_1 = new JLabel("User Id");
		label_1.setBounds(103, 78, 72, 16);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Password");
		label_2.setBounds(103, 158, 72, 16);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("Login");
		label_3.setFont(new Font("Poppins Light", Font.BOLD, 18));
		label_3.setBounds(154, 25, 66, 40);
		contentPane.add(label_3);
		
	}

}
