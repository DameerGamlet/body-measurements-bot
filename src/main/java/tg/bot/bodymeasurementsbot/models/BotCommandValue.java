package tg.bot.bodymeasurementsbot.models;

public enum BotCommandValue {
    START("/start", "Приветственное сообщение и запись имени."),
    HELP("/help", "Получить список команд и их описание."),
    INFO("/info", "Получить информацию о боте."),
    WEIGHT("/weight", "Записать текущий вес."),
    MEASUREMENTS("/measurements", "Записать охваты тела (грудь, плечи, таллия, живот, бедра)."),
    SET_REMINDER("/set_reminder", "Настроить время уведомлений о начале занятий."),
    SET_MOTIVATION_TIME("/set_motivation_time", "Настроить время ежедневных мотивационных цитат."),
    MOTIVATION("/motivation", "Получить случайную мотивационную цитату."),
    SET_TARGET_WEIGHT("/set_target_weight", "Установить целевой вес для расчета прогресса."),
    PROGRESS("/progress", "Получить отчет о прогрессе похудения."),
    FEEDBACK("/feedback", "Отправить сообщение с вопросом или предложением.");

    private final String command;
    private final String description;

    BotCommandValue(String command, String description) {
        this.command = command;
        this.description = description;
    }

    public String getCommand() {
        return command;
    }

    public String getDescription() {
        return description;
    }
}
