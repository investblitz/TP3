package Dao


import Entity.nota
import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface notadao {


        @Query("SELECT * from db ORDER BY db_nota ASC")
        fun getAllnotas(): LiveData<List<nota>>

        @Query("SELECT * FROM db WHERE db_dia== :dia")
        fun getBydia(dia: String): LiveData<List<dia>>

        @Query("SELECT * FROM db WHERE db_dia== :dia")
        fun getdiaFrom(dia: String): LiveData<dia>

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun insert(nota:nota)

        @Update
        suspend fun updateCity(nota: nota)

        @Query("DELETE FROM db")
        suspend fun deleteAll()

        @Query("DELETE FROM db where db_nota == :nota")
        suspend fun deleteBynota(cidade: String)

        @Query("UPDATE db SET db_dia=:dia WHERE db_nota == :nota")
        suspend fun updateCountryFromnota(nota: String, country: String)
        abstract fun getFromnota(nota: String): LiveData<nota>
        fun updatenota(cidade: Entity.nota)
}