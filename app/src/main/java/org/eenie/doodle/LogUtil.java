package org.eenie.doodle;


import com.orhanobut.logger.Logger;

/**
 * Created by Eenie on 2016/10/12 at 14:30
 * Des:
 */

public class LogUtil {

    private static boolean DEBUG = true;

    public static void json(String json) {
        if (DEBUG) {
            Logger.json(json);
        }
    }

//    public static void json(Object obj) {
//        if (DEBUG) {
//            Logger.json(JSON.toJSONString(obj));
//        }
//    }

    public static void e(String msg) {
        if (DEBUG) {
            Logger.e(msg);
        }
    }
    public static void e(String msg , Object... a) {
        if (DEBUG) {
            Logger.e(msg, a);
        }
    }

}
