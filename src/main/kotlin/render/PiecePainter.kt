package com.vasiniyo.app.render

import com.vasiniyo.app.domain.model.Board
import com.vasiniyo.app.domain.model.PieceColor
import java.awt.Graphics2D

fun interface PiecePainter {
    fun paint(graphics2D: Graphics2D, board: Board, tileSize: Int, turn: PieceColor)
}
