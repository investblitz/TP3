package db

import Dao.cidadedao
import Entity.cidade
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch



@Database(entities = arrayOf(cidade::class), version = 8, exportSchema = false)
public abstract class Db: RoomDatabase() {

    abstract fun cidadedao(): cidadedao

    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var cidadedao = database.cidadedao()

                    // Delete all content here.
                    cidadedao.deleteAll()

                    // Add sample cities.
                    var cidade = cidade(1, "Viana do Castelo", "Portugal")
                    cidadedao.insert(cidade)
                    cidade = cidade(2, "Porto", "Portugal")
                    cidadedao.insert(cidade)
                    cidade = cidade(3, "Aveiro", "Portugal")
                    cidadedao.insert(cidade)

                }
            }
        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: Db? = null

        fun getDatabase(context: Context, scope: CoroutineScope): Db {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    Db::class.java,
                    "cities_database",
                )
                    //estratégia de destruição
                    .fallbackToDestructiveMigration()
                    .addCallback(WordDatabaseCallback(scope))
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}
