package uz.isystem.data.network

import retrofit2.adapter.rxjava3.Result
import retrofit2.http.GET

interface DetailService {

    @GET("3/movie{}")
    suspend fun getMovie()

}