package qst.server.operator;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Robot;
import java.util.ArrayList;

public class Mva extends BaseOperator{
	
	private Robot robot;
	public Mva() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<String> exe(String cmdBody) throws Exception {
		ArrayList<String> ackMsg = new ArrayList<String>();
		robot=new Robot();
		String position[]=cmdBody.split(",");
		double ix=Double.parseDouble(position[0]);
		double iy=Double.parseDouble(position[1]);
		int x=0,y=0;
		if(ix>=0 && ix<1 &&iy>=0&&iy<1)
		{
			Dimension dimension=java.awt.Toolkit.getDefaultToolkit().getScreenSize();
			double width=dimension.getWidth();
			double height=dimension.getHeight();
			x=(int) (ix*width);
			y=(int) (iy*height);
		}
		else {
			x=(int) ix;
			y=(int) iy;
		}
		int k=10;//win10bug，导致鼠标绝对移动是一个逼近过程，用一个循环
		while((--k)>0)
		{
			robot.mouseMove(x,y);
		}
		
		ackMsg.add("ok");
		return ackMsg;
	}

}
