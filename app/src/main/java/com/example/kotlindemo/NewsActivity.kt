package com.example.kotlindemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_news.*

class NewsActivity : AppCompatActivity() {
    private var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        //1. Create Model
        val news_stories = ArrayList<News_Model>()

        news_stories.add(News_Model("New York Times", "Missles Fired off Coast of Iran"))
        news_stories.add(News_Model("Fox", "Fire Ze Missiles!"))
        news_stories.add(News_Model("CNN","Poor Missiles"))
        news_stories.add(News_Model("MSNBC","Missles Are Flying Everywhere!"))
        news_stories.add(News_Model("The Atlantic","Soviet Missles are going out of style"))
        news_stories.add(News_Model("The Economist","Demand For Missles wreaks havok on cobalt supply"))
        news_stories.add(News_Model("CBC","Canada Refuses to Go To War"))
        news_stories.add(News_Model("BBC","Parliament Offers Quip on Missle Situation"))
        news_stories.add(News_Model("Al Jazeera","Poor Missiles"))

        //2. Create the adapter - Pass in Model From #1.
        val obj_adapter = NewsAdapter(news_stories, this, R.layout.news_row )

        layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        news_recycler.layoutManager = layoutManager
        news_recycler.adapter = obj_adapter
    }


}
