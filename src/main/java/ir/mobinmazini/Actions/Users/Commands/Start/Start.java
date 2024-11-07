package ir.mobinmazini.Actions.Users.Commands.Start;

import ir.mobinmazini.utility.MyLogger.MyLogger;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

public class Start {
    final TelegramClient telegramClient;
    public Start(TelegramClient telegramClient) {
        this.telegramClient = telegramClient;
    }
    public void start(Update update) {
        Message message = update.getMessage();
        if (message.getText().equals("/start")){
            String messageText = """
                    سلام!
                    من ربات مبین گارد هستم
                    برای دیدن راهنما /help بزن""";
            SendMessage sendMessage = SendMessage.builder().chatId(message.getChatId()).text(messageText).build();
            sendMessage.setReplyToMessageId(message.getMessageId());
            try {
                telegramClient.execute(sendMessage);
            }catch (TelegramApiException e){
                MyLogger.error(e.getMessage());
            }
        }
    }
}
