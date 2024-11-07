package ir.immobinium.Actions.Users.Commands.Help;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import ir.immobinium.utility.MyLogger.MyLogger;

public class Help {
    final TelegramClient telegramClient;

    public Help(TelegramClient telegramClient) {
        this.telegramClient = telegramClient;
    }

    public void help(Update update) {
        Message message = update.getMessage();
        if (message.getText().equals("/help")) {
            String messageText = """
                    راهنمای ربات :\s

                    هنوز هیچی اینجا نیست""";
            SendMessage sendMessage = SendMessage.builder().chatId(message.getChatId()).text(messageText).build();
            sendMessage.setReplyToMessageId(message.getMessageId());
            try {
                telegramClient.execute(sendMessage);
            } catch (TelegramApiException e) {
                MyLogger.error(e.getMessage());
            }
        }
    }
}
