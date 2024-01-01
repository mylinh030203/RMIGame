package View;

import Model.GameData;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import BLL.Client_GUI_BLL;
public class Client_GUI extends JFrame{
	GameData gameData = new GameData();
	Client_GUI_BLL clientGuiBll;


	public JButton BT[][] = new JButton[100][100];
	public JButton ListUser;
	public JButton Exit;
	public Panel p = new Panel();
	JFrame client;
	public int size =450;
	public int n = 4;
	public Client_GUI(int n, int x, int y, int color) {
		this.n = n;
		client = new JFrame("Client");
		Container cont = client.getContentPane();
		Panel p = new Panel();
		p.setLayout(new GridLayout(n, n));
		
		for(int i = 0; i<n; i++) {
			for(int j = 0; j < n; j++) {
				BT[i][j] = new JButton();
				BT[i][j].setActionCommand(i + " " + j);
				BT[i][j].setIcon(getIcon(color, 1, (size) / n));
				BT[i][j].setBorder(null);
				p.add(BT[i][j]);
			}
		}
		BT[x][y].setIcon(getIcon(color, 2, (size) / n));
		
		cont.add(p);
		
		Panel p1 = new Panel();
		p1.setLayout(new FlowLayout());
		ListUser = new JButton("BXH");
		Exit = new JButton("Exit");
		p1.add(Exit);
		p1.add(ListUser);
		p1.setLayout(new GridLayout(1,2));
		cont.add(p1, "North");
		
		client.setSize(size, size + 55);
		client.setVisible(true);
		client.setLocationRelativeTo(null);
		client.setResizable(false);

		init();
	}
	public void init() {
		clientGuiBll = new Client_GUI_BLL() {
			@Override
			public void updateClientUI(GameData gameData) {
				NewGame(gameData.getN(), gameData.getX(), gameData.getY(), gameData.getColor());
			}

			@Override
			public void notification(String mess) {

			}
		};

//		clientGuiBll.onStartGame();

		for (int i = 0; i < gameData.getN(); i++)
			for (int j = 0; j < gameData.getN(); j++) {
				BT[i][j].addActionListener(e -> {
					// lấy giá trị x, y từ giao diện


					clientGuiBll.onClickAns(e.getActionCommand());
//						Client_GUI_BLL clientGuiBll = new Client_GUI_BLL() {
//							@Override
//							public void onClickAns(int x, int y) {
//								super.onClickAns(x, y);
//							}
//						}

				});
			}
		Exit.addActionListener(e -> {
			// TODO Auto-generated method stub
			System.out.println("Hello");
			setVisible(false);

		});

		ListUser.addActionListener(e -> {
			// TODO Auto-generated method stub

			//gọi bxh
		});
	}
//	private Icon getIcon(byte[] index, byte[] index2, int size) {
//		int width = size, height = size;
//
//		Image image = new ImageIcon(getClass().getResource("/image/image" + index + "_" + index2 + ".png")).getImage();
//		Icon icon = new ImageIcon(image.getScaledInstance(width, height, image.SCALE_SMOOTH));
//		return icon;
//	}
private Icon getIcon(int index, int index2, int size) {
	int width = size, height = size;
	Image image = new ImageIcon(getClass().getResource("/image/image" + index + "_" + index2 + ".png")).getImage();
	Icon icon = new ImageIcon(image.getScaledInstance(width, height, image.SCALE_SMOOTH));
	return icon;
}
	
	public void notification(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
	
	public Client_GUI NewGame(int n, int x, int y, int color) {
		client.dispose();
		return new Client_GUI(n, x, y, color);
	}


	
	public void setTitle(String title, int Score) {
		client.setTitle("Client: " + title + " | Score: " + Score);
	}
	public void setVisible(boolean Visible) {
		client.setVisible(Visible);
	}
	public void Dispose() {
		client.dispose();
	}
	
	public static void main(String[] args) {
		Client_GUI client_GUI = new  Client_GUI(5, 0, 0,1);
//		client_GUI.NewGame(10);
	}
}
