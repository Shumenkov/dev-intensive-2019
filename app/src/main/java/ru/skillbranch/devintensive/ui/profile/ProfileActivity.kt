package ru.skillbranch.devintensive.ui.profile

import android.content.Context
import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_profile_constraint.*
import ru.skillbranch.devintensive.R
import ru.skillbranch.devintensive.models.Profile
import ru.skillbranch.devintensive.viewmodels.ProfileViewModel

class ProfileActivity : AppCompatActivity() {

    companion object{
        const val IS_EDIT_MODE = "IS_EDIT_MODE"
    }

    private lateinit var viewModel: ProfileViewModel
    var isEditMode = false;
    lateinit var viewFields : Map<String,TextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        setContentView(R.layout.activity_profile_constraint)
        initViews(savedInstanceState)
        initViewModel()

        Log.d("M_", "onCreate")

    }

    fun initViews(savedInstanceState: Bundle?)
    {
        viewFields = mapOf(
            "nickName" to tv_nick_name,
            "rank" to tv_rank,
            "firstName" to et_first_name,
            "lastName" to et_last_name,
            "about" to et_about,
            "repository" to et_repository,
            "rating" to tv_rating,
            "respect" to tv_respect
            )

        isEditMode = savedInstanceState?.getBoolean(IS_EDIT_MODE,false)?:false
        showCurrentMode(isEditMode)

        btn_edit.setOnClickListener(View.OnClickListener {
            if(isEditMode) saveProfileInfo()
            this.isEditMode =!this.isEditMode
            showCurrentMode(this.isEditMode)
        })
    }

    private fun showCurrentMode(isEdit:Boolean)
    {
        val info = viewFields.filter { setOf( "firstName","lastName","about","repository").contains(it.key) }
        for((_,value) in info)
        {
            value as EditText
            value.isFocusable = isEdit
            value.isFocusableInTouchMode = isEdit
            value.isEnabled = isEdit
            value.background.alpha = if(isEdit) 255 else 0
        }

        ic_eye.visibility = if(isEdit) View.GONE else View.VISIBLE
        wr_about.isCounterEnabled = isEdit

        with(btn_edit)
        {
            val filter: ColorFilter? = if(isEdit)
            {
                PorterDuffColorFilter(
                    resources.getColor(R.color.color_accent, theme),
                    PorterDuff.Mode.SRC_IN
                )
            }
            else
                null

            val icon = if(isEdit)
                resources.getDrawable(R.drawable.ic_save_black_24dp, theme)
            else
                resources.getDrawable(R.drawable.ic_edit_black_24dp, theme)

            background.colorFilter = filter
            setImageDrawable(icon)
        }
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

        outState.putBoolean(IS_EDIT_MODE, isEditMode)
//        outState.putString("STATUS", benderObj.status.name)
//        outState.putString("QUESTION", benderObj.question.name)
//        outState.putString("TXTEDITINPUT", messageEt.text.toString())
//        Log.d("M_", "onSaveInstanceState ${benderObj.status.name}")
    }

    private fun initViewModel()
    {
        viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        viewModel.getProfileData().observe(this, Observer { updateUI(it) })
    }

    private fun updateUI(profile: Profile)
    {
        profile.toMap().also {
            for((key,value) in viewFields)
            {
                value.text = it[key].toString()
            }
        }
    }


    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun saveProfileInfo()
    {
        Profile(
            firstName = et_first_name.text.toString(),
            lastName = et_last_name.text.toString(),
            about = et_about.text.toString(),
            repository = et_repository.text.toString()
        ).apply {
            viewModel.saveProfileData(this)
        }
    }
}
