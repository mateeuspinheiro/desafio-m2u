package com.pinheiro.desafiom2u.data.remote

import com.pinheiro.desafiom2u.data.remote.dto.FilmesDTO
import com.pinheiro.desafiom2u.data.remote.dto.Result
import com.pinheiro.desafiom2u.data.remote.dto.RetrofitClient
import retrofit2.Call

class FilmesRepository(private val retrofitClient: RetrofitClient) : IFilmesRepository {
    override fun listarFilmesExbibicao(): Call<FilmesDTO> =
        retrofitClient.getInstance().getFilmesAPI().listarFilmesExbibicao()

    override fun listarFilmesRated(): Call<FilmesDTO> =
        retrofitClient.getInstance().getFilmesAPI().listarFilmesRated()


    override fun listarFilmesLancamentos() : Call<FilmesDTO> =
        retrofitClient.getInstance().getFilmesAPI().listarFilmesLancamentos()


    override fun listarFilmesPopular(): Call<FilmesDTO> =
        retrofitClient.getInstance().getFilmesAPI().listarFilmesPopular()


    override fun likeFilmes(id: Int): Call<FilmesDTO> =
        retrofitClient.getInstance().getFilmesAPI().likeFilmes(id)

    override fun listarImagensFilmes(id: Int): Call<FilmesDTO> =
        retrofitClient.getInstance().getFilmesAPI().likeFilmes(id)

    override fun listarComentariosFilmes(id: Int): Call<FilmesDTO> =
        retrofitClient.getInstance().getFilmesAPI().likeFilmes(id)

}