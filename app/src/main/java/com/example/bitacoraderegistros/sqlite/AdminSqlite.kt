package com.example.bitacoraderegistros.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class AdminSqlite (context: Context, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int)
    : SQLiteOpenHelper(context, name, factory, version){


    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("create table estudiante (n_control text primary key, nombre text, carrera text, semestre text, grupo text)")
        db!!.execSQL("create table materia (clave_materia text primary key, nombre_materia text, dia text, hora_entrada text, hora_salida text)")
        db!!.execSQL("create table sala (clave_sala text primary key, nombre_sala text)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}