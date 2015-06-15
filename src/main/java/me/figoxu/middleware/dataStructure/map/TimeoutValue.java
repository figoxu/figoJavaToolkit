package me.figoxu.middleware.dataStructure.map;

/**
 * Created with IntelliJ IDEA.
 * User: figo
 * Date: 15-5-8
 * Time: 上午8:48
 * To change this template use File | Settings | File Templates.
 */
public class TimeoutValue {
    private Long lastVisitTime;

    public Long getLastVisitTime() {
        return lastVisitTime;
    }

    public void setLastVisitTime(Long lastVisitTime) {
        this.lastVisitTime = lastVisitTime;
    }
}
