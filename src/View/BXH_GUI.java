package View;

import BLL.BXH_GUI_BLL;
import Model.User;
import util.VectorSubport;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class BXH_GUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	public Vector vT = new Vector<>();
	public Vector vD = new Vector<>();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BXH_GUI frame = new BXH_GUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BXH_GUI() {
		init();
		setVisible(true);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 578, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(49, 49, 465, 299);
		contentPane.add(scrollPane);
		
		vT.add("Username");
		vT.add("Fullname");
		vT.add("Age");
		vT.add("Score");
		
		table = new JTable();
		table.setModel(new DefaultTableModel(vD, vT));
		scrollPane.setViewportView(table);
		setLocationRelativeTo(null);
	}
	
	public void setData(Vector data) {
		vD = data;
		table.setModel(new DefaultTableModel(vD, vT));
	}
	public void init(){
		BXH_GUI_BLL bxhGuiBll = new BXH_GUI_BLL() {
			@Override
			public void notification(String mess) {

			}

			@Override
			public void updateBxhUI(List<User> userList) {
				Vector vt = VectorSubport.ArraylistToVector(userList);
				setData(vt);
			}
		};
	}
}
