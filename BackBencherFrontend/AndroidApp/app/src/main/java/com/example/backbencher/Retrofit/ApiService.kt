package com.example.backbencher.Retrofit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/fetch_subtitles")
    suspend fun fetchData(@Query("video_url") query: String): Response<String>

    @GET("/fetch_notes")
    suspend fun fetchDataNotes(@Query("video_url") query: String): Response<String>

    @GET("/fetch_summary")
    suspend fun fetchDataSummary(@Query("video_url") query: String): Response<String>
}