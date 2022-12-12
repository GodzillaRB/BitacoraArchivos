package com.example.bitacoraderegistros.ui.bitacora

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.bitacoraderegistros.MainActivity
import com.example.bitacoraderegistros.R
import com.example.bitacoraderegistros.databinding.FragmentBitacoraBinding
import com.example.bitacoraderegistros.date_time.DatePickerFragment
import com.example.bitacoraderegistros.files.*
import com.example.bitacoraderegistros.models.Estudiante
import com.example.bitacoraderegistros.models.Materia
import com.example.bitacoraderegistros.ui.ListarActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.io.File


class BitacoraFragment : Fragment() {

    private var _binding: FragmentBitacoraBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBitacoraBinding.inflate(inflater, container, false)
        val root: View = binding.root

        seleccionarFecha()
        buscarEstudiante()
        buscarMateria()
        registrarBitacora()

        return root
    }

    fun obtenerEstudiantes(): Estudiante {
        var file: File = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                .toString() + "/" + "RegistroEstudiante.txt"
        )
        val lista = getEstudiantes(file)
        var buscarEst = binding.nControlBitacora.text.toString()
        var estudiante = searchEstudiante(buscarEst, lista)


        return estudiante
    }

    fun showDialogEstudiante(cadena: String) {
        MaterialAlertDialogBuilder(requireActivity()).setTitle("Estudiante").setMessage(cadena)
            .setPositiveButton(resources.getString(R.string.okDiialog)) { dialog, w ->
            }.show()

    }

    fun obtenerMateria(): Materia {
        var file: File = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                .toString() + "/" + "RegistroMateria.txt"
        )
        val lista = getMaterias(file)
        var buscarMat = binding.claveMateriaBitacora.text.toString()
        var materia = searchMateria(buscarMat, lista)


        return materia
    }

    fun showDialogMateria(cadena: String) {
        MaterialAlertDialogBuilder(requireActivity()).setTitle("Materia").setMessage(cadena)
            .setPositiveButton(resources.getString(R.string.okDiialog)) { dialog, w ->

            }.show()
    }

    fun seleccionarFecha() {
        binding.fechaBitacora.setOnClickListener {
            showDatePickerDialog()
        }
    }

    private fun showDatePickerDialog() {
        val datePicker =
            DatePickerFragment({ day, month, year -> onDateSelected(day, month, year) })
        datePicker.show(MainActivity.ssuportFragmentManager, "datePicker")
    }

    /**
     * Función para asignar la fecha al EditText
     */
    fun onDateSelected(day: Int, month: Int, year: Int) {
        val dia: String = if (day < 10) "0$day" else "$day"
        val mes: String = if (month < 10) "0$month" else "$month"

        binding.fechaBitacora.setText("$dia/$mes/$year")
    }

    fun buscarEstudiante() {
        binding.btnBuscarNControl.setOnClickListener() {
            var estudiante = obtenerEstudiantes()
            if (!estudiante.nControl.equals("")) {
                showDialogEstudiante("NControl: ${estudiante.nControl}\nNombre: ${estudiante.nombreCompleto}\nCarrera: ${estudiante.carrera}\nSemestre: ${estudiante.semestre}\nGrupo: ${estudiante.grupo}")
            } else {
                showDialogEstudiante("No se encontró al estudiante!!")
            }
        }
    }

    fun buscarMateria() {
        binding.btnBuscarClaveMateria.setOnClickListener() {
            var materia = obtenerMateria()
            if (!materia.claveMateria.equals("")) {
                showDialogMateria("Clave Materia: ${materia.claveMateria}\nNombre: ${materia.nombreMateria}\nDía: ${materia.dia}\nHora de entrada: ${materia.horaEntrada}\nHora de Salida: ${materia.horaSalida}")
            } else {
                showDialogMateria("No se encontró la materia !!")
            }
        }
    }

    fun registrarBitacora() {

        with(binding) {
            btnRegistrarBitacora.setOnClickListener {

                var existeEstudiante = obtenerEstudiantes()
                var existeMateria = obtenerMateria()

                if (existeEstudiante.nControl == "" || existeMateria.claveMateria == "" || fechaBitacora.text.toString() == "") {
                    showDialogMateria("No se encontro un valor")
                } else {
                    var cadena = "\nFecha de Registro: ${fechaBitacora.text.toString()}\nDatos del Estudiante\nNumero de control: ${existeEstudiante.nControl}\nNombre Completo:${existeEstudiante.nombreCompleto}\nGrupo: ${existeEstudiante.grupo}\nCarrera: ${existeEstudiante.carrera}\nSemestre: ${existeEstudiante.semestre}," +
                                "\nDatos de la Materia\nClave de la Materia: ${existeMateria.claveMateria}\nNombre de la Materia: ${existeMateria.nombreMateria}\nDía: ${existeMateria.dia}\nHora de entrada: ${existeMateria.horaEntrada}\nHora de salida: ${existeMateria.horaSalida}\n"
                    escribirArchivo("RegistrarBitacora", cadena)

                    Toast.makeText(requireContext(), "Bitacora Registrada!!", Toast.LENGTH_LONG)
                        .show()
                }
            }
            btnListarBitacora.setOnClickListener {
                val cadena = leerArchivo("RegistrarBitacora")
                val intent = Intent(activity, ListarActivity::class.java)
                intent.putExtra("titulo", "Bitacora")
                intent.putExtra("contenido", cadena)
                startActivity(intent)
            }
        }
    }
}