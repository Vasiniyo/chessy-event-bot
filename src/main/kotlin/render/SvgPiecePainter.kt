package com.vasiniyo.app.render

import com.vasiniyo.app.domain.model.Board
import com.vasiniyo.app.domain.model.PieceColor
import com.vasiniyo.app.mapper.BoardMapper
import java.awt.Graphics2D
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO
import org.apache.batik.transcoder.TranscoderInput
import org.apache.batik.transcoder.TranscoderOutput
import org.apache.batik.transcoder.image.PNGTranscoder

class SvgPiecePainter : PiecePainter {
    override fun paint(graphics2D: Graphics2D, board: Board, tileSize: Int, turn: PieceColor) {
        board.pieces.entries.forEach { (pos, piece) ->
            val (x, y) = BoardMapper.toXY(pos, turn)
            val svg = SvgPieceResolver().resolve(piece)
            val pieceSize = tileSize.toFloat() - tileSize.toFloat() / 5
            val imgX = (x * tileSize) + (tileSize - pieceSize) / 2
            val imgY = (y * tileSize) + (tileSize - pieceSize) / 2
            val image = renderSvg(svg, pieceSize, pieceSize)
            graphics2D.drawImage(image, imgX.toInt(), imgY.toInt(), null)
        }
    }

    private fun renderSvg(svgBytes: ByteArray, width: Float, height: Float): BufferedImage {
        val transcoder =
            PNGTranscoder().apply {
                addTranscodingHint(PNGTranscoder.KEY_WIDTH, width)
                addTranscodingHint(PNGTranscoder.KEY_HEIGHT, height)
            }
        val outputStream = ByteArrayOutputStream()
        transcoder.transcode(
            TranscoderInput(ByteArrayInputStream(svgBytes)),
            TranscoderOutput(outputStream)
        )
        return ImageIO.read(ByteArrayInputStream(outputStream.toByteArray()))
    }
}
