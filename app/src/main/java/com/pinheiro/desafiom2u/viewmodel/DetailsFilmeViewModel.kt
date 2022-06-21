package com.pinheiro.desafiom2u.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pinheiro.desafiom2u.data.remote.FilmesRepository
import com.pinheiro.desafiom2u.data.remote.IFilmesRepository
import com.pinheiro.desafiom2u.data.remote.dto.FilmesDTO
import com.pinheiro.desafiom2u.data.remote.dto.RetrofitClient
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsFilmeViewModel(private val dispatcher: CoroutineDispatcher = Dispatchers.IO) : ViewModel() {

    private val repository: IFilmesRepository = FilmesRepository(RetrofitClient())

    private val _listarMaisComoEsse: MutableLiveData<FilmesDTO> = MutableLiveData()
    var listarMaisComoEsse: LiveData<FilmesDTO> = _listarMaisComoEsse

    fun listarMaisComoEsse(id: Int) {

            viewModelScope.launch(dispatcher) {
                repository.likeFilmes(id).enqueue(object : Callback<FilmesDTO> {
                    override fun onResponse(
                        call: Call<FilmesDTO>,
                        response: Response<FilmesDTO>
                    ) {
                        if (response.isSuccessful) {
                            response.body()?.let { list ->
                                _listarMaisComoEsse.postValue(list)
                            }
                        }
                    }

                    override fun onFailure(call: Call<FilmesDTO>, t: Throwable) {
                        Log.e(FilmesViewModel::class.java.name, t.toString())
                    }

                })
            }
    }
}