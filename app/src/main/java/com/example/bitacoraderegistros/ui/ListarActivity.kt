package com.example.bitacoraderegistros.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.bitacoraderegistros.databinding.ActivityListarBinding


class ListarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var extras = intent.extras


        with(binding){

            titulo.text = extras!!.getString("titulo")
            showData(extras!!.getStringArrayList("contenido") as ArrayList<String>)
        }

    }

    fun showData(li: ArrayList<String>){
        var lista = li
        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista)
        binding.lista.adapter = adapter
    }


}
