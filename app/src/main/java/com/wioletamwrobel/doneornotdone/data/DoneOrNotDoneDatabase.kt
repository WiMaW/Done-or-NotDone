package com.wioletamwrobel.doneornotdone.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ToDo::class], version = 1)
abstract class DoneOrNotDoneDatabase : RoomDatabase() {

    abstract fun getToDoDao() : ToDoDao

    companion object {

        @Volatile //thread safety
        private var DATABASE_INSTANCE: DoneOrNotDoneDatabase? = null

        fun getDatabase(context: Context): DoneOrNotDoneDatabase {

            //synchronized do not allow this same code to run on multiple threads
            return DATABASE_INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    DoneOrNotDoneDatabase::class.java,
                    "done_or_not_done_database"
                ).build()
                DATABASE_INSTANCE = instance
                instance
            }
        }
    }
}
