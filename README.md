# QA Testing Final

Проект содержит **автоматизированные UI-тесты** для проверки функциональности веб- и мобильного приложения Wikipedia.

### Покрываемые платформы:

* **Веб-сайт:** `ru.wikipedia.org` — 4 UI-теста
* **Мобильное приложение:** Wikipedia для **Android** — 5 UI-тестов

Тесты реализованы с использованием подхода **Page Object Model**, поддерживают конфигурацию через properties-файл и запускаются с помощью Maven-профилей.

---

## Технологический стек

* **Java 11+**
* **Selenium WebDriver**
* **Appium (UiAutomator2)**
* **TestNG**
* **Maven**
* **WebDriverManager**
* **Appium Java Client**

---

## Требования

### Общие

* Java 11+
* Maven 3.8+
* Git

### Для веб-тестов

* Браузер **Google Chrome**
* Доступ к сайту `https://ru.wikipedia.org`

### Для мобильных тестов

* Node.js и Appium

  ```bash
  npm install -g appium
  appium driver install uiautomator2
  ```
* Android Studio / Android SDK
* Настроенный Android-эмулятор
  (рекомендуется API 30+, допускается API 16)
* Установленное приложение **Wikipedia**
  (на эмуляторе или реальном устройстве)

---

## Настройка окружения

### Android SDK и переменные среды (Windows)

* `ANDROID_HOME=C:\Users\<USER>\AppData\Local\Android\Sdk`
* `ANDROID_SDK_ROOT=C:\Users\<USER>\AppData\Local\Android\Sdk`
* В `PATH` добавить:

  ```
  C:\Users\<USER>\AppData\Local\Android\Sdk\platform-tools
  ```

### Проверка корректности установки

```bash
adb version
```

---

## Конфигурация проекта

`src/test/resources/config.properties`

### Веб-тесты

```properties
web.base.url=https://ru.wikipedia.org
web.browser=chrome
web.timeout.seconds=10
```

### Мобильные тесты

```properties
mobile.platform.name=Android
mobile.platform.version=16
mobile.device.name=emulator-5554
mobile.automation.name=UiAutomator2
mobile.appium.server.url=http://127.0.0.1:4723

mobile.app.package=org.wikipedia
mobile.app.activity=org.wikipedia.main.MainActivity
mobile.app.path=   # оставить пустым, если приложение уже установлено
```

---

## Запуск тестов

### Все тесты (по умолчанию)

```bash
mvn clean test
```

### Только веб-тесты

```bash
mvn clean test -Pweb
```

### Только мобильные тесты

1. Запустить Android-эмулятор:

   ```bash
   emulator -avd Pixel_5
   ```
2. Проверить подключение устройства:

   ```bash
   adb devices
   ```
3. Запустить Appium-сервер:

   ```bash
   appium -p 4723
   ```
4. Запустить тесты:

   ```bash
   mvn clean test -Pmobile
   ```

---

## Тестовые сценарии

### Веб

* Проверка загрузки главной страницы
* Поиск статьи
* Навигация по сайту (возврат на «Заглавную страницу»)
* Проверка страницы входа

### Мобильное приложение

* Запуск приложения и проверка главного экрана
* Поиск статьи по ключевому слову
* Открытие статьи и проверка заголовка
* Навигация по контенту (скролл, оглавление)
* Поиск несуществующей статьи и проверка реакции приложения

---

## Устранение проблем

### Appium

Проверка версии:

```bash
appium --version
```

Переустановка UiAutomator2:

```bash
appium driver uninstall uiautomator2
appium driver install uiautomator2
```

### Эмулятор / ADB

Список эмуляторов:

```bash
emulator -list-avds
```

Перезапуск ADB:

```bash
adb kill-server
adb start-server
```

Проверка подключённых устройств:

```bash
adb devices
```

### Maven / зависимости

```bash
mvn clean install -U
```

