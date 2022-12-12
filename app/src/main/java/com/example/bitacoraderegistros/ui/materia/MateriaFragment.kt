package com.example.bitacoraderegistros.ui.materia

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import com.example.bitacoraderegistros.MainActivity
import com.example.bitacoraderegistros.R
import com.example.bitacoraderegistros.databinding.FragmentBitacoraBinding
import com.example.bitacoraderegistros.databinding.FragmentMateriaBinding
import com.example.bitacoraderegistros.date_time.DatePickerFragment
import com.example.bitacoraderegistros.date_time.TimePickerFragment
import com.example.bitacoraderegistros.files.escribirArchivo
import com.example.bitacoraderegistros.files.leerArchivo
import com.example.bitacoraderegistros.ui.ListarActivity

class MateriaFragment : Fragment() {

    val dias = listOf("Lunes", "Martes", "Miercoles", "Jueves", "Viernes")
    var diaSeleccionado = ""


    private var _binding: FragmentMateriaBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMateriaBinding.inflate(inflater, container, false)
        val root: View = binding.root
        agregarASpinners()
        horaSeleccionada()
        addMateria()
        listarContenido()
        limpiarDatos()

        return root
    }

    fun agregarASpinners(){
        with(binding){
            dia.adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, dias)
            diaSeleccionado = dia.selectedItem.toString()
        }
    }
    fun horaSeleccionada(){
        with(binding){
            horaInicio.setOnClickListener {
                showTimePickerDialog(horaInicio)
            }

            horaSalida.setOnClickListener {
                showTimePickerDialog(horaSalida)
            }
        }
    }

    fun addMateria(){
        with(binding){
            btnRegistrarMateria.setOnClickListener {
                var cadena = "${claveMateria.text.toString()},${nombreMateria.text.toString()},${dia.selectedItem},${horaInicio.text.toString()},${horaSalida.text.toString()}\n"
                escribirArchivo("RegistroMateria", cadena)

                Toast.makeText(requireContext(), "Materia Registrada!!", Toast.LENGTH_LONG).show()
                limpiarDatos()
            }
        }
    }

    private fun showTimePickerDialog(vi: EditText) {
        val timePicker = TimePickerFragment { onTimeSelected(it, vi) }

        timePicker.show(MainActivity.ssuportFragmentManager, "time")
    }

    fun onTimeSelected(it: String, vi: EditText){
        vi.setText("$it")
    }

    fun listarContenido() {
        binding.btnListarMateria.setOnClickListener {
            val cadena = leerArchivo("RegistroMateria")
            val intent = Intent(activity, ListarActivity::class.java)
            intent.putExtra("titulo", "Materias")
            intent.putExtra("contenido", cadena)
            startActivity(intent)
        }
    }

    fun limpiarDatos(){
        with(binding){
            claveMateria.setText(null)
            nombreMateria.setText(null)
            dia.setSelection(0)
            horaInicio.setText(null)
            horaSalida.setText(null)
            }
    }

}
