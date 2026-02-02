package com.vasiniyo.app.mapper

import com.vasiniyo.app.domain.model.Piece
import com.vasiniyo.app.domain.model.PieceColor
import com.vasiniyo.app.domain.model.PieceType

object PieceRenderMapper {
    private val pieces =
        mapOf(
            (PieceColor.WHITE to PieceType.KING) to "♔",
            (PieceColor.WHITE to PieceType.QUEEN) to "♕",
            (PieceColor.WHITE to PieceType.ROOK) to "♖",
            (PieceColor.WHITE to PieceType.BISHOP) to "♗",
            (PieceColor.WHITE to PieceType.KNIGHT) to "♘",
            (PieceColor.WHITE to PieceType.PAWN) to "♙",
            (PieceColor.BLACK to PieceType.KING) to "♚",
            (PieceColor.BLACK to PieceType.QUEEN) to "♛",
            (PieceColor.BLACK to PieceType.ROOK) to "♜",
            (PieceColor.BLACK to PieceType.BISHOP) to "♝",
            (PieceColor.BLACK to PieceType.KNIGHT) to "♞",
            (PieceColor.BLACK to PieceType.PAWN) to "♟",
        )

    fun toStringPiece(piece: Piece): String {
        return pieces[piece.color to piece.type] ?: ""
    }
}
