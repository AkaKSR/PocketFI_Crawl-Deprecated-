package to.na.akaksr.pocketfi.app;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import to.na.akaksr.pocketfi.api.Api;
import to.na.akaksr.pocketfi.vo.PocketFiVO;

public class PocketFi_Data_Usage_GUI extends JFrame {
	private static final long serialVersionUID = 8605082153487008091L;

//	private JLabel menuBar = new JLabel(new ImageIcon(PocketFi_Data_Usage_GUI.class.getResource("/res/splash/menuBar.png")));
	private JLabel menuBar = new JLabel(new ImageIcon(PocketFi_Data_Usage_GUI.class.getResource("/splash/menuBar.png")));
	// 네트워크 연결 상태
	private JLabel wibro = new JLabel(new ImageIcon(PocketFi_Data_Usage_GUI.class.getResource("/wibro/wibro_0.png")));
	// 잔여 배터리 상태
	private JLabel battery = new JLabel(new ImageIcon(PocketFi_Data_Usage_GUI.class.getResource("/battery/battery1_0.png")));
	
	private JLabel titleBar = new JLabel();
	// 항목별 라벨 이름
	private JLabel oID_TX_BYTES = new JLabel();
	private JLabel oID_RX_BYTES = new JLabel();
	private JLabel oID_DAILY_TX_BYTES = new JLabel();
	private JLabel oID_DAILY_RX_BYTES = new JLabel();
	private JLabel oID_TOTAL_TX_BYTES = new JLabel();
	private JLabel oID_TOTAL_RX_BYTES = new JLabel();
	private JLabel oID_PHONE_NUMBER = new JLabel();
	private JLabel oID_IP_ADDR = new JLabel();
	// 항목별 데이터 값
	private JLabel ID_TX_BYTES = new JLabel();
	private JLabel ID_RX_BYTES = new JLabel();
	private JLabel ID_DAILY_TX_BYTES = new JLabel();
	private JLabel ID_DAILY_RX_BYTES = new JLabel();
	private JLabel ID_TOTAL_TX_BYTES = new JLabel();
	private JLabel ID_TOTAL_RX_BYTES = new JLabel();
	private JLabel ID_PHONE_NUMBER = new JLabel();
	private JLabel ID_IP_ADDR = new JLabel();
	// 버튼
	private JButton refreshBtn = new JButton();
	private JButton exitBtn = new JButton();
	
	private int mouseX, mouseY;
	@SuppressWarnings("unused")
	private Api api;

	public PocketFi_Data_Usage_GUI(final PocketFiVO vo, final Api api) {
		
		// Test Code
		this.api = api;
		
		getContentPane().setBackground(Color.LIGHT_GRAY);

		setUndecorated(true);
		setTitle("PocketFi_Data_Usage");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		getContentPane().setLayout(null);

		titleBar.setText("데이터 사용량");
		titleBar.setBounds(10, 0, 300, 30);
		getContentPane().add(titleBar);

		menuBar.setBounds(0, 0, 300, 30);
		menuBar.setBackground(Color.gray);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);

			}
		});
		getContentPane().add(menuBar);

		oID_TX_BYTES.setText("송신량");
		oID_TX_BYTES.setBounds(10, 40, 95, 30);
		getContentPane().add(oID_TX_BYTES);

		ID_TX_BYTES.setText(vo.getID_TX_BYTES());
		ID_TX_BYTES.setBounds(120, 40, 95, 30);
		getContentPane().add(ID_TX_BYTES);

		oID_RX_BYTES.setText("수신량");
		oID_RX_BYTES.setBounds(10, 70, 95, 30);
		getContentPane().add(oID_RX_BYTES);

		ID_RX_BYTES.setText(vo.getID_RX_BYTES());
		ID_RX_BYTES.setBounds(120, 70, 95, 30);
		getContentPane().add(ID_RX_BYTES);

		oID_DAILY_TX_BYTES.setText("일간 송신량");
		oID_DAILY_TX_BYTES.setBounds(10, 100, 95, 30);
		getContentPane().add(oID_DAILY_TX_BYTES);

		ID_DAILY_TX_BYTES.setText(vo.getID_DAILY_TX_BYTES());
		ID_DAILY_TX_BYTES.setBounds(120, 100, 95, 30);
		getContentPane().add(ID_DAILY_TX_BYTES);

		oID_DAILY_RX_BYTES.setText("일간 수신량");
		oID_DAILY_RX_BYTES.setBounds(10, 130, 95, 30);
		getContentPane().add(oID_DAILY_RX_BYTES);

		ID_DAILY_RX_BYTES.setText(vo.getID_DAILY_RX_BYTES());
		ID_DAILY_RX_BYTES.setBounds(120, 130, 95, 30);
		getContentPane().add(ID_DAILY_RX_BYTES);

		oID_TOTAL_TX_BYTES.setText("월간 송신량");
		oID_TOTAL_TX_BYTES.setBounds(10, 160, 95, 30);
		getContentPane().add(oID_TOTAL_TX_BYTES);

		ID_TOTAL_TX_BYTES.setText(vo.getID_TOTAL_TX_BYTES());
		ID_TOTAL_TX_BYTES.setBounds(120, 160, 95, 30);
		getContentPane().add(ID_TOTAL_TX_BYTES);

		oID_TOTAL_RX_BYTES.setText("월간 수신량");
		oID_TOTAL_RX_BYTES.setBounds(10, 190, 95, 30);
		getContentPane().add(oID_TOTAL_RX_BYTES);

		ID_TOTAL_RX_BYTES.setText(vo.getID_TOTAL_RX_BYTES());
		ID_TOTAL_RX_BYTES.setBounds(120, 190, 95, 30);
		getContentPane().add(ID_TOTAL_RX_BYTES);

		oID_PHONE_NUMBER.setText("전화 번호");
		oID_PHONE_NUMBER.setBounds(10, 220, 95, 30);
		getContentPane().add(oID_PHONE_NUMBER);

		ID_PHONE_NUMBER.setText(vo.getLTE_DIAL());
		ID_PHONE_NUMBER.setBounds(120, 220, 95, 30);
		getContentPane().add(ID_PHONE_NUMBER);

		oID_IP_ADDR.setText("IP 주소");
		oID_IP_ADDR.setBounds(10, 250, 95, 30);
		getContentPane().add(oID_IP_ADDR);

		ID_IP_ADDR.setText(vo.getLTE_IP_ADDR());
		ID_IP_ADDR.setBounds(120, 250, 95, 30);
		getContentPane().add(ID_IP_ADDR);
		
		/*
		 *  TODO wibro 및 battery 화면 구현
		 *  battery 29x22
		 *  wibro 35x22
		 */
		
		
		refreshBtn.setText("Refresh");
		refreshBtn.setBounds(20, 350, 95, 30);
		refreshBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refresh(api, vo);
			}
		});
		getContentPane().add(refreshBtn);
		
		exitBtn.setText("Exit");
		exitBtn.setBounds(135, 350, 95, 30);
		exitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				api.close();
				System.exit(0);
			}
		});
		getContentPane().add(exitBtn);

		System.out.println(vo);
		
		repaint();
	}
	
	private void refresh(Api api, PocketFiVO vo) {
		System.out.println(vo);
		System.out.println();
		System.out.println(api);
		try {
			api.crawl(vo);
			api.information(vo);
			Thread.sleep(5000);
			
			ID_TX_BYTES.setText(vo.getID_TX_BYTES());
			ID_RX_BYTES.setText(vo.getID_RX_BYTES());
			ID_DAILY_TX_BYTES.setText(vo.getID_DAILY_TX_BYTES());
			ID_DAILY_RX_BYTES.setText(vo.getID_DAILY_RX_BYTES());
			ID_TOTAL_TX_BYTES.setText(vo.getID_TOTAL_TX_BYTES());
			ID_TOTAL_RX_BYTES.setText(vo.getID_TOTAL_RX_BYTES());
			ID_PHONE_NUMBER.setText(vo.getLTE_DIAL());
			ID_IP_ADDR.setText(vo.getLTE_IP_ADDR());
			
			repaint();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
