package com.example.bitacoraderegistros.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bitacoraderegistros.MainActivity
import com.example.bitacoraderegistros.R
import com.example.bitacoraderegistros.databinding.ActivityListarBinding

class ListarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extra: Bundle = intent.extras!!
        with(binding){
            titulo.text = "${extra.get("titulo").toString()}"
            contenido.text = "${extra.get("contenido").toString()}"
        }


    }
}