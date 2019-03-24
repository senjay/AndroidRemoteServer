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
		case "mov":
			return new Mov().exe(cmdbody);
		case "mva":
			return new Mva().exe(cmdbody);
		case "clk":
			return new Clk().exe(cmdbody);
		case "rol":
			return new Rol().exe(cmdbody);
		case "cmd":
			return new Cmd().exe(cmdbody);
		case "slp":
			return new Slp().exe(cmdbody);
		case "cps":
			return new Cps().exe(cmdbody);
		case "dlf":
			return new Dlf().exe(cmdbody);
		default:
			break;
		}
		return null;
	}

}
