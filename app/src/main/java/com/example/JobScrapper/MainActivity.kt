package com.example.JobScrapper

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.io.Serializable

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var jobScrapper = JobScrapper(this.applicationContext)

        val searchGigs = findViewById<Button>(R.id.searchGigs)
        val gigsLayout = findViewById<LinearLayout>(R.id.gigsLayout)
        val numDaysText = findViewById<TextView>(R.id.numberOfDays)
        val numSitesText = findViewById<TextView>(R.id.numberSites)
            numDaysText.text = "0"
            numSitesText.text = "5"

        val foundGigsButton = findViewById<Button>(R.id.foundGigs)
        foundGigsButton.setOnClickListener{
            launchFoundGigs(jobScrapper.list)
        }
        val todayOnly = findViewById<CheckBox>(R.id.todayOnly)
        todayOnly.setOnClickListener{
            if(todayOnly.isChecked){
                numDaysText.text = "0"
            }
        }

        searchGigs.setOnClickListener {
            val numDays = numDaysText.text.toString().toInt()
            val numSites = numSitesText.text.toString().toInt()

            CoroutineScope(IO).launch {
                jobScrapper.getRequest(gigsLayout,numDays,numSites,todayOnly.isChecked)
            }
        }
    }
    private fun launchFoundGigs(jobScrapperList: ArrayList<String>){
        val foundGigsIntent =  Intent(this,FoundGigs::class.java)
        foundGigsIntent.putExtra("JobScrapperList",jobScrapperList as Serializable)
        startActivity(foundGigsIntent)
    }



}







