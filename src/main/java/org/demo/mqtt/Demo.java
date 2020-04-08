package org.demo.mqtt;

import org.demo.mqtt.client.MqttClientDemo;
import org.demo.mqtt.utils.IpAddrUtil;

public class Demo {
    public static void main(String[] args) throws Exception{
        String localIp = IpAddrUtil.getIpAddress().getHostAddress();
        System.setProperty("local-ip", localIp);

        MqttClientDemo client = new MqttClientDemo();
        client.publishMessage("demo/msg", "Hello from mqtt-demo!", 1);
    }
}
