package client.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.program.ClientHandler;
import customer.CustomerVO;
import message.CustomerMessage;

public class LoginFrame extends JFrame implements ActionListener{
	JPanel loginPanel;
	JLabel loginL;
	JLabel idL;
	JLabel pwdL;
	
	JTextField idField;
	JTextField pwdField;
	
	JButton loginBt;
	JButton joinBt;
	
	public JoinFrame join;
	public BookStoreFrame bookStore;
	String id;
	
	public LoginFrame() {
		
		this.setTitle("로그인");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(200, 200, 300, 200);
		this.setLayout(null); //absoulute
		setComponent();
		this.setVisible(true);
		
		
	}
	
	public void setComponent() {
		loginPanel = new JPanel();
		loginPanel.setLayout(null);
		loginL = new JLabel();
		idL = new JLabel();
		pwdL = new JLabel();
		
		idField = new JTextField();
		pwdField = new JTextField();
		
		loginBt = new JButton();
		joinBt = new JButton();
		
		loginL.setText("로 그 인");
		idL.setText("아 이 디 : ");
		pwdL.setText("비 밀 번 호 : ");
		
		loginBt.setText("로그인");
		joinBt.setText("회원가입");

		loginL.setHorizontalAlignment(JLabel.CENTER);
		
		
		loginPanel.setBounds(0, 0, 300, 230);
		loginL.setBounds(0, 5, 300, 15);
		
		idL.setBounds(20, 25, 100, 30);
		pwdL.setBounds(20, 65, 100, 30);
		
		idField.setBounds(120, 25, 150, 30);
		pwdField.setBounds(120, 65, 150, 30);
		
		loginBt.setBounds(50, 115, 90, 30);
		joinBt.setBounds(150, 115, 90, 30);
		
		loginPanel.add(loginL);
		loginPanel.add(idL);
		loginPanel.add(pwdL);
		loginPanel.add(loginBt);
		loginPanel.add(joinBt);
		loginPanel.add(idField);
		loginPanel.add(pwdField);
		
		this.setContentPane(loginPanel);
		
		eventList();
		
	}
	
	public void eventList() {
		joinBt.addActionListener(this);
		loginBt.addActionListener(this);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(joinBt == e.getSource()) {
			join = new JoinFrame();
		}else if(loginBt == e.getSource()) {
			String custId = idField.getText();
			String password = pwdField.getText();
			
			if(custId.equals("") ||password.equals("") ) {
				JOptionPane.showConfirmDialog(null, "계정을 입력하세요","경고",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			
			CustomerMessage cmsg = new CustomerMessage();
			CustomerVO cvo = new CustomerVO();
			cvo.setCustId(custId);
			cvo.setPwd(password);
			cmsg.setState(3);
			cmsg.setCvo(cvo);
			id = idField.getText();
			try {
				ClientHandler.oos.writeObject(cmsg);
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
	//로그인 성공
	public void loginResult(CustomerMessage cm) {
		
		
		if(cm.getResult()!=0) {
			bookStore=null;
			JOptionPane.showMessageDialog(null, "로그인 성공");
			
			//마당 서적 창 띄움
			bookStore = new BookStoreFrame(this, id); 
			//로그인창 꺼둠
			dispose();
			
		}else {
			JOptionPane.showConfirmDialog(null, "계정이 잘못되었습니다. 확인하세요.","경고",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
			
		}
	}
	
	
	
	
	
	
//	public static void main(String[] args) {
//		new LoginFrame();
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
