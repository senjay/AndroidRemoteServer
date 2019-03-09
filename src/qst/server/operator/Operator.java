package qst.server.operator;

import java.util.ArrayList;

public class Operator {

	public static ArrayList<String> execmd(String cmdhead,String cmdbody) throws Exception {
		cmdhead.toLowerCase();
		switch (cmdhead) {
		case "opn":
			return new Opn().exe(cmdbody);
		case "key":
			return new Key().exe(cmdbody);
		default:
			break;
		}
		return null;
	}

}
