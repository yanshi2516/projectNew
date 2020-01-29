package com.example.projectnews.newstories

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.projectnews.R

class NewsDataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_data)

        val btnReadMore = findViewById<Button>(R.id.buttonReadMore)

        val newsDatailData = intent.getParcelableExtra<News>("newsDetailsData")

        val textViewTitle = findViewById<TextView>(R.id.textViewTitle)
        textViewTitle.text = newsDatailData?.title

        val textViewBy = findViewById<TextView>(R.id.textViewBy)
        textViewBy.text = newsDatailData?.by

        val textViewDescendants = findViewById<TextView>(R.id.textViewDescendants)
        textViewDescendants.append(newsDatailData?.descendants)

        val textViewTime = findViewById<TextView>(R.id.textViewTime)
        textViewTime.text = newsDatailData?.time.toString()

        btnReadMore.setOnClickListener {
            val uri = Uri.parse(newsDatailData.url)
            val intent = Intent(Intent.ACTION_VIEW,uri)
            startActivity(intent)
        }
    }
}
