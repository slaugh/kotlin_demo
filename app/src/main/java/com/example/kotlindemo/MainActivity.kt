package com.example.kotlindemo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import org.json.JSONArray
import java.io.File
import java.io.IOException
import java.io.InputStream


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("OnCreate")
    }

    override fun onStart() {
        super.onStart()
        println("OnStart")
    }

    override fun onResume() {
        super.onResume()
        println("OnResume")
    }

    override fun onPause() {
        super.onPause()
        println("OnPause")
    }

    override fun onStop() {
        super.onStop()
        println("OnStop")
    }

    override fun onRestart() {
        super.onRestart()
        println("OnRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("OnDestroy")
    }

    fun toastMe(view: View) {
        // val myToast = Toast.makeText(this, message, duration);
        val myToast = Toast.makeText(this, "Hello Toast!", Toast.LENGTH_SHORT)
        myToast.show()

        //This is an experiment in reading files - It should print out the names of awesome actors to the logs
        fileReadWriteExample()
    }

    private fun fileReadWriteExample() {
        //This is an experiment in reading files - It should print out the names of awesome actors to the logs
        var json: String? = null
        var array = arrayListOf<String>()

        try {

            // Successful experiment
            val inputStream: InputStream = assets.open("example2.json")
            json = inputStream.bufferedReader().readText() //This will give the json as text
            var jsonarray = JSONArray(json)
            for (i in 0..jsonarray.length() - 1) {
                var jsonobj = jsonarray.getJSONObject(i)
                println("JSON Object: " + jsonobj.getString("first_name"))
            }

            //try writing a file
            val filename = "writetest.json"
            val fileContents = "[\n" +
                    "  {\n" +
                    "    \"first_name\" : \"Sean\",\n" +
                    "    \"last_name\" : \"Bean\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"first_name\" : \"Peter\",\n" +
                    "    \"last_name\" : \"Parker\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"first_name\" : \"Sean\",\n" +
                    "    \"last_name\" : \"Connery\"\n" +
                    "  }\n" +
                    "]"

            //This gives some insight into how they find the filepath, create the file, and where they store it
            println(this.filesDir)
            val file = File(this.filesDir, filename)
            file.writeText(fileContents)
            println(file.absolutePath)


            //Now read and print the file contents
            val text = file.readText()
            println(text)


        } catch (e: IOException) {
            println(e)
        }
    }

    fun countMe (view: View) {
        // Get the text view
        val showCountTextView = findViewById<TextView>(R.id.counter_text)

        // Get the value of the text view.
        val countString = showCountTextView.text.toString()

        // Convert value to a number and increment it
        var count: Int = Integer.parseInt(countString)
        count++

        // Display the new value in the text view.
        showCountTextView.text = count.toString();
    }

    fun newsMe (view: View){
        // Create an Intent to start the second activity
        val newsIntent = Intent(this, NewsActivity::class.java)

        // Start the new activity.
        startActivity(newsIntent)
    }

}


