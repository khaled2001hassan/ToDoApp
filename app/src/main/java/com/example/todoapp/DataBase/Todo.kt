package com.example.todoapp.DataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val id:Int?=null,
    @ColumnInfo
    val title:String?=null,
    @ColumnInfo
    val description:String?=null,
    @ColumnInfo
    val date:Long?=null,
    @ColumnInfo
    val isDone:Boolean?=null
)
