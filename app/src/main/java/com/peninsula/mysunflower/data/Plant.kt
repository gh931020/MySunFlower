package com.peninsula.mysunflower.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
import java.util.Calendar.DAY_OF_YEAR
import kotlin.math.sin

@Entity(tableName = "plants")
data class Plant(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val plantId: String,
    val name: String,
    val description: String,
    val growZoneNumber: Int,
    /**
     * 浇水周期(天)
     */
    val wateringInterval: Int = 7,
    val imageUrl: String = ""
) {
    /**
     * Determines if the plant should be watered.  Returns true if [since]'s date > date of last
     * watering + watering Interval; false otherwise.
     */
    fun shouldBeWatered(since: Calendar, lastWaterDate: Calendar)=
        since > lastWaterDate.apply { add(DAY_OF_YEAR, wateringInterval) }

    override fun toString(): String {
        return name
    }
}
