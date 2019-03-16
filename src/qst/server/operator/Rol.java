package qst.server.operator;

import java.awt.Robot;
import java.util.ArrayList;

public class Rol extends BaseOperator{
	private Robot robot;
	public Rol() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<String> exe(String cmdBody) throws Exception {
		ArrayList<String> ackMsg = new ArrayList<String>();
		robot=new Robot();
		int cnt=Integer.parseInt(cmdBody);
		robot.mouseWheel(cnt);
		ackMsg.add("ok");
		System.out.println(cnt);
		return ackMsg;
	}

}
