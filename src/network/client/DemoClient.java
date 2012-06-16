package network.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * TODO
 * 
 * - Befehle verarbeiten
 * 
 * - Einbettung
 * 
 * @author dario
 * 
 */

public class DemoClient {
	public static void main(String[] args) {

		try (Socket socket = new Socket("localhost", 1338)) {

			DataInputStream dataIn = new DataInputStream(
					socket.getInputStream());
			DataOutputStream dataOut = new DataOutputStream(
					socket.getOutputStream());
			dataOut.writeUTF("Hallo Server!");
			String antwort = dataIn.readUTF();
			System.out.println("Der Server sagt: " + antwort);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
