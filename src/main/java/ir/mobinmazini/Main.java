package ir.mobinmazini;

import ir.mobinmazini.utility.MyLogger.MyLogger;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;

public class Main {
    public static void main(String[] args) {
        //Bot Token
        String botToken = "Your Bot Token";
        try (TelegramBotsLongPollingApplication botsApplication = new TelegramBotsLongPollingApplication()) {
            botsApplication.registerBot(botToken, new Bot(botToken));
            MyLogger.info("Bot successfully started!");
            Thread.currentThread().join();
        } catch (Exception e) {
            MyLogger.error(e.getMessage());
        }
    }
}