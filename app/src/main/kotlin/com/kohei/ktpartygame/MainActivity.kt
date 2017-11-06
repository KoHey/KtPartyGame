package com.kohei.ktpartygame

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonSingle.setOnClickListener {
            onClick()
        }

    }
    fun onClick(){
        val intent = Intent(applicationContext, PlayGameSingle::class.java)
        startActivity(intent)

    }

}
