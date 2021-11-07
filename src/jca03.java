import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class jca03 {

	private JFrame frame;
	private JTextField textpname;
	private JTextField txtprice;
	private JTextField txtcity;
	private JTextField txtpid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					jca03 window = new jca03();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public jca03() {
		initialize();
		Connect();
	}
	Connection con;
	PreparedStatement pst;
	
	 
	public void Connect()
	    {
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost/javacrud", "root","");
	        }
	        catch (ClassNotFoundException ex)
	        {
	          ex.printStackTrace();
	        }
	        catch (SQLException ex)
	        {
	            ex.printStackTrace();
	        }
	 
	    }

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setEnabled(false);
		frame.setBounds(100, 100, 670, 404);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Java Crude Application");
		lblNewLabel.setFont(new Font("Serif", Font.PLAIN, 30));
		lblNewLabel.setBounds(0, 11, 302, 31);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Product Name");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 23));
		lblNewLabel_2.setBounds(10, 53, 140, 27);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_2 = new JLabel("City");
		lblNewLabel_2_2.setFont(new Font("Times New Roman", Font.PLAIN, 23));
		lblNewLabel_2_2.setBounds(10, 129, 140, 27);
		frame.getContentPane().add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Price");
		lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 23));
		lblNewLabel_2_1.setBounds(10, 91, 140, 27);
		frame.getContentPane().add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_3 = new JLabel("Product Id");
		lblNewLabel_2_3.setFont(new Font("Times New Roman", Font.PLAIN, 23));
		lblNewLabel_2_3.setBounds(10, 279, 140, 27);
		frame.getContentPane().add(lblNewLabel_2_3);
		
		textpname = new JTextField();
		textpname.setBounds(160, 60, 463, 20);
		frame.getContentPane().add(textpname);
		textpname.setColumns(10);
		
		txtprice = new JTextField();
		txtprice.setBounds(160, 98, 463, 20);
		frame.getContentPane().add(txtprice);
		txtprice.setColumns(10);
		
		txtcity = new JTextField();
		txtcity.setBounds(160, 136, 463, 20);
		frame.getContentPane().add(txtcity);
		txtcity.setColumns(10);
		
		txtpid = new JTextField();
		txtpid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
			          
		            String id = txtpid.getText();
		 
		                pst = con.prepareStatement("select Name,Price,City from product where id = ?");
		                pst.setString(1, id);
		                ResultSet rs = pst.executeQuery();
		 
		            if(rs.next()==true)
		            {
		              
		                String name = rs.getString(1);
		                String price = rs.getString(2);
		                String city = rs.getString(3);
		                
		                textpname.setText(name);
		                txtprice.setText(price);
		                txtcity.setText(city);
		                
		                
		            }  
		            else
		            {
		             textpname.setText("");
		             txtprice.setText("");
		                txtcity.setText("");
		                
		            }
		            
		 
		 
		        }
		catch (SQLException ex) {
		          
		        }
			}
		});
		txtpid.setBounds(158, 273, 463, 20);
		frame.getContentPane().add(txtpid);
		txtpid.setColumns(10);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pname,price,city;
				pname= textpname.getText();
				price=txtprice.getText();
				city=txtcity.getText();
				try {
				pst = con.prepareStatement("insert into product(Name,Price,City)values(?,?,?)");
				pst.setString(1, pname);
				pst.setString(2, price);
				pst.setString(3, city);
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
				textpname.setText("");
				txtprice.setText("");
				txtcity.setText("");
				textpname.requestFocus();
				}
				catch (SQLException e1)
				{
				e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Verdana", Font.PLAIN, 19));
		btnNewButton.setBounds(10, 167, 115, 25);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pid;
				
				
				pid=txtpid.getText();
				
				try {
					pst = con.prepareStatement("delete from product where id=?");
					
					pst.setString(1, pid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Deleted!!!!!");
										          
					textpname.setText("");
					txtprice.setText("");
					txtcity.setText("");
					textpname.requestFocus();
					   }
					 
					catch (SQLException e1)
					        {
					e1.printStackTrace();
					}
			}
		});
		btnNewButton_1.setFont(new Font("Verdana", Font.PLAIN, 19));
		btnNewButton_1.setBounds(10, 203, 115, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Update");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
String pname,price,city,pid;
				
				pname= textpname.getText();
				price=txtprice.getText();
				city=txtcity.getText();
				pid=txtpid.getText();
				
				try {
					pst = con.prepareStatement("update product set Name= ?,Price= ?,City= ? where id=?");
					pst.setString(1, pname);
					pst.setString(2, price);
					pst.setString(3, city);
					pst.setString(4, pid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Updated!!!!!");
										          
					textpname.setText("");
					txtprice.setText("");
					txtcity.setText("");
					textpname.requestFocus();
					   }
					 
					catch (SQLException e1)
					        {
					e1.printStackTrace();
					}
			}
		});
		btnNewButton_2.setFont(new Font("Verdana", Font.PLAIN, 19));
		btnNewButton_2.setBounds(10, 237, 115, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Search");
		btnNewButton_3.setFont(new Font("Verdana", Font.PLAIN, 19));
		btnNewButton_3.setBounds(158, 304, 460, 23);
		frame.getContentPane().add(btnNewButton_3);
	}
}
