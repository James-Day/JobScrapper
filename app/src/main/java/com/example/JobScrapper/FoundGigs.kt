package com.example.JobScrapper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class FoundGigs : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_found_gigs)
       val backToMain = findViewById<Button>(R.id.backToMain)
        backToMain.setOnClickListener{
            val mainScreenIntent =  Intent(this,MainActivity::class.java)
            startActivity(mainScreenIntent)
        }
        val list = intent.extras?.get("JobScrapperList") as ArrayList<String>
      for(i in list.indices){
          setNewText(list[i])
      }
    }


    private fun setNewText(input: String) {

        val gigFoundLayout = findViewById<LinearLayout>(R.id.gigFoundLayout)
        val newGig = TextView(this)
        newGig.text = input
        newGig.textSize = 13F
        gigFoundLayout.addView(newGig)
    }
}