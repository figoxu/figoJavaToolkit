package me.figoxu.middleware.message;


import me.figoxu.middleware.app.packet.SysException;

/**
 *
 * User: figo.xu
 * Date: 13-5-21
 * Time: 上午10:18
 *
 */
public class SysExceptionHelper {
    public static SysException instance(int code) {
        String exceptionMessage = getExceptionMessage(code);
        SysException sysException = new SysException(code, exceptionMessage);
        return sysException;
    }


    /**
     * 获取异常信息
     *
     * @param code
     * @return
     */
    public static String getExceptionMessage(Integer code) {
        String key = "Exception.API." + code;
        String message = MessageHelper.getProperty(key, "");
        return message;
    }
}
