package com.core.app.service;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.io.IOException;
import java.util.Date;

/**
 * Created by alvin on 16-6-24.
 */
public class MqttSendDemo {
    public static void main(String[] args) throws IOException {
        /**
         * 设置当前用户私有的MQTT的接入点。例如此处示意使用XXX，实际使用请替换用户自己的接入点。接入点的获取方法是，在控制台申请MQTT实例，每个实例都会分配一个接入点域名。
         */
        final String broker ="tcp://mqtt-cn-45904q9f40p.mqtt.aliyuncs.com:1883";
        /**
         * 设置阿里云的AccessKey，用于鉴权
         */
        final String acessKey ="LTAIsQ6fl0ZLtl2J";
        /**
         * 设置阿里云的SecretKey，用于鉴权
         */
        final String secretKey ="Io4VkVd6jIsBiaS5oe0RLkGVmn9amZ";
        /**
         * 发消息使用的一级Topic，需要先在MQ控制台里申请
         */
        final String topic ="tracks";

        /**
         * MQTT的ClientID，一般由两部分组成，GroupID@@@DeviceID
         * 其中GroupID在MQ控制台里申请
         * DeviceID由应用方设置，可能是设备编号等，需要唯一，否则服务端拒绝重复的ClientID连接
         */
        final String producerClientId ="GID_track@@@DEVICE_002";
        final String consumerClientId ="GID_track@@@DEVICE_002";
        String sign;
        MemoryPersistence persistence = new MemoryPersistence();
        try {
            final MqttClient sampleClient = new MqttClient(broker, producerClientId, persistence);
            final MqttConnectOptions connOpts = new MqttConnectOptions();
            System.out.println("Connecting to broker: " + broker);
            /**
             * 计算签名，将签名作为MQTT的password。
             * 签名的计算方法，参考工具类MacSignature，第一个参数是ClientID的前半部分，即GroupID
             * 第二个参数阿里云的SecretKey
             */
            sign = MacSignature.macSignature(producerClientId.split("@@@")[0], secretKey);
            connOpts.setUserName(acessKey);
            connOpts.setServerURIs(new String[] { broker });
            connOpts.setPassword(sign.toCharArray());
            connOpts.setCleanSession(false);
            connOpts.setKeepAliveInterval(100);
            sampleClient.setCallback(new MqttCallback() {
                public void connectionLost(Throwable throwable) {
                    System.out.println("mqtt connection lost");
                    throwable.printStackTrace();
                    while(!sampleClient.isConnected()){
                        try {
                            sampleClient.connect(connOpts);
                        } catch (MqttException e) {
                            e.printStackTrace();
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                    System.out.println("messageArrived:" + topic + "------" + new String(mqttMessage.getPayload()));
                }
                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                    System.out.println("deliveryComplete:" + iMqttDeliveryToken.getMessageId());
                }
            });
            sampleClient.connect(connOpts);
            for (int i = 0; i < 10; i++) {
                try {
                    String scontent = new Date()+"MQTT Test body" + i;
                    final MqttMessage message = new MqttMessage(scontent.getBytes());
                    message.setQos(0);
                    System.out.println(i+" pushed at "+new Date()+" "+ scontent);
                    /**
                     *消息发送到某个主题Topic，所有订阅这个Topic的设备都能收到这个消息。
                     * 遵循MQTT的发布订阅规范，Topic也可以是多级Topic。此处设置了发送到二级topic
                     */
                    sampleClient.publish(topic+"/notice/", message);
                    /**
                     * 如果发送P2P消息，二级Topic必须是“p2p”,三级topic是目标的ClientID
                     * 此处设置的三级topic需要是接收方的ClientID
                     */
                    String p2pTopic =topic+"/p2p/"+consumerClientId;
                    sampleClient.publish(p2pTopic,message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception me) {
            me.printStackTrace();
        }
    }
}
