package com.example.retrofitfilm.services

import com.example.retrofitfilm.models.MovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface MovieApiInterface {
    @GET("/3/movie/popular?api_key=01fc4a6b68277884f109cb68d3e0b5d2")
    fun getmovielist(): Call<MovieResponse>
}