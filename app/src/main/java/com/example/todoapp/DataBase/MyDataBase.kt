package com.example.todoapp.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Todo::class], version = 1)
abstract class MyDataBase:RoomDatabase() {
    abstract fun getTodeDao():TodoDao
companion object{
    var dataBase : MyDataBase?=null
    fun getInstance (context :Context):MyDataBase{
        if (dataBase==null){
            dataBase = Room.databaseBuilder(context,MyDataBase::class.java,"My DataBase")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }
        return dataBase!!
    }
}

}