package com.pinheiro.desafiom2u.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pinheiro.desafiom2u.data.remote.FilmesRepository
import com.pinheiro.desafiom2u.data.remote.IFilmesRepository
import com.pinheiro.desafiom2u.data.remote.dto.FilmesDTO
import com.pinheiro.desafiom2u.data.remote.dto.Result
import com.pinheiro.desafiom2u.data.remote.dto.RetrofitClient
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FilmesViewModel(private val dispatcher: CoroutineDispatcher = Dispatchers.IO) : ViewModel() {

    private val repository: IFilmesRepository = FilmesRepository(RetrofitClient())

    private val _listarFilmesExibicao: MutableLiveData<FilmesDTO> = MutableLiveData()
    private val _listarFilmesLancamento: MutableLiveData<FilmesDTO> = MutableLiveData()
    var listaFilme: LiveData<FilmesDTO> = _listarFilmesExibicao
    var listaFilmeExibicao : LiveData<FilmesDTO> = _listarFilmesLancamento

    fun listarFilmesExibicao() {

        viewModelScope.launch(dispatcher) {
            repository.listarFilmesExbibicao().enqueue(object : Callback<FilmesDTO> {
                override fun onResponse(
                    call: Call<FilmesDTO>,
                    response: Response<FilmesDTO>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { list ->
                            _listarFilmesExibicao.postValue(list)
                        }
                    }
                }

                override fun onFailure(call: Call<FilmesDTO>, t: Throwable) {
                    Log.e(FilmesViewModel::class.java.name, t.toString())
                }

            })
        }
    }

    private val _listarFilmesEmBreve: MutableLiveData<FilmesDTO> = MutableLiveData()
    var listarFilmesLancamentos: LiveData<FilmesDTO> = _listarFilmesEmBreve

    fun listarFilmesLancamentos() {

        viewModelScope.launch(dispatcher) {
            repository.listarFilmesLancamentos().enqueue(object : Callback<FilmesDTO> {
                override fun onResponse(
                    call: Call<FilmesDTO>,
                    response: Response<FilmesDTO>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { list ->
                            _listarFilmesEmBreve.postValue(list)
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

