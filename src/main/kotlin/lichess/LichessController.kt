package com.vasiniyo.app.lichess

import com.vasiniyo.app.lichess.dto.LichessDailyRequest
import com.vasiniyo.app.lichess.dto.LichessDailyResponse
import com.vasiniyo.app.model.BoardFactory
import com.vasiniyo.app.render.BoardRenderService

class LichessController(
    private val boardService: BoardRenderService,
    private val boardFactory: BoardFactory
) {
    fun getDailyChallenge(request: LichessDailyRequest): LichessDailyResponse {
        val board = boardFactory.fromSan(request.san)
        val settings = request.settings
        val image = boardService.getDailyChallenge(board, settings)
        return LichessDailyResponse(image)
    }
}
