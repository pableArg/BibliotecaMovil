package com.example.bibliotecamovil.bibliotecamovil.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.bibliotecamovil.R
import com.example.bibliotecamovil.bibliotecamovil.domain.sharedPreferences.Preferences
import com.example.bibliotecamovil.databinding.ActivitySharedPreferencesBinding

class SharedPreferencesActivity : AppCompatActivity() {
    private lateinit var sharedPrefs: Preferences
    private lateinit var binding: ActivitySharedPreferencesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_shared_preferences)
        sharedPrefs= Preferences(applicationContext)
        initUI()
        checkUserValues()
    }

    private fun checkUserValues() {
        if(sharedPrefs.getName().isNotEmpty()){
            goTODetail()
        }
    }

    private fun initUI(){
        binding.btnNext.setOnClickListener {
            accessToDetail()
        }
    }

    private fun accessToDetail() {
        if(binding.etName.text.toString().isNotEmpty()){
            sharedPrefs.saveName(binding.etName.toString())
        }else {
            Toast.makeText(this, "Complete el nombre para continuar", Toast.LENGTH_SHORT).show()
        }
    }

    fun goTODetail(){
        //HAY QUE HACER EL INTENT A LA SIGUIENTE ACTIVITY
        /*startActivity(intent, NameOtraActivity::class.java)*/
    }
}