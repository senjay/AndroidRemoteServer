package qst.server.operator;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Clk extends BaseOperator{
	private Robot robot;
	public Clk() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<String> exe(String cmdBody) throws Exception {
		ArrayList<String> ackMsg = new ArrayList<String>();
		robot=new Robot();
		switch (cmdBody) {
		case "left":
			robot.mousePress(KeyEvent.BUTTON1_MASK);
			robot.mouseRelease(KeyEvent.BUTTON1_MASK);
			break;
		case "right":
			robot.mousePress(KeyEvent.BUTTON3_MASK);
			robot.mouseRelease(KeyEvent.BUTTON3_MASK);
			break;
		case "left_press":
			robot.mousePress(KeyEvent.BUTTON1_MASK);
			break;
		case "left_release":
			robot.mouseRelease(KeyEvent.BUTTON1_MASK);
			break;
		case "right_press":
			robot.mousePress(KeyEvent.BUTTON3_MASK);
			break;
		case "right_release":
			robot.mouseRelease(KeyEvent.BUTTON3_MASK);
			break;
		}		
		ackMsg.add("ok");
		return ackMsg;
	}

}
