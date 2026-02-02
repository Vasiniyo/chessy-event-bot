package com.vasiniyo.app.mapper

import com.github.bhlangonijr.chesslib.Side
import com.vasiniyo.app.domain.model.Board
import com.vasiniyo.app.domain.model.File
import com.vasiniyo.app.domain.model.Piece
import com.vasiniyo.app.domain.model.PieceColor
import com.vasiniyo.app.domain.model.PieceType
import com.vasiniyo.app.domain.model.Position
import com.vasiniyo.app.domain.model.Rank

object BoardMapper {
    fun toBoard(board: com.github.bhlangonijr.chesslib.Board): Board {
        return Board(
            buildMap {
                board.boardToArray().mapIndexed { index, piece ->
                    if (piece.pieceType != null) {
                        put(
                            indexToPosition(index),
                            Piece(
                                toPieceType(piece.pieceType),
                                toPieceSide(piece.pieceSide),
                            )
                        )
                    }
                }
            }
        )
    }

    fun toPieceType(pieceType: com.github.bhlangonijr.chesslib.PieceType): PieceType {
        return when (pieceType) {
            com.github.bhlangonijr.chesslib.PieceType.KING -> PieceType.KING
            com.github.bhlangonijr.chesslib.PieceType.QUEEN -> PieceType.QUEEN
            com.github.bhlangonijr.chesslib.PieceType.ROOK -> PieceType.ROOK
            com.github.bhlangonijr.chesslib.PieceType.BISHOP -> PieceType.BISHOP
            com.github.bhlangonijr.chesslib.PieceType.KNIGHT -> PieceType.KNIGHT
            com.github.bhlangonijr.chesslib.PieceType.PAWN -> PieceType.PAWN
            com.github.bhlangonijr.chesslib.PieceType.NONE -> PieceType.NONE
        }
    }

    fun toPieceSide(pieceSide: Side): PieceColor {
        return when (pieceSide) {
            Side.WHITE -> PieceColor.WHITE
            Side.BLACK -> PieceColor.BLACK
        }
    }

    private fun indexToPosition(index: Int): Position {
        val file = File.entries[index % 8]
        val rank = Rank.entries[index / 8]
        return Position(file, rank)
    }
}
