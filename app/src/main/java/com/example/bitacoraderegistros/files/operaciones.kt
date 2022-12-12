package com.example.bitacoraderegistros.files

import android.os.Environment
import com.example.bitacoraderegistros.models.Estudiante
import com.example.bitacoraderegistros.models.Materia
import com.example.bitacoraderegistros.models.Sala
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.lang.Exception
import java.util.*

val ruta = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString()

fun escribirArchivo(name: String, contenido: String) {
    val fileWrite = FileWriter("$ruta/$name.txt", true)
    //Una vez abierto escribiremos en el archivo
    val escribir = BufferedWriter(fileWrite)
    //Guardamos lo que tiene cadena en escribir
    escribir.write(contenido)
    //Cerramos el archivo
    escribir.close()
    fileWrite.close()
}

fun leerArchivo(nombre: String) = (File(
    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        .toString() + "/" + "$nombre.txt"
)).readText()

fun getEstudiantes(file: File): MutableList<Estudiante> {
    var lista: MutableList<Estudiante> = mutableListOf()

    try {
        var scanner = Scanner(file)
        while (scanner.hasNextLine()) {
            var linea: String = scanner.nextLine()
            var delimiter = Scanner(linea)
            delimiter.useDelimiter("\\s*,\\s*")
            var numControl = delimiter.next()
            var nombreCompleto = delimiter.next()
            var carrera = delimiter.next()
            var semestre = delimiter.next()
            var grupo = delimiter.next()
            var estudiante = Estudiante(numControl, nombreCompleto, carrera, semestre.toInt(), grupo)

            lista.add(estudiante)
        }

    } catch (e: Exception) {
        println(e.message)
    }

    return lista
}

fun getMaterias(file: File): MutableList<Materia>{

    var lista: MutableList<Materia> = mutableListOf()

    try {
        var scanner = Scanner(file)
        while (scanner.hasNextLine()) {
            var linea: String = scanner.nextLine()
            var delimiter = Scanner(linea)
            delimiter.useDelimiter("\\s*,\\s*")
            var claveMateria = delimiter.next()
            var nombreMateria = delimiter.next()
            var dia = delimiter.next()
            var horaEntrada = delimiter.next()
            var horaSalida = delimiter.next()
            var materia = Materia(claveMateria, nombreMateria, dia, horaEntrada, horaSalida)

            lista.add(materia)
        }

    } catch (e: Exception) {
        println(e.message)
    }

    return lista

}
fun getSala(file: File): MutableList<Sala>{
    var lista: MutableList<Sala> = mutableListOf()

    try {
        var scanner = Scanner(file)
        while (scanner.hasNextLine()) {
            var linea: String = scanner.nextLine()
            var delimiter = Scanner(linea)
            delimiter.useDelimiter("\\s*,\\s*")
            var claveSala = delimiter.next()
            var nombreSala = delimiter.next()
            var sala = Sala(claveSala, nombreSala)

            lista.add(sala)
        }

    } catch (e: Exception) {
        println(e.message)
    }

    return lista
}