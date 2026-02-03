package com.vasiniyo.app.render

import com.vasiniyo.app.domain.model.Board
import com.vasiniyo.app.mapper.BoardMapper
import com.vasiniyo.app.mapper.PieceRenderMapper
import java.awt.Color
import java.awt.Font
import java.awt.Graphics2D

class TextPiecePainter : PiecePainter {
    override fun paint(graphics2D: Graphics2D, board: Board, tileSize: Int) {
        val oldFont = graphics2D.font
        val oldColor = graphics2D.color
        graphics2D.font = Font("Serif", Font.PLAIN, 48)
        graphics2D.color = Color.BLACK
        board.pieces.entries.forEach { (pos, piece) ->
            val (x, y) = BoardMapper.toXY(pos)
            val imgX = (x * tileSize) + tileSize / 6f
            val imgY = (y * tileSize) + tileSize * 0.75f
            val imgPiece = PieceRenderMapper.toStringPiece(piece)
            graphics2D.drawString(imgPiece, imgX, imgY)
        }
        graphics2D.font = oldFont
        graphics2D.color = oldColor
    }
}
