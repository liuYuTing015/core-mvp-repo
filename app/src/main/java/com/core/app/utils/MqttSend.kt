package com.core.app.utils

import com.core.app.service.MacSignature
import org.eclipse.paho.client.mqttv3.*
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence
import java.io.IOException
import java.util.*

/**
 * Created by Ting on 17/6/19.
 */

class MqttSend {
    /**
     * 设置当前用户私有的MQTT的接入点。例如此处示意使用XXX，实际使用请替换用户自己的接入点。接入点的获取方法是，在控制台申请MQTT实例，每个实例都会分配一个接入点域名。
     */
    val broker = "tcp://mqtt-cn-45904q9f40p.mqtt.aliyuncs.com:1883"
    /**
     * 设置阿里云的AccessKey，用于鉴权
     */
    val accessKey = "LTAIsQ6fl0ZLtl2J"
    /**
     * 设置阿里云的SecretKey，用于鉴权
     */
    val secretKey = "Io4VkVd6jIsBiaS5oe0RLkGVmn9amZ"
    /**
     * 发消息使用的一级Topic，需要先在MQ控制台里申请
     */
    val topic = "tracks"
    /**
     * MQTT的ClientID，一般由两部分组成，GroupID@@@DeviceID
     * 其中GroupID在MQ控制台里申请
     * DeviceID由应用方设置，可能是设备编号等，需要唯一，否则服务端拒绝重复的ClientID连接
     */
    val consumerClientId = "GID_track@@@DEVICE_002"
    val producerClientId = "GID_track@@@DEVICE_002"
    val persistence = MemoryPersistence()
    fun sendMessage() {
        try {
            val sign: String
            val sampleClient = MqttClient(broker, producerClientId, persistence)
            val connOpts = MqttConnectOptions()
            println("Connecting to broker: " + broker)
            /**
             * 计算签名，将签名作为MQTT的password。
             * 签名的计算方法，参考工具类MacSignature，第一个参数是ClientID的前半部分，即GroupID
             * 第二个参数阿里云的SecretKey
             */
            sign = MacSignature.macSignature(producerClientId.split(("@@@").toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()[0], secretKey)
            connOpts.userName = accessKey
            connOpts.serverURIs = arrayOf(elements = broker)
            connOpts.password = sign.toCharArray()
            connOpts.isCleanSession = false
            connOpts.keepAliveInterval = 100

            sampleClient.setCallback(object : MqttCallback {
                override fun connectionLost(throwable: Throwable) {
                    println("mqtt connection lost")
                    throwable.printStackTrace()
                    while (!sampleClient.isConnected) {
                        try {
                            sampleClient.connect(connOpts)
                        } catch (e: MqttException) {
                            e.printStackTrace()
                        }
                        try {
                            Thread.sleep(1000)
                        } catch (e: InterruptedException) {
                            e.printStackTrace()
                        }
                    }
                }

                @Throws(Exception::class)
                override fun messageArrived(topic: String, mqttMessage: MqttMessage) {
                    println("messageArrived:" + topic + "------" + String(mqttMessage.payload))
                }

                override fun deliveryComplete(iMqttDeliveryToken: IMqttDeliveryToken) {
                    System.out.println("deliveryComplete:" + iMqttDeliveryToken.messageId)
                }
            })
            sampleClient.connect(connOpts)
            for (i in 0..9) {
                try {
                    val scontent: String = "MQTT Test body" + i
                    val message = MqttMessage(scontent.toByteArray())
                    message.qos = 0
                    // println(i + " pushed at "  + " " + scontent)
                    /**
                     *消息发送到某个主题Topic，所有订阅这个Topic的设备都能收到这个消息。
                     * 遵循MQTT的发布订阅规范，Topic也可以是多级Topic。此处设置了发送到二级topic
                     */
                    sampleClient.publish(topic, message)
                    /**
                     * 如果发送P2P消息，二级Topic必须是“p2p”,三级topic是目标的ClientID
                     * 此处设置的三级topic需要是接收方的ClientID
                     */
                    //                    val p2pTopic = topic + "/p2p/" + consumerClientId
//                    sampleClient.publish(p2pTopic, message)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        } catch (me: Exception) {
            me.printStackTrace()
        }
    }
}