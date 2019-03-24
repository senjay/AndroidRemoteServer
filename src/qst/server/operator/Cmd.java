package qst.server.operator;

import java.util.ArrayList;

public class Cmd extends BaseOperator{

	public Cmd() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<String> exe(String cmdBody) throws Exception {
		ArrayList<String> ackMsg = new ArrayList<String>();
		Runtime.getRuntime().exec("cmd /c "+cmdBody);
		ackMsg.add("ok");
		return ackMsg;
	}

}
