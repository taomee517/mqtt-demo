package org.demo.mqtt.utils;

public class BytesUtil {

    public static String toString(byte[] bytes){
        StringBuilder sb = new StringBuilder("[");
        int lastIndex = bytes.length-1;
        for (int i=0;i<lastIndex;i++) {
            byte b = bytes[i];
            sb.append(b);
            sb.append(",");
        }
        sb.append(bytes[lastIndex]);
        sb.append("]");
        return sb.toString();
    }
}
