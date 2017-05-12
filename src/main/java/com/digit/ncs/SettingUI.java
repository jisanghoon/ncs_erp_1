package com.digit.ncs;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.digit.ncs.setting.service.ExportSettingService;
import com.digit.ncs.setting.service.ImportSettingService;
import com.digit.ncs.setting.service.InitSettingService;
import com.digit.ncs.setting.service.ServiceSetting;

@SuppressWarnings("serial")
public class SettingUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnInit;
	private JButton btnBackUp;
	private JButton btnRestore;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SettingUI frame = new SettingUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SettingUI() {
		setTitle("DB관리메뉴");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 425, 99);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 3, 20, 0));

		btnInit = new JButton("초기화");
		btnInit.addActionListener(this);
		contentPane.add(btnInit);

		btnBackUp = new JButton("백업");
		btnBackUp.addActionListener(this);
		contentPane.add(btnBackUp);

		btnRestore = new JButton("복원");
		btnRestore.addActionListener(this);
		contentPane.add(btnRestore);
		btnRestore.setAlignmentX(Component.RIGHT_ALIGNMENT);

		setSize(360, 110);
		setResizable(false);
		setFrameRelocation();
	}

	public void setFrameRelocation() {

		Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((windowSize.width - getSize().width) / 2, (windowSize.height - getSize().height) / 2);
	}

	public void actionPerformed(ActionEvent e) {
		ServiceSetting create = null;
		String msg = "";

		if (e.getSource() == btnRestore) {
			create = new ImportSettingService();
			msg = "데이터 복원 완료";

		} else if (e.getSource() == btnBackUp) {
			create = new ExportSettingService();
			msg = "데이터 백업 완료";

		} else if (e.getSource() == btnInit) {
			create = new InitSettingService();
			msg = "초기화 완료";
		}

		create.initSetting();
		JOptionPane.showMessageDialog(null, msg);
	}

}
