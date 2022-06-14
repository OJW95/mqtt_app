package org.techtown.myktor.request

import android.hardware.Sensor
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement

class Request {
    private val BASE_URL = "http://192.168.0.5:8080/sensor"
    private val client = HttpClient() {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                ignoreUnknownKeys = true
                isLenient = true
            })
        }

        defaultRequest {
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }
    }
    suspend fun getHtml(topics: List<String>): List<Sensor> {
        val dbList = mutableListOf<Sensor>()
        for (topic in topics) {
            val response = client.get("$BASE_URL/$topic")
            val result = Json.decodeFromJsonElement<MutableList<Sensor>>(response.body())
            //println(response.javaClass.name)
            //println(result)
            dbList.add(result[0])
            //println(dbList)
        }
        return dbList
    }
}