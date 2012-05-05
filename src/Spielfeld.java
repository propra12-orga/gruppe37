import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Spielfeld extends Default {
	public Spielfeld() {
		frame.getContentPane().add(panel1);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setVisible(true);
		panel1.setLayout(null);
		panel1.add(bild1);
		panel1.add(bild2);
		panel1.add(bild3);
		panel1.add(bild4);
		panel1.add(bild5);
		panel1.add(bild6);
		panel1.add(bild7);
		panel1.add(bild8);
		panel1.add(bild9);
		panel1.add(bild10);
		panel1.add(bild11);
		panel1.add(bild12);
		panel1.add(bild13);
		panel1.add(bild14);

		panel1.add(bild15);
		panel1.add(bild16);
		panel1.add(bild17);
		panel1.add(bild18);
		panel1.add(bild19);
		panel1.add(bild20);
		panel1.add(bild21);
		panel1.add(bild22);
		panel1.add(bild23);
		panel1.add(bild24);
		panel1.add(bild25);
		panel1.add(bild26);
		panel1.add(bild27);
		panel1.add(bild28);
		panel1.add(bild29);

		panel1.add(bild30);
		panel1.add(bild31);
		panel1.add(bild32);
		panel1.add(bild33);
		panel1.add(bild34);
		panel1.add(bild35);
		panel1.add(bild36);
		panel1.add(bild37);
		panel1.add(bild38);
		panel1.add(bild39);
		panel1.add(bild40);
		panel1.add(bild41);
		panel1.add(bild42);

		panel1.add(bild43);
		panel1.add(bild44);
		panel1.add(bild45);
		panel1.add(bild46);
		panel1.add(bild47);
		panel1.add(bild48);
		panel1.add(bild49);
		panel1.add(bild50);
		panel1.add(bild51);
		panel1.add(bild52);
		panel1.add(bild53);
		panel1.add(bild54);
		panel1.add(bild55);
		panel1.add(bild56);

		// Im Uhrzeigersinn, 14 pro Reihe

		bild1.setBounds(40, 40, 30, 30);
		bild2.setBounds(70, 40, 30, 30);
		bild3.setBounds(100, 40, 30, 30);
		bild4.setBounds(130, 40, 30, 30);
		bild5.setBounds(160, 40, 30, 30);
		bild6.setBounds(190, 40, 30, 30);
		bild7.setBounds(220, 40, 30, 30);
		bild8.setBounds(250, 40, 30, 30);
		bild9.setBounds(280, 40, 30, 30);
		bild10.setBounds(310, 40, 30, 30);
		bild11.setBounds(340, 40, 30, 30);
		bild12.setBounds(370, 40, 30, 30);
		bild13.setBounds(400, 40, 30, 30);
		bild14.setBounds(430, 40, 30, 30);

		bild15.setBounds(460, 40, 30, 30);
		bild16.setBounds(460, 70, 30, 30);
		bild17.setBounds(460, 100, 30, 30);
		bild18.setBounds(460, 130, 30, 30);
		bild19.setBounds(460, 160, 30, 30);
		bild20.setBounds(460, 190, 30, 30);
		bild21.setBounds(460, 220, 30, 30);
		bild22.setBounds(460, 250, 30, 30);
		bild23.setBounds(460, 280, 30, 30);
		bild24.setBounds(460, 310, 30, 30);
		bild25.setBounds(460, 340, 30, 30);
		bild26.setBounds(460, 370, 30, 30);
		bild27.setBounds(460, 400, 30, 30);
		bild28.setBounds(460, 430, 30, 30);
		bild29.setBounds(460, 460, 30, 30);

		bild30.setBounds(430, 460, 30, 30);
		bild31.setBounds(400, 460, 30, 30);
		bild32.setBounds(370, 460, 30, 30);
		bild33.setBounds(340, 460, 30, 30);
		bild34.setBounds(310, 460, 30, 30);
		bild35.setBounds(280, 460, 30, 30);
		bild36.setBounds(250, 460, 30, 30);
		bild37.setBounds(220, 460, 30, 30);
		bild38.setBounds(190, 460, 30, 30);
		bild39.setBounds(160, 460, 30, 30);
		bild40.setBounds(130, 460, 30, 30);
		bild41.setBounds(100, 460, 30, 30);
		bild42.setBounds(70, 460, 30, 30);

		bild43.setBounds(40, 460, 30, 30);
		bild44.setBounds(40, 430, 30, 30);
		bild45.setBounds(40, 400, 30, 30);
		bild46.setBounds(40, 370, 30, 30);
		bild47.setBounds(40, 340, 30, 30);
		bild48.setBounds(40, 310, 30, 30);
		bild49.setBounds(40, 280, 30, 30);
		bild50.setBounds(40, 250, 30, 30);
		bild51.setBounds(40, 220, 30, 30);
		bild52.setBounds(40, 190, 30, 30);
		bild53.setBounds(40, 160, 30, 30);
		bild54.setBounds(40, 130, 30, 30);
		bild55.setBounds(40, 100, 30, 30);
		bild56.setBounds(40, 70, 30, 30);
	}

	private final JFrame frame = new JFrame();
	private final ImageIcon solidBlock = new ImageIcon("HardBlock.png");
	private final JPanel panel1 = new JPanel();

	// Grafiklabels der statischen Blöcke

	private final JLabel bild1 = new JLabel(solidBlock);
	private final JLabel bild2 = new JLabel(solidBlock);
	private final JLabel bild3 = new JLabel(solidBlock);
	private final JLabel bild4 = new JLabel(solidBlock);
	private final JLabel bild5 = new JLabel(solidBlock);
	private final JLabel bild6 = new JLabel(solidBlock);
	private final JLabel bild7 = new JLabel(solidBlock);
	private final JLabel bild8 = new JLabel(solidBlock);
	private final JLabel bild9 = new JLabel(solidBlock);
	private final JLabel bild10 = new JLabel(solidBlock);
	private final JLabel bild11 = new JLabel(solidBlock);
	private final JLabel bild12 = new JLabel(solidBlock);
	private final JLabel bild13 = new JLabel(solidBlock);
	private final JLabel bild14 = new JLabel(solidBlock);

	private final JLabel bild15 = new JLabel(solidBlock);
	private final JLabel bild16 = new JLabel(solidBlock);
	private final JLabel bild17 = new JLabel(solidBlock);
	private final JLabel bild18 = new JLabel(solidBlock);
	private final JLabel bild19 = new JLabel(solidBlock);
	private final JLabel bild20 = new JLabel(solidBlock);
	private final JLabel bild21 = new JLabel(solidBlock);
	private final JLabel bild22 = new JLabel(solidBlock);
	private final JLabel bild23 = new JLabel(solidBlock);
	private final JLabel bild24 = new JLabel(solidBlock);
	private final JLabel bild25 = new JLabel(solidBlock);
	private final JLabel bild26 = new JLabel(solidBlock);
	private final JLabel bild27 = new JLabel(solidBlock);
	private final JLabel bild28 = new JLabel(solidBlock);
	private final JLabel bild29 = new JLabel(solidBlock);

	private final JLabel bild30 = new JLabel(solidBlock);
	private final JLabel bild31 = new JLabel(solidBlock);
	private final JLabel bild32 = new JLabel(solidBlock);
	private final JLabel bild33 = new JLabel(solidBlock);
	private final JLabel bild34 = new JLabel(solidBlock);
	private final JLabel bild35 = new JLabel(solidBlock);
	private final JLabel bild36 = new JLabel(solidBlock);
	private final JLabel bild37 = new JLabel(solidBlock);
	private final JLabel bild38 = new JLabel(solidBlock);
	private final JLabel bild39 = new JLabel(solidBlock);
	private final JLabel bild40 = new JLabel(solidBlock);
	private final JLabel bild41 = new JLabel(solidBlock);
	private final JLabel bild42 = new JLabel(solidBlock);

	private final JLabel bild43 = new JLabel(solidBlock);
	private final JLabel bild44 = new JLabel(solidBlock);
	private final JLabel bild45 = new JLabel(solidBlock);
	private final JLabel bild46 = new JLabel(solidBlock);
	private final JLabel bild47 = new JLabel(solidBlock);
	private final JLabel bild48 = new JLabel(solidBlock);
	private final JLabel bild49 = new JLabel(solidBlock);
	private final JLabel bild50 = new JLabel(solidBlock);
	private final JLabel bild51 = new JLabel(solidBlock);
	private final JLabel bild52 = new JLabel(solidBlock);
	private final JLabel bild53 = new JLabel(solidBlock);
	private final JLabel bild54 = new JLabel(solidBlock);
	private final JLabel bild55 = new JLabel(solidBlock);
	private final JLabel bild56 = new JLabel(solidBlock);

	public static void main(String[] args) {
		new Spielfeld();
	}
}