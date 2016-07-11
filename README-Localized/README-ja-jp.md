# Microsoft Graph を使った Android 用 Office 365 Connect サンプル

[ ![ビルドの状態](https://ricalo.visualstudio.com/_apis/public/build/definitions/06256fa7-d8e5-4ca0-8639-7c00eb6f1fe9/7/badge)](https://ricalo.visualstudio.com/_apis/public/build/definitions/06256fa7-d8e5-4ca0-8639-7c00eb6f1fe9/7/badge)

[ ![Office 365 Connect サンプル](../readme-images/O365-Android-Connect-video_play_icon.png)](https://www.youtube.com/watch?v=3IQIDFrqhY4 "活用できるサンプルを確認するにはこちらをクリックしてください")

各 Android アプリで Office 365 のサービスとデータの操作を開始するため、最初に Office 365 に接続する必要があります。このサンプルでは、Microsoft Graph (以前は Office 365 統合 API と呼ばれていた) を介して、1 つの API に接続して呼び出す方法を示します。
> メモ: このサンプルをより迅速に実行するため、「[Office 365 API を使う](http://dev.office.com/getting-started/office365apis?platform=option-android#setup)」ページに記載された登録の簡略化をお試しください。

## デバイスの要件

Connect のサンプルを実行するには、デバイスが次の要件を満たしている必要があります。

* 画面のサイズが 800 x 480 以上である。
* Android の API レベルが 15 以上である。
 
## 前提条件

Android 用 Office 365 Connect サンプルを使うには次が必要です。

*[Android Studio](http://developer.android.com/sdk/index.html) バージョン 1.0 以降。
* [Java 開発キット (JDK) 7](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html)。
* Office 365 アカウント。Office 365 アプリのビルドを開始するために必要なリソースを含む [Office 365 Developer サブスクリプション](https://profile.microsoft.com/RegSysProfileCenter/wizardnp.aspx?wizid=14b845d0-938c-45af-b061-f798fbb4d170)にサインアップできます。

    > メモ: サブスクリプションをすでにお持ちの場合、上記のリンクをクリックすると、「*申し訳ございません。現在のアカウントに追加できません*」というメッセージが表示されるページに移動します。その場合は、現在使っている Office 365 サブスクリプションのアカウントをご利用いただけます。
* アプリケーションを登録する Microsoft Azure テナント。Azure Active Directory は、アプリケーションが認証と承認に使用する ID サービスを提供します。ここでは、試用版サブスクリプションを取得できます。 [Microsoft Azure](https://account.windowsazure.com/SignUp)。

     > 重要: Azure サブスクリプションが Office 365 テナントにバインドされていることを確認する必要もあります。確認する方法については、Active Directory チームのブログ投稿「[複数の Windows Azure Active Directory を作成して管理する](http://blogs.technet.com/b/ad/archive/2013/11/08/creating-and-managing-multiple-windows-azure-active-directories.aspx)」をご覧ください。「**新しいディレクトリを追加する**」セクションで、この方法を説明しています。また、詳しくは、「[Office 365 開発環境のセットアップ](https://msdn.microsoft.com/office/office365/howto/setup-development-environment#bk_CreateAzureSubscription)」と「**Office 365 アカウントを Azure AD と関連付けて、アプリを作成して管理する**」のセクションをご覧ください。
      
* Azure に登録されたアプリケーションのクライアント ID とリダイレクト URI の値。このサンプル アプリケーションには、**Microsoft Graph** の**ユーザーとしてメールを送信する**アクセス許可を付与する必要があります。[Azure にネイティブ クライアント アプリケーションを追加](https://msdn.microsoft.com/office/office365/HowTo/add-common-consent-manually#bk_RegisterNativeApp)し、[適切なアクセス許可を付与](https://github.com/OfficeDev/O365-Android-Microsoft-Graph-Connect/wiki/Grant-permissions-to-the-Connect-application-in-Azure)します。

## Android Studio を使ってサンプルを開く

1. developer.android.com の[指示](http://developer.android.com/sdk/installing/adding-packages.html)に従って、[Android Studio](http://developer.android.com/sdk/index.html) をインストールし、Android SDK パッケージを追加します。
2. このサンプルをダウンロードするか、複製を作成します。
3. Android Studio を起動します。
	1. 開いているプロジェクトをすべて閉じ、**[既存のAndroid Studio プロジェクトを開く]** を選択します。
	2. ローカル リポジトリを参照し、O365-Android-Microsoft-Graph-Connect プロジェクトを選択します。**[OK]** をクリックします。
	
	> メモ: Android Studio では、Gradle ラッパーを使うかどうかを尋ねるダイアログが表示されることがあります。**[OK]** をクリックします。
	> 
	> **検出されたフレームワーク****Android サポート リポジトリ**
4. Constants.java ファイルを開きます。
	1. CLIENT_ID 定数を検索して、その String 値を Azure Active Directory に登録されているクライアント ID と同じ値に設定します。
	2. REDIRECT_URI 定数を検索して、その String 値を Azure Active Directory に登録されているリダイレクト URI と同じ値に設定します。
	![Office 365 Connect サンプル](../readme-images/O365-Android-Connect-Constants.png "定数ファイル内のクライアント ID とリダイレクト URI の値")

    > メモ: CLIENT_ID と REDIRECT_URI の値がない場合は、[ネイティブ クライアント アプリケーションを Azure に追加](https://msdn.microsoft.com/ja-jp/library/azure/dn132599.aspx#BKMK_Adding)し、CLIENT_ID と REDIRECT_URI を書き留めます。

Connect のサンプルをビルドしたら、エミュレーターやデバイス上で実行できます。**[デバイスの選択]** ダイアログから API レベル 15 以上のデバイスを選びます。

サンプルについて詳しくは、「[Android アプリで Microsoft Graph を呼び出す](https://graph.microsoft.io/ja-jp/docs/platform/android)」をご覧ください。

## 質問とコメント

Office 365 Android Connect プロジェクトに関するフィードバックをお寄せください。質問や提案につきましては、このリポジトリの「[問題](https://github.com/OfficeDev/O365-Android-Microsoft-Graph-Connect/issues)」セクションで送信できます。

Office 365 開発全般の質問につきましては、「[スタック オーバーフロー](http://stackoverflow.com/questions/tagged/Office365+API)」に投稿してください。質問やコメントには、必ず [Office365] と [API] のタグを付けてください。

## 次の手順

このサンプルでは、アプリが Office 365 を使用して操作する必要がある重要項目のみを示しています。Office 365 API を使って、アプリでできることはさらにあります。たとえば、ユーザーが予定表で作業日を管理できるようにする、ユーザーが OneDrive に保存したすべてのファイルで必要な情報を検索する、連絡先のリストからユーザーが必要とする人を正確に見つけるなどです。「[Android 用 Office 365 API スタート プロジェクト](https://github.com/officedev/O365-Android-Start/)」でさらに説明しています。より良いアイデアの助けになればと思います。
  
## その他の技術情報

* [Office 365 API プラットフォームの概要](https://msdn.microsoft.com/office/office365/howto/platform-development-overview)
* [Office 365 API を使う](http://dev.office.com/getting-started/office365apis)
* [Office 365 Microsoft Graph の概要](http://graph.microsoft.io)
* [Office 365 SDK for Android](https://github.com/OfficeDev/Office-365-SDK-for-Android)
* [Office 365 API スタート プロジェクトとコード サンプル](https://msdn.microsoft.com/office/office365/howto/starter-projects-and-code-samples)
* [Office 365 Android Microsoft Graph のスニペット](https://github.com/OfficeDev/O365-Android-Microsoft-Graph-Snippets)
* [Android 用 Office 365 API スタート プロジェクト](https://github.com/OfficeDev/O365-Android-Start)
* [Android 用 Office 365 プロファイル サンプル](https://github.com/OfficeDev/O365-Android-Profile)


## 著作権
Copyright (c) 2015 Microsoft.All rights reserved.
