package com.example.hilt_practice.network

import com.example.hilt_practice.network.model.BlogData
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Flowable
import io.reactivex.Observable
import org.intellij.lang.annotations.Flow
import retrofit2.http.GET

interface NetworkService {

    @GET("blogs")
    fun getBlogs(): Flowable<List<BlogData>>
}