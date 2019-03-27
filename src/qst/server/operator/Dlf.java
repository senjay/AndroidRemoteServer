package qst.server.operator;

import java.io.File;
import java.util.ArrayList;

import qst.server.socket.FileDownAndUpLoadSocketThread;

public class Dlf extends BaseOperator{

	public Dlf() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<String> exe(String cmdBody) throws Exception {
		

		ArrayList<String> ackMsg = new ArrayList<String>();
		long filepos = 0;
		File file = null;
		int pos=cmdBody.indexOf("?");
		if(pos!=-1)
		{
			filepos=Integer.parseInt(cmdBody.substring(pos+1));
			file=new File(cmdBody.substring(0, pos));
		}
		else
		{
			filepos=0;
			file=new File(cmdBody);
		}
		FileDownAndUpLoadSocketThread fileUpLoadSocketThread=new FileDownAndUpLoadSocketThread(file,filepos,"up");
		String port =fileUpLoadSocketThread.getPort()+"";
		ackMsg.add("ok");
		ackMsg.add("dlf");
		ackMsg.add(port);
		ackMsg.add(file.length()+"");
		fileUpLoadSocketThread.start();
		return ackMsg;
	}

}
