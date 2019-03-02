package qst.server.app;

import java.io.IOException;

import qst.server.socket.CmdServerSocket;

public class ServerSocketApp {

	public ServerSocketApp() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 try {
	            new CmdServerSocket().work();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	}

}
