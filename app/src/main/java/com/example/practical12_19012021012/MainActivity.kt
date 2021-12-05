package com.example.practical12_19012021012

import android.os.Bundle
import android.os.StrictMode
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val btn = findViewById<Button>(R.id.btn_fetch)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val lvContacts = findViewById<ListView>(R.id.lv_users)
        val contactsAdapter = ContactsAdapter(this, UserData.arrOfUser)
        lvContacts.adapter = contactsAdapter

        btn.setOnClickListener {
            val BASE_URL = "https://api.json-generator.com/templates/JHOkVc1Jartq/data?access_token=pyabkeewzr4u8cbil1drcd8voa1t8gi6bjvxcznm"

            val client = OkHttpClient()
            val request = Request.Builder()
                    .url(BASE_URL)
                    .get()
                    .build()

            val response = client.newCall(request).execute()
            val jsonDataString = response.body()?.string()

            val json = JSONObject(jsonDataString)
            if (!response.isSuccessful) {
                val errors = json.getJSONArray("errors").join(", ")
                throw Exception(errors)
            }

            val jsonArray: JSONArray = json.getJSONArray("users")
            for (i in 0 until jsonArray.length()) {
                val userInfo = jsonArray.getJSONObject(i)
                val id = userInfo.getString("id").toLong()
                val name = userInfo.getJSONObject("name")
                val firstName = name.getString("firstname")
                val lastName = name.getString("lastname")
                val phone = userInfo.getString("phone")
                val address = userInfo.getString("address")

                val user = User(
                    id,
                    Name(
                        firstName,
                        lastName
                    ),
                    phone,
                    address
                )

                UserData.arrOfUser.add(user)

                contactsAdapter.notifyDataSetChanged()
            }
        }

    }
}