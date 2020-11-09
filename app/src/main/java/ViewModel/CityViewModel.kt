package ViewModel

import Entity.cidade
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import db.Db
import db.dbrepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CityViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: dbrepository
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allCities: LiveData<List<cidade>>

    init {
            val cidadedao = Db.getDatabase(application, viewModelScope).cidadedao()
        repository = dbrepository(cidadedao)
        allCities = repository.allCities
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(cidade: cidade) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(cidade)
    }

    // delete all
    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }

    // delete by city
    fun deleteByCity(cidade: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteByCity(cidade)
    }

    fun getCitiesByCountry(pais: String): LiveData<List<cidade>> {
        return repository.getCitiesByCountry(pais)
    }

    fun getCountryFromCity(city: String): LiveData<cidade> {
        return repository.getCountryFromCity(city)
    }

    fun updateCity(cidade: cidade) = viewModelScope.launch {
        repository.updateCity(cidade)
    }

    fun updateCountryFromCity(cidade: String, country: String) = viewModelScope.launch {
        repository.updateCountryFromCity(cidade, country)
    }
}
