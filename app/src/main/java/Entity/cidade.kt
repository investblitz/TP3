package Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "db")

class cidade(
    // Int? = null so when creating instance id has not to be passed as argument
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "db_cidade") val cidade: String,
    @ColumnInfo(name = "db_pais") val pais: String
)