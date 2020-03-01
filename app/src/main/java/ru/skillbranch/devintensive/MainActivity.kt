package ru.skillbranch.devintensive

import android.content.Context
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import ru.skillbranch.devintensive.models.Bender

class MainActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        setContentView(R.layout.activity_profile_constraint)

        Log.d("M_", "onCreate")

    }



    override fun onRestart() {
        super.onRestart()
        Log.d("M_", "onRestart")
    }

    override fun onStart() {
        super.onStart()
        Log.d("M_", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("M_", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("M_", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("M_", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("M_", "onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

//        outState.putString("STATUS", benderObj.status.name)
//        outState.putString("QUESTION", benderObj.question.name)
//        outState.putString("TXTEDITINPUT", messageEt.text.toString())
//        Log.d("M_", "onSaveInstanceState ${benderObj.status.name}")
    }

    override fun onClick(v: View?)
    {
        if(v?.id == R.id.iv_send)
        {
            checkAnswer()
        }
    }

    fun checkAnswer()
    {



    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

}
