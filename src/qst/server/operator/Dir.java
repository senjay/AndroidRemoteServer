package qst.server.operator;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Dir extends BaseOperator{

	public Dir() {
		// TODO Auto-generated constructor stub
	}

	public static ArrayList<String> exeDir(String cmdBody,String lastpath) throws IOException {
		// TODO Auto-generated method stub
		File[] listFiles=null;
		File file=null;
		 ArrayList<String> msgBackList=new ArrayList<String>();
		//如果第一次发送的命令就是..默认返回盘符,否则上一级（上一次的路径+..），注意if顺序
		if (cmdBody.equals(".."))
		{
			if(lastpath.equals(""))
				cmdBody="...";
			else
			{
				cmdBody=lastpath+"\\..";
			}
		}
		
		if(cmdBody.equals("..."))
		{
			listFiles=File.listRoots();
			msgBackList.add("ok");
			msgBackList.add("");
			for(File mfile:listFiles)
			{				
				msgBackList.add(mfile.getCanonicalPath() + ">" + "888" + ">" + "888" + ">" + "2" + ">");//数字888为占位用			
			}
			
		}
		else {
			file = new File(cmdBody);
			System.out.println("发送来的命令："+cmdBody);
			msgBackList.add("ok");
			listFiles = file.listFiles();
			msgBackList.add(file.getCanonicalPath());
			System.out.println("使用的路径"+file.getCanonicalPath());
			for (File mfile : listFiles) {
				String fileName = mfile.getName();
				long lastModified = mfile.lastModified();// 获取文件修改时间
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 给时间格式，例如：2018-03-16
																							// 09:50:23
				String fileDate = dateFormat.format(new Date(lastModified));// 取得文件最后修改时间，并按格式转为字符串
				String fileSize = "0";
				String isDir = "1";
				if (!mfile.isDirectory()) {// 判断是否为目录
					isDir = "0";
					fileSize = "" + mfile.length();
				}
				msgBackList.add(fileName + ">" + fileDate + ">" + fileSize + ">" + isDir + ">");
			}
			
		}
		return msgBackList;
	}

	@Override
	public ArrayList<String> exe(String cmdBody) throws Exception {
		// TODO Auto-generated method stub
		
		return null;
	}
}
