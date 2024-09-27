package com.example.praktika22_2

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class Facti : AppCompatActivity() {
    private lateinit var number: EditText
    private lateinit var interestingFact:TextView
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_facti)

        number=findViewById(R.id.number)
        interestingFact=findViewById(R.id.interestingFacts)
        button=findViewById(R.id.buttonGetCurs)

        button.setOnClickListener {
            getResult(number.text.toString())
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun getResult(city: String) {
        if (number.text.toString().isNotEmpty() && city.isNotEmpty()) {
            val type = "trivia"
            val url = "http://numbersapi.com/$number"
            val queue = Volley.newRequestQueue(this)
            val stringRequest = StringRequest(
                Request.Method.GET,
                url,
                { response ->
                    try {
                        val obj = JSONObject(response)
                        val main = obj.getJSONObject("main")
                        val fact = main.getString("temp")

                        interestingFact.text = "$interestingFact"


                        Log.d("MyLog", "Number: $number")
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                },
                { error ->
                    Log.d("MyLog", "Volley error: $error")
                }
            )
            queue.add(stringRequest)
        }
    }

}