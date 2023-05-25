package com.sample.simpsonsviewer.repository.apis

import com.sample.simpsonsviewer.entities.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterAPI {

    @GET("/")
    suspend fun getCharacterResult(
        @Query("q", encoded = true) q: String,
        @Query("format") format: String
    ): Response.CharacterResult
}