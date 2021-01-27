package com.easy.utils;

/**
 * @author zhouym
 * @version [1.0, 2018/3/1]
 */
public class JavaDataTypeUtil {
    public JavaDataTypeUtil() {
    }

    public static boolean isPrimitiveWrapper(String type) {
        byte var2 = -1;
        switch(type.hashCode()) {
            case -2056817302:
                if(type.equals("java.lang.Integer")) {
                    var2 = 2;
                }
                break;
            case -527879800:
                if(type.equals("java.lang.Float")) {
                    var2 = 3;
                }
                break;
            case -515992664:
                if(type.equals("java.lang.Short")) {
                    var2 = 1;
                }
                break;
            case 344809556:
                if(type.equals("java.lang.Boolean")) {
                    var2 = 7;
                }
                break;
            case 398507100:
                if(type.equals("java.lang.Byte")) {
                    var2 = 0;
                }
                break;
            case 398795216:
                if(type.equals("java.lang.Long")) {
                    var2 = 4;
                }
                break;
            case 761287205:
                if(type.equals("java.lang.Double")) {
                    var2 = 5;
                }
                break;
            case 1195259493:
                if(type.equals("java.lang.String")) {
                    var2 = 6;
                }
        }

        switch(var2) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                return true;
            default:
                return false;
        }
    }

    public static boolean isPrimitive(String type) {
        byte var2 = -1;
        switch(type.hashCode()) {
            case -1325958191:
                if(type.equals("double")) {
                var2 = 6;
            }
            break;
            case 104431:
                if(type.equals("int")) {
                var2 = 3;
            }
            break;
            case 3039496:
                if(type.equals("byte")) {
                var2 = 0;
            }
            break;
            case 3052374:
                if(type.equals("char")) {
                var2 = 1;
            }
            break;
            case 3327612:
                if(type.equals("long")) {
                var2 = 5;
            }
            break;
            case 64711720:
                if(type.equals("boolean")) {
                var2 = 7;
            }
            break;
            case 97526364:
                if(type.equals("float")) {
                var2 = 4;
            }
            break;
            case 109413500:
                if(type.equals("short")) {
                var2 = 2;
            }
        }

        switch(var2) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                return true;
            default:
                return false;
        }
    }
}
