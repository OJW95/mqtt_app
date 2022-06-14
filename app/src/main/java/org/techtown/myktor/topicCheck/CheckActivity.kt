package org.techtown.myktor.topicCheck

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import org.techtown.myktor.R
import org.techtown.myktor.request.RequestActivity

// 센서 추가 시 센서 체크박스 추가로 넣어주면 됨!
class CheckActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_entity)

        //sensor check
        val temper: CheckBox = findViewById(R.id.temper)
        val humid : CheckBox = findViewById(R.id.humid)

        val getButton: Button = findViewById(R.id.getButton)
        var intent: Intent? = null

        getButton.setOnClickListener {
            intent = Intent(this, RequestActivity::class.java).apply {
                putExtra("temper", temper.isChecked)
                putExtra("humid", humid.isChecked)
            }
            startActivity(intent)
        }
    }
}