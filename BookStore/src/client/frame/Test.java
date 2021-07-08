package client.frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JScrollBar;
import javax.swing.JSpinner;
import javax.swing.JButton;

public class Test {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test window = new Test();
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
	public Test() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 809, 551);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel total = new JPanel();
		total.setBounds(12, 10, 771, 494);
		frame.getContentPane().add(total);
		total.setLayout(null);
		
		JLabel title = new JLabel("New label");
		title.setBounds(121, 14, 215, 42);
		total.add(title);
		
		JTabbedPane tab = new JTabbedPane(JTabbedPane.TOP);
		tab.setBounds(30, 66, 420, 418);
		total.add(tab);
		
		JScrollBar scrollBar = new JScrollBar();
		tab.addTab("New tab", null, scrollBar, null);
		
		JScrollBar scrollBar_1 = new JScrollBar();
		tab.addTab("New tab", null, scrollBar_1, null);
		
		JPanel detail = new JPanel();
		detail.setBounds(462, 89, 297, 395);
		total.add(detail);
		detail.setLayout(null);
		
		
		
		JButton ordercanc = new JButton("New button");
		ordercanc.setBounds(100, 327, 108, 36);
		detail.add(ordercanc);
		
		JLabel orderid = new JLabel("New label");
		orderid.setBounds(22, 71, 91, 36);
		detail.add(orderid);
		
		JLabel booknameL = new JLabel("New label");
		booknameL.setBounds(22, 117, 91, 36);
		detail.add(booknameL);
		
		JLabel salepriceL = new JLabel("New label");
		salepriceL.setBounds(22, 163, 91, 36);
		detail.add(salepriceL);
		
		JLabel cntL = new JLabel("New label");
		cntL.setBounds(22, 209, 91, 36);
		detail.add(cntL);
		
		JLabel orderdatL = new JLabel("New label");
		orderdatL.setBounds(22, 255, 91, 36);
		detail.add(orderdatL);
		
		JLabel orderidV = new JLabel("New label");
		orderidV.setBounds(125, 71, 160, 36);
		detail.add(orderidV);
		
		JLabel bookNameVal = new JLabel("New label");
		bookNameVal.setBounds(125, 117, 160, 36);
		detail.add(bookNameVal);
		
		JLabel salepriceVal = new JLabel("New label");
		salepriceVal.setBounds(125, 163, 160, 36);
		detail.add(salepriceVal);
		
		JLabel cntval = new JLabel("New label");
		cntval.setBounds(125, 209, 160, 36);
		detail.add(cntval);
		
		JLabel orderdateVal = new JLabel("New label");
		orderdateVal.setBounds(125, 255, 160, 36);
		detail.add(orderdateVal);
		
		JLabel username = new JLabel("New label");
		username.setBounds(462, 15, 161, 40);
		total.add(username);
		
		JButton logout = new JButton("New button");
		logout.setBounds(635, 14, 114, 42);
		total.add(logout);
		
		JPanel panel = new JPanel();
		panel.setBounds(462, 87, 297, 397);
		total.add(panel);
	}
}
