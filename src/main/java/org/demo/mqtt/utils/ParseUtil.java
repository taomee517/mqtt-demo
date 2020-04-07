package org.demo.mqtt.utils;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ParseUtil {


    /**
     * 获取MQTT消息的剩余长度
     * @param buffer MQTT消息
     * @return 消息剩余长度
     */
    public static int bytes2Length(ByteBuffer buffer){
        int length = 0;
        int temp = 0;
        byte digit = 0;
        int offset = 0;
        do{
            digit = buffer.get();

            //获取当前字节的长度值
            temp = digit & 0x7f;

            //如果后续还有长度字节，就将当前长度值左移7*offset位
            //1 ==> 1000 0000
            temp <<= (7 * offset);

            length |= temp;
            offset ++;
        }while ((digit & 0x80) != 0);
        return length;
    }



    /**
     * int类型长度解析为1-4个字节
     * @param length
     */
    public static byte[] length2Bytes(int length) {
        int val = length;
        ByteBuffer bf = ByteBuffer.allocate(4);
        do {
            int digit = val & 0xff;
            val >>= 7;
            if (val > 0) {
                digit = digit | 0x80;
            }
            bf.put(((byte) digit));
        } while (val > 0);
        bf.flip();
        byte[] bytes = new byte[bf.limit()];
        bf.get(bytes);
        return bytes;
    }
}
