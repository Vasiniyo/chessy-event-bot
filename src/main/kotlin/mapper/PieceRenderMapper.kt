package com.vasiniyo.app.mapper

import com.vasiniyo.app.domain.model.File
import com.vasiniyo.app.domain.model.Piece
import com.vasiniyo.app.domain.model.PieceColor
import com.vasiniyo.app.domain.model.PieceType
import com.vasiniyo.app.domain.model.Rank

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

    fun toStringFile(file: File, turn: PieceColor): String {
        return when (turn) {
            PieceColor.WHITE ->
                when (file) {
                    File.A -> "a"
                    File.B -> "b"
                    File.C -> "c"
                    File.D -> "d"
                    File.E -> "e"
                    File.F -> "f"
                    File.G -> "g"
                    File.H -> "h"
                }
            PieceColor.BLACK -> {
                when (file) {
                    File.A -> "h"
                    File.B -> "g"
                    File.C -> "f"
                    File.D -> "e"
                    File.E -> "d"
                    File.F -> "c"
                    File.G -> "b"
                    File.H -> "a"
                }
            }
        }
    }

    fun toStringRank(rank: Rank, turn: PieceColor): String {
        return when (turn) {
            PieceColor.WHITE ->
                when (rank) {
                    Rank.R1 -> "1"
                    Rank.R2 -> "2"
                    Rank.R3 -> "3"
                    Rank.R4 -> "4"
                    Rank.R5 -> "5"
                    Rank.R6 -> "6"
                    Rank.R7 -> "7"
                    Rank.R8 -> "8"
                }
            PieceColor.BLACK -> {
                when (rank) {
                    Rank.R1 -> "8"
                    Rank.R2 -> "7"
                    Rank.R3 -> "6"
                    Rank.R4 -> "5"
                    Rank.R5 -> "4"
                    Rank.R6 -> "3"
                    Rank.R7 -> "2"
                    Rank.R8 -> "1"
                }
            }
        }
    }
}
