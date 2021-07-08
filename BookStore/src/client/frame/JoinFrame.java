package client.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import client.program.ClientHandler;
import customer.CustomerVO;
import message.CustomerMessage;

public class JoinFrame extends JFrame implements ActionListener, KeyListener{
	JPanel joinPanel;
	JLabel joinL;
	JLabel idL;
	JLabel pwdL;
	JLabel pwdOkL;
	//비밀번호 확인 라벨
	JLabel pwdChekL;
	JLabel nameL;
	JLabel addressL;
	JLabel phoneL;
	
	JTextField idField;
	//패스워드 모양 :****
	JPasswordField pwdField;
	JPasswordField pwdOkField;
	JTextField nameField;
	JTextField addressField;
	JTextField phoneField;
	
	JButton idCheckBt;
	JButton joinBt;
	JButton cancelBt;
	
	int idCheck;
	int pwdCheck;
	
	public JoinFrame() {
		this.setTitle("회원가입");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(400, 200, 350, 450);
		this.setLayout(null); //absoulute
		setComponent();
		this.setVisible(true);
		
	}
	
	public void setComponent() {
		joinPanel = new JPanel();
		joinPanel.setLayout(null);
		
		idL = new JLabel();
		idL.setText("아이디");
		pwdL = new JLabel();
		pwdL.setText("비밀번호");
		pwdOkL = new JLabel();
		pwdOkL.setText("비밀번호 확인");
		pwdChekL = new JLabel();
//		pwdChekL.setText("");
		nameL = new JLabel();
		nameL.setText("이름");
		addressL = new JLabel();
		addressL.setText("주소");
		phoneL = new JLabel();
		phoneL.setText("핸드폰");
		joinL = new JLabel();
		joinL.setText("회원가입");
		
		
		
		joinL.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		joinL.setHorizontalAlignment(JLabel.CENTER);
		
		idField = new JTextField();
		pwdField = new JPasswordField();
		pwdOkField = new JPasswordField();
		nameField = new JTextField();
		addressField = new JTextField();
		phoneField = new JTextField();
		
		idCheckBt = new JButton();
		idCheckBt.setText("중복 확인");
		joinBt = new JButton();
		joinBt.setText("가입");
		cancelBt = new JButton();
		cancelBt.setText("취소");
		
		
		joinL.setBounds(56, 22, 227, 44);
		idL.setBounds(12, 84, 85, 32);
		pwdL.setBounds(12, 126, 85, 32);
		pwdOkL.setBounds(12, 170, 85, 32);
		
		nameL.setBounds(12, 213, 85, 32);
		addressL.setBounds(12, 255, 85, 32);
		phoneL.setBounds(12, 297, 85, 32);
		
		idField.setBounds(98, 90, 103, 21);
		pwdField.setBounds(98, 133, 171, 21);
		
		pwdChekL.setBounds(98, 198, 150, 15);
		
		pwdOkField.setBounds(98, 176, 171, 21);
		nameField.setBounds(98, 219, 171, 21);
		addressField.setBounds(98, 261, 171, 21);
		phoneField.setBounds(98, 303, 171, 21);
		
		idCheckBt.setBounds(213, 89, 97, 23);
		joinBt.setBounds(56, 345, 97, 35);
		cancelBt.setBounds(186, 345, 97, 35);
		
		joinPanel.add(joinL);
		joinPanel.add(idL);
		joinPanel.add(pwdL);
		joinPanel.add(pwdOkL);
		joinPanel.add(pwdChekL);
		joinPanel.add(nameL);
		joinPanel.add(addressL);
		joinPanel.add(phoneL);
		joinPanel.add(idField);
		joinPanel.add(pwdField);
		joinPanel.add(pwdOkField);
		joinPanel.add(nameField);
		joinPanel.add(addressField);
		joinPanel.add(phoneField);
		joinPanel.add(idCheckBt);
		joinPanel.add(joinBt);
		joinPanel.add(cancelBt);
		
		this.setContentPane(joinPanel);
		eventList();
	}
	
	
	public void eventList() {
		cancelBt.addActionListener(this);	//회원가입취소버튼
		idCheckBt.addActionListener(this);	//아이디중복체크버튼
		joinBt.addActionListener(this);		//회원가입버튼
		pwdOkField.addKeyListener(this);	//패스워드 확인
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(cancelBt == e.getSource()) {
			dispose();
		}else if(idCheckBt == e.getSource()) {
			//id 입력 -> 서버로 id를 보내야하는 상황
			String id = idField.getText();
			if(id.equals("")) {
				JOptionPane.showConfirmDialog(null, "아이디를 입력하세요.",
						"경고",JOptionPane.DEFAULT_OPTION);
				idField.requestFocus();
				return;
			}
			CustomerMessage cmsg = new CustomerMessage();
			CustomerVO cvo = new CustomerVO();
			cvo.setCustId(id);
			cmsg.setCvo(cvo);
			cmsg.setState(1);//1 : id check	
			
			
			//write
			ObjectOutputStream oos =  ClientHandler.oos;
			try {
				oos.writeObject(cmsg);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			
		}else if(joinBt == e.getSource()) {
			if(idCheck==0) {	//id체크 안하고 회원가입 하려고 할 때
				JOptionPane.showConfirmDialog(null, "아이디 중복 체크 해주세요.","경고",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
				return;
			}
			if(pwdField.getText().equals("") ) {	//비밀번호를 입력하지 않고 회원가입 하려고 할 때
				JOptionPane.showConfirmDialog(null, "비밀번호를 입력하세요","경고",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
				pwdField.requestFocus();
				return;
			}
			if(pwdOkField.getText().equals("") ) {	//비밀번호 확인 안 하고 회원가입 하려고 할 때
				JOptionPane.showConfirmDialog(null, "비밀번호 확인을 입력하세요","경고",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
				pwdField.requestFocus();
				return;
			}
			if(pwdCheck==0 ) {	//비밀번호 확인이랑 비밀번호가 맞지 않을 때
				JOptionPane.showConfirmDialog(null, "비밀번호 확인이 필요합니다.","경고",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			
			CustomerMessage cmsg = new CustomerMessage();
			CustomerVO cvo = new CustomerVO();
			
			cvo.setCustId(idField.getText());
			cvo.setPwd(pwdField.getText()); 
			cvo.setName(nameField.getText());
			cvo.setAddress(addressField.getText());
			cvo.setPhone(phoneField.getText());
			
			cmsg.setState(2);
			cmsg.setCvo(cvo);
			try {
				ClientHandler.oos.writeObject(cmsg);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
	}
	
	
	public static void main(String[] args) {
		new JoinFrame();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	//키보드 입력 이벤트
	@Override
	public void keyReleased(KeyEvent e) {
		
		String pwd = pwdField.getText();
		String pwdOk = pwdOkField.getText();
		if(pwd.equals(pwdOk)) {
			//폰트 색상
			pwdChekL.setForeground(Color.black);
			pwdChekL.setText("비밀번호 확인 완료");
			pwdCheck=1;
		}else {
			//폰트 색상
			pwdChekL.setForeground(Color.red);
			pwdChekL.setText("비밀번호가 맞지 않습니다.");
			pwdCheck=0;
		}
		
	}
	
	
	
	//아이디 중복체크 결과 처리
	public void idCheckResult(int result) {
		if(result == 0) {
			//result == 0
			//사용가능한 아이디 입니다.
			JOptionPane.showMessageDialog(null, "사용가능한 아이디 입니다.");
			idCheck = 1;
		}else {
			//result ==? 
			//이미존재하는 아이디 입니다.
			//idField에 있는 값을 지원주고 포커스 맞춰준다.
			JOptionPane.showConfirmDialog(null, "이미 존재하는 아이디 입니다.","경고",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
			idField.setText("");
			idField.requestFocus();
			idCheck = 0;
		}
	}
	
	//회원가입 결과 처리
	public void joinCheck(CustomerMessage cm) {
		if(cm.getResult()!=0) {
			JOptionPane.showMessageDialog(null, "가입이 완료되었습니다.");
			dispose();
			
		}else {
			JOptionPane.showConfirmDialog(null, "잘못된 정보를 입력하였습니다.","경고",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
		}
	}
	
	
	
	
	
	
}
