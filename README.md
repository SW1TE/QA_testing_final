# Testing Project
Автоматизированные UI‑тесты для:

веб‑сайта ru.wikipedia.org (4 теста)

мобильного приложения Wikipedia для Android (5 тестов)

## Стек технологий
- Java 11+
- Selenium WebDriver
- Appium (UiAutomator2)
- TestNG
- Maven
- WebDriverManager
- Appium Java Client

## Требования
### Общие:

- Java 11+
- Maven 3.8+
- Git

### Для веб‑тестов:

- Браузер **Google Chrome**
- Доступ к `https://ru.wikipedia.org`

### Для мобильных тестов:

- Node.js + Appium:
- npm install -g appium
- appium driver install uiautomator2
- Android Studio / Android SDK
- Созданный Android‑эмулятор (рекомендуется API 30+, возможен API 16)
- Приложение Wikipedia, установленное на эмуляторе/устройстве

## Структура проекта
```
src/
└── test/
├── java/
│ └── com/automation/
│ ├── config/ # Конфигурация (TestConfig)
│ ├── web/ # Веб Page Object'ы и тесты
│ └── mobile/ # Мобильные Page Object'ы и тесты
└── resources/
├── config.properties # Настройки web + mobile
├── testng-web.xml # Suite для веб-тестов
└── testng-mobile.xml # Suite для мобильных тестов
```

## Настройка окружения

### Android SDK и переменные среды (Windows):
- ANDROID_HOME = C:\Users<USER>\AppData\Local\Android\Sdk
- ANDROID_SDK_ROOT = C:\Users<USER>\AppData\Local\Android\Sdk
- В PATH добавить: C:\Users<USER>\AppData\Local\Android\Sdk\platform-tools

### Проверка:
adb version

### Конфигурация проекта (src/test/resources/config.properties):

Веб-тесты
web.base.url=https://ru.wikipedia.org
web.browser=chrome
web.timeout.seconds=10

Мобильные тесты
mobile.platform.name=Android
mobile.platform.version=16
mobile.device.name=emulator-5554
mobile.automation.name=UiAutomator2
mobile.appium.server.url=http://127.0.0.1:4723

mobile.app.package=org.wikipedia
mobile.app.activity=org.wikipedia.main.MainActivity
mobile.app.path= # пусто, если приложение уже установлено

## Запуск тестов
Все тесты по умолчанию (в зависимости от профиля в pom.xml):
mvn clean test

## Веб‑тесты (ru.wikipedia.org):
mvn clean test -Pweb

## Мобильные тесты (приложение Wikipedia):

- Запуск Android‑эмулятора:
emulator -avd Pixel_5
- Проверка подключения устройства:
adb devices
- Запуск Appium‑сервера:
appium -p 4723
- Запуск мобильных тестов:
mvn clean test -Pmobile

## Тестовые сценарии
### Веб:

- Проверка главной страницы
- Поиск статьи
- Навигация по сайту (переход на «Заглавную страницу»)
- Проверка страницы входа

### Мобильные:

- Запуск приложения и проверка главного экрана
- Поиск статьи по ключевому слову
- Открытие статьи и проверка заголовка
- Навигация по контенту (скролл, оглавление)
- Поиск несуществующей статьи и проверка реакции приложения

## Устранение проблем
### Appium:

Проверка установки:
appium --version

Переустановка драйвера UiAutomator2:
appium driver uninstall uiautomator2
appium driver install uiautomator2

### Эмулятор / ADB:

Список доступных эмуляторов:
emulator -list-avds

Перезапуск ADB:
adb kill-server
adb start-server

Проверка подключённых устройств:
adb devices

### Maven / зависимости:

Очистка и обновление:
mvn clean install -U
