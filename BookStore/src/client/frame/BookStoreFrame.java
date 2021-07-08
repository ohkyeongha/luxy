package client.frame;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import book.BookVO;
import client.program.ClientHandler;
import message.BookMessage;
import message.Message;
import message.OrdersMessage;
import orders.OrdersVO;

public class BookStoreFrame extends JFrame implements ActionListener, MouseListener {

	// 전체 패널
	// 배경 이미지 삽입
	static JPanel totalPanel = new JPanel() {
		//이미지 읽어오기
		Image backGroundImg = new ImageIcon("./img/backgound.png").getImage();
		//창 크기에 맞게 변경
		Image scaledBackGroundImg = backGroundImg.getScaledInstance(800, 550, Image.SCALE_DEFAULT);
		Image backGroundFinalImg = new ImageIcon(scaledBackGroundImg).getImage();
		
		public void paintComponent(Graphics g) {			//그리는 함수
			g.drawImage(backGroundFinalImg, 0, 0, null);	//backgroundImg를 그려줌	
			setOpaque(false); 								//투명하게
            super.paintComponent(g);

		}
	};	

	
	// 책 정보 패널
	JPanel bookDetailPanel;
	// 주문 정보 패널
	JPanel ordersDetailPanel;
	// tab 패널
	JTabbedPane tab;

	// 제목, 아이디, 로그아웃 버튼
	JLabel titleL;
	JLabel custIdL;
	JButton logOutBt;

	// book tab 테이블
	DefaultTableModel bookDTM;
	JTable bookTable;
	JScrollPane bookScroll;

	// order tab 테이블
	DefaultTableModel ordersDTM;
	JTable ordersTable;
	JScrollPane ordersScroll;

	// 책 정보 패널 내용
	JLabel imgL;
	JLabel imgVal;
	JLabel bookIdVal;
	JLabel bookNameL;
	JLabel bookNameVal;
	JLabel publisherL;
	JLabel publisherVal;
	JLabel priceL;
	JLabel priceVal;
	JSpinner cnt;
	JButton orderBt;

	// 주문 정보 패널 내용
	JLabel orderIdL;
	JLabel orderBookNameL;
	JLabel orderSalePriceL;
	JLabel orderCntL;
	JLabel orderDateL;

	JLabel orderIdVal;
	JLabel orderBookNameVal;
	JLabel orderSalePriceVal;
	JLabel orderCntVal;
	JLabel orderDateVal;

	JButton orderCancelBt;

	LoginFrame login;
	String id;
	int bookRow;
	int ordersRow;

	public BookStoreFrame(LoginFrame login, String id) {
		this.login = login;
		this.id = id;

		this.setTitle("마당 서점");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setBounds(200, 200, 800, 550);
		this.setLayout(new FlowLayout());
		// 화면구성
		setComponent();

		// 초기 데이터 설정
		initTableRequest();

		// 이벤트 모음
		eventList();

		this.setVisible(true);
	}

	public void setComponent() {
//		totalPanel = new JPanel() {
//			
//		}
		totalPanel.setLayout(null);

		bookDetailPanel = new JPanel();
		bookDetailPanel.setLayout(null);
		bookDetailPanel.setBorder(new EtchedBorder());

		ordersDetailPanel = new JPanel();
		ordersDetailPanel.setLayout(null);
		ordersDetailPanel.setBorder(new EtchedBorder());

		tab = new JTabbedPane();

		titleL = new JLabel();
		titleL.setText("마 당 서 점");
		titleL.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		titleL.setHorizontalAlignment(JLabel.CENTER);
		custIdL = new JLabel();
		custIdL.setText(id + "님 환영합니다.");
		logOutBt = new JButton();
		logOutBt.setText("로그아웃");

		String[] bookCol = { "책번호", "책이름", "출판사", "가격", "이미지경로" };
		bookDTM = new DefaultTableModel(bookCol, 0);
		bookTable = new JTable(bookDTM);

		// 열 숨기기
		bookTable.getColumn("이미지경로").setWidth(0);
		bookTable.getColumn("이미지경로").setMinWidth(0);
		bookTable.getColumn("이미지경로").setMaxWidth(0);

		bookScroll = new JScrollPane(bookTable);
		String[] ordersCol = { "주문번호", "책이름", "주문가격", "수량", "주문일자", "책번호" };
		ordersDTM = new DefaultTableModel(ordersCol, 0);
		ordersTable = new JTable(ordersDTM);
		ordersScroll = new JScrollPane(ordersTable);

		// 열 숨기기
		ordersTable.getColumn("책번호").setWidth(0);
		ordersTable.getColumn("책번호").setMinWidth(0);
		ordersTable.getColumn("책번호").setMaxWidth(0);

		// 책 내역
		imgL = new JLabel();
		imgVal = new JLabel();
		imgL.setBorder(new EtchedBorder());
		bookIdVal = new JLabel();
		bookNameL = new JLabel();
		bookNameL.setText("책이름 :");
		publisherL = new JLabel();
		publisherL.setText("출판사 : ");
		priceL = new JLabel();
		priceL.setText("가 격 : ");
		bookNameVal = new JLabel();
		publisherVal = new JLabel();
		priceVal = new JLabel();
		cnt = new JSpinner();
		orderBt = new JButton();
		orderBt.setText("주문");

		// 주문 내역
		orderIdL = new JLabel();
		orderIdL.setText("주문번호 : ");
		orderBookNameL = new JLabel();
		orderBookNameL.setText("책이름 : ");
		orderSalePriceL = new JLabel();
		orderSalePriceL.setText("주문가격 : ");
		orderCntL = new JLabel();
		orderCntL.setText("주문수량 : ");
		orderDateL = new JLabel();
		orderDateL.setText("주문일자");

		orderIdVal = new JLabel();
		orderBookNameVal = new JLabel();
		orderSalePriceVal = new JLabel();
		orderCntVal = new JLabel();
		orderDateVal = new JLabel();
		orderCancelBt = new JButton();
		orderCancelBt.setText("주문취소");

		totalPanel.setBounds(12, 10, 771, 494);
		bookDetailPanel.setBounds(462, 89, 297, 395);
		ordersDetailPanel.setBounds(462, 89, 297, 395);
		titleL.setBounds(121, 14, 215, 42);
		custIdL.setBounds(635, 15, 100, 20);
		logOutBt.setBounds(645, 35, 90, 25);

		tab.setBounds(30, 66, 420, 418);

		// 책 내역 위치
		imgL.setBounds(67, 10, 171, 165);
		bookNameL.setBounds(22, 199, 75, 31);
		publisherL.setBounds(22, 240, 75, 31);
		priceL.setBounds(22, 281, 75, 31);
		bookNameVal.setBounds(109, 199, 165, 31);
		publisherVal.setBounds(109, 240, 165, 31);
		priceVal.setBounds(109, 281, 165, 31);
		cnt.setBounds(22, 341, 75, 31);
		orderBt.setBounds(109, 338, 108, 36);

		// 주문내역 위치
		orderIdL.setBounds(22, 31, 91, 36);
		orderBookNameL.setBounds(22, 87, 91, 36);
		orderSalePriceL.setBounds(22, 143, 91, 36);
		orderCntL.setBounds(22, 199, 91, 36);
		orderDateL.setBounds(22, 255, 91, 36);
		orderIdVal.setBounds(125, 31, 160, 36);
		orderBookNameVal.setBounds(125, 87, 160, 36);
		orderSalePriceVal.setBounds(125, 143, 160, 36);
		orderCntVal.setBounds(125, 199, 160, 36);
		orderDateVal.setBounds(125, 255, 160, 36);
		orderCancelBt.setBounds(100, 327, 108, 36);

		// tab 패널
		tab.addTab("책 목록", bookScroll);
		tab.addTab("주문 내역", ordersScroll);

		// 책 정보 패널
		bookDetailPanel.add(imgL);
		bookDetailPanel.add(bookNameL);
		bookDetailPanel.add(publisherL);
		bookDetailPanel.add(priceL);
		bookDetailPanel.add(bookNameVal);
		bookDetailPanel.add(publisherVal);
		bookDetailPanel.add(priceVal);
		bookDetailPanel.add(cnt);
		bookDetailPanel.add(orderBt);

		// 주문 정보 패널
		ordersDetailPanel.add(orderIdL);
		ordersDetailPanel.add(orderBookNameL);
		ordersDetailPanel.add(orderSalePriceL);
		ordersDetailPanel.add(orderCntL);
		ordersDetailPanel.add(orderDateL);
		ordersDetailPanel.add(orderIdVal);
		ordersDetailPanel.add(orderBookNameVal);
		ordersDetailPanel.add(orderSalePriceVal);
		ordersDetailPanel.add(orderCntVal);
		ordersDetailPanel.add(orderDateVal);
		ordersDetailPanel.add(orderCancelBt);

		// 전체 패널
		totalPanel.add(titleL);
		totalPanel.add(custIdL);
		totalPanel.add(logOutBt);
		totalPanel.add(tab);
		totalPanel.add(bookDetailPanel);

		this.setContentPane(totalPanel);

	}
	
	public void eventList() {
		logOutBt.addActionListener(this); // 로그아웃
		orderBt.addActionListener(this); // 주문
		bookTable.addMouseListener(this); // 책 테이블 한 행 선택
		ordersTable.addMouseListener(this); // 주문 테이블 한 행 선택
		tab.addMouseListener(this); //책목록,주문내역 탭 선택
		orderCancelBt.addActionListener(this); //주문취소 
	}

	public void change(JPanel beforePanel, JPanel afterPanel) {
		totalPanel.remove(beforePanel);
		totalPanel.add(afterPanel);
		this.setContentPane(totalPanel);
		//새로 화면 구성
		revalidate();
		repaint();
	}

	// 초기데이터 요청
	public void initTableRequest() {
		bookSelectAll();
		orderSelectAll();

	}

	public void bookSelectAll() {
		BookMessage bmsg = new BookMessage();
		bmsg.setState(1);

		try {
			ClientHandler.oos.writeObject(bmsg);
			ClientHandler.oos.flush();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void orderSelectAll() {
		OrdersMessage omsg = new OrdersMessage();
		OrdersVO ovo = new OrdersVO();
		ovo.setCustId(id);
		omsg.setState(1);
		omsg.setOvo(ovo);
		try {
			ClientHandler.oos.writeObject(omsg);
			ClientHandler.oos.flush();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	// 초기데이터 응답
	public void initTableResponse(Message msg) {
		if (msg instanceof BookMessage) {
			BookMessage bmsg = (BookMessage) msg;
			addRowBook(bmsg.getBlist());
		} else if (msg instanceof OrdersMessage) {
			OrdersMessage bmsg = (OrdersMessage) msg;
			addRowOrder(bmsg.getOlist());
		}

	}

	public void addRowBook(ArrayList<BookVO> blist) {
		String[] bookCol = new String[5];
		for (BookVO bvo : blist) {
			bookCol[0] = String.valueOf(bvo.getBookId());
			bookCol[1] = bvo.getBookName();
			bookCol[2] = bvo.getPublisher();
			bookCol[3] = String.valueOf(bvo.getPrice());
			bookCol[4] = bvo.getImgPath();
			bookDTM.addRow(bookCol);

		}
	}

	public void addRowOrder(ArrayList<OrdersVO> olist) {
		String[] OrdersCol = new String[5];
		for (OrdersVO ovo : olist) {
			OrdersCol[0] = String.valueOf(ovo.getOrderId());
			OrdersCol[1] = ovo.getBookName();
			OrdersCol[2] = String.valueOf(ovo.getSalePrice());
			OrdersCol[3] = String.valueOf(ovo.getCount());
			OrdersCol[4] = String.valueOf(ovo.getOrderDate());

			ordersDTM.addRow(OrdersCol);

		}
	}

	// 버튼 클릭 이벤트 함수
	@Override
	public void actionPerformed(ActionEvent e) {

		if (logOutBt == e.getSource()) { // 로그아웃
			id = null;
			totalPanel.removeAll();	//total패널에 있는 컴포넌트 전체 삭제
			login.setVisible(true);
			dispose();
			
			
		} else if (orderBt == e.getSource()) { // 주문

			OrdersMessage omsg = new OrdersMessage();
			OrdersVO ovo = new OrdersVO();

			// 주문수량 X 금액
			int count = Integer.parseInt(cnt.getValue().toString());
			if (count == 0) {
				JOptionPane.showConfirmDialog(null, "수량을 넣어주세요", "경고", JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			int price = Integer.parseInt(priceVal.getText());
			int salePrice = count * price;

			ovo.setBookId(Integer.parseInt(bookIdVal.getText()));
			ovo.setBookName(bookNameVal.getText());
			ovo.setCustId(id);
			ovo.setSalePrice(salePrice);
			ovo.setCount(count);
			omsg.setState(2);
			omsg.setOvo(ovo);
			try {
				ClientHandler.oos.writeObject(omsg);
				ClientHandler.oos.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		} else if (orderCancelBt == e.getSource()) { // 주문취소
			OrdersMessage omsg = new OrdersMessage();
			OrdersVO ovo = new OrdersVO();
			ovo.setOrderId(Integer.parseInt(orderIdVal.getText()));
			omsg.setState(3);
			omsg.setOvo(ovo);
			try {
				ClientHandler.oos.writeObject(omsg);
				ClientHandler.oos.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}
	}


	// 주문 결과 처리
	public void orderResult(int result) {
		if (result != 0) {
			JOptionPane.showMessageDialog(null, "주문이 정상적으로 완료되었습니다.");

		} else {
			JOptionPane.showConfirmDialog(null, "주문에 실패하였습니다.", "경고", JOptionPane.DEFAULT_OPTION,
					JOptionPane.WARNING_MESSAGE);

		}
	}

	// 주문 취소 결과 처리
	public void orderCancelResult(int result) {
		if (result != 0) {
			JOptionPane.showMessageDialog(null, "주문이 취소되었습니다.");
			ordersDTM.removeRow(ordersRow);
			orderIdVal.setText("");
			orderBookNameVal.setText("");
			orderSalePriceVal.setText("");
			orderCntVal.setText("");
			orderDateVal.setText("");
			

		} else {
			JOptionPane.showConfirmDialog(null, "주문 취소에 실패하였습니다.", "경고", JOptionPane.DEFAULT_OPTION,
					JOptionPane.WARNING_MESSAGE);

		}
	}
	
	// 테이블 마우스 클릭 이벤트 함수
	@Override
	public void mouseClicked(MouseEvent e) {
		if (bookTable == e.getSource()) {
			bookRow = bookTable.getSelectedRow();
			
			String bookId = bookTable.getValueAt(bookRow, 0).toString();
			String bookName = bookTable.getValueAt(bookRow, 1).toString();
			String publisher = bookTable.getValueAt(bookRow, 2).toString();
			String price = bookTable.getValueAt(bookRow, 3).toString();
			String imgPath = null;
			ImageIcon scaledImgIcon = null;
			
			// 데이터베이스에 이미지 경로가 있는지 확인
			if (bookTable.getValueAt(bookRow, 4) != null) {
				// 이미지 사이즈 변경
				imgPath = bookTable.getValueAt(bookRow, 4).toString();
				//데이터베이스에 있는 이미지경로를 통해 이미지 생성
				Image selectedImg = new ImageIcon(imgPath).getImage();
				Image scaledImg = selectedImg.getScaledInstance(171, 165, Image.SCALE_DEFAULT);
				scaledImgIcon = new ImageIcon(scaledImg);
			}
			
			//이미지 넣기
			imgL.setIcon(scaledImgIcon);
			
			bookIdVal.setText(bookId);
			bookNameVal.setText(bookName);
			publisherVal.setText(publisher);
			priceVal.setText(price);
			
		} else if (ordersTable == e.getSource()) {
			
			ordersRow = ordersTable.getSelectedRow();
			
			String orderId = ordersTable.getValueAt(ordersRow, 0).toString();
			String orderBookName = ordersTable.getValueAt(ordersRow, 1).toString();
			String orderSalePrice = ordersTable.getValueAt(ordersRow, 2).toString();
			String orderCnt = ordersTable.getValueAt(ordersRow, 3).toString();
			String orderDate = ordersTable.getValueAt(ordersRow, 4).toString();
			
			orderIdVal.setText(orderId);
			orderBookNameVal.setText(orderBookName);
			orderSalePriceVal.setText(orderSalePrice);
			orderCntVal.setText(orderCnt);
			orderDateVal.setText(orderDate);
			
		} else if (tab == e.getSource()) {	//tab(책 목록, 주문내역) 클릭 동작 이벤트
			if (tab.getSelectedIndex() == 0) {
				change(ordersDetailPanel, bookDetailPanel);
			} else if (tab.getSelectedIndex() == 1) {
				ordersDTM.setNumRows(0);
				change(bookDetailPanel, ordersDetailPanel);
				orderSelectAll();
			}
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		new BookStoreFrame(null, "2");
	}

}
