package com.vasiniyo.app.telegram.command

import com.vasiniyo.app.lichess.LichessApiService
import com.vasiniyo.app.lichess.LichessController
import com.vasiniyo.app.lichess.dto.LichessDailyRequest
import com.vasiniyo.app.render.BoardSettings
import com.vasiniyo.app.telegram.service.BotService
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO
import org.slf4j.LoggerFactory

class DailyCommand(
    private val botService: BotService,
    private val lichessApiService: LichessApiService,
    private val lichessController: LichessController,
    private val settings: BoardSettings
) {
    private val logger = LoggerFactory.getLogger(DailyCommand::class.java)

    fun handle(chatId: Long) {
        try {
            val puzzle = lichessApiService.fetchDailyPuzzlePgn()
            val request = LichessDailyRequest(puzzle.game.pgn, settings)
            val response = lichessController.getDailyChallenge(request)
            val stream = ByteArrayOutputStream()
            ImageIO.write(response.boardImage, "png", stream)
            botService.sendPhoto(chatId, stream.toByteArray())
        } catch (e: Exception) {
            botService.sendMessage(chatId, "Не удалось получить дейлик")
            logger.error("lichess daily puzzle request failed: chatId=$chatId", e)
        }
    }
}
