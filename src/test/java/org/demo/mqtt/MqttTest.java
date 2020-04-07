package org.demo.mqtt;

import org.demo.mqtt.utils.BytesUtil;
import org.demo.mqtt.utils.ParseUtil;
import org.junit.Test;

import java.nio.ByteBuffer;

public class MqttTest {

    @Test
    public void  volumeTest(){
        for(int i=1;i<10000000; i++){
            byte[] temp = ParseUtil.length2Bytes(i);
            ByteBuffer buffer = ByteBuffer.allocate(temp.length);
            buffer.put(temp);
            buffer.flip();
            int length = ParseUtil.bytes2Length(buffer);
            System.out.println("length:" + length + ", i:" + i);
            if(length != i){
                System.out.println("解析有误！");
                System.exit(0);
            }
        }
    }


    @Test
    public void bytes2LengthTest(){
        ByteBuffer bf = ByteBuffer.allocate(4);
//        bf.put(((byte) 0xff));
//        bf.put(((byte) 0xff));
//        bf.put(((byte) 0xff));
//        bf.put(((byte) 0x7f));

        bf.put(((byte) 0x81));
        bf.put(((byte) 0x1));

        bf.flip();
        int len = ParseUtil.bytes2Length(bf);
        System.out.println("消息长度是：" + len);
    }


    @Test
    public void length2BytesTest(){
        int length = 65536;
        byte[] lengthBytes = ParseUtil.length2Bytes(length);
        System.out.println(length + "转成字节数组的结果为：" + BytesUtil.toString(lengthBytes));
    }
}
