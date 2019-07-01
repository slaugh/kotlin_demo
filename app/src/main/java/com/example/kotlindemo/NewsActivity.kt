package com.example.kotlindemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.LinearLayout
//import io.reactivex.rxkotlin.toObservable
//import io.reactivex.rxkotlin.Observable
import kotlinx.android.synthetic.main.activity_news.*
import okhttp3.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException


class NewsActivity : AppCompatActivity() {
    private var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        //1. Initiate Model
        val news_stories = ArrayList<News_Model>()
        //2. Create the adapter - Pass in Model From #1.
        val obj_adapter = NewsAdapter(news_stories, this, R.layout.news_row )

        layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        news_recycler.layoutManager = layoutManager
        news_recycler.adapter = obj_adapter

//        //RxKotlin Study of Observables and subscriptions
//        Observable.just("Hello KotlinRx").subscribe{
//            println(it)
//        }
//
//        val popular: Observable<String> = Observable.just("StarWars")
//        popular.subscribe(){println(it)}
//
//        val stories = listOf("solo", "rogueOne", "theLastJedi").toObservable()

        getNews(news_stories, obj_adapter)

     }

    private fun getNews(
        news_stories: ArrayList<News_Model>,
        obj_adapter: NewsAdapter
    ) {
        //1.1 Get Model Data
        var client = OkHttpClient()

        val request = Request.Builder()
            .url(getString(R.string.news_url))
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("DEBUG", "onFailure: " + e.toString())
            }

            override fun onResponse(call: Call, response: Response) {

                val responseData = response.body()?.string()
                runOnUiThread {
                    try {
                        var json = JSONObject(responseData)
                        Log.d("DEBUG", "Request Successful!")
                        Log.d("DEBUG", json.toString())
                        val news = json.getJSONArray("articles")

                        for (i in 0..(news.length() - 1)) {
                            val article = news.getJSONObject(i)

                            //From the article extract: source -> name
                            val name = article.getJSONObject("source").getString("name")

                            //From the article extract: title
                            val title = article.getString("title")

                            Log.d("DEBUG", "name: " + name + ", " + "title: " + title)
                            //Populate news_stories
                            news_stories.add(News_Model(name, title))
                        }
                        obj_adapter.notifyDataSetChanged()
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }
        })
    }
}
