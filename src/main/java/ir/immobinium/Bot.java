package ir.immobinium;

import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import ir.immobinium.Actions.Users.Commands.Help.Help;
import ir.immobinium.Actions.Users.Commands.Start.Start;
import ir.immobinium.Actions.Users.ID.ID;

public class Bot implements LongPollingSingleThreadUpdateConsumer {
    final TelegramClient telegramClient;
    private final Start start;
    private final Help help;
    private final ID id;

    public Bot(String botToken) {
        telegramClient = new OkHttpTelegramClient(botToken);
        start = new Start(telegramClient);
        help = new Help(telegramClient);
        id = new ID(telegramClient);
    }

    @Override
    public void consume(Update update) {

        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            start.start(update);
            help.help(update);
            id.id(update);
        }
    }
}
