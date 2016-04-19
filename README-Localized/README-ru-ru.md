# Пример приложения Android, подключающегося к Office 365 и использующего Microsoft Graph

[ ![Состояние сборки](https://travis-ci.org/OfficeDev/O365-Android-Microsoft-Graph-Connect.svg?branch=master)](https://travis-ci.org/OfficeDev/O365-Android-Microsoft-Graph-Connect)

[ ![Пример приложения, подключающегося к Office 365](../readme-images/O365-Android-Connect-video_play_icon.png)](https://www.youtube.com/watch?v=3IQIDFrqhY4 "Щелкните, чтобы просмотреть этот пример в действии")

Подключение к Office 365 — это первый шаг, который должно выполнить каждое приложение для Android, чтобы начать работу со службами и данными Office 365. В этом примере показано, как подключить, а затем вызвать один API с помощью Microsoft Graph (прежнее название — единый API Office 365).
> Примечание. Перейдите на страницу [Начало работы с API Office 365](http://dev.office.com/getting-started/office365apis?platform=option-android#setup) для упрощенной регистрации, чтобы ускорить запуск этого примера.

## Требования к устройству

Для запуска приложения для подключения устройство должно соответствовать следующим требованиям:

* размер экрана должен составлять не менее 800 x 480;
* должен использоваться API Android уровня 15 или более высокого.
 
## Предварительные требования

Чтобы воспользоваться примером приложения Android, подключающегося к Office 365, требуются перечисленные ниже компоненты.

* [Android Studio](http://developer.android.com/sdk/index.html) версии 1.0 или более поздней.
* [Java Development Kit (JDK) 7](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html).
* Учетная запись Office 365. Вы можете [подписаться на план Office 365 для разработчиков](https://portal.office.com/Signup/Signup.aspx?OfferId=6881A1CB-F4EB-4db3-9F18-388898DAF510&DL=DEVELOPERPACK&ali=1#0), включающий ресурсы, которые необходимы для создания приложений Office 365.

    > Примечание. Если у вас уже есть подписка, при выборе приведенной выше ссылки откроется страница с сообщением *К сожалению, вы не можете добавить это к своей учетной записи*. В этом случае используйте учетную запись, сопоставленную с текущей подпиской на Office 365.
* Клиент Microsoft Azure для регистрации приложения. В Azure Active Directory доступны службы идентификации, которые приложения используют для проверки подлинности и авторизации. Здесь можно получить пробную подписку: [Microsoft Azure](https://account.windowsazure.com/SignUp).

     > Внимание! Убедитесь, что ваша подписка на Azure привязана к клиенту Office 365. Для этого просмотрите запись в блоге команды Active Directory, посвященную [созданию нескольких каталогов Microsoft Azure AD и управлению ими](http://blogs.technet.com/b/ad/archive/2013/11/08/creating-and-managing-multiple-windows-azure-active-directories.aspx). Инструкции приведены в разделе о **добавлении нового каталога**. Дополнительные сведения см. в статье [Как настроить среду разработки для Office 365](https://msdn.microsoft.com/office/office365/howto/setup-development-environment#bk_CreateAzureSubscription) и, в частности, в разделе **Связывание Azure AD и учетной записи Office 365 для создания приложений и управления ими**.
      
* Универсальный код ресурса (URI) для перенаправления и идентификатор клиента, указанные при регистрации приложения в Azure. Этому примеру приложения необходимо предоставить разрешение **Отправка почты от имени пользователя** для **Microsoft Graph**. [Добавьте в Azure основное клиентское приложение](https://msdn.microsoft.com/office/office365/HowTo/add-common-consent-manually#bk_RegisterNativeApp) и [предоставьте ему необходимые разрешения](https://github.com/OfficeDev/O365-Android-Microsoft-Graph-Connect/wiki/Grant-permissions-to-the-Connect-application-in-Azure).

## Открытие примера с помощью Android Studio

1. Установите [Android Studio](http://developer.android.com/sdk/index.html) и добавьте пакеты SDK для Android в соответствии с [инструкциями](http://developer.android.com/sdk/installing/adding-packages.html) на сайте developer.android.com.
2. Скачайте или клонируйте этот пример.
3. Запустите Android Studio.
	1. Закройте все открытые проекты, а затем выберите команду **Open an existing Android Studio project** (Открыть существующий проект Android Studio).
	2. Перейдите в локальный репозиторий и выберите проект O365-Android-Microsoft-Graph-Connect. Нажмите кнопку **OK** (ОК).
	
	> Примечание. В Android Studio может появиться диалоговое окно с запросом на использование программы-оболочки Gradle. Нажмите кнопку **OK** (ОК).
	> 
	> **Frameworks detected****Репозиторий поддержки Android**
4. Откройте файл Constants.java.
	1. Найдите константу CLIENT_ID и присвойте ей строковое значение, которое равно идентификатору клиента, зарегистрированному в Azure Active Directory.
	2. Найдите константу REDIRECT_URI и присвойте ей строковое значение, которое равно универсальному коду ресурса (URI) для перенаправления, зарегистрированному в Azure Active Directory. ![Пример приложения, подключающегося к Office 365](../readme-images/O365-Android-Connect-Constants.png "Значения идентификатора клиента и универсального кода ресурса (URI) для перенаправления в файле Constants")

    > Примечание. Если у вас нет значений CLIENT_ID и REDIRECT_URI, [добавьте основное клиентское приложение в Azure](https://msdn.microsoft.com/ru-ru/library/azure/dn132599.aspx#BKMK_Adding) и запишите CLIENT_ID и REDIRECT_URI.

После сборки приложения Connect его можно запустить в эмуляторе или на устройстве. Выберите устройство с API уровня 15 или более высокого в диалоговом окне **Choose device** (Выбор устройства).

Дополнительные сведения о примере приложения см. в статье [Вызов Microsoft Graph в приложении для Android](https://graph.microsoft.io/ru-ru/docs/platform/android).

## Вопросы и комментарии

Мы будем рады получить от вас отзывы о проекте приложения Android, подключающегося к Office 365. Отправляйте нам свои вопросы и предложения в раздел этого репозитория, посвященный [проблемам](https://github.com/OfficeDev/O365-Android-Microsoft-Graph-Connect/issues).

Общие вопросы о разработке решений для Office 365 следует публиковать на сайте [Stack Overflow](http://stackoverflow.com/questions/tagged/Office365+API). Обязательно помечайте свои вопросы и комментарии тегами [Office365] и [API].

## Дальнейшие действия

В этом примере показаны только основные компоненты, необходимые приложениям для работы с Office 365. Интерфейсы API Office 365 значительно расширяют возможности ваших приложений. Например, пользователи могут управлять расписанием своего рабочего дня с помощью календаря, находить во всех файлах из OneDrive только нужные сведения, а также искать необходимых пользователей в своих списках контактов. Дополнительные данные см. в статье [Начальный проект API Office 365 для Android](https://github.com/officedev/O365-Android-Start/). Она поможет вам развить свои идеи.
  
## Дополнительные ресурсы

* [Общие сведения о платформе API Office 365](https://msdn.microsoft.com/office/office365/howto/platform-development-overview)
* [Начало работы с API Office 365](http://dev.office.com/getting-started/office365apis)
* [Общие сведения о Microsoft Graph для Office 365](http://graph.microsoft.io)
* [Пакет SDK Office 365 для Android](https://github.com/OfficeDev/Office-365-SDK-for-Android)
* [Начальные проекты и примеры кода касательно API Office 365](https://msdn.microsoft.com/office/office365/howto/starter-projects-and-code-samples)
* [Фрагменты кода из Microsoft Graph для доступа к Office 365 в Android](https://github.com/OfficeDev/O365-Android-Microsoft-Graph-Snippets)
* [Начальный проект API Office 365 для Android](https://github.com/OfficeDev/O365-Android-Start)
* [Пример профиля Office 365 для Android](https://github.com/OfficeDev/O365-Android-Profile)


## Авторские права
(c) Корпорация Майкрософт (Microsoft Corporation), 2015. Все права защищены.
