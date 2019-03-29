package qst.server.operator;

import java.io.File;
import java.util.ArrayList;

import qst.server.socket.FileDownAndUpLoadSocketThread;

public class Ulf extends BaseOperator{

	public Ulf() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<String> exe(String cmdBody) throws Exception {
		ArrayList<String> ackMsg = new ArrayList<String>();
		int filemode = -1;
		File file = null;
		int pos=cmdBody.indexOf("?");
		String filename="";
		long filepos=0;
		//获取上传模式
		if(pos!=-1)
		{
			filemode=Integer.parseInt(cmdBody.substring(pos+1));
			filename=cmdBody.substring(0, pos);
		}
		else
		{
			filemode=-1;
			filename=cmdBody;	
		}
		//对上传模式进行文件创建方法分析
		file=new File(filename);
		if(filemode==-1)
		{
			if(file.exists())
			{
				
				String temp=filename;
				filename=temp.substring(0, temp.indexOf("."))+"(1)."+temp.substring(temp.indexOf(".")+1);
				file=new File(filename);
			}
			int i=1;
			while(file.exists())
			{
				
				filename=filename.replace("("+i+")", "("+(++i)+")");		
				System.out.println(filename);
				file=new File(filename);
			}
			file.createNewFile();
			filepos=0;
		}
		else{
			if(!file.exists())
			{
				file.createNewFile();
				filepos=0;
			}
			else
			{
				filepos=file.length();
			}
		}
		//将filepos 传送给服务端...

		FileDownAndUpLoadSocketThread fileDownLoadSocketThread=new FileDownAndUpLoadSocketThread(file,filepos,"down");
		String port =fileDownLoadSocketThread.getPort()+"";

		ackMsg.add("ok");
		ackMsg.add("ulf");
		ackMsg.add(port);
		ackMsg.add(filepos+"");
		fileDownLoadSocketThread.start();
		return ackMsg;
	}

}
