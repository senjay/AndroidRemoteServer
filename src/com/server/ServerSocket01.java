package com.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;

public class ServerSocket01 {
    int port = 8019;// 自定义一个端口，端口号尽可能挑选一些不被其他服务占用的端口，祥见http://blog.csdn.net/hsj521li/article/details/7678880
    static int connect_count = 0;// 连接次数统计
    ArrayList<String>  msgBackList=new ArrayList<String>();

    public ServerSocket01() {
        // TODO Auto-generated constructor stub
    }

    public ServerSocket01(int port) {
        super();
        this.port = port;
    }

    private void printLocalIp(ServerSocket serverSocket) {// 枚举打印服务端的IP
        try {
            System.out.println("服务端命令端口prot=" + serverSocket.getLocalPort());
            Enumeration<NetworkInterface> interfaces = null;
            interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface ni = interfaces.nextElement();
                Enumeration<InetAddress> addresss = ni.getInetAddresses();
                while (addresss.hasMoreElements()) {
                    InetAddress nextElement = addresss.nextElement();
                    String hostAddress = nextElement.getHostAddress();
                    System.out.println("本机IP地址为：" + hostAddress);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void work() throws IOException {
        // 注意：由于Socket的工作是阻塞式，Android端Socket的工作必须在新的线程中实现，若在UI主线程中工作会报错

        ServerSocket serverSocket = new ServerSocket(port);
        printLocalIp(serverSocket);
        while (true) {// 无限循环，使之能结束当前socket服务后，准备下一次socket服务

            System.out.println("Waiting client to connect.....");
            Socket socket = serverSocket.accept();// 阻塞式，直到有客户端连接进来，才会继续往下执行，否则一直停留在此代码
            System.out.println("Client connected from: "
                    + socket.getRemoteSocketAddress().toString());

            // eclipse 快捷键
            // alt+/ 代码补全
            // ctr+1 代码修正
            // ctr+2，L 命名给局部变量

            // TODO: As follows:
            
            ArrayList<String> cmdlist=readSocketMsg(socket);
            dealCmd(cmdlist);
            writebackMsg(socket);
            close(socket);
            // 实现读客户端发送过来的命令，例如实现private ArrayList<String> readSocketMsg(Socket socket) throws IOException函数
            // 调用 ArrayList<String> cmdList=readSocketMsg(socket);
            // 定义一个全局变量 ArrayList<String>  msgBackList,供服务端处理命令，并将返回结果赋值给msgBackList
            // msgBackList=dealCmd(cmdList);//处理命令，例如dir命令，并将处理结果给msgBackList
            // 实现服务端写回数据函数 private void writebackMsg(Socket socket) throws IOException
            // 将msgBackList按规定的格式写回给客户端
            // 实现 private void close(Socket socket) throws IOException，关闭socket
            // 调用 close(socket);

            System.out.println("当前Socket服务结束");
        }
    }
    private void close(Socket socket) throws IOException{
    	socket.close();
    }
    private ArrayList<String> dealCmd(ArrayList<String> cmdlist) {
		// TODO Auto-generated method stub
    	//ArrayList<String> backlist=new ArrayList<String>();
    	String cmd=cmdlist.get(0);
    	String cmdtype=cmd.substring(0,cmd.indexOf(":") );//命令类型
   	 	String cmdbody=cmd.substring(cmdtype.length()+1);//文件地址
   	 	exeDir(cmdbody);
		return null;
	}
    private void writebackMsg(Socket socket) throws IOException
    {
    	OutputStreamWriter osw=new OutputStreamWriter(socket.getOutputStream(), "utf-8");
    	BufferedWriter writer=new BufferedWriter(osw);
    	writer.write(msgBackList.size()+"\n");
    	for (int i = 0; i < msgBackList.size(); i++) {
			writer.write(msgBackList.get(i)+"\n");
		}
    	writer.flush();
    }
	private ArrayList<String> readSocketMsg(Socket socket) throws IOException
    {
    	ArrayList<String> cmdlist=new ArrayList<String>();
    	InputStreamReader isr=new InputStreamReader(socket.getInputStream(),"utf-8");
    	BufferedReader reader=new BufferedReader(isr);
    	String numStr = reader.readLine();
    	int linenum= Integer.parseInt(numStr);
    	for (int i = 0; i <linenum; i++) {
    		cmdlist.add(reader.readLine());
			
		}
		return cmdlist;
    	
    }
	private void exeDir(String cmdBody) {
        // TODO Auto-generated method stub
        File file = new File(cmdBody);
        File[] listFiles = file.listFiles();
        
        msgBackList.clear();
        
        try {
			msgBackList.add(file.getCanonicalPath());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        for(File mfile:listFiles){
            String fileName = mfile.getName();
            long lastModified = mfile.lastModified();//获取文件修改时间
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//给时间格式，例如：2018-03-16 09:50:23
            String fileDate = dateFormat.format(new Date(lastModified));//取得文件最后修改时间，并按格式转为字符串
            String fileSize="0";
            String isDir="1";
            if(!mfile.isDirectory()){//判断是否为目录
                isDir="0";
                fileSize=""+mfile.length();
            }
            msgBackList.add(fileName+">"+fileDate+">"+fileSize+">"+isDir+">");
        }

    }

}
