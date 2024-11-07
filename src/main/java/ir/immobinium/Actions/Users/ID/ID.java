package ir.immobinium.Actions.Users.ID;

import org.telegram.telegrambots.meta.api.methods.GetUserProfilePhotos;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import ir.immobinium.utility.MyLogger.MyLogger;

import java.util.List;

public class ID {
    final TelegramClient telegramClient;

    public ID(TelegramClient telegramClient) {
        this.telegramClient = telegramClient;
    }

    public void id(Update update) {
        Message message = update.getMessage();
        User user = message.getFrom();
        if (message.getText().equals("ایدی") || message.getText().equals("id")) {
            if (message.isReply()) {
                user = message.getReplyToMessage().getFrom();
            }
            String id = "نام : " + user.getFirstName() + "\n"
                    + "یوزر نیم : @" + user.getUserName() + "\n\n"
                    + "-----------";
            SendMessage sendMessage = SendMessage.builder().chatId(message.getChatId()).text(id).build();
            sendMessage.setReplyToMessageId(message.getMessageId());

            try {
                List<List<PhotoSize>> photoList = telegramClient.execute(new GetUserProfilePhotos(user.getId()))
                        .getPhotos();
                if (!photoList.isEmpty() && !photoList.getFirst().isEmpty()) {
                    PhotoSize photo = photoList.getFirst().getFirst(); // انتخاب اولین عکس از لیست
                    SendPhoto sendPhoto = SendPhoto.builder()
                            .chatId(String.valueOf(message.getChatId()))
                            .photo(new InputFile(photo.getFileId()))
                            .caption(id)
                            .replyToMessageId(message.getMessageId())
                            .build();

                    telegramClient.execute(sendPhoto);
                } else {
                    telegramClient.execute(sendMessage);
                }
            } catch (TelegramApiException e) {
                MyLogger.error(e.getMessage());
            }
        }
    }
}
