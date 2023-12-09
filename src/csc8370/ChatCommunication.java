package csc8370;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;


public class ChatCommunication {
	String type;
	String hisPublic;
	String hisModulus;
	String myPublicKey;
	String myModulus;
	ElGamal myelgamal;
	ObjectInputStream in;
    ObjectOutputStream out;
    BufferedReader stdIn;
    Socket socket;
    int count1=0;
    int count2=0;
    public ChatCommunication(Socket echoSocket,ObjectInputStream in,ObjectOutputStream out, String type) {
    	myelgamal = new ElGamal();
    	myPublicKey = myelgamal.getPublicKey();
    	myModulus = myelgamal.getModulus();
        this.type = type;
    	this.socket = echoSocket;
    	this.out = out;
    	this.in = in;
        stdIn = new BufferedReader(
                new InputStreamReader(System.in));
    }
    
    public void sendMessage(Packet inputString) throws EOFException, SocketException {
    
    	if (count1==0||count1==1){
    	}
    	else
    	inputString.core.text = myelgamal.encrypt(inputString.core.text,hisModulus,hisPublic);
        try {
            out.writeObject(inputString);
            count1+=1;
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
    
    public Packet receiveMessage() throws IOException, ClassNotFoundException, SocketException, EOFException {
        Packet temp = (Packet) in.readObject();
        
        if(count2==0||count2==1){
        	
        }
        else{
        	if (type.equalsIgnoreCase("Client")){
        		System.out.println("Server: ");
        	}
        	else {
        		System.out.println("Client: ");
        	}
        	System.out.println("Encrypted Message: "+temp.core.text);
        	temp.core.text = myelgamal.decrypt(temp.core.text);
	        System.out.println("Decrypted Message: "+temp.core.text);
	 
	        
        }
        count2+=1;
        return temp;
    }
    
    public void endChat() {
        try {
            socket.close();
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
