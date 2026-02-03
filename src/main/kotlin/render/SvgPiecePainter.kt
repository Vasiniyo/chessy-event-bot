package com.vasiniyo.app.render

import com.vasiniyo.app.domain.model.Board
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
    override fun paint(graphics2D: Graphics2D, board: Board, tileSize: Int) {
        board.pieces.entries.forEach { (pos, piece) ->
            val (x, y) = BoardMapper.toXY(pos)
            val svg = SvgPieceResolver().resolve(piece)
            val imgX = (x * tileSize)
            val imgY = (y * tileSize)
            val image = renderSvg(svg, tileSize.toFloat(), tileSize.toFloat())
            graphics2D.drawImage(image, imgX, imgY, null)
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
