package com.vasiniyo.app.render

import com.vasiniyo.app.domain.model.Board
import com.vasiniyo.app.domain.model.File
import com.vasiniyo.app.domain.model.Position
import com.vasiniyo.app.domain.model.Rank
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
            val (x, y) = toXY(pos)
            val imgX = (x * tileSize) + tileSize / 6f
            val imgY = (y * tileSize) + tileSize * 0.75f
            val imgPiece = PieceRenderMapper.toStringPiece(piece)
            graphics2D.drawString(imgPiece, imgX, imgY)
        }
        graphics2D.font = oldFont
        graphics2D.color = oldColor
    }

    private fun toXY(position: Position): Pair<Int, Int> {
        val x =
            when (position.file) {
                File.A -> 0
                File.B -> 1
                File.C -> 2
                File.D -> 3
                File.E -> 4
                File.F -> 5
                File.G -> 6
                File.H -> 7
            }
        val y =
            when (position.rank) {
                Rank.R1 -> 7
                Rank.R2 -> 6
                Rank.R3 -> 5
                Rank.R4 -> 4
                Rank.R5 -> 3
                Rank.R6 -> 2
                Rank.R7 -> 1
                Rank.R8 -> 0
            }
        return x to y
    }
}
