package qst.server.operator;

import java.util.ArrayList;

public class Slp extends BaseOperator{

	public Slp() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<String> exe(String cmdBody) throws Exception {
		ArrayList<String> ackMsg = new ArrayList<String>();
		Thread.sleep(Integer.parseInt(cmdBody));
		ackMsg.add("ok");
		return ackMsg;
	}

}
