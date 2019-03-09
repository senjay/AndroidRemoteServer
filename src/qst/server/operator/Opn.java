package qst.server.operator;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Opn extends BaseOperator{

	public Opn() {
		// TODO Auto-generated constructor stub
	}
	public static  ArrayList<String> exeOpn(String path) throws IOException {
		ArrayList<String>msgBackList =new ArrayList<String>();
		Desktop desk=Desktop.getDesktop();  
		File file=new File(path);//创建一个java文件系统  
		desk.open(file); //调用open（File f）方法打开文件   
		msgBackList.add("ok");
		msgBackList.add(file.getCanonicalPath()+"打开成功");
		return msgBackList;
		
	}
	@Override
	public ArrayList<String> exe(String cmdBody) throws Exception {	
		ArrayList<String>msgBackList =new ArrayList<String>();
		Desktop desk=Desktop.getDesktop();  
		File file=new File(cmdBody);//创建一个java文件系统  
		desk.open(file); //调用open（File f）方法打开文件   
		msgBackList.add("ok");
		msgBackList.add(file.getCanonicalPath()+"打开成功");
		return msgBackList;
	}
}
