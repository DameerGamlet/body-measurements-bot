# Техническое задание для Telegram бота "BodyMeasurementBot"

## Введение
BodyMeasurementBot - это Telegram бот, предназначенный для помощи пользователям 
ведения ежедневных замеров веса и охватов тела, настройки уведомлений о начале 
занятий, получения мотивационных цитат дня и отчетов о прогрессе похудения. 

Бот
будет разработан с использованием Spring Boot для обеспечения стабильности, удобства
и безопасности.

## 1. Требования

### 1.1. Функциональные требования

1. Ведение ежедневных замеров:
    - Возможность ввода и сохранения данных о весе и охватах тела (грудь, плечи, 
    - талия, живот, бедра) для каждого дня.
    - Возможность просмотра истории замеров за определенный период.

2. Уведомления о начале занятий:
    - Возможность настройки времени уведомлений для начала занятий пользователем.
    - Регулярная отправка уведомлений в указанное время с мотивационными сообщениями.

3. Мотивационные цитаты дня:
    - Ежедневная отправка пользователю случайной мотивационной цитаты в установленное время.

4. Расчет прогресса похудения:
    - Автоматический расчет процента потери веса относительно начального значения.
    - Возможность установки целевого веса пользователем.

5. Функции обратной связи:
    - Возможность для пользователя отправить сообщение с вопросами или предложениями.

### 1.2. Нефункциональные требования

1. Безопасность:
    - Данные пользователей должны храниться в зашифрованном виде.
    - Взаимодействие с ботом должно происходить через защищенное SSL-соединение.

2. Масштабируемость:
    - Бот должен поддерживать обработку запросов от большого числа пользователей одновременно.

3. Удобство использования:
    - Интерфейс бота должен быть интуитивно понятным и удобным для взаимодействия.

4. Надежность:
    - Бот должен быть устойчив к сбоям и обрывам соединения, способен восстанавливаться после возможных сбоев.

## 2. Описание команд бота

### 2.1. Команды для ведения замеров

1. `/start` - запуск бота и приветственное сообщение.
2. `/weight <вес>` - запись текущего веса.
3. `/measurements <грудь> <плечи> <таллия> <живот> <бедра>` - запись текущих охватов тела.
4. `/history` - просмотр истории замеров.

### 2.2. Команды для уведомлений о занятиях и мотивации

1. `/set_reminder <время>` - установка времени уведомлений о начале занятий.
2. `/set_motivation_time <время>` - установка времени ежедневной отправки мотивационной цитаты.
3. `/motivation` - получение мотивационной цитаты в любое время.

### 2.3. Команды для управления похудением

1. `/set_target_weight <целевой_вес>` - установка целевого веса для расчета прогресса.
2. `/progress` - получение отчета о прогрессе похудения.

### 2.4. Команда для обратной связи

1. `/feedback <сообщение>` - отправка сообщения с вопросами или предложениями.

## 3. Интерфейс бота

Бот будет предоставлять текстовый интерфейс в Telegram для взаимодействия с пользователями. Ответы бота будут содержать информацию о выполненных командах и прогрессе похудения, а также мотивационные сообщения и подтверждения действий.

## 4. Описание дополнительных функциональностей

1. Хранение данных:
    - Бот будет использовать базу данных для хранения данных пользователей (вес, охваты, целевой вес и т.д.).
    - Взаимодействие с базой данных будет реализовано с использованием Spring Data JPA.

2. Генерация мотивационных цитат:
    - Бот будет иметь базу данных с мотивационными цитатами.
    - Ежедневно будет выбираться случайная цитата из базы для отправки пользователю.

3. Расчет прогресса похудения:
    - При установке целевого веса бот будет автоматически считать процент потери веса относительно начального значения.

4. Уведомления:
    - Бот будет использовать механизм отправки уведомлений с использованием планировщика задач (например, Spring's TaskScheduler).

5. Обработка ошибок:
    - Бот должен предусмотреть обработку ошибок и некорректных команд с отправкой соответствующих сообщений пользователю.

## 5. Заключ

ение

Это техническое задание описывает основные требования и функциональности Telegram бота "BodyMeasurementBot". Бот будет использовать Spring Boot для обеспечения стабильности и удобства разработки, базу данных для хранения данных пользователей, а также различные дополнительные функциональности для обеспечения качественного опыта пользователей и помощи им в достижении поставленных целей по здоровью и физической форме.