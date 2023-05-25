package com.sample.simpsonsviewer.repository

import com.sample.simpsonsviewer.entities.DataResult
import com.sample.simpsonsviewer.entities.Response
import com.sample.simpsonsviewer.repository.apis.CharacterAPI
import com.sample.simpsonsviewer.utility.Constants
import java.lang.Exception

class CharacterRepository(private val api: CharacterAPI) {

    suspend fun getCharacterResult(): DataResult<List<Response.CharacterItem>> {
        return try {
            val characterResult = api.getCharacterResult(
                q = Constants.q,
                format = Constants.JSON
            )
            DataResult.Success(characterResult.results)
        } catch (e: Exception) {
            DataResult.Failure(e)
        }
    }
}