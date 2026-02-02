package com.vasiniyo.app.render

import com.vasiniyo.app.domain.model.Board
import com.vasiniyo.app.domain.model.File
import com.vasiniyo.app.domain.model.Rank
import java.awt.Color
import java.awt.image.BufferedImage
import okhttp3.internal.immutableListOf

class ImageBoardBuilder(private val tileSize: Int, private val piecePainter: PiecePainter) {
    private val imgWidth = File.entries.size * tileSize
    private val imgHeight = Rank.entries.size * tileSize
    private val image: BufferedImage =
        BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB)
    private val graphics2D = image.createGraphics()

    fun withEmptyBoard(whiteTile: Color, blackTile: Color): ImageBoardBuilder {
        val colors = immutableListOf(whiteTile, blackTile)
        Rank.entries.forEach { rank ->
            File.entries.forEach { file ->
                graphics2D.color = colors[(rank.ordinal + file.ordinal) % 2]
                val x = file.ordinal * tileSize
                val y = rank.ordinal * tileSize
                graphics2D.fillRect(x, y, tileSize, tileSize)
            }
        }
        return this
    }

    fun withPieces(
        board: Board,
    ): ImageBoardBuilder {
        piecePainter.paint(graphics2D, board, tileSize)
        return this
    }

    fun build(): BufferedImage {
        graphics2D.dispose()
        return image
    }
}
