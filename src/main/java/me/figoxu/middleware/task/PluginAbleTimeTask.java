package me.figoxu.middleware.task;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 * User: figo
 * Date: 15-5-8
 * Time: 上午8:52
 * To change this template use File | Settings | File Templates.
 */
public class PluginAbleTimeTask extends TimerTask {
    private List<Runnable> runnableList = new ArrayList<Runnable>();

    public void plugin(Runnable runnable){
        runnableList.add(runnable);
    }

    @Override
    public void run() {
        for(Runnable runnable:runnableList){
            new Thread(runnable).start();
        }
    }
}
