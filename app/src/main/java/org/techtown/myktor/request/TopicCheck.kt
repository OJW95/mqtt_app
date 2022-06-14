package org.techtown.myktor.request

import android.content.Intent
// 센서 추가 가능! 추가로 if문만 적어주면 됨!
fun topicCheck(intent: Intent): ArrayList<String> {
    val topicList = ArrayList<String>()

    val temperature = intent.getBooleanExtra("temper", false)
    val humidity = intent.getBooleanExtra("humid", false)

    if (temperature) {
        topicList.add("temper")
    }
    if (humidity) {
        topicList.add("humid")
    }
    return topicList
}