package com.vasiniyo.app.lichess

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.vasiniyo.app.lichess.dto.DailyPuzzleDto
import okhttp3.OkHttpClient
import okhttp3.Request

class LichessApiService(private val client: OkHttpClient) {
    private val dailyPuzzle = "https://lichess.org/api/puzzle/daily"

    fun fetchDailyPuzzlePgn(): DailyPuzzleDto {
        val request =
            Request.Builder().url(dailyPuzzle).header("Accept", "application/json").build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) {
                error("HTTP ${response.code}")
            }
            val body = response.body!!.string()
            val dto = jacksonObjectMapper().readValue(body, DailyPuzzleDto::class.java)
            return dto
        }
    }
}
