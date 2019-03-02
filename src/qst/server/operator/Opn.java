package qst.server.operator;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Opn {

	public Opn() {
		// TODO Auto-generated constructor stub
	}
	public static  ArrayList<String> exeOpn(String path,ArrayList<String> msgBackList) throws IOException {
		
		Desktop desk=Desktop.getDesktop();  
		File file=new File(path);//创建一个java文件系统  
		desk.open(file); //调用open（File f）方法打开文件   
		msgBackList.add("ok");
		return msgBackList;
		
	}
}
