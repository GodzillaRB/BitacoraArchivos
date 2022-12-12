package com.example.bitacoraderegistros.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.bitacoraderegistros.R
import com.example.bitacoraderegistros.databinding.ActivityListarBinding
import com.example.bitacoraderegistros.files.getEstudiantes
import com.example.bitacoraderegistros.sqlite.getEstudianteDB

class ListarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            showData()
        }

    }

    fun showData(){
        var lista = getEstudianteDB(this)
        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista)
        binding.lista.adapter = adapter
    }


}
