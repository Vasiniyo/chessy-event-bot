package com.vasiniyo.app.render

import com.vasiniyo.app.domain.model.Board
import java.awt.image.BufferedImage

class BoardRenderService(private val piecePainter: PiecePainter) {
    fun getDailyChallenge(board: Board, settings: BoardSettings): BufferedImage {
        return ImageBoardBuilder(
                board.turn,
                settings.whiteTile,
                settings.blackTile,
                settings.tileSize,
                piecePainter
            )
            .withEmptyBoard()
            .withNumbers()
            .withPieces(board)
            .build()
    }
}
