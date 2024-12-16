package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.RowFilter;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

public class adminSetting extends JFrame {
	private JMenuBar menuBar;
	private JMenu managerMenu;
	private ImageIcon icon;
	private JMenu helpMenu;
	private ImageIcon iconhelp;
	private JToolBar toolBar;
	private JButton button1, button2, button3, button4, button5;
	private JTabbedPane tabbedPane;
	private JButton selectedButton = null;


	public adminSetting() {
		// TODO Auto-generated constructor stub

		setTitle("Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1300, 750);
		setResizable(false);
		setLocationRelativeTo(null);

		// Tạo JMenuBar và thêm các JMenu
		menuBar = new JMenuBar();

		managerMenu = new JMenu("Manager");
		icon = new ImageIcon(adminSetting.class.getResource("/img/Settings-icon.png"));
		managerMenu.setIcon(new ImageIcon(icon.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));

		helpMenu = new JMenu("Help");
		iconhelp = new ImageIcon(adminSetting.class.getResource("/img/Question-icon.png"));
		helpMenu.setIcon(new ImageIcon(iconhelp.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));

		menuBar.add(managerMenu);
		menuBar.add(helpMenu);
		this.setJMenuBar(menuBar);

		// Tạo JToolBar chứa các JButton
		toolBar = new JToolBar();
		Dimension buttonSize = new Dimension(130, 60);
		button1 = createButton("Trang chủ", "/img/home-icon.png", buttonSize, true);
		button2 = createButton("Quản lý vé", "/img/ticketmanger.png", buttonSize, false);
		button3 = createButton("Quản lý chuyến bay", "/img/flight-management.png", buttonSize, false);
		button4 = createButton("Quản ký khách hàng", "/img/Profile-icon.png", buttonSize, false);
		button5 = createButton("Đăng xuất", "/img/exit-icon.png", buttonSize, false);

		button1.addActionListener(new XuLyButton());
		button2.addActionListener(new XuLyButton());
		button3.addActionListener(new XuLyButton());
		button4.addActionListener(new XuLyButton());
		button5.addActionListener(new XuLyButton());

		toolBar.add(button1);
		toolBar.add(button2);
		toolBar.add(button3);
		toolBar.add(button4);
		toolBar.add(button5);

		// Tạo JTabbedPane và thêm các tab
		tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Trang chủ", home());

		// Sắp xếp các thành phần vào JFrame
		this.getContentPane().add(toolBar, "North");
		this.getContentPane().add(tabbedPane, "Center");

		this.setVisible(true);
	}

	public class XuLyButton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			JButton clickedButton = (JButton) e.getSource();
			if (clickedButton == button1 || clickedButton == button2 || clickedButton == button3
					|| clickedButton == button4) {
				resetButtonColors();
				clickedButton.setBackground(Color.lightGray);
				clickedButton.setForeground(Color.WHITE);

			}

			// Gọi phương thức cập nhật tab
			if (clickedButton == button1) {
				updateTab("Trang chủ", home());
			} else if (clickedButton == button2) {
				updateTab("Quản lý vé", mtickets());
			} else if (clickedButton == button3) {
				updateTab("Quản lý chuyến bay", mPlane());
			} else if (clickedButton == button4) {
				updateTab("Quản lý khách hàng", mCustomer());
			} else if (clickedButton == button5) {
				int confirmResult = JOptionPane.showConfirmDialog(adminSetting.this, "Bạn có có muốn đăng xuất?",
						"Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (confirmResult == JOptionPane.YES_OPTION) {
					new LoginForm();
					setVisible(false);
				}
			}
		}
	}

	private JPanel home() {
		// TODO Auto-generated method stub
		JPanel panel = new JPanel();
		return panel;
	}

	private List<Object[]> originalData; // Lưu dữ liệu gốc

	public JPanel mtickets() {
	    // Tạo panel chính với BorderLayout
	    JPanel mainPanel = new JPanel(new BorderLayout());
	    mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

	    // Tiêu đề
	    JLabel titleLabel = new JLabel("Quản lý vé máy bay", JLabel.CENTER);
	    titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
	    titleLabel.setForeground(new Color(33, 37, 41));
	    mainPanel.add(titleLabel, BorderLayout.NORTH);

	    // Dữ liệu mẫu và tiêu đề cột
	    String[] columnNames = {"Mã chuyến bay", "Tên khách hàng", "Số CCCD", "Giới tính", "Điểm đi", "Điểm đến", "Ngày bay", "SĐT", "Email"};
	    Object[][] data = {
	        {"VN123", "Nguyễn Văn A", "123456789", "Nam", "Hà Nội", "TP. Hồ Chí Minh", "2024-12-20", "0123456789", "a@gmail.com"},
	        {"VN124", "Trần Thị B", "987654321", "Nữ", "Đà Nẵng", "Hà Nội", "2024-12-22", "0987654321", "b@gmail.com"},
	        {"VN125", "Trần Văn C", "111222333", "Nam", "Hải Phòng", "Cần Thơ", "2024-12-23", "0981122334", "c@gmail.com"}
	    };

	    // Lưu dữ liệu gốc
	    originalData = new ArrayList<>();
	    for (Object[] row : data) {
	        originalData.add(row);
	    }

	    DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
	    JTable ticketTable = new JTable(tableModel);
	    ticketTable.setRowHeight(25);
	    ticketTable.setFont(new Font("Arial", Font.PLAIN, 14));
	    JTableHeader header = ticketTable.getTableHeader();
	    header.setFont(new Font("Arial", Font.BOLD, 14));
	    header.setBackground(new Color(63, 81, 181));
	    header.setForeground(Color.WHITE);

	    JScrollPane tableScrollPane = new JScrollPane(ticketTable);
	    tableScrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
	    mainPanel.add(tableScrollPane, BorderLayout.CENTER);

	    // Nút tìm kiếm
	    JButton searchButton = new JButton("Tìm khách hàng");
	    searchButton.setFont(new Font("Arial", Font.BOLD, 16));
	    searchButton.setBackground(new Color(76, 175, 80));
	    searchButton.setForeground(Color.WHITE);
	    searchButton.setFocusPainted(false);
	    searchButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

	    // Nút chỉnh sửa
	    JButton editButton = new JButton("Chỉnh sửa");
	    editButton.setFont(new Font("Arial", Font.BOLD, 16));
	    editButton.setBackground(new Color(255, 165, 0));
	    editButton.setForeground(Color.WHITE);
	    editButton.setFocusPainted(false);
	    editButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

	    JPanel buttonPanel = new JPanel();
	    buttonPanel.add(searchButton);
	    buttonPanel.add(editButton);
	    buttonPanel.setBackground(Color.WHITE);
	    mainPanel.add(buttonPanel, BorderLayout.SOUTH);

	    // Sự kiện tìm kiếm khách hàng
	    searchButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            String searchTerm = JOptionPane.showInputDialog(mainPanel, "Nhập tên hoặc SĐT khách hàng:", "Tìm kiếm khách hàng", JOptionPane.PLAIN_MESSAGE);
	            if (searchTerm == null || searchTerm.trim().isEmpty()) {
	                JOptionPane.showMessageDialog(mainPanel, "Vui lòng nhập thông tin để tìm kiếm!", "Thông báo", JOptionPane.WARNING_MESSAGE);
	                return;
	            }

	            // Khôi phục dữ liệu gốc trước khi tìm kiếm
	            tableModel.setRowCount(0); // Xóa tất cả các hàng hiện tại
	            for (Object[] row : originalData) {
	                tableModel.addRow(row); // Thêm lại dữ liệu gốc
	            }

	            boolean found = false;
	            // Lọc dữ liệu theo từ khóa
	            for (int i = tableModel.getRowCount() - 1; i >= 0; i--) {
	                String name = (String) tableModel.getValueAt(i, 1);
	                String phone = (String) tableModel.getValueAt(i, 7);

	                if (!name.toLowerCase().contains(searchTerm.toLowerCase()) && !phone.contains(searchTerm)) {
	                    tableModel.removeRow(i); // Xóa dòng không phù hợp
	                } else {
	                    found = true;
	                }
	            }

	            if (!found) {
	                JOptionPane.showMessageDialog(mainPanel, "Không tìm thấy khách hàng phù hợp!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	            }
	        }
	    });

	    // Sự kiện chỉnh sửa thông tin vé
	    editButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            int selectedRow = ticketTable.getSelectedRow();
	            if (selectedRow != -1) {
	                // Lấy thông tin dòng đã chọn
	                Object[] rowData = new Object[tableModel.getColumnCount()];
	                for (int i = 0; i < rowData.length; i++) {
	                    rowData[i] = tableModel.getValueAt(selectedRow, i);
	                }

	                // Mở hộp thoại để chỉnh sửa tất cả thông tin
	                String newFlightCode = JOptionPane.showInputDialog(mainPanel, "Nhập mã chuyến bay:", rowData[0]);
	                if (newFlightCode != null && !newFlightCode.trim().isEmpty()) {
	                    tableModel.setValueAt(newFlightCode, selectedRow, 0); // Cập nhật mã chuyến bay
	                }

	                String newName = JOptionPane.showInputDialog(mainPanel, "Nhập tên khách hàng:", rowData[1]);
	                if (newName != null && !newName.trim().isEmpty()) {
	                    tableModel.setValueAt(newName, selectedRow, 1); // Cập nhật tên khách hàng
	                }

	                String newCCCD = JOptionPane.showInputDialog(mainPanel, "Nhập số CCCD:", rowData[2]);
	                if (newCCCD != null && !newCCCD.trim().isEmpty()) {
	                    tableModel.setValueAt(newCCCD, selectedRow, 2); // Cập nhật số CCCD
	                }

	                String newGender = JOptionPane.showInputDialog(mainPanel, "Nhập giới tính:", rowData[3]);
	                if (newGender != null && !newGender.trim().isEmpty()) {
	                    tableModel.setValueAt(newGender, selectedRow, 3); // Cập nhật giới tính
	                }

	                String newDeparture = JOptionPane.showInputDialog(mainPanel, "Nhập điểm đi:", rowData[4]);
	                if (newDeparture != null && !newDeparture.trim().isEmpty()) {
	                    tableModel.setValueAt(newDeparture, selectedRow, 4); // Cập nhật điểm đi
	                }

	                String newArrival = JOptionPane.showInputDialog(mainPanel, "Nhập điểm đến:", rowData[5]);
	                if (newArrival != null && !newArrival.trim().isEmpty()) {
	                    tableModel.setValueAt(newArrival, selectedRow, 5); // Cập nhật điểm đến
	                }

	                String newDate = JOptionPane.showInputDialog(mainPanel, "Nhập ngày bay:", rowData[6]);
	                if (newDate != null && !newDate.trim().isEmpty()) {
	                    tableModel.setValueAt(newDate, selectedRow, 6); // Cập nhật ngày bay
	                }

	                String newPhone = JOptionPane.showInputDialog(mainPanel, "Nhập số điện thoại:", rowData[7]);
	                if (newPhone != null && !newPhone.trim().isEmpty()) {
	                    tableModel.setValueAt(newPhone, selectedRow, 7); // Cập nhật số điện thoại
	                }

	                String newEmail = JOptionPane.showInputDialog(mainPanel, "Nhập email:", rowData[8]);
	                if (newEmail != null && !newEmail.trim().isEmpty()) {
	                    tableModel.setValueAt(newEmail, selectedRow, 8); // Cập nhật email
	                }

	                // Cập nhật lại dữ liệu gốc
	                originalData.set(selectedRow, rowData);
	            } else {
	                JOptionPane.showMessageDialog(mainPanel, "Vui lòng chọn một vé để chỉnh sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
	            }
	        }
	    });

	    return mainPanel;
	}


	private JPanel mPlane() {
	    // Tập hợp lưu trữ mã chuyến bay đã tạo để đảm bảo không trùng lặp
	    Set<String> generatedCodes = new HashSet<>();
	    
	    JPanel panel = new JPanel(new BorderLayout(10, 10));
	    panel.setBorder(new EmptyBorder(10, 10, 10, 10));

	    // --- Tạo form nhập liệu ---
	    JPanel inputPanel = new JPanel(new GridBagLayout());
	    inputPanel.setPreferredSize(new Dimension(380, 600)); 
	    inputPanel.setBorder(BorderFactory.createTitledBorder(
	            BorderFactory.createEtchedBorder(), "Thông tin chuyến bay",
	            TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 18))); 

	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.insets = new Insets(10, 15, 10, 15);
	    gbc.fill = GridBagConstraints.HORIZONTAL;

	    // Các trường nhập liệu
	    JLabel lblNgayCC = new JLabel("Ngày cất cánh:");
	    lblNgayCC.setFont(new Font("Arial", Font.BOLD, 20));

	    JLabel lblThoiGian = new JLabel("Thời gian bay:");
	    lblThoiGian.setFont(new Font("Arial", Font.BOLD, 20));

	    JLabel lblSanBayDi = new JLabel("Sân bay đi:");
	    lblSanBayDi.setFont(new Font("Arial", Font.BOLD, 20)); 

	    JLabel lblSanBayDen = new JLabel("Sân bay đến:");
	    lblSanBayDen.setFont(new Font("Arial", Font.BOLD, 20)); 

	    JTextField txtThoiGian = new JTextField();
	    JTextField txtSanBayDi = new JTextField();
	    JTextField txtSanBayDen = new JTextField();

	    JDateChooser dateChooser = new JDateChooser();
	    dateChooser.setDateFormatString("dd-MM-yyyy");

	    // Thêm JSpinner cho thời gian bay (chọn giờ và phút)
	    JSpinner timeSpinner = new JSpinner(new SpinnerDateModel());
	    JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
	    timeSpinner.setEditor(timeEditor);

	    // Thêm label và các trường nhập vào form
	    int row = 0;
	    addFormRow(inputPanel, gbc, row++, lblNgayCC, dateChooser);
	    addFormRow(inputPanel, gbc, row++, lblThoiGian, timeSpinner);
	    addFormRow(inputPanel, gbc, row++, lblSanBayDi, txtSanBayDi);
	    addFormRow(inputPanel, gbc, row++, lblSanBayDen, txtSanBayDen);

	    // --- Bảng danh sách chuyến bay ---
	    String[] columnNames = {"Mã chuyến bay", "Ngày cất cánh", "Thời gian bay", "Sân bay đi", "Sân bay đến"};
	    DefaultTableModel tableModel = new DefaultTableModel(null, columnNames);
	    JTable table = new JTable(tableModel);
	    table.setRowHeight(25);
	    JScrollPane scrollPane = new JScrollPane(table);
	    scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách chuyến bay"));

	    // --- Nút chức năng ---
	    JButton btnAdd = createButton("Thêm");
	    JButton btnUpdate = createButton("Cập nhật");
	    JButton btnDelete = createButton("Xóa");
	    JButton btnClear = createButton("Tạo mới");

	    JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 0));
	    buttonPanel.add(btnAdd);
	    buttonPanel.add(btnUpdate);
	    buttonPanel.add(btnDelete);
	    buttonPanel.add(btnClear);

	    gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 2;
	    inputPanel.add(buttonPanel, gbc);

	    // --- Sử dụng đúng kích thước panel phải ---
	    panel.add(inputPanel, BorderLayout.WEST);  
	    panel.add(scrollPane, BorderLayout.CENTER);

	    // --- Chức năng cho nút ---
	    btnAdd.addActionListener(e -> {
	        // Kiểm tra trường hợp nhập liệu chưa đầy đủ
	        if (dateChooser.getDate() == null || txtSanBayDi.getText().isEmpty() || txtSanBayDen.getText().isEmpty() || timeSpinner.getValue() == null) {
	            JOptionPane.showMessageDialog(panel, "Vui lòng điền đầy đủ thông tin!");
	            return; // Dừng lại nếu có thông tin thiếu
	        }

	        String maCB = generateFlightCode(generatedCodes);
	        String ngayCC = new SimpleDateFormat("dd-MM-yyyy").format(dateChooser.getDate());
	        String thoiGian = new SimpleDateFormat("HH:mm").format(timeSpinner.getValue());
	        String sanBayDi = txtSanBayDi.getText();
	        String sanBayDen = txtSanBayDen.getText();

	        tableModel.addRow(new Object[]{maCB, ngayCC, thoiGian, sanBayDi, sanBayDen});
	        JOptionPane.showMessageDialog(panel, "Thêm chuyến bay thành công!");
	        clearFields(txtSanBayDi, txtSanBayDen);
	        dateChooser.setCalendar(null);
	        timeSpinner.setValue(new Date());
	    });

	    btnDelete.addActionListener(e -> {
	        int selectedRow = table.getSelectedRow();
	        if (selectedRow >= 0) {
	            tableModel.removeRow(selectedRow);
	            JOptionPane.showMessageDialog(panel, "Xóa chuyến bay thành công!");
	        } else {
	            JOptionPane.showMessageDialog(panel, "Hãy chọn chuyến bay cần xóa.");
	        }
	    });

	    btnUpdate.addActionListener(e -> {
	        int selectedRow = table.getSelectedRow();
	        if (selectedRow >= 0) {
	            // Kiểm tra nếu chưa có thông tin cần thiết
	            if (dateChooser.getDate() == null || txtSanBayDi.getText().isEmpty() || txtSanBayDen.getText().isEmpty() || timeSpinner.getValue() == null) {
	                JOptionPane.showMessageDialog(panel, "Vui lòng điền đầy đủ thông tin!");
	                return; // Dừng lại nếu có thông tin thiếu
	            }

	            tableModel.setValueAt(new SimpleDateFormat("dd-MM-yyyy").format(dateChooser.getDate()), selectedRow, 1);
	            tableModel.setValueAt(new SimpleDateFormat("HH:mm").format(timeSpinner.getValue()), selectedRow, 2);
	            tableModel.setValueAt(txtSanBayDi.getText(), selectedRow, 3);
	            tableModel.setValueAt(txtSanBayDen.getText(), selectedRow, 4);
	            JOptionPane.showMessageDialog(panel, "Cập nhật chuyến bay thành công!");
	        } else {
	            JOptionPane.showMessageDialog(panel, "Hãy chọn chuyến bay cần cập nhật và điền đủ thông tin.");
	        }
	    });

	    btnClear.addActionListener(e -> {
	        clearFields(txtSanBayDi, txtSanBayDen);
	        dateChooser.setCalendar(null);
	        timeSpinner.setValue(new Date());
	    });

	    return panel;
	}

	// Các hàm tiện ích phụ trợ
	private void addFormRow(JPanel panel, GridBagConstraints gbc, int row, JLabel label, Component input) {
	    gbc.gridx = 0; gbc.gridy = row;
	    panel.add(label, gbc);
	    gbc.gridx = 1;
	    panel.add(input, gbc);
	}

	private JButton createButton(String text) {
	    JButton button = new JButton(text);
	    button.setPreferredSize(new Dimension(150, 40));
	    return button;
	}

	private String generateFlightCode(Set<String> generatedCodes) {
	    Random random = new Random();
	    String code;
	    do {
	        code = "VN-" + (1000 + random.nextInt(9999));
	    } while (!generatedCodes.add(code));
	    return code;
	}

	private void clearFields(JTextField... fields) {
	    for (JTextField field : fields) {
	        field.setText("");
	    }
	}
	
	
	private JPanel mCustomer() {
	    // Tạo panel chính
	    JPanel panel = new JPanel(new BorderLayout());

	    // Tiêu đề
	    JLabel title = new JLabel("Quản lý tài khoản người dùng", JLabel.CENTER);
	    title.setFont(new Font("Arial", Font.BOLD, 20));
	    panel.add(title, BorderLayout.NORTH);

	    // Dữ liệu gốc (giả lập)
	    List<Object[]> originalData = new ArrayList<>(Arrays.asList(
	        new Object[]{"123456789", "Nguyen Van A", "0123456789", "a@gmail.com", "Nam", "userA", "passwordA"},
	        new Object[]{"987654321", "Le Thi B", "0987654321", "b@gmail.com", "Nữ", "userB", "passwordB"},
	        new Object[]{"111222333", "Tran Van C", "0981122334", "c@gmail.com", "Nam", "userC", "passwordC"}
	    ));

	    // Bảng và mô hình dữ liệu
	    String[] columnNames = {"Số CCCD", "Họ và Tên", "SĐT", "Email", "Giới tính", "Tên Đăng Nhập", "Mật Khẩu"};
	    DefaultTableModel tableModel = new DefaultTableModel(originalData.toArray(new Object[0][0]), columnNames);
	    JTable table = new JTable(tableModel);
	    table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
	    table.getTableHeader().setBackground(new Color(0, 51, 153));
	    table.getTableHeader().setForeground(Color.WHITE);
	    table.setRowHeight(25);
	    JScrollPane scrollPane = new JScrollPane(table);
	    panel.add(scrollPane, BorderLayout.CENTER);

	    // Panel chứa ô tìm kiếm và các nút chức năng
	    JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

	    // Ô tìm kiếm
	    JTextField searchField = new JTextField(20);
	    JButton btnSearch = new JButton("Tìm Kiếm");
	    btnSearch.setBackground(new Color(0, 123, 255));
	    btnSearch.setForeground(Color.WHITE);
	    btnSearch.setFont(new Font("Arial", Font.BOLD, 14));
	    btnSearch.setPreferredSize(new Dimension(150, 35));

	    // Chức năng tìm kiếm động
	    btnSearch.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            String searchTerm = searchField.getText().trim();
	            if (searchTerm.isEmpty()) {
	                JOptionPane.showMessageDialog(panel, "Vui lòng nhập thông tin để tìm kiếm!", "Thông báo", JOptionPane.WARNING_MESSAGE);
	                return;
	            }

	            // Lọc dữ liệu theo từ khóa
	            tableModel.setRowCount(0); // Xóa tất cả các hàng hiện tại
	            boolean found = false;
	            for (Object[] row : originalData) {
	                String name = (String) row[1];
	                String phone = (String) row[2];

	                // Kiểm tra nếu tên hoặc số điện thoại chứa từ khóa tìm kiếm
	                if (name.toLowerCase().contains(searchTerm.toLowerCase()) || phone.contains(searchTerm)) {
	                    tableModel.addRow(row);
	                    found = true;
	                }
	            }

	            if (!found) {
	                JOptionPane.showMessageDialog(panel, "Không tìm thấy tài khoản phù hợp!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	            }
	        }
	    });

	    // Nút Đặt Lại Mật Khẩu
	    JButton btnResetPassword = new JButton("Đặt Lại Mật Khẩu");
	    btnResetPassword.setBackground(new Color(34, 177, 76));
	    btnResetPassword.setForeground(Color.WHITE);
	    btnResetPassword.setFont(new Font("Arial", Font.BOLD, 14));
	    btnResetPassword.setPreferredSize(new Dimension(150, 35));
	    btnResetPassword.addActionListener(e -> {
	        int selectedRow = table.getSelectedRow();
	        if (selectedRow != -1) {
	            String newPassword = JOptionPane.showInputDialog(panel, "Nhập mật khẩu mới:");
	            if (newPassword != null && !newPassword.trim().isEmpty()) {
	                // Cập nhật mật khẩu trong bảng
	                tableModel.setValueAt(newPassword, selectedRow, 6); // Cột 6 là cột mật khẩu

	                // Cập nhật mật khẩu trong dữ liệu gốc
	                originalData.get(selectedRow)[6] = newPassword;  // Cập nhật mật khẩu trong danh sách dữ liệu gốc

	                // Đảm bảo bảng được làm mới
	                tableModel.fireTableDataChanged(); // Đảm bảo bảng được làm mới
	                JOptionPane.showMessageDialog(panel, "Mật khẩu đã được đặt lại.");
	            } else {
	                JOptionPane.showMessageDialog(panel, "Mật khẩu không hợp lệ.");
	            }
	        } else {
	            JOptionPane.showMessageDialog(panel, "Vui lòng chọn tài khoản cần đặt lại mật khẩu.");
	        }
	    });

	    // Thêm các thành phần vào bottom panel
	    bottomPanel.add(new JLabel("Tìm kiếm (Tên/SĐT):"));
	    bottomPanel.add(searchField);
	    bottomPanel.add(btnSearch);
	    bottomPanel.add(btnResetPassword);

	    panel.add(bottomPanel, BorderLayout.SOUTH);
	    return panel;
	}



	private void updateTab(String title, JPanel newView) {
		int tabIndex = tabbedPane.indexOfTab(title);
		if (tabIndex == -1) {
			tabbedPane.removeAll();
			tabbedPane.addTab(title, newView);
			tabbedPane.setSelectedIndex(0);
		} else {
			tabbedPane.setComponentAt(tabIndex, newView);
			tabbedPane.setSelectedIndex(tabIndex);
		}

	}

	private void resetButtonColors() {
		for (JButton button : new JButton[] { button1, button2, button3, button4 }) {
			if (button != selectedButton) {
				button.setBackground(null);
				button.setForeground(Color.BLACK);
			}
		}
	}

	public static JButton createButton(String text, String iconPath, Dimension size, boolean isDefault) {
		JButton button = new JButton(text);

		ImageIcon icon = new ImageIcon(adminSetting.class.getResource(iconPath));
		Image img = icon.getImage();
		Image resizedImage = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		button.setIcon(new ImageIcon(resizedImage));

		button.setVerticalTextPosition(SwingConstants.BOTTOM);
		button.setHorizontalTextPosition(SwingConstants.CENTER);

		button.setPreferredSize(size);
		button.setMinimumSize(size);
		button.setMaximumSize(size);
		button.setFocusPainted(false);
		if (isDefault) {
			button.setBackground(Color.lightGray);
			button.setForeground(Color.WHITE);
		} else {
			button.setBackground(null);
		}
		return button;

	}

	public static void main(String[] args) {
		new adminSetting();
	}
}
