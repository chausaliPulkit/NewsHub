package com.example.newshub.data.db

import androidx.room.TypeConverter
import com.example.newshub.data.model.Source

class Converters {

    //    when we save Article data to database room will only save its name property in table article
    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name
    }

    //    when we are retrieving data from database table room will use this function to return source object
    @TypeConverter
    fun toSource(name: String): Source {
//        using name for both id and name field
        return Source(name, name)
    }
}