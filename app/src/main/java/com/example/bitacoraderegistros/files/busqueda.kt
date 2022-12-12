package com.example.bitacoraderegistros.files

import com.example.bitacoraderegistros.models.Estudiante
import com.example.bitacoraderegistros.models.Materia

fun searchEstudiante(numControl: String, lista: MutableList<Estudiante>): Estudiante{
    var estudiante: Estudiante = Estudiante("","","",0,"")
    for ((i, valor) in lista.withIndex()){
        if (valor.nControl == numControl){
            estudiante.nControl = valor.nControl
            estudiante.nombreCompleto = valor.nombreCompleto
            estudiante.carrera = valor.carrera
            estudiante.semestre = valor.semestre
            estudiante.grupo = valor.grupo
        }
    }

    return estudiante
}

fun searchMateria(claveMateria: String, lista: MutableList<Materia>): Materia{
    var materia: Materia = Materia("","","","","")
    for ((i, valor) in lista.withIndex()){
        if (valor.claveMateria == claveMateria){
            materia.claveMateria = valor.claveMateria
            materia.nombreMateria = valor.nombreMateria
            materia.dia = valor.dia
            materia.horaEntrada = valor.horaEntrada
            materia.horaSalida = valor.horaSalida
        }
    }

    return materia
}