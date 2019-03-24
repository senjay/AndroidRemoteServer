package qst.server.operator;

import java.io.File;
import java.util.ArrayList;

import qst.server.socket.FileDownLoadSocketThread;

public class Dlf extends BaseOperator{

	public Dlf() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<String> exe(String cmdBody) throws Exception {
		//Î´Íê³É
		ArrayList<String> ackMsg = new ArrayList<String>();
		long filepos = 0;
		File file = null;
		int pos=cmdBody.indexOf("?");
		if(pos!=-1)
			filepos=Integer.parseInt(cmdBody.substring(pos+1));
		else
			pos=0;
		FileDownLoadSocketThread fileDownLoadSocketThread=new FileDownLoadSocketThread(file,filepos);
		ackMsg.add("ok");
		return ackMsg;
	}

}
