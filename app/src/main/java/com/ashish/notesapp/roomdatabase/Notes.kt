package com.ashish.notesapp.roomdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Entity(tableName = "Notes_table")
data class Notes(
    @PrimaryKey(autoGenerate = true) val id:Int?=null,
    @ColumnInfo(name = "title") var title:String?,
    //@ColumnInfo(name = "date_time") var dateTime: Long?=null,
    @ColumnInfo(name = "description") var description:String?,

):Serializable
