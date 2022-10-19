package com.studio.noodoeassignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.studio.noodoeassignment.data.LogInResponse

class MainActivity : AppCompatActivity() {

    lateinit var userInfo: LogInResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
    }
}