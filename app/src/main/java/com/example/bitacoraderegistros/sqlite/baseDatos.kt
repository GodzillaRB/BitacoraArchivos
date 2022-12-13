package com.example.bitacoraderegistros.sqlite

import android.content.ContentValues
import android.content.Context
import com.example.bitacoraderegistros.models.Estudiante
import com.example.bitacoraderegistros.models.Materia
import com.example.bitacoraderegistros.models.Sala

fun setEstudianteDB(estudiante: Estudiante, context: Context){
    val admin = AdminSqlite(context,"estudiante", null,1)
    val db = admin.writableDatabase
    val registro = ContentValues()
    registro.put("n_control", estudiante.nControl)
    registro.put("nombre", estudiante.nombreCompleto)
    registro.put("carrera", estudiante.carrera)
    registro.put("semestre", estudiante.semestre)
    registro.put("grupo", estudiante.grupo)

    db.insert("estudiante", null, registro)
}

fun setMateriaDB(materia: Materia, context: Context){
    val admin = AdminSqlite(context,"materia", null,1)
    val db = admin.writableDatabase
    val registro = ContentValues()
    registro.put("clave_materia", materia.claveMateria)
    registro.put("nombre_materia", materia.nombreMateria)
    registro.put("dia", materia.dia)
    registro.put("hora_entrada", materia.horaEntrada)
    registro.put("hora_salida", materia.horaSalida)

    db.insert("materia", null, registro)
}

fun setSalaDB(sala: Sala, context: Context){
    val admin = AdminSqlite(context,"sala", null,1)
    val db = admin.writableDatabase
    val registro = ContentValues()
    registro.put("clave_sala", sala.claveSala)
    registro.put("nombre_sala", sala.nombreSala)

    db.insert("sala", null, registro)
}

fun getEstudianteDB(context: Context):ArrayList<String> {
    var lista = arrayListOf<String>()

    val admin = AdminSqlite(context, "estudiante", null, 1)
    val db = admin.writableDatabase
    val fila = db.rawQuery("select n_control,nombre,carrera,semestre,grupo from estudiante", null)
        while (fila.moveToNext()) {
            var contenido = "Número de control: ${fila.getString(0)} - Nombre: ${fila.getString(1)} - Carrera: ${fila.getString(2)} - Semestre: ${fila.getString(3)} - Grupo: ${fila.getString(4)}"
            lista.add(contenido)
        }
    db.close()

    return lista
}

fun getMateriaDB(context: Context):ArrayList<String> {
    var lista = arrayListOf<String>()

    val admin = AdminSqlite(context, "materia", null, 1)
    val db = admin.writableDatabase
    val fila = db.rawQuery("select clave_materia,nombre_materia,dia,hora_entrada,hora_salida from materia", null)
        while (fila.moveToNext()) {
            var contenido = "Clave de la materia: ${fila.getString(0)} - Nombre: ${fila.getString(1)} - Día: ${fila.getString(2)} - Hora de Entrada: ${fila.getString(3)} - Hora de salida: ${fila.getString(4)}"
            lista.add(contenido)
        }
    db.close()

    return lista
}

fun getSalaDB(context: Context):ArrayList<String> {
    var lista = arrayListOf<String>()

    val admin = AdminSqlite(context, "sala", null, 1)
    val db = admin.writableDatabase
    val fila = db.rawQuery("select clave_sala,nombre_sala from sala", null)
        while (fila.moveToNext()) {
            var contenido = "Clave de la Sala: ${fila.getString(0)} - Nombre: ${fila.getString(1)}"
            lista.add(contenido)
        }
    db.close()

    return lista
}