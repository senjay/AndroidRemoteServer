package qst.server.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class FileDownAndUpLoadSocketThread extends Thread{

	private String method="";//上传还是下载,up 为给客户端发送文件,down为从客户端接收文件
	private ServerSocket serverSocket;
	private long filePos=0l;
	private File file;
	
	private Socket socket;
	public FileDownAndUpLoadSocketThread(File file,long filePos,String method) {
	    // TODO Auto-generated constructor stub
	    try {
	        serverSocket = new ServerSocket(0);//动态分配可用端口
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    this.file=file;
	    this.filePos=filePos;
	    this.method=method;
	}
	private void work() throws IOException {       	
	       System.out.println("等待文件客户端接入...");
	       socket = serverSocket.accept();// 阻塞式，直到有客户端连接进来，才会继续往下执行，否则一直停留在此代码
	       System.out.println("文件下载客户端接入"+ socket.getRemoteSocketAddress().toString());
	       if(method.equals("up"))
	    	   sendFile();	
	       else if (method.equals("down"))
	    	  receivefile();
	   
	       close();
	       System.out.println("文件Socket服务结束");	            
	    
	}
	private void sendFile() throws IOException {	
		 byte[] sendByte = new byte[1024];		 
		 DataOutputStream dout=new DataOutputStream(socket.getOutputStream());
		 FileInputStream fis = new FileInputStream(file);
		 fis.skip(filePos);//跳过已经下载的长度
		 //dout.writeUTF(file.getName());//发送文件名
		 
         int length;
         while((length = fis.read(sendByte, 0, sendByte.length))>0){
             dout.write(sendByte,0,length);
             
             dout.flush();
         }
         dout.close();
	}
	 private void close() throws IOException{
	    	socket.close();
	 }
	 
	 
	 
	 private void receivefile () throws IOException {
		    byte[] inputByte =new byte[1024];
	        int length = 0;
	        DataInputStream din = new DataInputStream(socket.getInputStream());
	        FileOutputStream fout=new FileOutputStream(file,true);//以追加方式写入,因为不管什么模式文件原本存在与否  前面都已经分析创建好了文件
	       
	        while (true) {
	            if (din != null) {
	                length = din.read(inputByte, 0, inputByte.length);
	            }
	            if (length == -1) {
	                break;
	            }	       
	            
	            fout.write(inputByte, 0, length);
	            fout.flush();
	        }
	        if (fout != null)
	            fout.close();
	        if (din != null)
	            din.close();
	        if (socket != null)
	            socket.close();
	}
	public int getPort()
	{
		return serverSocket.getLocalPort();		
	}
	@Override
	public void run() {
		super.run();
		try {
			work();
		} catch (IOException e) {
			e.printStackTrace();
			try {
				close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
