package me.figoxu.middleware.dataStructure.map;

import org.xxiongdi.zfct.core.task.PluginAbleTimeTask;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: figo
 * Date: 15-5-8
 * Time: 上午8:47
 * To change this template use File | Settings | File Templates.
 */
public class AutoTimeOutClearMap<K,V extends TimeoutValue> extends ConcurrentHashMap<K,V>{
    private long liveTime = 1000*60*5;

    /**
     *
     * @param pluginAbleTimeTask 定时器，确保会被定时器调度，定时清理
     */
    public AutoTimeOutClearMap(PluginAbleTimeTask pluginAbleTimeTask){
        pluginAbleTimeTask.plugin(new AutoTimeOutClearRunnable(this));
        this.liveTime = 1000*60*5;
    }
    public AutoTimeOutClearMap(PluginAbleTimeTask pluginAbleTimeTask,long liveTime){
        pluginAbleTimeTask.plugin(new AutoTimeOutClearRunnable(this));
        this.liveTime = liveTime;
    }

    @Override
    public V put(K key, V value) {
        if(value==null){
            return value;
        }
        if(value.getLastVisitTime()==null||value.getLastVisitTime()<=0){
            value.setLastVisitTime(System.currentTimeMillis());
        }
        return super.put(key, value);
    }

    public void refreshTime2Live(K key){
        V v = this.get(key);
        put(key,v);
    }

    public void clearTimeOut(){
        Set<K> kSet = this.keySet();
        Iterator<K> iterator = kSet.iterator();
        long currentMillis = System.currentTimeMillis();
        List<K> key4removeList = new ArrayList<K>();
        while (iterator.hasNext()){
            K key = iterator.next();
            V v = this.get(key);
            long livingTime= v.getLastVisitTime()- currentMillis;
            if(livingTime-liveTime>0){
                key4removeList.add(key);
            }
        }
        for(K k:key4removeList){
            try{
              this.remove(k);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

}
