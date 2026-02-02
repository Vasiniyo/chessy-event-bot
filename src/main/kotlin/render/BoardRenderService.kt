package com.vasiniyo.app.render

import com.vasiniyo.app.domain.model.Board
import java.awt.image.BufferedImage

class BoardRenderService(private val piecePainter: PiecePainter) {
    fun getDailyChallenge(board: Board, settings: BoardSettings): BufferedImage {
        return ImageBoardBuilder(settings.tileSize, piecePainter)
            .withEmptyBoard(settings.whiteTile, settings.blackTile)
            .withPieces(board)
            .build()
    }
}
