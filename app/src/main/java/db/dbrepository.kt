package db

import Dao.notadao
import Entity.nota
import androidx.lifecycle.LiveData

class dbrepository(private val notadao: notadao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allCities: LiveData<List<nota>> = notadao.getAllnotas()


    fun getdiaByCountry(dia: String): LiveData<List<nota>> {
        return notadao.getBydia(dia)
    }

    fun getFromnota(nota: String): LiveData<nota> {
        return notadao.getFromnota(nota)
    }

    suspend fun insert(nota: nota) {
        notadao.insert(nota)
    }

    suspend fun deleteAll(){
        notadao.deleteAll()
    }

    suspend fun deleteBynota(cidade: String){
        notadao.deleteBynota(cidade)
    }

    suspend fun updatenota(cidade: nota) {
        notadao.updatenota(cidade)
    }

    suspend fun updateCountryFromnota(city: String, country: String){
        notadao.updateCountryFromnota(city, country)
    }

}