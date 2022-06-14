package org.techtown.myktor.request

import android.content.Intent
import android.hardware.Sensor
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.MainScope
import org.eclipse.paho.client.mqttv3.*
import org.techtown.myktor.R
import org.techtown.myktor.data.ChatMessage
import org.techtown.myktor.mqtt.ControlActivity
import org.techtown.myktor.mqtt.MqttClient

val CatList = arrayListOf<ChatMessage>()


class RequestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val reqButton: Button = findViewById(R.id.reqbutton)
        val sendMsg : EditText = findViewById(R.id.sendmsg)

        val topics = topicCheck(intent)
        val mqtt = MqttClient()

        val recycler: RecyclerView = findViewById(R.id.list)

        recycler.layoutManager = LinearLayoutManager(this)

        mqtt.connect(this@RequestActivity).actionCallback = object : IMqttActionListener {
            override fun onSuccess(asyncActionToken: IMqttToken?) {
                Toast.makeText(this@RequestActivity, "connect ok", Toast.LENGTH_SHORT).show()
                mqtt.subscribe("server")
                mqtt.subscribe("image")
                Toast.makeText(this@RequestActivity, "sub on", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                TODO("Not yet implemented")
            }
        }

        reqButton.setOnClickListener {
            val msg = sendMsg.text
            if (msg.isNotEmpty()) {
                CatList.add(ChatMessage("user", msg.toString()))
                mqtt.publish("user", msg.toString())
            }
        }

        mqtt.mqttClient.setCallback(object : MqttCallback {
            override fun connectionLost(cause: Throwable?) {
                TODO("Not yet implemented")
            }

            override fun messageArrived(topic: String?, message: MqttMessage?) {
                if (message != null) {
                    CatList.add(ChatMessage(topic.toString(), message.payload.decodeToString()))
                    recycler.adapter = Adapter(CatList)
                    recycler.scrollToPosition(CatList.size - 1)
                }
            }

            override fun deliveryComplete(token: IMqttDeliveryToken?) {
                recycler.adapter = Adapter(CatList)
                recycler.scrollToPosition(CatList.size - 1)
            }
        })
    }
}