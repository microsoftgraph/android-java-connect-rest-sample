# 使用 Microsoft Graph 的 Office 365 Connect 範例 (適用於 Android)

[ ![組建狀態](https://ricalo.visualstudio.com/_apis/public/build/definitions/06256fa7-d8e5-4ca0-8639-7c00eb6f1fe9/7/badge)](https://ricalo.visualstudio.com/_apis/public/build/definitions/06256fa7-d8e5-4ca0-8639-7c00eb6f1fe9/7/badge)

[ ![Office 365 Connect 範例](../readme-images/O365-Android-Connect-video_play_icon.png)](https://www.youtube.com/watch?v=3IQIDFrqhY4 "按一下以查看執行中的範例")

連接到 Office 365 是每個 Android 應用程式要開始使用 Office 365 服務和資料時必須採取的第一個步驟。此範例示範如何透過 Microsoft Graph (之前稱為 Office 365 統一 API) 連接而後呼叫一個 API。
> 注意事項： 嘗試可簡化註冊的 [Office 365 API 入門](http://dev.office.com/getting-started/office365apis?platform=option-android#setup)頁面，以便您更快速地執行這個範例。

## 裝置需求

若要執行 Connect 範例，您的裝置必須符合下列需求：

* 800 x 480 或更大的螢幕大小。
* Android API 層級 15 或更高層級。
 
## 必要條件

若要使用 Android 適用的 Office 365 Connect 範例，您需要下列各項：

* [Android Studio](http://developer.android.com/sdk/index.html) 1.0 版或更新版本。
* [JAVA 開發套件 (JDK) 7](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html)。
* Office 365 帳戶。您可以註冊 [Office 365 開發人員訂用帳戶](https://profile.microsoft.com/RegSysProfileCenter/wizardnp.aspx?wizid=14b845d0-938c-45af-b061-f798fbb4d170)，其中包含開始建置 Office 365 應用程式所需的資源。

    > 注意事項： 如果您已有訂用帳戶，則先前的連結會讓您連到顯示「抱歉，您無法將之新增到您目前的帳戶」訊息的頁面。在此情況下，請使用您目前的 Office 365 訂用帳戶所提供的帳戶。
* 用來註冊您的應用程式的 Microsoft Azure 租用戶。Azure Active Directory 會提供識別服務，以便應用程式用於驗證和授權。在這裡可以取得試用版的訂用帳戶： [Microsoft Azure](https://account.windowsazure.com/SignUp)。

     > 重要事項： 您還需要確定您的 Azure 訂用帳戶已繫結至您的 Office 365 租用戶。若要執行這項操作，請參閱 Active Directory 小組的部落格文章：[建立和管理多個 Windows Azure Active Directory](http://blogs.technet.com/b/ad/archive/2013/11/08/creating-and-managing-multiple-windows-azure-active-directories.aspx)。**新增目錄**一節將說明如何執行這項操作。如需詳細資訊，也可以參閱[設定 Office 365 開發環境](https://msdn.microsoft.com/office/office365/howto/setup-development-environment#bk_CreateAzureSubscription)和**建立 Office 365 帳戶與 Azure AD 的關聯以便建立和管理應用程式**一節。
      
* 在 Azure 中註冊之應用程式的用戶端識別碼和重新導向 URI 值。此範例應用程式必須取得 **Microsoft Graph** 的 [以使用者身分傳送郵件] 權限。[在 Azure 中新增 Web 應用程式](https://msdn.microsoft.com/office/office365/HowTo/add-common-consent-manually#bk_RegisterNativeApp)和[授與適當的權限](https://github.com/OfficeDev/O365-Android-Microsoft-Graph-Connect/wiki/Grant-permissions-to-the-Connect-application-in-Azure)給它。

## 使用 Android Studio 開啟範例

1. 根據 developer.android.com 上的[指示](http://developer.android.com/sdk/installing/adding-packages.html)，安裝 [Android Studio](http://developer.android.com/sdk/index.html) 及新增 Android SDK 套件。
2. 下載或複製這個範例。
3. 啟動 Android Studio。
	1. 關閉您可能已開啟的任何專案，然後選擇 [開啟現有的 Android Studio 專案]。
	2. 瀏覽至您的本機儲存機制，然後選擇 O365-Android-Microsoft-Graph-Connect 專案。按一下 [確定]。
	
	> 注意事項： Android Studio 可能會顯示一個對話方塊，詢問您是否要使用 Gradle 包裝函式。按一下 [確定]。
	> 
	> **偵測到的架構****Android Support Repository**
4. 開啟 Constants.java 檔案。
	1. 尋找 CLIENT_ID 常數並將其字串值設定為等於您在 Azure Active Directory 中註冊的用戶端識別碼。
	2. 尋找 REDIRECT_URI 常數並將其字串值設定為等於您在 Azure Active Directory 中註冊的重新導向 URI。
	![Office 365 Connect 範例](../readme-images/O365-Android-Connect-Constants.png "Constants 檔案中的用戶端識別碼和重新導向 URI 值")

    > 注意事項： 如果您沒有 CLIENT_ID 和 REDIRECT_URI 值，請[在 Azure 中新增原生用戶端應用程式](https://msdn.microsoft.com/zh-tw/library/azure/dn132599.aspx#BKMK_Adding)並記下 CLIENT_ID 和 REDIRECT_URI。

一旦建置 Connect 範例，您可以在模擬器或裝置上執行它。從 [選擇裝置] 對話方塊挑選使用 API 層級 15 或更高層級的裝置。

若要深入了解此範例，請參閱[在 Android 應用程式中呼叫 Microsoft Graph](https://graph.microsoft.io/zh-tw/docs/platform/android)。

## 問題與意見

我們很樂於收到您對於 Office 365 Android Connect 專案的意見反應。您可以在此儲存機制的[問題](https://github.com/OfficeDev/O365-Android-Microsoft-Graph-Connect/issues)區段中，將您的問題及建議傳送給我們。

請在 [Stack Overflow](http://stackoverflow.com/questions/tagged/Office365+API) 提出有關 Office 365 開發的一般問題。務必以 [Office365] 和 [API] 標記您的問題或意見。

## 後續步驟

這個範例只會顯示您的應用程式要使用 Office 365 所需的基本資訊。您的應用程式可以使用 Office 365 API 做很多事，像是利用行事曆幫助您的使用者管理其工作天、在他們儲存於 OneDrive 的所有檔案中只尋找他們所需的資訊，或從他們的連絡人清單中找到她們真正需要的人。我們在[適用於 Android 的 Office 365 API 入門專案](https://github.com/officedev/O365-Android-Start/)中有更多資訊與您分享。我們認為這有助於激發您的想法。
  
## 其他資源

* [Office 365 API 平台概觀](https://msdn.microsoft.com/office/office365/howto/platform-development-overview)
* [Office 365 API 入門](http://dev.office.com/getting-started/office365apis)
* [Office 365 Microsoft Graph 概觀](http://graph.microsoft.io)
* [Office 365 SDK for Android](https://github.com/OfficeDev/Office-365-SDK-for-Android)
* [Office 365 API 入門專案和程式碼範例](https://msdn.microsoft.com/office/office365/howto/starter-projects-and-code-samples)
* [Office 365 Android Microsoft Graph 程式碼片段](https://github.com/OfficeDev/O365-Android-Microsoft-Graph-Snippets)
* [適用於 Android 的 Office 365 API 入門專案](https://github.com/OfficeDev/O365-Android-Start)
* [適用於 Android 的 Office 365 Profile 範例](https://github.com/OfficeDev/O365-Android-Profile)


## 著作權
Copyright (c) 2015 Microsoft.著作權所有，並保留一切權利。
