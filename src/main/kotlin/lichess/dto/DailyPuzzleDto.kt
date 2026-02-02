package com.vasiniyo.app.lichess.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true) data class DailyPuzzleDto(val game: GameDto)

@JsonIgnoreProperties(ignoreUnknown = true) data class GameDto(val pgn: String)
