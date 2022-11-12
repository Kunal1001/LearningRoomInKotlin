package com.example.writtingroombykunalkumar

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(notes: Notes)
    @Delete
    suspend fun delete(notes: Notes)
    @Query("select * from notes_table order by id asc")
    fun getData() : LiveData<List<Notes>>
}