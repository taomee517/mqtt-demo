package org.demo.mqtt.client;

import lombok.extern.slf4j.Slf4j;
import org.demo.mqtt.callback.MqttRecieveCallback;
import org.demo.mqtt.utils.SecureSocketFactoryBuilder;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * MqttClient
 *
 * @Author: taomee
 * @Date: 2020/4/8 0008 11:27
 * @Description:
 */
@Slf4j
public class MqttClientDemo {
    public static MqttClient mqttClient = null;
    private static MemoryPersistence memoryPersistence = null;
    private static MqttConnectOptions mqttConnectOptions = null;

    private static String client_id = "123";
    private static String username = "testOne";
    private static String password = "6156ADE136B3BF385B595CF3A69BFAFF2D6AFE87FF3EE1B49224F51D0259B00B014BA7771064E9F46CBF67F27780AABCCC1C252142397FEE8316A91CB0C52176";
    private static String server_url = "ssl://127.0.0.1:8885";
    private static String protocol_version = "SSLv3";
    private static String cert_path = "F:\\open source\\mqtt-spy\\mqtt-broker.cer";

    static {
        try {
            init(client_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static  void init(String clientId) throws Exception {
        //初始化连接设置对象
        mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setUserName(username);
        mqttConnectOptions.setPassword(password.toCharArray());
        //设置SSL凭证
        mqttConnectOptions.setSocketFactory(SecureSocketFactoryBuilder.getSocketFactory(protocol_version, cert_path));
        //初始化MqttClient
        //true可以安全地使用内存持久性作为客户端断开连接时清除的所有状态
        mqttConnectOptions.setCleanSession(true);
        //设置连接超时
        mqttConnectOptions.setConnectionTimeout(30);
        //设置持久化方式
        memoryPersistence = new MemoryPersistence();

        if(null != memoryPersistence && null != clientId) {
            try {
                mqttClient = new MqttClient(server_url, clientId, memoryPersistence);
            } catch (MqttException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else {
            log.warn("须指定持久化方式和用户id");
        }

        //设置连接和回调
        if(null != mqttClient) {
            //创建回调函数对象
            MqttRecieveCallback mqttRecieveCallback = new MqttRecieveCallback();
            //客户端添加回调函数
            mqttClient.setCallback(mqttRecieveCallback);
            //创建连接
            try {
                log.info("创建连接");
                mqttClient.connect(mqttConnectOptions);
                log.info("连接创建成功：{}", mqttClient.isConnected());
            } catch (MqttException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else {
            log.info("mqttClient为空");
        }

    }

    //	关闭连接
    public void closeConnect() {
        //关闭存储方式
        if(null != memoryPersistence) {
            try {
                memoryPersistence.close();
            } catch (MqttPersistenceException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else {
            log.info("memoryPersistence is null");
        }

//		关闭连接
        if(null != mqttClient) {
            if(mqttClient.isConnected()) {
                try {
                    mqttClient.disconnect();
                    mqttClient.close();
                } catch (MqttException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }else {
                log.info("mqttClient is not connect");
            }
        }else {
            log.info("mqttClient is null");
        }
    }

    //	发布消息
    public void publishMessage(String pubTopic,String message,int qos){
        if(null != mqttClient) {
            log.info("发布消息: {}", mqttClient.isConnected());
            log.info("id: {}", mqttClient.getClientId());
            MqttMessage mqttMessage = new MqttMessage();
            mqttMessage.setQos(qos);
            mqttMessage.setPayload(message.getBytes());
            try {
                mqttClient.publish(pubTopic,mqttMessage);
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }else {
            reConnect();
        }

    }
    //	重新连接
    public void reConnect() {
        if(null != mqttClient) {
            if(!mqttClient.isConnected()) {
                if(null != mqttConnectOptions) {
                    try {
                        mqttClient.connect(mqttConnectOptions);
                    } catch (MqttException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }else {
                    log.info("mqttConnectOptions is null");
                }
            }else {
                log.info("mqttClient is null or connect");
            }
        }else {
            try {
                init(client_id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    //	订阅主题
    public void subTopic(String topic) {
        if(null != mqttClient&& mqttClient.isConnected()) {
            try {
                mqttClient.subscribe(topic, 1);
            } catch (MqttException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else {
            log.info("mqttClient is error");
        }
    }


    //	清空主题
    public void cleanTopic(String topic) {
        if(null != mqttClient&& !mqttClient.isConnected()) {
            try {
                mqttClient.unsubscribe(topic);
            } catch (MqttException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else {
            log.info("mqttClient is error");
        }
    }
}
