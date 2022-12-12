package com.example.bitacoraderegistros.sqlite

import android.content.ContentValues
import android.content.Context
import com.example.bitacoraderegistros.models.Estudiante

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