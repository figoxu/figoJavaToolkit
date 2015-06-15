package org.xxiongdi.figo.middleware;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Locale;

/**
 * Created by IntelliJ IDEA.
 * User: XUJH
 * Date: 12-2-26
 * Time: 下午6:39
 *
 */
public class I18NUtil {
    public static String getI18nFileContent(Class clazz){
        StringBuffer i18nContent = new StringBuffer("");
        Field[] fields = clazz.getDeclaredFields();
        for(Field field:fields){
            if( Modifier.isStatic(field.getModifiers())  ){
                continue;
            }
            i18nContent.append(clazz.getName() + "." + field.getName() + "=" + field.getName()+"\n");
        }
        return i18nContent.toString();
    }


//    public static String getMessage(HttpServletRequest request,String key){
//        MessageResources messageResources = (MessageResources) request.getAttribute(Globals.MESSAGES_KEY);
//        Locale locale = getLocale(request);
//        String message = messageResources.getMessage(locale,key);
//        return message;
//    }
//
//    public static Locale getLocale(HttpServletRequest request) {
//        return (Locale) request.getSession().getAttribute(Globals.LOCALE_KEY);
//    }

    public static void main(String[] args) {
//        String i18n =  I18NUtil.getI18nFileContent(SiteAdvInfo.class) ;
//        System.out.println(i18n);
    }
}
