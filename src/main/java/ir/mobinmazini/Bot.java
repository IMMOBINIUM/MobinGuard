package ir.mobinmazini;

import ir.mobinmazini.Actions.Users.Commands.Help.Help;
import ir.mobinmazini.Actions.Users.Commands.Start.Start;
import ir.mobinmazini.Actions.Users.ID.ID;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.generics.TelegramClient;

public class Bot implements LongPollingSingleThreadUpdateConsumer {
    final TelegramClient telegramClient;
    private final Start start ;
    private final Help help;
    private final ID id;
    public Bot(String botToken){
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
