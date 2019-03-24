package qst.server.socket;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;

public class FileDownLoadSocketThread {

	private ServerSocket serverSocket;
	private long filePos=0l;
	private File file;
	public FileDownLoadSocketThread(File file,long filePos) {
	    // TODO Auto-generated constructor stub
	    try {
	        serverSocket = new ServerSocket(0);//动态分配可用端口
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    this.file=file;
	    this.filePos=filePos;
	}
	public int getPort()
	{
		return serverSocket.getLocalPort();
		
	}

}
