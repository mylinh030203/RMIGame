package Presentation;

import DAL.Model.GameData;

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
	GameData gameData;
	Client_GUI_BLL clientGuiBll;

	byte[]anh1,anh2;
	public JButton BT[][] = new JButton[100][100];
	public JButton ListUser;
	public JButton Exit;
	public Panel p = new Panel();
	JFrame client;
	public int size =450;
	public int n = 4;

	public Client_GUI() {
		init();

		client = new JFrame("Client");
		Container cont = client.getContentPane();
		Panel p = new Panel();
		p.setLayout(new GridLayout(n, n));
		
		for(int i = 0; i<n; i++) {
			for(int j = 0; j < n; j++) {
				BT[i][j] = new JButton();
				BT[i][j].setActionCommand(i + " " + j);
				BT[i][j].setIcon(getIcon(gameData.getImage(), (size) / n));
				BT[i][j].setBorder(null);
				p.add(BT[i][j]);
			}
		}
		BT[gameData.getX()][gameData.getY()].setIcon(getIcon(gameData.getImage2(), (size) / n));


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

		addAction();
	}
//	public void addAction() {
//		addAction();
//	}

	public void init() {
		clientGuiBll = new Client_GUI_BLL(){
			@Override
			public void updateClientUI() {
				NewGame();
			}


			@Override
			public void onResultWrong() {
				block();

			}

			@Override
			public void notification(String mess) {

			}
		};

		this.gameData = clientGuiBll.onStartGame();
		this.n = gameData.getN();
	}

	public  void addAction() {

		for (int i = 0; i < gameData.getN(); i++)
			for (int j = 0; j < gameData.getN(); j++) {
				BT[i][j].addActionListener(e -> {
					String[] ans = e.getActionCommand().split(" ");
					int x = Integer.parseInt(ans[0]);
					int y = Integer.parseInt(ans[1]);

					clientGuiBll.onClickAns(x, y);

				});
			}
		Exit.addActionListener(e -> {
			// TODO Auto-generated method stub
			System.out.println("Hello");
			setVisible(false);

		});

		ListUser.addActionListener(e -> {
			// TODO Auto-generated method stub
			bxh();
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
private Icon getIcon(byte[]anh, int size) {
	int width = size, height = size;
	Image image = new ImageIcon(anh).getImage();
	Icon icon = new ImageIcon(image.getScaledInstance(width, height, image.SCALE_SMOOTH));
	return icon;
}


	
	public void notification(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
	
	public Client_GUI NewGame() {
		client.dispose();
		return new Client_GUI();
	}

	public Block_GUI block(){
		client.dispose();
		return new Block_GUI();
	}
	private void bxh(){
		BXH_GUI.getInstance().showRanking();
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
		Client_GUI client_GUI = new  Client_GUI();
//		client_GUI.NewGame(10);
	}
}
