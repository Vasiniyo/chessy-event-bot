package com.vasiniyo.app.lichess.dto

import com.vasiniyo.app.render.BoardSettings

data class LichessDailyRequest(val san: String, val settings: BoardSettings)
