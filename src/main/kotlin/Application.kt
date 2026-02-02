package com.vasiniyo.app

import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.model.BotCommand
import com.pengrad.telegrambot.model.botcommandscope.BotCommandScopeAllPrivateChats
import com.pengrad.telegrambot.request.SetMyCommands
import com.vasiniyo.app.telegram.service.BotService

fun configure(botToken: String): TelegramBot {
    val bot = TelegramBot(botToken)
    val commands = arrayOf(BotCommand("/daily", "Получить ежедневную задачу из lichess"))
    val setCommandsRequest =
        SetMyCommands(*commands).scope(BotCommandScopeAllPrivateChats()).languageCode("ru")
    bot.execute(setCommandsRequest)
    return bot
}

fun main() {
    val botToken = System.getenv("BOT_TOKEN") ?: error("BOT_TOKEN environment required")
    val bot = configure(botToken)
    val botService = BotService(bot)
    botService.startPolling()
}
