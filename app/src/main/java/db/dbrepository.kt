package db

import Dao.cidadedao
import Entity.cidade
import androidx.lifecycle.LiveData

class dbrepository(private val cidadedao: cidadedao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allCities: LiveData<List<cidade>> = cidadedao.getAllCities()


    fun getCitiesByCountry(pais: String): LiveData<List<cidade>> {
        return cidadedao.getCitiesByCountry(pais)
    }

    fun getCountryFromCity(cidade: String): LiveData<cidade> {
        return cidadedao.getCountryFromCity(cidade)
    }

    suspend fun insert(cidade: cidade) {
        cidadedao.insert(cidade)
    }

    suspend fun deleteAll(){
        cidadedao.deleteAll()
    }

    suspend fun deleteByCity(cidade: String){
        cidadedao.deleteByCity(cidade)
    }

    suspend fun updateCity(cidade: cidade) {
        cidadedao.updateCity(cidade)
    }

    suspend fun updateCountryFromCity(city: String, country: String){
        cidadedao.updateCountryFromCity(city, country)
    }

}