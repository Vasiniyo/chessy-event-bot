package com.vasiniyo.app.telegram.service

import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.UpdatesListener
import com.pengrad.telegrambot.utility.kotlin.extension.request.getMe
import com.pengrad.telegrambot.utility.kotlin.extension.request.sendMessage
import com.pengrad.telegrambot.utility.kotlin.extension.request.sendPhoto
import com.vasiniyo.app.lichess.LichessApiService
import com.vasiniyo.app.lichess.LichessController
import com.vasiniyo.app.model.BoardFactory
import com.vasiniyo.app.render.BoardRenderService
import com.vasiniyo.app.render.BoardSettings
import com.vasiniyo.app.render.SvgPiecePainter
import com.vasiniyo.app.telegram.command.DailyCommand
import java.awt.Color
import okhttp3.OkHttpClient
import org.slf4j.LoggerFactory

class BotService(private val bot: TelegramBot) {
    private val logger = LoggerFactory.getLogger(BotService::class.java)

    private val piecePainter = SvgPiecePainter()
    private val httpClient = OkHttpClient()
    private val lichessApiService = LichessApiService(httpClient)
    private val boardService = BoardRenderService(piecePainter)
    private val boardFactory = BoardFactory()
    private val lichessController = LichessController(boardService, boardFactory)
    private val settings =
        BoardSettings(
            tileSize = 100,
            whiteTile = Color(255, 228, 238),
            blackTile = Color(219, 112, 147)
        )

    fun sendPhoto(chatId: Long, bytes: ByteArray) {
        bot.sendPhoto(chatId, bytes)
    }

    fun sendMessage(chatId: Long, message: String) {
        bot.sendMessage(chatId, message)
    }

    fun startPolling() {
        val dailyCommand =
            DailyCommand(
                this,
                lichessApiService,
                lichessController,
                settings,
            )
        val botUsername = bot.getMe().user().username()
        logger.info("Bot started: @{}", botUsername)
        bot.setUpdatesListener { updates ->
            for (update in updates) {
                val message = update.message() ?: continue
                val text = message.text() ?: continue
                val parts = text.trim().split("@", limit = 2)
                val username = parts.getOrNull(1)
                if (username != null && !username.equals(botUsername, ignoreCase = true)) {
                    continue
                }
                val chatId = message.chat().id()
                val command = parts.getOrNull(0)?.lowercase()
                when (command) {
                    "/daily" -> dailyCommand.handle(chatId)
                    else -> continue
                }
            }
            UpdatesListener.CONFIRMED_UPDATES_ALL
        }
    }
}
