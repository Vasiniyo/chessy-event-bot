package com.vasiniyo.app.model

import com.github.bhlangonijr.chesslib.move.MoveList
import com.vasiniyo.app.domain.model.Board
import com.vasiniyo.app.mapper.BoardMapper

class BoardFactory {
    fun fromSan(san: String): Board {
        val board = com.github.bhlangonijr.chesslib.Board()
        val list = MoveList()
        list.loadFromSan(san)
        for (move in list) {
            board.doMove(move)
        }
        return BoardMapper.toBoard(board)
    }
}
