package org.demo.mqtt.callback;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * MqttRecieveCallback
 *
 * @Author: taomee
 * @Date: 2020/4/8 0008 11:34
 * @Description:
 */
@Slf4j
public class MqttRecieveCallback implements MqttCallback {

    @Override
    public void connectionLost(Throwable throwable) {
        log.error("发生异常：{}", throwable);
    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        System.out.println("Client 接收消息主题 : " + topic);
        System.out.println("Client 接收消息Qos : " + mqttMessage.getQos());
        System.out.println("Client 接收消息内容 : " + new String(mqttMessage.getPayload()));
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
