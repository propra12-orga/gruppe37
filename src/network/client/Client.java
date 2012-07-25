package network.client;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import Gui.Main;

public class Client implements KeyListener {

	public int playerNR;

	private final Main window;

	/** Steuerungseingabe wird in Mainpanel implementiert */
	public Client(Main parent) {
		window = parent;
		window.addKeyListener(this);
		connect();

	}

	public static void connect() {

		try {
			Socket socket = new Socket("134.99.38.183", 1338);
			DataInputStream dataIn = new DataInputStream(
					socket.getInputStream());

			DataOutputStream dataOut = new DataOutputStream(
					socket.getOutputStream());
			dataOut.writeUTF("Hallo Server - Tobi!1");
			String antwort = dataIn.readUTF();

			System.out.println("Der Server sagt: " + antwort);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	/** Loslassen einer Taste */
	public void keyReleased(KeyEvent e) {
		try {
			if ((e.getKeyCode()) == (KeyEvent.VK_UP)) {
				Socket socket = new Socket("134.99.38.183", 1338);
				DataOutputStream dataOut = new DataOutputStream(
						socket.getOutputStream());
				dataOut.writeUTF("notHoch");
			}

			else if ((e.getKeyCode()) == (KeyEvent.VK_DOWN)) {
				Socket socket = new Socket("134.99.38.183", 1338);
				DataOutputStream dataOut = new DataOutputStream(
						socket.getOutputStream());
				dataOut.writeUTF("notRunter");
			}

			else if ((e.getKeyCode()) == (KeyEvent.VK_LEFT)) {
				Socket socket = new Socket("134.99.38.183", 1338);
				DataOutputStream dataOut = new DataOutputStream(
						socket.getOutputStream());
				dataOut.writeUTF("notLinks");
			}

			else if ((e.getKeyCode()) == (KeyEvent.VK_RIGHT)) {
				Socket socket = new Socket("134.99.38.183", 1338);
				DataOutputStream dataOut = new DataOutputStream(
						socket.getOutputStream());
				dataOut.writeUTF("notRechts");
			}
		} catch (Exception errrr) {

		}

	}

	@Override
	/** Drücken (und Halten) einer Taste */
	public void keyPressed(KeyEvent e) {
		try {
			// move player up with UP-ARROW key
			if ((e.getKeyCode()) == (KeyEvent.VK_UP)) {
				Socket socket = new Socket("134.99.38.183", 1338);
				DataOutputStream dataOut = new DataOutputStream(
						socket.getOutputStream());
				dataOut.writeUTF("Hoch");
			}

			else if ((e.getKeyCode()) == (KeyEvent.VK_DOWN)) {
				Socket socket = new Socket("134.99.38.183", 1338);
				DataOutputStream dataOut = new DataOutputStream(
						socket.getOutputStream());
				dataOut.writeUTF("Runter");
			}

			else if ((e.getKeyCode()) == (KeyEvent.VK_LEFT)) {
				Socket socket = new Socket("134.99.38.183", 1338);
				DataOutputStream dataOut = new DataOutputStream(
						socket.getOutputStream());
				dataOut.writeUTF("Links");
			}

			else if ((e.getKeyCode()) == (KeyEvent.VK_RIGHT)) {

				Socket socket = new Socket("134.99.38.183", 1338);
				DataOutputStream dataOut = new DataOutputStream(
						socket.getOutputStream());
				dataOut.writeUTF("Rechts");

			} else if ((e.getKeyCode()) == (KeyEvent.VK_CONTROL)) {

				Socket socket = new Socket("134.99.38.183", 1338);
				DataOutputStream dataOut = new DataOutputStream(
						socket.getOutputStream());
				dataOut.writeUTF("Bombe");

			}

		} catch (Exception errrr) {

		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}
