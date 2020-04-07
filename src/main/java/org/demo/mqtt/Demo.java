package org.demo.mqtt;

import org.demo.mqtt.utils.BytesUtil;
import org.demo.mqtt.utils.ParseUtil;

public class Demo {
    public static void main(String[] args) {
        byte[] lengthBytes = ParseUtil.length2Bytes(129);
        System.out.println("128 转成字节数组的结果为：" + BytesUtil.toString(lengthBytes));
    }
}
