package csc8370;

import java.io.EOFException;
import java.io.IOException;
import java.net.SocketException;

public class Sender extends Thread {
	String prompt;
	ChatCommunication chat;
	Packet pkt;
	public Sender(ChatCommunication chat, String prompt) {
		this.chat = chat;
		this.prompt = prompt;
	}

	public void run() {
		pkt = new Packet(new Message(chat.myPublicKey));
		try {
			chat.sendMessage(pkt);
		} catch (EOFException | SocketException e) {
			e.printStackTrace();
		}
		pkt = new Packet(new Message(chat.myModulus));
		try {
			chat.sendMessage(pkt);
		} catch (EOFException | SocketException e) {
			e.printStackTrace();
		}

		while (true) {
			String input="";
			try {
				input = chat.stdIn.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (input.equalsIgnoreCase("End Chat")) {
				chat.endChat();
				return;
			}
			pkt = new Packet(new Message(input));
			try {
				chat.sendMessage(pkt);
			} catch (EOFException | SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        System.out.println("<sent at: "+pkt.timestamp+">\n");
		}
	}

}
