package qst.server.operator;

import java.awt.event.KeyEvent;
import java.util.HashMap;

public class VisualKeyMap {

	 private static HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
	 private static final VisualKeyMap VISUAL_KEY_MAP = new VisualKeyMap();
	 
	 private VisualKeyMap() {// 在私有的构造函数中对hashVisualKeyMap赋值，完成映射对象的赋值，该构造函数只在
	        // private static final VisualKeyMap VISUAL_KEY_MAP=new  VisualKeyMap()静态变量中new出一次
	        // 无论调用多少次VisualKeyMap.getInstance()方法只返回VISUAL_KEY_MAP对象，而不会再调用构造函数new出对象
	        // 若要取客户端发送的"vk_space"对应的java.awt.event.KeyEvent.VK_SPACE值，则可通过VisualKeyMap.getVisualKey("vk_space")实现

	        hashMap.put("VK_0", KeyEvent.VK_0);//大写的Key，以方便录入，客户端发送大小写不区分
	        
	        hashMap.put("VK_F1", KeyEvent.VK_F1);
	        hashMap.put("VK_F2", KeyEvent.VK_F2);
	        hashMap.put("VK_F3", KeyEvent.VK_F3);
	        hashMap.put("VK_F4", KeyEvent.VK_F4);
	        hashMap.put("VK_F5", KeyEvent.VK_F5);
	        
	        hashMap.put("VK_ALT", KeyEvent.VK_ALT);
	        hashMap.put("VK_SHIFT", KeyEvent.VK_SHIFT);
	        hashMap.put("VK_ESCAPE", KeyEvent.VK_ESCAPE);
	        hashMap.put("VK_CONTROL", KeyEvent.VK_CONTROL);
	        hashMap.put("VK_TAB", KeyEvent.VK_TAB);
	        hashMap.put("VK_WINDOWS", KeyEvent.VK_WINDOWS);
	        hashMap.put("VK_SPACE", KeyEvent.VK_SPACE);
	        hashMap.put("VK_ENTER", KeyEvent.VK_ENTER);
	        hashMap.put("VK_BACK_SPACE", KeyEvent.VK_BACK_SPACE);
	        
	        hashMap.put("VK_DOWN", KeyEvent.VK_DOWN);
	        hashMap.put("VK_LEFT", KeyEvent.VK_LEFT);
	        hashMap.put("VK_RIGHT", KeyEvent.VK_RIGHT);
	        hashMap.put("VK_UP", KeyEvent.VK_UP);
	        
	        hashMap.put("VK_PAGE_DOWN", KeyEvent.VK_PAGE_DOWN);
	        hashMap.put("VK_PAGE_UP", KeyEvent.VK_PAGE_UP);
	        
	        hashMap.put("VK_B", KeyEvent.VK_B);
	        hashMap.put("VK_M", KeyEvent.VK_M);
	        hashMap.put("VK_V", KeyEvent.VK_V);

	    }


	 public static int getVisualKey(String key) {
	        //调用时只需VisualKeyMap.getVisualKey(String key)即可
	        return hashMap.get(key.toUpperCase());//把key转为大写
	    }


}
