package qst.server.operator;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Robot;
import java.util.ArrayList;

public class Mov extends BaseOperator {
	
	private Robot robot;
	public Mov() {
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public ArrayList<String> exe(String cmdBody) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<String> ackMsg = new ArrayList<String>();
		robot=new Robot();
		String dif[]=cmdBody.split(",");
		int x = 0,y = 0;
		int difx=Integer.parseInt(dif[0]);
		int dify=Integer.parseInt(dif[1]);
		Point point = java.awt.MouseInfo.getPointerInfo().getLocation();
		Dimension dimension=java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int width=(int) dimension.getWidth();
		int height=(int) dimension.getHeight();
		x=point.x+difx;
		y=point.y+dify;
		
		x=x<0?0:x;
		x=x>width?width:x;
		y=y<0?0:y;
		y=y>height?height:y;		
		robot.mouseMove(x,y);
		
		ackMsg.add("ok");
		return ackMsg;
	}

}
