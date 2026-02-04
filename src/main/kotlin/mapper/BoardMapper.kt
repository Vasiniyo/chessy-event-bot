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
            },
            turn =
                when (board.sideToMove) {
                    Side.WHITE -> PieceColor.WHITE
                    Side.BLACK -> PieceColor.BLACK
                },
            lastTurn =
                listOf(
                    indexToPosition(board.backup.last().move.from.ordinal),
                    indexToPosition(board.backup.last().move.to.ordinal)
                )
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

    fun toXY(position: Position, turn: PieceColor): Pair<Int, Int> {
        val x =
            when (turn) {
                PieceColor.WHITE ->
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
                PieceColor.BLACK -> {
                    when (position.file) {
                        File.A -> 7
                        File.B -> 6
                        File.C -> 5
                        File.D -> 4
                        File.E -> 3
                        File.F -> 2
                        File.G -> 1
                        File.H -> 0
                    }
                }
            }
        val y =
            when (turn) {
                PieceColor.WHITE -> {
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
                }
                PieceColor.BLACK -> {
                    when (position.rank) {
                        Rank.R1 -> 0
                        Rank.R2 -> 1
                        Rank.R3 -> 2
                        Rank.R4 -> 3
                        Rank.R5 -> 4
                        Rank.R6 -> 5
                        Rank.R7 -> 6
                        Rank.R8 -> 7
                    }
                }
            }
        return x to y
    }

    private fun indexToPosition(index: Int): Position {
        val file = File.entries[index % 8]
        val rank = Rank.entries[index / 8]
        return Position(file, rank)
    }
}
