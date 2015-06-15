package me.figoxu.middleware.dataStructure.map;

/**
 * Created with IntelliJ IDEA.
 * User: figo
 * Date: 15-5-8
 * Time: 上午8:50
 * To change this template use File | Settings | File Templates.
 */
public class AutoTimeOutClearRunnable implements Runnable{
    AutoTimeOutClearMap autoTimeOutClearMap;

    public AutoTimeOutClearRunnable(AutoTimeOutClearMap autoTimeOutClearMap){
         this.autoTimeOutClearMap = autoTimeOutClearMap;
    }

    @Override
    public void run() {
         this.autoTimeOutClearMap.clearTimeOut();
    }
}
