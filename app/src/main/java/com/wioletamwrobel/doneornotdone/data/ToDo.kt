package com.wioletamwrobel.doneornotdone.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "to_do")
data class ToDo(
    @ColumnInfo(name = "to_do_id")@PrimaryKey(autoGenerate = true) val toDoId: Int = 0,
    val title: String,
    val description: String? = null,
    @ColumnInfo(name = "is_starred") val isStarred: Boolean = false,

)
