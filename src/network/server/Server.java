package network.server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import Gui.Main;

public class Server {
	public static boolean exit = false;
	public static boolean rechts = false;
	public static boolean bombe = false;
	public static String nachricht;
	/** Bewegung nach rechts */
	public static boolean moveRight2;
	/** Bewegung nach links */
	public static boolean moveLeft2;
	/** Bewegung nach unten */
	public static boolean moveDown2;
	/** Bewegung nach oben */
	public static boolean moveUp2;

	private final Main window;

	public Server(Main parent) {
		window = parent;

		try {
			ServerSocket serverSocket = new ServerSocket(1338);

			while (exit == false) {
				Socket clientSocket = serverSocket.accept();

				InputStream in = clientSocket.getInputStream();
				OutputStream out = clientSocket.getOutputStream();
				DataInputStream dataIn = new DataInputStream(in);
				DataOutputStream dataOut = new DataOutputStream(out);

				nachricht = dataIn.readUTF();

				if (nachricht.contains("Rechts")) {
					moveRight2 = true;
					moveLeft2 = false;
					moveUp2 = false;
					moveDown2 = false;
					if (nachricht.contains("notRechts")) {
						moveRight2 = false;
					}
				}
				if (nachricht.contains("Links")) {
					moveLeft2 = true;
					moveRight2 = false;
					moveUp2 = false;
					moveDown2 = false;
					if (nachricht.contains("notLinks")) {
						moveLeft2 = false;
					}
				}
				if (nachricht.contains("Hoch")) {
					moveUp2 = true;
					moveDown2 = false;
					moveLeft2 = false;
					moveRight2 = false;
					if (nachricht.contains("notHoch")) {
						moveUp2 = false;
					}
				}
				if (nachricht.contains("Runter")) {
					moveDown2 = true;
					moveLeft2 = false;
					moveRight2 = false;
					moveUp2 = false;
					if (nachricht.contains("notRunter")) {
						moveDown2 = false;
					}
				}
				if (nachricht.contains("Bombe")) {
					bombe = true;
					delay.start();
				}

				window.netpanel.control(1);
				dataOut.writeUTF("Sendetest: " + nachricht);
				dataOut.writeUTF("Test2:" + nachricht);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	javax.swing.Timer delay = new javax.swing.Timer(1000, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			bombe = false;
			delay.stop();
		}
	});
}
