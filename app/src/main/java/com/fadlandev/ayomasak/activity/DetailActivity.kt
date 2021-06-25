package com.fadlandev.ayomasak.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fadlandev.ayomasak.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        tv_title.text = intent.getStringExtra("title")
        tv_desc.text = intent.getStringExtra("desc")
        tv_ingredient.text = intent.getStringExtra("ingredient")

    }
}
