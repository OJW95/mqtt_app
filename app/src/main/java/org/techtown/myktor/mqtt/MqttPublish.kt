package org.techtown.myktor.mqtt

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.*


class MqttClient {

    lateinit var mqttClient: MqttAndroidClient

    fun connect(context: Context): IMqttToken {
        mqttClient =
            MqttAndroidClient(context, "tcp://54.157.12.31:1883", "test")
        val options = MqttConnectOptions()

        return mqttClient.connect(options)
    }

    fun publish(topic: String, value: String) {
        val encodedPayload = value.toByteArray(charset("UTF-8"))
        val message = MqttMessage(encodedPayload)
        message.qos = 2
        message.isRetained = false
        mqttClient.publish(topic, message)
    }

    fun subscribe(topic: String) {
        mqttClient.subscribe(topic, 0)
    }

    fun subscribe(topics: ArrayList<String>) {
        for (topic in topics) {
            mqttClient.subscribe(topic, 0)
        }
    }

    fun disconnect() {
        mqttClient.disconnect(null, object : IMqttActionListener {
            override fun onSuccess(asyncActionToken: IMqttToken?) {
                Log.d(TAG, "onSuccess: disconnect")
            }

            override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                Log.d(TAG, "onFailure: not disconnect")
            }
        })
    }
}