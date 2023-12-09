package csc8370;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    ChatCommunication chat;
    ServerSocket serverSocket;
    public Server(int portNumber) throws IOException, ClassNotFoundException{
        serverSocket = new ServerSocket(portNumber);
        System.out.println("Waiting for client connection...  ");
        while(true) {
			acceptConnection();
        }
    }
    
    private void acceptConnection() throws IOException, ClassNotFoundException {
        Socket clientSocket = serverSocket.accept();
        String address =  (clientSocket.getInetAddress()).getHostAddress();
        ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
        Packet temp;
			temp = (Packet) (in.readObject());
			System.out.println(address + " : " +temp.core.text);
        if (temp.core.text.contains("Hey! Want to chat?")) {
        	
        	chat = new ChatCommunication(clientSocket,in,out,"Server");
            temp.core.text = "Sure. Let us begin. My public keys are : ";
            out.writeObject(temp);
            System.out.println("let's chat");
        Sender sender = new Sender(chat, "Me : ");
        Receiver receiver = new Receiver(chat, "Client : ","");
        sender.start();
        receiver.start();
        } else {
            System.out.println("Yo");
            clientSocket.close();            
        }
        
    }
}
