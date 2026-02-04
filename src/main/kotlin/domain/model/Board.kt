package com.vasiniyo.app.domain.model

data class Board(
    val pieces: Map<Position, Piece>,
    val turn: PieceColor,
    val lastTurn: List<Position>,
)
