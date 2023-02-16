package com.example.todoapp.DataBase

import androidx.room.*

@Dao
interface TodoDao {
    @Insert
    fun addTodo(todo:Todo)
    @Delete
    fun deleteTodo(todo:Todo)
    @Update
    fun upDateTodo(todo:Todo)
    @Query("select * from Todo")
    fun getTodos():List<Todo>
    @Query("select * from Todo where date = :date")
    fun getTodoByDate(date : Long):List<Todo>
}