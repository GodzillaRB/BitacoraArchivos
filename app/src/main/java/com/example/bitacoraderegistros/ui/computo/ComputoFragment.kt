package com.example.bitacoraderegistros.ui.computo

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.bitacoraderegistros.R
import com.example.bitacoraderegistros.databinding.FragmentBitacoraBinding
import com.example.bitacoraderegistros.databinding.FragmentComputoBinding
import com.example.bitacoraderegistros.files.escribirArchivo
import com.example.bitacoraderegistros.files.leerArchivo
import com.example.bitacoraderegistros.models.Materia
import com.example.bitacoraderegistros.models.Sala
import com.example.bitacoraderegistros.sqlite.getSalaDB
import com.example.bitacoraderegistros.sqlite.setSalaDB
import com.example.bitacoraderegistros.ui.ListarActivity

class ComputoFragment : Fragment() {


    private var _binding: FragmentComputoBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentComputoBinding.inflate(inflater, container, false)

        val root: View = binding.root
        addSala()
        listarContenido()
        return root
    }

    fun addSala() {
        with(binding) {
            btnRegistrarSala.setOnClickListener {
                if (clave.text.toString() != "" && nombreSala.text.toString() != "") {
                    var sala: Sala? = null
                    sala = Sala(
                        clave.text.toString(),
                        nombreSala.text.toString()
                    )
                    setSalaDB(sala, requireActivity())
                    Toast.makeText(requireContext(), "Sala Registrada!!", Toast.LENGTH_LONG).show()
                    limpiarDatos()
                }else
                    Toast.makeText(requireContext(), "Favor de rellenar todos los campos", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun listarContenido() {
        binding.btnListarSala.setOnClickListener {
            val cadena = getSalaDB(requireActivity())
            val intent = Intent(activity, ListarActivity::class.java)
            intent.putExtra("titulo", "Sala")
            intent.putExtra("contenido", cadena)
            startActivity(intent)
        }
    }

    fun limpiarDatos() {
        with(binding) {
            clave.setText(null)
            nombreSala.setText(null)
        }
    }
}