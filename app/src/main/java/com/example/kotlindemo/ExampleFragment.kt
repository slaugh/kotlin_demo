package com.example.kotlindemo

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ExampleFragment : Fragment() {

    /**
     * This represents a little experiment in fragment lifecycles. See how the system navigates between
     * MainAvtivity and ExampleFragment as the system boots up:
     * 2019-07-08 19:51:44.685 31398-31398/? I/System.out: OnAttach: ExampleFragment
     * 2019-07-08 19:51:44.686 31398-31398/? I/System.out: OnCreate: ExampleFragment
     * 2019-07-08 19:51:44.689 31398-31398/? I/System.out: OnCreateView: ExampleFragment
     * 2019-07-08 19:51:44.697 31398-31398/? I/System.out: OnCreate: Main
     * 2019-07-08 19:51:44.701 31398-31398/? I/System.out: OnActivityCreated: ExampleFragment
     * 2019-07-08 19:51:44.702 31398-31398/? I/System.out: OnActivityCreate: ExampleFragment
     * 2019-07-08 19:51:44.702 31398-31398/? I/System.out: OnStart: Main
     * 2019-07-08 19:51:44.704 31398-31398/? I/System.out: OnResume: Main
     * 2019-07-08 19:51:44.704 31398-31398/? I/System.out: OnResume: ExampleFragment
     *
     * Next after opening the news activity:
     * 2019-07-08 19:51:59.094 31398-31398/com.example.kotlindemo I/System.out: OnPause: ExampleFragment
     * 2019-07-08 19:51:59.094 31398-31398/com.example.kotlindemo I/System.out: OnPause: Main
     * And shortly after:
     * 2019-07-08 19:51:59.690 31398-31398/com.example.kotlindemo I/System.out: OnStop: ExampleFragment
     * 2019-07-08 19:51:59.690 31398-31398/com.example.kotlindemo I/System.out: OnStop: Main
     *
     * On Pressing Back:
     * 2019-07-08 19:55:50.004 31398-31398/com.example.kotlindemo I/System.out: OnRestart: Main
     * 2019-07-08 19:55:50.004 31398-31398/com.example.kotlindemo I/System.out: OnActivityCreate: ExampleFragment
     * 2019-07-08 19:55:50.004 31398-31398/com.example.kotlindemo I/System.out: OnStart: Main
     * 2019-07-08 19:55:50.005 31398-31398/com.example.kotlindemo I/System.out: OnResume: Main
     * 2019-07-08 19:55:50.005 31398-31398/com.example.kotlindemo I/System.out: OnResume: ExampleFragment
     *
     * Note an interesting point that OnActivityCreate comes between OnRestart: Main and OnStart: Main, slightly
     * different than the beginning of the apps creation
     */

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        println("OnAttach: ExampleFragment")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("OnCreate: ExampleFragment")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        println("OnCreateView: ExampleFragment")
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment, container, false)

        //Attach a click handler
        view.setOnClickListener { clickProfile() }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        println("OnActivityCreated: ExampleFragment")
    }

    override fun onStart() {
        super.onStart()
        println("OnActivityCreate: ExampleFragment")
    }

    override fun onResume() {
        super.onResume()
        println("OnResume: ExampleFragment")
    }

    override fun onPause() {
        super.onPause()
        println("OnPause: ExampleFragment")
    }

    override fun onStop() {
        super.onStop()
        println("OnStop: ExampleFragment")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        println("OnDestroyView: ExampleFragment")

    }

    override fun onDestroy() {
        super.onDestroy()
        println("OnDestroy: ExampleFragment")

    }

    override fun onDetach() {
        super.onDetach()
        println("OnDetach: ExampleFragment")
    }

    /**
     * What is triggered when the user clicks on the profile picture
     */
    fun clickProfile(){
        println("clickProfile")
        //TODO: Start a new activity for testing this functionality

    }

}