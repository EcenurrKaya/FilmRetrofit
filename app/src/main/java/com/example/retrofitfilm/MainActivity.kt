package com.example.retrofitfilm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.ProxyFileDescriptorCallback
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitfilm.databinding.ActivityMainBinding
import com.example.retrofitfilm.models.Movie
import com.example.retrofitfilm.models.MovieResponse
import com.example.retrofitfilm.services.MovieApiInterface
import com.example.retrofitfilm.services.MovieApiService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        rv_movie_list.layoutManager=LinearLayoutManager(this)
        rv_movie_list.setHasFixedSize(true)
        getmovie { movies:List<Movie>->
            rv_movie_list.adapter=MovieAdapter(movies)
        }
    }

    private fun getmovie(callback: (List<Movie>)->Unit){
        val apiService = MovieApiService.getinstance().create(MovieApiInterface::class.java)
        apiService.getmovielist().enqueue(object: Callback<MovieResponse>{
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                return callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {

            }

        })
    }
}