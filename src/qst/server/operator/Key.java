package qst.server.operator;

import java.awt.Robot;
import java.util.ArrayList;

public class Key extends BaseOperator {
    private  Robot robot;
    @Override
    public ArrayList<String> exe(String cmdBody) throws Exception {
        // TODO Auto-generated method stub
        // cmdBody，若有逗号，表示组合键，否则为单键
        // 组合键示例：VK_ALT+VK_TAB,VK_TAB+VK_ALT 表示先按下alt键，再按下tab键，再释放tab键，再释放alt键
        // 逗号前面的+表示按下键的顺序，逗号后面的+表示释放键的顺序
        ArrayList<String> ackMsg = new ArrayList<String>();
        robot=new Robot();
        int splitIdx = cmdBody.indexOf(",");
        //先判断键类型有无带","的，若有，则按下键的顺序和释放键的顺序由逗号前的“+”和逗号后的“+”来设置顺序
        if (splitIdx < 1) {
            int splitIdx2 = cmdBody.indexOf("+");//靠“+”来分割组合键
            if(splitIdx2<1){
                singleKeyPress(cmdBody);//没有“+”号表示单按键
            }else{
                simpleComboKeyPress(cmdBody);//组合键
            }
        }else{
            String keyPressStr=cmdBody.substring(0, splitIdx);//取0-（spisplitIdx-1）索引构成的子字符串
            String keyReleaseStr=cmdBody.substring(splitIdx+1);//取splitIdx+1到结尾的子字符串
            comboKeyPress(keyPressStr,keyReleaseStr);
        }
        ackMsg.add("ok");
        ackMsg.add("key:"+cmdBody);
        return ackMsg;
        
    }
    private void simpleComboKeyPress(String keyPressStr){
        String[] keyPressArray = keyPressStr.split("\\+");
        //split里的字符符合正则表达式规则， "+”是正则表达式的关键词，不能直接用，需要转义，而\本身是转义，所以需要\\来表示
        for(int i=0;i<keyPressArray.length;i++){//按“+”的顺序按下按键
            int keycode = VisualKeyMap.getVisualKey(keyPressArray[i]);
            robot.keyPress(keycode);

        }
        for(int i=keyPressArray.length-1;i>=0;i--){//反序释放按键
            int keycode = VisualKeyMap.getVisualKey(keyPressArray[i]);
            robot.keyRelease(keycode);  
        }
    }

    private  void comboKeyPress(String keyPressStr, String keyReleaseStr) {
        // TODO Auto-generated method stub
        String[] keyPressArray = keyPressStr.split("\\+");
        String[] keyReleaseArray = keyReleaseStr.split("\\+");
        for(int i=0;i<keyPressArray.length;i++){
            int keycode = VisualKeyMap.getVisualKey(keyPressArray[i]);
            robot.keyPress(keycode);

        }
        for(int i=0;i<keyReleaseArray.length;i++){
            int keycode = VisualKeyMap.getVisualKey(keyReleaseArray[i]);
            robot.keyRelease(keycode);
        }
    }

    private void singleKeyPress(String cmdBody) {
        // TODO Auto-generated method stub
        int keycode = VisualKeyMap.getVisualKey(cmdBody);
        robot.keyPress(keycode);
        robot.keyRelease(keycode);

    }

}
