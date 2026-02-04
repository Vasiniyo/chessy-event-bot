package com.vasiniyo.app.render

import com.vasiniyo.app.domain.model.Board
import com.vasiniyo.app.domain.model.File
import com.vasiniyo.app.domain.model.PieceColor
import com.vasiniyo.app.domain.model.Position
import com.vasiniyo.app.domain.model.Rank
import com.vasiniyo.app.mapper.PieceRenderMapper
import java.awt.Color
import java.awt.Font
import java.awt.image.BufferedImage
import okhttp3.internal.immutableListOf

class ImageBoardBuilder(
    private val turn: PieceColor,
    private val whiteTile: Color,
    private val blackTile: Color,
    private val lastTurnWhiteTitle: Color,
    private val lastTurnBlackTitle: Color,
    private val tileSize: Int,
    private val piecePainter: PiecePainter
) {
    private val imgWidth = File.entries.size * tileSize
    private val imgHeight = Rank.entries.size * tileSize
    private val image: BufferedImage =
        BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB)
    private val graphics2D = image.createGraphics()

    fun withNumbers(): ImageBoardBuilder {
        val oldFont = graphics2D.font
        val oldColor = graphics2D.color
        graphics2D.font = Font("Arial", Font.BOLD, tileSize / 5)
        graphics2D.color = whiteTile
        File.entries.forEach { file ->
            val (x, y) = file.ordinal to Rank.entries.size - 1
            val imgX = (x * tileSize) + tileSize / 15f
            val imgY = (y * tileSize) + tileSize * 0.95f
            val fileStr = PieceRenderMapper.toStringFile(file, turn)
            graphics2D.drawString(fileStr, imgX, imgY)
            graphics2D.color = if (graphics2D.color == whiteTile) blackTile else whiteTile
        }
        graphics2D.color = blackTile
        Rank.entries.forEachIndexed { index, rank ->
            val x = File.entries.size - 1
            val y = Rank.entries.size - index - 1
            val imgX = (x * tileSize) + tileSize * 0.8f
            val imgY = (y * tileSize) + tileSize / 5f
            val rankStr = PieceRenderMapper.toStringRank(rank, turn)
            graphics2D.drawString(rankStr, imgX, imgY)
            graphics2D.color = if (graphics2D.color == whiteTile) blackTile else whiteTile
        }
        graphics2D.font = oldFont
        graphics2D.color = oldColor
        return this
    }

    fun withEmptyBoard(lastTurn: List<Position>): ImageBoardBuilder {
        val colors = immutableListOf(blackTile, whiteTile)
        val lastTurnColors = immutableListOf(lastTurnBlackTitle, lastTurnWhiteTitle)
        Rank.entries.forEach { rank ->
            File.entries.forEach { file ->
                val color = colors[(rank.ordinal + file.ordinal) % 2]
                drawSquare(file, rank, color)
            }
        }
        lastTurn.forEach {
            val color = lastTurnColors[(it.rank.ordinal + it.file.ordinal) % 2]
            drawSquare(it.file, it.rank, color)
        }
        return this
    }

    fun withPieces(
        board: Board,
    ): ImageBoardBuilder {
        piecePainter.paint(graphics2D, board, tileSize, turn)
        return this
    }

    fun build(): BufferedImage {
        graphics2D.dispose()
        return image
    }

    private fun drawSquare(file: File, rank: Rank, color: Color) {
        val oldColor = graphics2D.color
        graphics2D.color = color
        when (turn) {
            PieceColor.WHITE -> {
                val x = file.ordinal * tileSize
                val y = (Rank.entries.size - rank.ordinal - 1) * tileSize
                graphics2D.fillRect(x, y, tileSize, tileSize)
            }
            PieceColor.BLACK -> {
                val x = (File.entries.size - file.ordinal - 1) * tileSize
                val y = rank.ordinal * tileSize
                graphics2D.fillRect(x, y, tileSize, tileSize)
            }
        }
        graphics2D.color = oldColor
    }
}
