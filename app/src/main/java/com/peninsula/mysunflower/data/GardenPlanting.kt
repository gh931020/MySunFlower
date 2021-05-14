package com.peninsula.mysunflower.data

import androidx.room.*
import java.util.*

/**
 * [GardenPlanting] represents when a user add a [Plant] to their garden, with useful metadata.
 * Properties such as [lastWateringDate] are used fo notifications ,such as when to water the plant
 *
 * Declaring the column info allows for the renaming of variables without implementing a
 * database migration, as the cloumn name would not change
 * @property plantId String
 * @property plantDate Calendar
 * @property lastWateringDate Calendar
 * @property gardenPlantId Long
 * @constructor
 */
@Entity(
    tableName = "garden_plantings",
    foreignKeys = [
        ForeignKey(entity = Plant::class, parentColumns = ["id"], childColumns = ["plant_id"])
    ],
    indices = [Index("plant_id")]
)
data class GardenPlanting(
    @ColumnInfo(name = "plant_id")
    val plantId: String,
    /**
     * Indicates when the [Plant] was planted. Used for showing notification when it's time
     * to harvest the plant.
     */
    @ColumnInfo(name = "plant_date")
    val plantDate: Calendar = Calendar.getInstance(),
    /**
     * Indicates when the [Plant] was last wartered. used for showing notification when it's
     * time to water the plant.
     */
    @ColumnInfo(name = "last_watering_date")
    val lastWateringDate: Calendar = Calendar.getInstance()
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var gardenPlantId: Long = 0
}
