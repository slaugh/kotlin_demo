package com.example.kotlindemo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import android.widget.TextView
import android.widget.Toast
import org.json.JSONArray
import java.io.File
import java.io.IOException
import java.io.InputStream
import kotlinx.android.parcel.Parcelize

/**
 * Main Activity that is created on startup
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("OnCreate: Main")
    }

    /**
     * This function gives some logging visibility into the lifecycle for onStart()
     */
    override fun onStart() {
        super.onStart()
        println("OnStart: Main")
    }

    /**
     * This function gives some logging visibility into the lifecycle for onResume()
     */
    override fun onResume() {
        super.onResume()
        println("OnResume: Main")
    }

    /**
     * This function gives some logging visibility into the lifecycle for onPause()
     */
    override fun onPause() {
        super.onPause()
        println("OnPause: Main")
    }

    /**
     * This function gives some logging visibility into the lifecycle for onStop()
     */
    override fun onStop() {
        super.onStop()
        println("OnStop: Main")
    }

    /**
     * This function gives some logging visibility into the lifecycle for onRestart()
     */
    override fun onRestart() {
        super.onRestart()
        println("OnRestart: Main")
    }

    /**
     * This function gives some logging visibility into the lifecycle for onDestroy()
     */
    override fun onDestroy() {
        super.onDestroy()
        println("OnDestroy: Main")
    }

    /**
     * This function is linked to the "Toast" button, it displays a toast and runs the file ready write experiments
     * @property view - Needed otherwise is doesn't run properly
     */
    fun toastMe(view: View) {
        // val myToast = Toast.makeText(this, message, duration);
        val myToast = Toast.makeText(this, "Running Scott's File Experiments", Toast.LENGTH_LONG)
        myToast.show()

        //This is an experiment in reading files - It should print out the names of awesome actors to the logs
        fileReadWriteExample()
    }

    /**
     * This function is linked to the "Count" button, it increments a counter and displays it on the screen
     * @property view - Needed otherwise it doesn't run properly
     */
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

    /**
     * This function is linked to the "News" button which opens up the news activity
     * @property view - Needed otherwise it doesn't run properly
     */
    fun newsMe (view: View){
        // Create an Intent to start the second activity
        val newsIntent = Intent(this, NewsActivity::class.java)

        //Parcelables experiment1
        var parcelableClass = ParcelableClass(appName = "KotlinDemo", time=1)
        newsIntent.putExtra("ParcelClass", parcelableClass)

        //Parcelables eperiment2
        var parcelOldSchoolHuman = ParcelOldSchoolHuman(firstName = "Scott", lastName = "Laughlin", yearOfBirth = 1987)
        newsIntent.putExtra("human", parcelOldSchoolHuman)

        // Start the new activity.
        startActivity(newsIntent)
    }

    /**
     * This is a set of experiments for reading and writing to the filesystem
     * Reference: https://developer.android.com/training/data-storage/files
     *
     */
    private fun fileReadWriteExample() {

        try {
            // Experiment 1 - Read from a source file
            val source_filename = "actors.json"
            val json = readActorsFile(source_filename)


            // Experiment 2 - Write to an internal file
            val internal_filename = "writetest.json"
            val fileContents = json
            writeReadExperiment(internal_filename, fileContents)

        } catch (e: IOException) {
            println(e)
        }
    }


    /**
     * READ AND WRITE FILESYSTEM EXPERIMENT
     * This is an experiment in reading and writing to the android filesystem via internal file
     * Reference: https://developer.android.com/training/data-storage/files
     * @property internal_filename - the filename where you want to store the data
     * @property fileContents - the json data that you want to write to the file
     */
    private fun writeReadExperiment(internal_filename: String, fileContents: String) {

        //This gives some insight into how they find the filepath, create the file, and where they store it
        println(this.filesDir)
        val file = File(this.filesDir, internal_filename)
        file.writeText(fileContents)
        println(file.absolutePath)

        //Now read and print the file contents
        val text = file.readText()
        println("Read from Internal Storage: " + text)
    }

    /**
     * READ FROM A SOURCE CODE JSON FILE PLACED IN "assets" FOLDER
     * Successful experiment - Game of Thrones Actors
     * Reference: https://stackoverflow.com/questions/34921818/how-to-read-json-file-from-assets-folder-in-android/51322947
     * @property source_filename - the filename of the source file in 'assets' folder
     * @return json - the json text extracted from the file
     */
    private fun readActorsFile(source_filename: String): String {

        //Open and read the JSON file
        val inputStream: InputStream = assets.open(source_filename)
        var json = inputStream.bufferedReader().readText() //This will give the json as text

        // Parse and print first and last name of each actor in the json text
        var jsonarray = JSONArray(json)
        for (i in 0..jsonarray.length() - 1) {

            var jsonobj = jsonarray.getJSONObject(i)
            println("First Name: " + jsonobj.getString("first_name"))
            println("Last Name: " + jsonobj.getString("last_name"))

        }

        return json
    }

}


/**
 * Class used for @Parcelize PARCELABLES EXPERIMENT1
 * Reference: https://medium.com/the-lazy-coders-journal/easy-parcelable-in-kotlin-the-lazy-coders-way-9683122f4c00
 */
@Parcelize
class ParcelableClass(val appName: String, val time: Int) : Parcelable {
    fun present(){
        println("Presenting: " + appName)
    }
}

/**
 * Class used for @Parcelize PARCELABLES EXPERIMENT2 - Oldschool methods
 * Reference: https://medium.com/the-lazy-coders-journal/easy-parcelable-in-kotlin-the-lazy-coders-way-9683122f4c00
 */
class ParcelOldSchoolHuman(var firstName: String, var lastName: String, var yearOfBirth: Int) : Parcelable {
    val fullName = "$firstName $lastName"

    //Use of custom setter which validates the input
    var age = 0
        set(value){
            if (value < 0 ) throw IllegalArgumentException(
                "Age cannot be negative")
            field = value
        }

    /* Interestingly you will have access to the initialized values when you
    access the method in addition to the member variables
     */
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    init {
        this.age = 2019 - yearOfBirth
    }

    fun present() {
        println("Hello, I'm ${fullName}!")
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(firstName)
        parcel.writeString(lastName)
        parcel.writeInt(yearOfBirth)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ParcelOldSchoolHuman> {
        override fun createFromParcel(parcel: Parcel): ParcelOldSchoolHuman {
            return ParcelOldSchoolHuman(parcel)
        }

        override fun newArray(size: Int): Array<ParcelOldSchoolHuman?> {
            return arrayOfNulls(size)
        }
    }


}





