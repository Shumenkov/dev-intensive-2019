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

    lateinit var benderImage:ImageView
    lateinit var textTXT:TextView
    lateinit var messageEt: EditText
    lateinit var sendBtn: ImageView

    lateinit var benderObj:Bender

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("M_", "onCreate")

        benderImage = findViewById(R.id.iv_bender)
        textTXT= findViewById(R.id.tv_text)
        messageEt= findViewById(R.id.et_message)
        sendBtn= findViewById(R.id.iv_send)

        messageEt.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_DONE){

                checkAnswer()
                Log.d("M_", "DONE")
                true
            } else {
                false
            }
        }

        val status = savedInstanceState?.getString("STATUS")?:Bender.Status.NORMAL.name
        val question = savedInstanceState?.getString("QUESTION")?:Bender.Question.NAME.name
        messageEt.setText(savedInstanceState?.getString("TXTEDITINPUT"))
        benderObj = Bender(Bender.Status.valueOf(status), Bender.Question.valueOf(question))

        val (r,g,b) = benderObj.status.color
        benderImage.setColorFilter(Color.rgb(r,g,b),PorterDuff.Mode.MULTIPLY)

        textTXT.setText(benderObj.askQuestion())
        sendBtn.setOnClickListener(this)
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

        outState.putString("STATUS", benderObj.status.name)
        outState.putString("QUESTION", benderObj.question.name)
        outState.putString("TXTEDITINPUT", messageEt.text.toString())
        Log.d("M_", "onSaveInstanceState ${benderObj.status.name}")
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
        val (phrase,color) = benderObj.listernAnsver(messageEt.text.toString().toLowerCase())
        messageEt.setText("")
        messageEt.hideKeyboard()
        val (r,g,b) = color
        benderImage.setColorFilter(Color.rgb(r,g,b),PorterDuff.Mode.MULTIPLY)
        textTXT.setText(phrase)


    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

}
