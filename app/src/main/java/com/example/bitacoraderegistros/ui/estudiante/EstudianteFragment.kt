package com.example.bitacoraderegistros.ui.estudiante

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.bitacoraderegistros.R
import com.example.bitacoraderegistros.databinding.FragmentBitacoraBinding
import com.example.bitacoraderegistros.databinding.FragmentEstudianteBinding
import com.example.bitacoraderegistros.files.escribirArchivo
import com.example.bitacoraderegistros.files.leerArchivo
import com.example.bitacoraderegistros.ui.ListarActivity

class EstudianteFragment : Fragment() {

    val semestres = listOf(1,2,3,4,5,6,7,8,9,10,11,12)
    val carreras = listOf("Informática", "TICS", "Agronomia", "Biología", "Forestal")

    private var _binding: FragmentEstudianteBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEstudianteBinding.inflate(inflater, container, false)
        val root: View = binding.root
        limpiarDatos()
        agregarASpinners()
        addStudent()
        listarContenido()

        return root
    }

    fun agregarASpinners(){
        with(binding){
            semestre.adapter = ArrayAdapter<Int>(requireContext(), android.R.layout.simple_spinner_item, semestres)

            carrera.adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, carreras)
        }
    }


    fun addStudent(){
        with(binding){
            btnRegistrarEstudiante.setOnClickListener {
                var cadena = "${nControl.text.toString()},${name.text.toString()},${carrera.selectedItem},${semestre.selectedItem},${obtenerGrupo()}\n"
                escribirArchivo("RegistroEstudiante", cadena)

                Toast.makeText(requireContext(), "Usuario Registrado!!", Toast.LENGTH_LONG).show()
                limpiarDatos()
            }
        }
    }

    fun obtenerGrupo() = if (binding.btnA.isChecked) "A" else "B"

    fun listarContenido(){
        binding.btnListarEstudiante.setOnClickListener {
            val cadena = leerArchivo("RegistroEstudiante")
            val intent = Intent(activity, ListarActivity::class.java)
            intent.putExtra("titulo", "Estudiantes")
            intent.putExtra("contenido", cadena)
            startActivity(intent)
        }
    }

    fun limpiarDatos(){

        with(binding){
            nControl.setText(null)
            name.setText(null)
            carrera.setSelection(0)
            semestre.setSelection(0)
        }
    }

}