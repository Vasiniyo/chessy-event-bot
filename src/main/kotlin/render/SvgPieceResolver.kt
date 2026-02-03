package com.vasiniyo.app.render

import com.vasiniyo.app.domain.model.Piece
import com.vasiniyo.app.domain.model.PieceColor
import com.vasiniyo.app.domain.model.PieceType

class SvgPieceResolver {
    private val svgPieces =
        mapOf(
            (PieceColor.WHITE to PieceType.KING) to "White_King",
            (PieceColor.WHITE to PieceType.QUEEN) to "White_Queen",
            (PieceColor.WHITE to PieceType.ROOK) to "White_Rook",
            (PieceColor.WHITE to PieceType.BISHOP) to "White_Bishop",
            (PieceColor.WHITE to PieceType.KNIGHT) to "White_Knight",
            (PieceColor.WHITE to PieceType.PAWN) to "White_Pawn",
            (PieceColor.BLACK to PieceType.KING) to "Black_King",
            (PieceColor.BLACK to PieceType.QUEEN) to "Black_Queen",
            (PieceColor.BLACK to PieceType.ROOK) to "Black_Rook",
            (PieceColor.BLACK to PieceType.BISHOP) to "Black_Bishop",
            (PieceColor.BLACK to PieceType.KNIGHT) to "Black_Knight",
            (PieceColor.BLACK to PieceType.PAWN) to "Black_Pawn",
        )

    fun resolve(piece: Piece): ByteArray {
        val pieceName = svgPieces[piece.color to piece.type] ?: error("SVG not found for $piece")
        return javaClass.getResourceAsStream("/chess-pieces/default/$pieceName.svg")?.readBytes()
            ?: error("SVG not found for $piece")
    }
}
