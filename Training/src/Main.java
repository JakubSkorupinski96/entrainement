import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;

import dao.ComputerDAO;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener; 


public class Main extends Frame implements ActionListener, WindowListener {

	private final static String URL = "jdbc:mysql://localhost:3306/computer-database-db";
	private final static String USER = "root";
	private final static String PASS = "";
	
	private Label pcNameLabel;     
	private TextField nameTF; 
	private Button pcBouton;   
	private int count = 0;   
	
	public Main () {
		setLayout(new FlowLayout());
		addWindowListener(this);
		 
		pcNameLabel = new Label("PC Name");  
		add(pcNameLabel);                    
		 
		nameTF = new TextField(count + "", 10); 
		nameTF.setEditable(false);       
		add(nameTF);                    
		      
		pcBouton = new Button("Search");  
		add(pcBouton);                   
		 
		pcBouton.addActionListener(this);
		 
		setTitle("User Interface"); 
		setSize(500, 300);        
		 
		setVisible(true);        	 
	}
	
	
	public static void main(String[] args) {
			
		Main app = new Main();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			System.out.println("First catch");
			e1.printStackTrace();
		}
		
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			//dao.Computer.createComputer(conn,"IBM 5100",null,null,1);
			dao.ComputerDAO.deleteComputer(conn,"IBM 5100");
			dao.ComputerDAO.listDetails(conn, "IBM 5100");
			dao.CompanyDAO.listCompanies(conn);
			dao.ComputerDAO.listComputers(conn);
		} catch (SQLException e) {
			System.out.println("lol");
			e.printStackTrace();
		}
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowClosing(WindowEvent arg0) {
		System.exit(0);
		
	}


	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	 

}
