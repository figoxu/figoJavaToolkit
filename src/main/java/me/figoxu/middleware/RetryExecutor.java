package me.figoxu.middleware;

import org.apache.log4j.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-5-13
 * Time: 下午3:25
 * To change this template use File | Settings | File Templates.
 */
public abstract class RetryExecutor {
    private static Logger logger = Logger.getLogger(RetryExecutor.class);
    private Object result;

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    /**
     * @should test
     */
    public abstract void doing() throws Exception;

    public boolean execute(int retryTimes){
        boolean statusFlag = false;
        int tryTimes = 0;
        while (!statusFlag && tryTimes<retryTimes){
            try{
                doing();
                statusFlag = true;
            }catch (Exception ex){
                logger.error("",ex);
                statusFlag = false;
            }
            tryTimes++;
        }
        logger.info(this.getClass().getName()+" execute @times:"+retryTimes+"  @realExecuteTimes:"+tryTimes+" @status:"+statusFlag);
        return statusFlag;
    }



}
