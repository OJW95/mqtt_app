package org.techtown.myktor.mqtt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlinx.coroutines.*
import okhttp3.Dispatcher
import org.eclipse.paho.client.mqttv3.IMqttToken
import org.eclipse.paho.client.mqttv3.MqttException
import org.techtown.myktor.R

class ControlActivity : AppCompatActivity() {
    private val mainScope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_control)

        val connBtn : Button = findViewById(R.id.conn_btn)
        val pubBtn : Button = findViewById(R.id.pub_btn)
        val topics = intent.getStringArrayListExtra("topics")
        val mqttPub = MqttClient()

        connBtn.setOnClickListener {
            mainScope.launch {
                kotlin.runCatching {
                    mqttPub.connect(this@ControlActivity)
                }.onSuccess {
                    Toast.makeText(this@ControlActivity, "Success connect", Toast.LENGTH_SHORT).show()
                }.onFailure {
                    Toast.makeText(this@ControlActivity, "Fail connect", Toast.LENGTH_SHORT).show()
                }
            }
        }
        pubBtn.setOnClickListener {
            mainScope.launch {
                kotlin.runCatching {
                    mqttPub.publish("LED", "off")
                }.onSuccess {
                    Toast.makeText(this@ControlActivity, "Topic: LED, Value: on", Toast.LENGTH_SHORT).show()
                    mqttPub.disconnect()
                }.onFailure {  }
            }
        }

    }
}