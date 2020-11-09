package Dao


import Entity.cidade
import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface cidadedao {


        @Query("SELECT * from db ORDER BY db_cidade ASC")
        fun getAllCities(): LiveData<List<cidade>>

        @Query("SELECT * FROM db WHERE db_pais== :pais")
        fun getCitiesByCountry(pais: String): LiveData<List<cidade>>

        @Query("SELECT * FROM db WHERE db_cidade== :cidade")
        fun getCountryFromCity(cidade: String): LiveData<cidade>

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun insert(cidade:cidade)

        @Update
        suspend fun updateCity(cidade: cidade)

        @Query("DELETE FROM db")
        suspend fun deleteAll()

        @Query("DELETE FROM db where db_cidade == :cidade")
        suspend fun deleteByCity(cidade: String)

        @Query("UPDATE db SET db_pais=:country WHERE db_cidade == :cidade")
        suspend fun updateCountryFromCity(cidade: String, country: String)
    }