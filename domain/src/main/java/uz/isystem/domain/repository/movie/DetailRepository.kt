package uz.isystem.domain.repository.movie

import uz.isystem.utills.ResultWrapper

interface DetailRepository<T,E,S,R,I,A,C> {

    suspend fun getMovie(lang: String, apiKey: String, id: Int): ResultWrapper<T?, Any?>

    suspend fun getVideo(id: Int, apiKey: String, lang: String) : ResultWrapper<E?, Any>

    suspend fun getMovieCrew(id: Int, apiKey: String, lang: String) : ResultWrapper<S?, Any>

    suspend fun getRecomm(lang: String, apiKey: String, id: Int): ResultWrapper<R?, Any?>

    suspend fun getSimilar(id: Int, apiKey: String, lang: String) : ResultWrapper<I?, Any>

    suspend fun getSeriesDetail(lang: String, apiKey: String, id:Int): ResultWrapper<A?, Any?>

    suspend fun getImages(id: Int, apiKey: String) : ResultWrapper<C?, Any>




}