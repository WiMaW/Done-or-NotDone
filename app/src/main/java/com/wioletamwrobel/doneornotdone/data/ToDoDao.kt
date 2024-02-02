package com.wioletamwrobel.doneornotdone.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ToDoDao {

    @Insert
    fun createToDo(toDo: ToDo)

    @Query("SELECT * FROM to_do")
    fun getAllToDo(): List<ToDo>

    @Update
    fun updateToDo(toDo: ToDo)

    @Delete
    fun deleteToDo(toDo: ToDo)

}