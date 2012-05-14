import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Einstellungen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Einstellungen() {

		initUI();

	}

	public final void initUI() {

		JPanel panel = new JPanel();
		getContentPane().add(panel);

		panel.setLayout(null);

		JButton quitButton = new JButton("Beenden");
		quitButton.setBounds(490, 315, 90, 40);
		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});

		panel.add(quitButton);

		setTitle("Einstellungen");
		setSize(600, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Einstellungen ex = new Einstellungen();
				ex.setVisible(true);

				/*
				 * final JLabel label = new JLabel("Lautstärke");
				 * this.add(label); JSlider wahl = new
				 * JSlider(JSlider.HORIZONTAL, 0, 255, 0);
				 * wahl.addChangeListener(new ChangeListener() {
				 * 
				 * @Override public void stateChanged(ChangeEvent e) { JSlider
				 * source = (JSlider) e.getSource(); if
				 * (!source.getValueIsAdjusting()) { int wert =
				 * source.getValue(); Color farbe = new Color(wert, wert, wert);
				 * label.setForeground(farbe); } } }); this.add(wahl,
				 * BorderLayout.CENTER); }
				 * 
				 * private void add(JLabel label) { // TODO Auto-generated
				 * method stub
				 * 
				 * }
				 * 
				 * private void add(JSlider wahl, String center) { // TODO
				 * Auto-generated method stub
				 */}
		});
	}
}