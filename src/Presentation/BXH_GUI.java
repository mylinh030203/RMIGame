package Presentation;

import BLL.BXH_GUI_BLL;
import DAL.Model.User;
import util.VectorSupport;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class BXH_GUI extends JFrame {

	private volatile static BXH_GUI instance;

	public static BXH_GUI getInstance() {
		if (instance == null) {
			instance = new BXH_GUI();
		}

		return instance;
	}

	private JPanel contentPane;
	private JTable table;
	public Vector vT = new Vector<>();
	public Vector vD = new Vector<>();

	private BXH_GUI_BLL bxhGuiBll;

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
	private BXH_GUI() {
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

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				hideRanking();
			}
		});


	}
	
	public void setData(Vector data) {
		vD = data;
		table.setModel(new DefaultTableModel(vD, vT));
	}
	private void init(){
		this.bxhGuiBll = new BXH_GUI_BLL() {
			@Override
			public void notification(String mess) {

			}

			@Override
			public void updateBxhUI(List<User> userList) {
				Vector vt = VectorSupport.ArraylistToVector(userList);
				setData(vt);
			}
		};
	}

	public void showRanking() {
		this.bxhGuiBll.onShowRanking();
		this.setVisible(true);
	}

	private void hideRanking() {
		this.bxhGuiBll.onHide();
		this.setVisible(false);
	}
}
