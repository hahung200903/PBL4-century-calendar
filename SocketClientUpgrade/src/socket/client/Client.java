package socket.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import socket.model.Calendar;
import socket.model.Date;
import socket.model.Day;

public class Client extends JFrame {
	
	private static final String host = "localhost";
	private static final int port = 2003;

	private JPanel contentPane;
	private JTextField txtInput;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private JPanel panel_6;
	private JPanel panel_7;
	private JPanel panel_8;
	private JPanel panel_9;
	private JPanel panel_10;
	private JPanel panel_11;
	private JPanel panel_12;
	private JPanel yearPanel;
	private JLabel yearLable;
	private JTable table_1;
	private JTable table_2;
	private JLabel February;
	private JTable table_3;
	private JLabel March;
	private JLabel April;
	private JLabel May;
	private JLabel June;
	private JLabel July;
	private JLabel August;
	private JLabel September;
	private JLabel October;
	private JLabel November;
	private JLabel December;
	private JTable table_4;
	private JTable table_5;
	private JTable table_6;
	private JTable table_7;
	private JTable table_8;
	private JTable table_9;
	private JTable table_10;
	private JTable table_11;
	private JTable table_12;
	
	private Calendar response;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client frame = new Client();
					frame.setTitle("Lịch Vạn Niên");
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
	public Client() {
		setBackground(new Color(255, 255, 255));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1348, 560);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel inputLable = new JLabel("Nhập năm:");
		inputLable.setFont(new Font("Tahoma", Font.BOLD, 13));
		inputLable.setBounds(20, 17, 77, 44);
		contentPane.add(inputLable);
		
		txtInput = new JTextField();
		txtInput.setText("2023");
		inputLable.setLabelFor(txtInput);
		txtInput.setBounds(98, 24, 96, 33);
		contentPane.add(txtInput);
		txtInput.setColumns(10);
		
		JButton btnInput = new JButton("OK");
		btnInput.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnInput.setToolTipText("");
		btnInput.setBounds(203, 24, 58, 33);
		contentPane.add(btnInput);
		
		yearPanel = new JPanel();
		yearPanel.setBackground(new Color(192, 192, 192));
		yearPanel.setBounds(20, 66, 1279, 44);
		contentPane.add(yearPanel);
		yearPanel.setLayout(new BorderLayout(0, 0));
		
		yearLable = new JLabel("");
		yearLable.setHorizontalAlignment(SwingConstants.CENTER);
		yearLable.setFont(new Font("Tahoma", Font.BOLD, 25));
		
		JPanel panel = new JPanel();
		panel.setBounds(20, 110, 1279, 396);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(2, 6, 0, 0));
		
		panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		DefaultTableCellRenderer redInSunday = new DefaultTableCellRenderer();
		redInSunday.setForeground(Color.red);
		DefaultTableModel model_1 = new DefaultTableModel(); 
		table_1 = new JTable(model_1);
		table_1.setBackground(new Color(255, 255, 255));
		table_1.setEnabled(false);
		table_1.setCellSelectionEnabled(true);
		table_1.setRowSelectionAllowed(false);
		table_1.setShowGrid(false);
		
		JLabel January = new JLabel("Tháng 1");
		January.setFont(new Font("Tahoma", Font.PLAIN, 20));
		January.setHorizontalAlignment(SwingConstants.CENTER);
		January.setBounds(0, 0, 213, 40);
		panel_1.add(January);
		
		panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		DefaultTableModel model_2 = new DefaultTableModel();
		table_2 = new JTable(model_2);
		table_2.setEnabled(false);
		table_2.setCellSelectionEnabled(true);
		table_2.setRowSelectionAllowed(false);
		table_2.setShowGrid(false);
		
		February = new JLabel("Tháng 2");
		February.setHorizontalAlignment(SwingConstants.CENTER);
		February.setFont(new Font("Tahoma", Font.PLAIN, 20));
		February.setBounds(0, 0, 213, 40);
		panel_2.add(February);
		
		panel_3 = new JPanel();
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		DefaultTableModel model_3 = new DefaultTableModel();
		table_3 = new JTable(model_3);
		table_3.setEnabled(false);
		table_3.setCellSelectionEnabled(true);
		table_3.setRowSelectionAllowed(false);
		table_3.setShowGrid(false);
		
		March = new JLabel("Tháng 3");
		March.setHorizontalAlignment(SwingConstants.CENTER);
		March.setFont(new Font("Tahoma", Font.PLAIN, 20));
		March.setBounds(0, 0, 213, 40);
		panel_3.add(March);
		
		panel_4 = new JPanel();
		panel.add(panel_4);
		panel_4.setLayout(null);
		
		April = new JLabel("Tháng 4");
		April.setHorizontalAlignment(SwingConstants.CENTER);
		April.setFont(new Font("Tahoma", Font.PLAIN, 20));
		April.setBounds(0, 0, 213, 40);
		panel_4.add(April);
		
		DefaultTableModel model_4 = new DefaultTableModel();
		table_4 = new JTable(model_4);
		table_4.setEnabled(false);
		table_4.setCellSelectionEnabled(true);
		table_4.setRowSelectionAllowed(false);
		table_4.setShowGrid(false);
		
		panel_5 = new JPanel();
		panel.add(panel_5);
		panel_5.setLayout(null);
		
		May = new JLabel("Tháng 5");
		May.setHorizontalAlignment(SwingConstants.CENTER);
		May.setFont(new Font("Tahoma", Font.PLAIN, 20));
		May.setBounds(0, 0, 213, 40);
		panel_5.add(May);
		
		DefaultTableModel model_5 = new DefaultTableModel();
		table_5 = new JTable(model_5);
		table_5.setEnabled(false);
		table_5.setCellSelectionEnabled(true);
		table_5.setRowSelectionAllowed(false);
		table_5.setShowGrid(false);
		
		panel_6 = new JPanel();
		panel.add(panel_6);
		panel_6.setLayout(null);
		
		June = new JLabel("Tháng 6");
		June.setHorizontalAlignment(SwingConstants.CENTER);
		June.setFont(new Font("Tahoma", Font.PLAIN, 20));
		June.setBounds(0, 0, 213, 40);
		panel_6.add(June);
		
		DefaultTableModel model_6 = new DefaultTableModel();
		table_6 = new JTable(model_6);
		table_6.setEnabled(false);
		table_6.setCellSelectionEnabled(true);
		table_6.setRowSelectionAllowed(false);
		table_6.setShowGrid(false);
		
		panel_7 = new JPanel();
		panel.add(panel_7);
		panel_7.setLayout(null);
		
		July = new JLabel("Tháng 7");
		July.setHorizontalAlignment(SwingConstants.CENTER);
		July.setFont(new Font("Tahoma", Font.PLAIN, 20));
		July.setBounds(0, 0, 213, 40);
		panel_7.add(July);
		
		DefaultTableModel model_7 = new DefaultTableModel();
		table_7 = new JTable(model_7);
		table_7.setEnabled(false);
		table_7.setCellSelectionEnabled(true);
		table_7.setRowSelectionAllowed(false);
		table_7.setShowGrid(false);
		
		panel_8 = new JPanel();
		panel.add(panel_8);
		panel_8.setLayout(null);
		
		August = new JLabel("Tháng 8");
		August.setHorizontalAlignment(SwingConstants.CENTER);
		August.setFont(new Font("Tahoma", Font.PLAIN, 20));
		August.setBounds(0, 0, 213, 40);
		panel_8.add(August);
		
		DefaultTableModel model_8 = new DefaultTableModel();
		table_8 = new JTable(model_8);
		table_8.setEnabled(false);
		table_8.setCellSelectionEnabled(true);
		table_8.setRowSelectionAllowed(false);
		table_8.setShowGrid(false);
		
		panel_9 = new JPanel();
		panel.add(panel_9);
		panel_9.setLayout(null);
		
		September = new JLabel("Tháng 9");
		September.setHorizontalAlignment(SwingConstants.CENTER);
		September.setFont(new Font("Tahoma", Font.PLAIN, 20));
		September.setBounds(0, 0, 213, 40);
		panel_9.add(September);
		
		DefaultTableModel model_9 = new DefaultTableModel();
		table_9 = new JTable(model_9);
		table_9.setEnabled(false);
		table_9.setCellSelectionEnabled(true);
		table_9.setRowSelectionAllowed(false);
		table_9.setShowGrid(false);
		
		panel_10 = new JPanel();
		panel.add(panel_10);
		panel_10.setLayout(null);
		
		October = new JLabel("Tháng 10");
		October.setHorizontalAlignment(SwingConstants.CENTER);
		October.setFont(new Font("Tahoma", Font.PLAIN, 20));
		October.setBounds(0, 0, 213, 40);
		panel_10.add(October);
		
		DefaultTableModel model_10 = new DefaultTableModel();
		table_10 = new JTable(model_10);
		table_10.setEnabled(false);
		table_10.setCellSelectionEnabled(true);
		table_10.setRowSelectionAllowed(false);
		table_10.setShowGrid(false);
		
		panel_11 = new JPanel();
		panel.add(panel_11);
		panel_11.setLayout(null);
		
		November = new JLabel("Tháng 11");
		November.setHorizontalAlignment(SwingConstants.CENTER);
		November.setFont(new Font("Tahoma", Font.PLAIN, 20));
		November.setBounds(0, 0, 213, 40);
		panel_11.add(November);
		
		DefaultTableModel model_11 = new DefaultTableModel();
		table_11 = new JTable(model_11);
		table_11.setEnabled(false);
		table_11.setCellSelectionEnabled(true);
		table_11.setRowSelectionAllowed(false);
		table_11.setShowGrid(false);
		
		panel_12 = new JPanel();
		panel.add(panel_12);
		panel_12.setLayout(null);
		
		December = new JLabel("Tháng 12");
		December.setHorizontalAlignment(SwingConstants.CENTER);
		December.setFont(new Font("Tahoma", Font.PLAIN, 20));
		December.setBounds(0, 0, 213, 40);
		panel_12.add(December);
		
		DefaultTableModel model_12 = new DefaultTableModel();
		table_12 = new JTable(model_12);
		table_12.setEnabled(false);
		table_12.setCellSelectionEnabled(true);
		table_12.setRowSelectionAllowed(false);
		table_12.setShowGrid(false);
		
		String[] columns = {"S", "M", "T", "W", "T", "F", "S"};
		for (int i = 0; i < 7; i++) {
			model_1.addColumn(columns[i]);
			model_2.addColumn(columns[i]);
			model_3.addColumn(columns[i]);
			model_4.addColumn(columns[i]);
			model_5.addColumn(columns[i]);
			model_6.addColumn(columns[i]);
			model_7.addColumn(columns[i]);
			model_8.addColumn(columns[i]);
			model_9.addColumn(columns[i]);
			model_10.addColumn(columns[i]);
			model_11.addColumn(columns[i]);
			model_12.addColumn(columns[i]);
		}
		btnInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				response = connect(txtInput.getText());
				yearLable.setText(txtInput.getText());
				Object[][] thang1 = castToObject(response.getJanuary());
				Object[][] thang2 = castToObject(response.getFebruary());
				Object[][] thang3 = castToObject(response.getMarch());
				Object[][] thang4 = castToObject(response.getApril());
				Object[][] thang5 = castToObject(response.getMay());
				Object[][] thang6 = castToObject(response.getJune());
				Object[][] thang7 = castToObject(response.getJuly());
				Object[][] thang8 = castToObject(response.getAugust());
				Object[][] thang9 = castToObject(response.getSeptember());
				Object[][] thang10 = castToObject(response.getOctober());
				Object[][] thang11 = castToObject(response.getNovember());
				Object[][] thang12= castToObject(response.getDecember());
				
				model_1.setRowCount(0);
				model_2.setRowCount(0);
				model_3.setRowCount(0);
				model_4.setRowCount(0);
				model_5.setRowCount(0);
				model_6.setRowCount(0);
				model_7.setRowCount(0);
				model_8.setRowCount(0);
				model_9.setRowCount(0);
				model_10.setRowCount(0);
				model_11.setRowCount(0);
				model_12.setRowCount(0);
				
				for (int i = 0; i < thang1.length; i++) {
					model_1.addRow(thang1[i]);
					model_2.addRow(thang2[i]);
					model_3.addRow(thang3[i]);
					model_4.addRow(thang4[i]);
					model_5.addRow(thang5[i]);
					model_6.addRow(thang6[i]);
					model_7.addRow(thang7[i]);
					model_8.addRow(thang8[i]);
					model_9.addRow(thang9[i]);
					model_10.addRow(thang10[i]);
					model_11.addRow(thang11[i]);
					model_12.addRow(thang12[i]);
				}
				
				
			}
		});
		yearPanel.add(yearLable);
		panel_1.add(table_1);
		table_1.setBorder(new MatteBorder(1, 1, 1, 0, (Color) new Color(0, 0, 0)));
		table_1.setBounds(0, 39, 213, 156);
		table_1.setRowHeight(22);
		table_1.setDefaultRenderer(Object.class, new brownInOtherMonth());
//		table_1.getColumnModel().getColumn(0).setCellRenderer(redInSunday);
		
		panel_2.add(table_2);
		table_2.setBounds(0, 39, 213, 156);
		table_2.setBorder(new MatteBorder(1, 1, 1, 0, (Color) new Color(0, 0, 0)));
		table_2.setRowHeight(22);
		table_2.setDefaultRenderer(Object.class, new brownInOtherMonth());

		panel_3.add(table_3);
		table_3.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		table_3.setBounds(0, 39, 213, 156);
		table_3.setRowHeight(22);
		table_3.setDefaultRenderer(Object.class, new brownInOtherMonth());

		panel_4.add(table_4);
		table_4.setBorder(new MatteBorder(1, 0, 1, 0, (Color) new Color(0, 0, 0)));
		table_4.setBounds(0, 39, 213, 156);
		table_4.setRowHeight(22);
		table_4.setDefaultRenderer(Object.class, new brownInOtherMonth());

		panel_5.add(table_5);
		table_5.setBorder(new MatteBorder(1, 1, 1, 0, (Color) new Color(0, 0, 0)));
		table_5.setBounds(0, 39, 213, 156);
		table_5.setRowHeight(22);
		table_5.setDefaultRenderer(Object.class, new brownInOtherMonth());

		panel_6.add(table_6);
		table_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		table_6.setBounds(0, 39, 213, 156);
		table_6.setRowHeight(22);
		table_6.setDefaultRenderer(Object.class, new brownInOtherMonth());

		panel_7.add(table_7);
		table_7.setBorder(new MatteBorder(1, 1, 1, 0, (Color) new Color(0, 0, 0)));
		table_7.setBounds(0, 39, 213, 156);
		table_7.setRowHeight(22);
		table_7.setDefaultRenderer(Object.class, new brownInOtherMonth());

		panel_8.add(table_8);
		table_8.setBorder(new MatteBorder(1, 1, 1, 0, (Color) new Color(0, 0, 0)));
		table_8.setBounds(0, 39, 213, 156);
		table_8.setRowHeight(22);
		table_8.setDefaultRenderer(Object.class, new brownInOtherMonth());

		panel_9.add(table_9);
		table_9.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		table_9.setBounds(0, 39, 213, 156);
		table_9.setRowHeight(22);
		table_9.setDefaultRenderer(Object.class, new brownInOtherMonth());

		panel_10.add(table_10);
		table_10.setBorder(new MatteBorder(1, 0, 1, 0, (Color) new Color(0, 0, 0)));
		table_10.setBounds(0, 39, 213, 156);
		table_10.setRowHeight(22);
		table_10.setDefaultRenderer(Object.class, new brownInOtherMonth());

		panel_11.add(table_11);
		table_11.setBorder(new MatteBorder(1, 1, 1, 0, (Color) new Color(0, 0, 0)));
		table_11.setBounds(0, 39, 213, 156);
		table_11.setRowHeight(22);
		table_11.setDefaultRenderer(Object.class, new brownInOtherMonth());

		panel_12.add(table_12);
		table_12.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		table_12.setBounds(0, 39, 213, 156);
		table_12.setRowHeight(22);
		table_12.setDefaultRenderer(Object.class, new brownInOtherMonth());
		
		addMouseListenerToTable(table_1, 1);
        addMouseListenerToTable(table_2, 2);
        addMouseListenerToTable(table_3, 3);
        addMouseListenerToTable(table_4, 4);
        addMouseListenerToTable(table_5, 5);
        addMouseListenerToTable(table_6, 6);
        addMouseListenerToTable(table_7, 7);
        addMouseListenerToTable(table_8, 8);
        addMouseListenerToTable(table_9, 9);
        addMouseListenerToTable(table_10, 10);
        addMouseListenerToTable(table_11, 11);
        addMouseListenerToTable(table_12, 12);
	}
	
	private void addMouseListenerToTable(JTable table, int M) {
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	Date[][] date = null;
            	switch (M) {
            		case 1: date = response.getJanuary(); break;
            		case 2: date = response.getFebruary(); break;
            		case 3: date = response.getMarch(); break;
            		case 4: date = response.getApril(); break;
            		case 5: date = response.getMay(); break;
            		case 6: date = response.getJune(); break;
            		case 7: date = response.getJuly(); break;
            		case 8: date = response.getAugust(); break;
            		case 9: date = response.getSeptember(); break;
            		case 10: date = response.getOctober(); break;
            		case 11: date = response.getNovember(); break;
            		case 12: date = response.getDecember(); break;
            	}
                int row = table.rowAtPoint(e.getPoint()) - 1;
                int column = table.columnAtPoint(e.getPoint());
                String thu = "Thứ ";
                switch(column) {
	                case 0: thu = "Chủ nhật"; break;
	                case 1: thu += "Hai"; break;
	                case 2: thu += "Ba"; break;
	                case 3: thu += "Tư"; break;
	                case 4: thu += "Năm"; break;
	                case 5: thu += "Sáu"; break;
	                case 6: thu += "Bảy"; break;
                }
                if (row >= 0 && column >= 0) {
                    Date day = date[row][column];
                    int solarYear = day.Solar.year;
                    int solarMonth = day.Solar.month;
                    int solarDay = day.Solar.day;
                    int lunarYear = day.Lunar.year;
                    int lunarMonth = day.Lunar.month;
                    int lunarDay = day.Lunar.day;
                    String[] lunarDetail = day.DetailLunar.split(", ");
                    String lunarDayDetail = lunarDetail[0];
                    String lunarMonthDetail = lunarDetail[1];
                    String lunarYearDetail = lunarDetail[2];
                    
                    JFrame content = new JFrame("Chi tiết ngày: " + solarDay + "/" + solarMonth + "/" + solarYear);
                    content.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    content.setLayout(null);
            		
                    JLabel thangDuong = new JLabel("Tháng " + solarMonth);
            		thangDuong.setFont(new Font("Tahoma", Font.PLAIN, 20));
            		thangDuong.setBounds(186, 10, 112, 34);
            		content.add(thangDuong);
            		
            		JLabel ngayDuong = new JLabel("" + solarDay);
            		ngayDuong.setForeground(new Color(255, 0, 0));
            		ngayDuong.setFont(new Font("Tahoma", Font.PLAIN, 40));
            		ngayDuong.setBounds(205, 54, 93, 97);
            		content.add(ngayDuong);
            		
            		JLabel ngayThu = new JLabel("" + thu);
            		ngayThu.setFont(new Font("Tahoma", Font.PLAIN, 20));
            		ngayThu.setBounds(190, 161, 84, 36);
            		content.add(ngayThu);
            		
            		JLabel namAm = new JLabel("Năm " + lunarYear);
            		namAm.setBounds(74, 72, 67, 13);
            		content.add(namAm);
            		
            		JLabel thangAm = new JLabel("Tháng " + lunarMonth + " ÂL");
            		thangAm.setBounds(74, 104, 80, 13);
            		content.add(thangAm);
            		
            		JLabel ngayAm = new JLabel("Ngày " + lunarDay);
            		ngayAm.setBounds(74, 138, 67, 13);
            		content.add(ngayAm);
            		
            		JLabel detailNgay = new JLabel("" + lunarDayDetail);
            		detailNgay.setBounds(330, 72, 100, 13);
            		content.add(detailNgay);
            		
            		JLabel detailThang = new JLabel("" + lunarMonthDetail);
            		detailThang.setBounds(330, 104, 110, 13);
            		content.add(detailThang);
            		
            		JLabel detailNam = new JLabel("" + lunarYearDetail);
            		detailNam.setBounds(330, 138, 100, 13);
            		content.add(detailNam);
            		
            		content.setSize(500, 250);
                    content.setLocationRelativeTo(null);
                    content.setVisible(true);
                }
            }
        });
    }
	
	private Object[][] castToObject(Date[][] array){
		int numRows = array.length;
		int numCols = array[0].length;

		Object[][] objArray = new Object[numRows+1][numCols];

		objArray[0][0] = "Su";
		objArray[0][1] = "M";
		objArray[0][2] = "T";
		objArray[0][3] = "W";
		objArray[0][4] = "T";
		objArray[0][5] = "F";
		objArray[0][6] = "S";
		for (int i = 0; i < numRows; i++) {
		    for (int j = 0; j < numCols; j++) {
		    	objArray[i+1][j] = array[i][j].Solar.day;
		    }
		}
		return objArray;
	}
	
	private Calendar connect(String year) {
		Socket socket = null;
		try {
			socket = new Socket(host, port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Client connected in port " + port);
		
		//Sending to server
		PrintStream ps = null;
		try {
			ps = new PrintStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		ps.println(year);
		//Receiving from server
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Calendar calendar = (Calendar)ois.readObject();
			return calendar;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static class brownInOtherMonth extends DefaultTableCellRenderer {
		public brownInOtherMonth() {
            setHorizontalAlignment(SwingConstants.CENTER);
        }
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if((row == 1 && (int)value >= 23) || (row >= 5 && (int)value <= 14)){
            	c.setBackground(Color.LIGHT_GRAY);
            	c.setForeground(Color.GRAY);
            }
            else {
                setBackground(table.getBackground());
                setForeground(table.getForeground());
            }

            return c;
        }
    }
	private static class redInSunday extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (row == 0) c.setForeground(Color.RED);
            else {
                setBackground(table.getBackground());
                setForeground(table.getForeground());
            }
            return c;
        }
    }
}
