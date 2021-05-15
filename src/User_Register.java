import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.sun.org.apache.bcel.internal.generic.I2F;

import java.util.regex.Matcher; 
import java.util.regex.Pattern; 

    
public class User_Register extends JFrame {

	private JPanel contentPane;

	private JFrame frame;
	private JTextField tf_user_name;
	private JTextField email_tf;
	private static JTextField phone_tf;
	private static JTextField tf_user_id;
	private JPasswordField pass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User_Register frame = new User_Register();
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
	
    public static boolean isValid(String email) 
    { 
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
                              
        Pattern pat = Pattern.compile(emailRegex); 
        if (email == null) 
            return false; 
        return pat.matcher(email).matches(); 
    } 
    
    public static boolean validate_phone()
    {
    	if (phone_tf.getText().matches("\\d{10}")) 
    	{
    		return true;
    	}
		return false;
	}
    
    public static boolean validate_id()
    {
    	if (tf_user_id.getText().matches("\\d{6}")) 
    	{
    		return true;
    	}
		return false;
	}
    
	public User_Register() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 421, 497);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.desktop);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel label = new JLabel("User Name");
		label.setBounds(118, 54, 72, 16);
		contentPane.add(label);
		
		tf_user_name = new JTextField();
		tf_user_name.setColumns(10);
		tf_user_name.setBounds(118, 78, 164, 32);
		contentPane.add(tf_user_name);
		
		JLabel label_1 = new JLabel("Register");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Montserrat SemiBold", Font.BOLD, 22));
		label_1.setBackground(Color.YELLOW);
		label_1.setBounds(159, 0, 107, 52);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Email Id");
		label_2.setBounds(118, 123, 72, 16);
		contentPane.add(label_2);
		
		email_tf = new JTextField();
		email_tf.setColumns(10);
		email_tf.setBounds(118, 147, 164, 32);
		contentPane.add(email_tf);
		
		JLabel label_3 = new JLabel("Phone Number");
		label_3.setBounds(118, 192, 90, 16);
		contentPane.add(label_3);
		
		phone_tf = new JTextField();
		phone_tf.setColumns(10);
		phone_tf.setBounds(118, 216, 164, 32);
		contentPane.add(phone_tf);
		
		JLabel label_4 = new JLabel("User ID (six digit number)");
		label_4.setBounds(118, 261, 164, 16);
		contentPane.add(label_4);
		
		tf_user_id = new JTextField();
		tf_user_id.setColumns(10);
		tf_user_id.setBounds(118, 285, 164, 32);
		contentPane.add(tf_user_id);
		
		JLabel label_5 = new JLabel("Password");
		label_5.setBounds(118, 324, 90, 16);
		contentPane.add(label_5);
		
		pass = new JPasswordField();
		pass.setBounds(118, 349, 164, 32);
		contentPane.add(pass);
		
		JButton next_btn = new JButton("Register");
		next_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(isValid(email_tf.getText()))
				{
					if(validate_phone())
					{
						if(validate_id())
						{
							
							Connection conn = null;
							try 
							{
								Class.forName("com.mysql.cj.jdbc.Driver");
								conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root@123");
								
								Statement stmt =  conn.createStatement();
								String sql = "insert into users" + " (name,email,phone,user_id,password) " + " values('"+ tf_user_name.getText() +"','"+ email_tf.getText() +"','"+phone_tf.getText() +"','"+tf_user_id.getText() +"','"+ pass.getText().toString() +"') ";
								stmt.executeUpdate(sql);
								System.out.println("Insertion done");
								conn.close();
								
								JOptionPane.showMessageDialog(null, "Registration Sucessfully");

								String id = tf_user_id.getText();
								
								User_Login uLogin = new User_Login();
								uLogin.setVisible(true);
								dispose();
								
							} 
							catch (Exception e) 
							{
								JOptionPane.showMessageDialog(null, "This Id already exists.Please choose another id.");
							}
							
						}
						else
						{
							JOptionPane.showMessageDialog(null, "User Id needs six digit number");
						}
						
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Please Enter a valid phone number");
					}
					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please Enter a valid email ID");
				}
			}
		});
		next_btn.setForeground(Color.WHITE);
		next_btn.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		next_btn.setBackground(Color.BLACK);
		next_btn.setBounds(139, 399, 116, 38);
		contentPane.add(next_btn);	
		
	}
	
}
