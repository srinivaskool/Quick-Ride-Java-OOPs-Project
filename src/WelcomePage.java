import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class WelcomePage extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomePage frame = new WelcomePage();
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
	public WelcomePage() {
		getContentPane().setBackground(SystemColor.desktop);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 421, 497);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblWelcomeToQuick = new JLabel("Welcome To Quick Ride");
		lblWelcomeToQuick.setBackground(SystemColor.textText);
		lblWelcomeToQuick.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblWelcomeToQuick.setForeground(SystemColor.menuText);
		lblWelcomeToQuick.setBounds(82, 27, 260, 33);
		getContentPane().add(lblWelcomeToQuick);
		
		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.setFocusable(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				User_Login ul = new User_Login();
				dispose();
				ul.setVisible(true);
				
			}
		});
		btnNewButton.setBounds(127, 319, 150, 33);
		btnNewButton.setForeground(Color.MAGENTA);
		btnNewButton.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		getContentPane().add(btnNewButton);
		
		JButton btnRegister = new JButton("REGISTER");
		btnRegister.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		btnRegister.setForeground(Color.MAGENTA);
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				User_Register ug = new User_Register();
				dispose();
				ug.setVisible(true);
			}
		});
		btnRegister.setBounds(127, 365, 150, 33);
		getContentPane().add(btnRegister);
		
		JLabel label = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/Quickride.png")).getImage();
		label.setIcon (new ImageIcon (img));
		label.setBounds(27, 73, 826, 363);
		getContentPane().add(label);
	}

}
