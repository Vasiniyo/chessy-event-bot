package com.vasiniyo.app.render

import com.vasiniyo.app.domain.model.Board
import java.awt.image.BufferedImage

class BoardRenderService(private val piecePainter: PiecePainter) {
    private val cache: MutableMap<Board, BufferedImage> = mutableMapOf()

    fun getDailyChallenge(board: Board, settings: BoardSettings): BufferedImage {
        val cached = cache[board]
        if (cached != null) {
            return cached
        }
        val image =
            ImageBoardBuilder(
                    board.turn,
                    settings.whiteTile,
                    settings.blackTile,
                    settings.lastTurnWhiteTile,
                    settings.lastTurnBlackTile,
                    settings.tileSize,
                    piecePainter
                )
                .withEmptyBoard(board.lastTurn)
                .withNumbers()
                .withPieces(board)
                .build()
        cache[board] = image
        return image
    }
}
