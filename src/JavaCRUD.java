import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.border.CompoundBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.PasswordAuthentication;

public class JavaCRUD {

	private JFrame frame1;
	private JFrame frame2;
	private JTextField textName;
	private JTextField textEn;
	private JTextField textClass;
	private JTextField txtBatch;
	private JTable table;
	private JTextField txtSearch;

	/**
	 * Launch the application.
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					JavaCRUD window = new JavaCRUD();
					IDandPassword idandPassword = new IDandPassword();
					LoginPageStudentRes loginPageStudentRes = new LoginPageStudentRes(idandPassword.logininfo());
//					student_res student_res = new student_res();
//					window.frame1.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JavaCRUD() {
//		initialize();
		 Connect();
		 table_Load();
	}
	

	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	String url = "com.mysql.cj.jdbc.Driver";
	
	
	
	private void Connect() {
		{
			try 
			{
				Class.forName(url);
				con = DriverManager.getConnection("jdbc:mysql://localhost/javacrud", "root", "");
			}
			catch (ClassNotFoundException ex) 
			{
				
			}
			catch(SQLException ex)
			{
				
			}
		}	
	}
	
	public void table_Load() {
		{
			try {
				pst = con.prepareStatement("Select * from student_regs");
				rs = pst.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel (rs));
			}
			catch(SQLException e) {
				e.printStackTrace();
	}
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize2() {
		frame2 = new JFrame();
		frame2.setTitle("CRUD Application");
		frame2.getContentPane().setForeground(new Color(255, 255, 255));
		frame2.getContentPane().setFont(new Font("Bell MT", Font.BOLD, 23));
		frame2.setBounds(100, 100, 745, 516);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.getContentPane().setLayout(null);
		

	
		
	}
	
	private void initialize() {
		frame1 = new JFrame();
		frame1.setTitle("CRUD Application");
		frame1.getContentPane().setForeground(new Color(255, 255, 255));
		frame1.getContentPane().setFont(new Font("Bell MT", Font.BOLD, 23));
		frame1.setBounds(100, 100, 745, 516);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Student Registration Application");
		lblNewLabel.setFont(new Font("Leelawadee", Font.BOLD, 30));
		lblNewLabel.setBounds(125, 11, 471, 54);
		frame1.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(255, 255, 255));
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(25, 99, 346, 230);
		frame1.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Student Name:");
		lblNewLabel_1.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 29, 108, 33);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Enroll No:");
		lblNewLabel_1_1.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(10, 58, 108, 33);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Class:");
		lblNewLabel_1_2.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(10, 89, 108, 26);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Batch:");
		lblNewLabel_1_3.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 14));
		lblNewLabel_1_3.setBounds(10, 118, 108, 33);
		panel.add(lblNewLabel_1_3);
		
		textName = new JTextField();
		textName.setBounds(121, 36, 189, 20);
		panel.add(textName);
		textName.setColumns(10);
		
		textEn = new JTextField();
		textEn.setColumns(10);
		textEn.setBounds(121, 65, 189, 20);
		panel.add(textEn);
		
		textClass = new JTextField();
		textClass.setColumns(10);
		textClass.setBounds(121, 93, 189, 20);
		panel.add(textClass);
		
		txtBatch = new JTextField();
		txtBatch.setColumns(10);
		txtBatch.setBounds(121, 125, 189, 20);
		panel.add(txtBatch);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String StName,stENo,Stclass,stbatch;
				
				
				StName = textName.getText();
				stENo = textEn.getText();
				Stclass = textClass.getText();
				stbatch = txtBatch.getText();
				
				try {
					pst = con.prepareStatement("insert into student_regs(Name,EnrollNo,Class,Batch)values(?,?,?,?)");
					pst.setString(1, StName);
					pst.setString(2, stENo);
					pst.setString(3, Stclass);
					pst.setString(4, stbatch);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Added Successfully");
					table_Load();
					textName.setText("");
					textEn.setText("");
					textClass.setText("");
					txtBatch.setText("");
					textName.requestFocus();
				}
				
				catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(105, 105, 105));
		btnNewButton.setBounds(28, 178, 90, 35);
		panel.add(btnNewButton);
		
		JButton btnDelete = new JButton("Exit");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		btnDelete.setForeground(new Color(255, 255, 255));
		btnDelete.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		btnDelete.setBackground(Color.GRAY);
		btnDelete.setBounds(228, 178, 90, 35);
		panel.add(btnDelete);
		
		JButton btnEdit = new JButton("Clear");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textName.setText("");
				textEn.setText("");
				textClass.setText("");
				txtBatch.setText("");
				textName.requestFocus();
			}
		});
		btnEdit.setForeground(new Color(255, 255, 255));
		btnEdit.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		btnEdit.setBackground(Color.GRAY);
		btnEdit.setBounds(128, 178, 90, 35);
		panel.add(btnEdit);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(381, 108, 330, 215);
		frame1.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search By Name", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(22, 340, 349, 68);
		frame1.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Search Id:");
		lblNewLabel_1_1_1.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(10, 20, 104, 33);
		panel_1.add(lblNewLabel_1_1_1);
		
		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				try {
					String id = txtSearch.getText();
						
						pst = con.prepareStatement("select Name,EnrollNo,Class,Batch from student_regs where id = ? ");
						pst.setString(1, id);
						ResultSet rs = pst.executeQuery();
						
						if(rs.next() == true) {
							String Name = rs.getString(1);
							String EnrollNo = rs.getString(2);
							String Class = rs.getString(3);
							String Batch = rs.getString(4);
							
							textName.setText(Name);
							textEn.setText(EnrollNo);
							textClass.setText(Class);
							txtBatch.setText(Batch);
							
							
						}
						else {
							textName.setText("");
							textEn.setText("");
							textClass.setText("");
							txtBatch.setText("");
							
						}
				}
				catch (SQLException SE) {
					// TODO: handle exception
				} {
					
				}
				
				
			}
		});
		txtSearch.setColumns(10);
		txtSearch.setBounds(84, 27, 202, 20);
		panel_1.add(txtSearch);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Author", TitledBorder.LEFT, TitledBorder.BOTTOM, null, new Color(0, 0, 0)));
		panel_2.setBounds(381, 388, 330, 62);
		frame1.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Name: Rizvi Ahmed Abbas");
		lblNewLabel_2.setFont(new Font("Sitka Small", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(10, 11, 310, 32);
		panel_2.add(lblNewLabel_2);
		
		JButton UpdateButton = new JButton("Update");
		UpdateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String StName,stENo,Stclass,stbatch,bid;
				
				
				StName = textName.getText();
				stENo = textEn.getText();
				Stclass = textClass.getText();
				stbatch = txtBatch.getText();
				bid = txtSearch.getText();
				
				
				try {
					pst = con.prepareStatement("update student_regs set Name = ?,EnrollNo = ?,Class = ?,Batch = ? where id = ?");
					pst.setString(1, StName);
					pst.setString(2, stENo);
					pst.setString(3, Stclass);
					pst.setString(4, stbatch);
					pst.setString(5, bid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Updated Successfully!");
					table_Load();
					textName.setText("");
					textEn.setText("");
					textClass.setText("");
					txtBatch.setText("");
					textName.requestFocus();
				}
				
				catch(SQLException e1) {
					e1.printStackTrace();
				}
				
				
			}
		});
		UpdateButton.setForeground(Color.WHITE);
		UpdateButton.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		UpdateButton.setBackground(SystemColor.controlDkShadow);
		UpdateButton.setBounds(438, 334, 90, 35);
		frame1.getContentPane().add(UpdateButton);
		
		JButton btnDelete_1 = new JButton("Delete");
		btnDelete_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String bid;
				
				
				bid = txtSearch.getText();
				
				
				try {
					pst = con.prepareStatement("delete from student_regs where id = ?");
					pst.setString(1, bid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Deleted Successfully!");
					table_Load();
					textName.setText("");
					textEn.setText("");
					textClass.setText("");
					txtBatch.setText("");
					textName.requestFocus();
				}
				
				catch(SQLException e1) {
					e1.printStackTrace();
				}
				
				
			}
			
		});
		btnDelete_1.setForeground(Color.WHITE);
		btnDelete_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		btnDelete_1.setBackground(Color.GRAY);
		btnDelete_1.setBounds(544, 334, 90, 35);
		frame1.getContentPane().add(btnDelete_1);
		
		JLabel lblNewLabel_3 = new JLabel("Student Table");
		lblNewLabel_3.setFont(new Font("Sitka Small", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(475, 76, 163, 24);
		frame1.getContentPane().add(lblNewLabel_3);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initialize2();
			}
		});
		btnNewButton_1.setBounds(32, 427, 89, 23);
		frame1.getContentPane().add(btnNewButton_1);
	}
}
