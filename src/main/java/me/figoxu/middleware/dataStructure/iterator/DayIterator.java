package me.figoxu.middleware.dataStructure.iterator;


import me.figoxu.middleware.DateHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: figo
 * Date: 15-5-9
 * Time: 上午9:39
 * To change this template use File | Settings | File Templates.
 */
public class DayIterator implements Iterable<Date>{
   List<Date> dateList = new ArrayList<Date>();

    public DayIterator(Date startDate,Date endDate){
        Date sTime = DateHelper.getGivenDateStartTime(startDate);
        Date eTime = DateHelper.getGivenDateStartTime(endDate);
        Date nextTime = null;
        boolean hasNext = true;
        nextTime = sTime;
        while (hasNext){
            dateList.add(nextTime);
            nextTime = DateHelper.getTomorrow(nextTime);
            if(nextTime.getTime()-eTime.getTime()>0){
                hasNext=false;
            }
        }
    }

    public List<Date> getDateList() {
        return dateList;
    }

    @Override
    public Iterator<Date> iterator() {
        return dateList.iterator();
    }
}
