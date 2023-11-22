package tg.bot.bodymeasurementsbot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import tg.bot.bodymeasurementsbot.config.BotConfig;
import tg.bot.bodymeasurementsbot.models.BotCommandMessage;
import tg.bot.bodymeasurementsbot.models.BotCommandValue;
import tg.bot.bodymeasurementsbot.utils.ExceptionMessage;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig botConfig;

    private final BotCommandMessage commandMessage;

    public TelegramBot(BotConfig botConfig, BotCommandMessage commandMessage) {
        this.botConfig = botConfig;
        this.commandMessage = commandMessage;
        List<BotCommand> botCommandList = new ArrayList<>();

        for (BotCommandValue commandValue : BotCommandValue.values()) {
            botCommandList.add(new BotCommand(commandValue.getCommand(), commandValue.getDescription()));
        }

        try {
            this.execute(new SetMyCommands(botCommandList, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            e.printStackTrace();
            log.error(ExceptionMessage.UNABLE_LOAD_BOT_MENU);
        }
    }

    private String messageFormatting(String command) {
        return command.replaceAll("%n", "\n");
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId();
            String messageText = update.getMessage().getText();

            System.out.println("chatId: " + chatId + ", messageText: \"" + messageText + "\"");

            switch (messageText) {
                case "/start" -> startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                case "/info" -> sendMessage(chatId, messageFormatting(commandMessage.getInfo()));
                case "/help" -> sendMessage(chatId, messageFormatting(commandMessage.getHelp()));
                case "/mydata" -> sendMessage(chatId, "Команда \"mydata\" ещё не реализовано");
                case "/deletedata" -> sendMessage(chatId, "Команда \"deletedata\" ещё не реализовано");
                case "/settings" -> sendMessage(chatId, "Команда \"settings\" ещё не реализовано");
                default -> sendMessage(chatId, ExceptionMessage.TG_COMMAND_UNSUPPORTED);
            }
        }
    }

    private void startCommandReceived(long chatId, String name) {
        log.info("Пользователь: \"%s\" успешно запустил бота!".formatted(name));

        // TODO: добавить больше данных

        sendMessage(chatId, messageFormatting(commandMessage.getStart().formatted(name)));
    }

    private void sendMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);

        log.info("Отправляется сообщение в чате с ID: %s и с сообщением: \"%s\"".formatted(chatId, textToSend));

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            log.error("Невозможно отправить сообщение в чате с ID: %s по причине: \"%s\"".formatted(chatId, e.getMessage()));
        }
    }

    @Override
    public String getBotUsername() {
        return botConfig.getName();
    }

    public String getBotToken() {
        return botConfig.getToken();
    }
}
