# 使用 Microsoft Graph 的适于 Android 的 Office 365 Connect 示例

![构建状态](https://ricalo.visualstudio.com/_apis/public/build/definitions/06256fa7-d8e5-4ca0-8639-7c00eb6f1fe9/7/badge)

[ ![Office 365 Connect 示例](../readme-images/O365-Android-Connect-video_play_icon.png)](https://www.youtube.com/watch?v=3IQIDFrqhY4 "单击查看活动示例")

连接到 Office 365 是每个 Android 应用开始使用 Office 365 服务和数据必须采取的第一步。该示例演示如何通过 Microsoft Graph（旧称 Office 365 统一 API）连接并调用一个 API。
> 注意： 尝试 [Office 365 API 入门](http://dev.office.com/getting-started/office365apis?platform=option-android#setup)页面，其简化了注册，使您可以更快地运行该示例。

## 设备要求

要运行该 Connect 示例，您的设备需要满足以下要求：

* 800 x 480 或更大的屏幕尺寸。
* Android API 级别为 15 级或更高。
 
## 先决条件

要使用适于 Android 的 Office 365 Connect 示例，您需要以下软件：

* [Android Studio](http://developer.android.com/sdk/index.html) 1.0 或更高版本。
* [Java Development Kit (JDK) 7](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html)。
* Office 365 帐户。您可以注册 [Office 365 开发人员订阅](https://aka.ms/devprogramsignup)，其中包含您开始构建 Office 365 应用所需的资源。

    > 注意： 如果您已经订阅，之前的链接会将您转至包含以下信息的页面：*抱歉，您无法将其添加到当前帐户*。在这种情况下，请使用当前 Office 365 订阅中的帐户。
* 用于注册您的应用程序的 Microsoft Azure 租户。Azure Active Directory 为应用程序提供了用于进行身份验证和授权的标识服务。您还可在此处获得试用订阅： [Microsoft Azure](https://account.windowsazure.com/SignUp)。

     > 重要说明： 您还需要确保您的 Azure 订阅已绑定到 Office 365 租户。要执行这一操作，请参阅 Active Directory 团队的博客文章：[创建和管理多个 Microsoft Azure Active Directory](http://blogs.technet.com/b/ad/archive/2013/11/08/creating-and-managing-multiple-windows-azure-active-directories.aspx)。**添加新目录**一节将介绍如何执行此操作。您还可以参阅[设置 Office 365 开发环境](https://msdn.microsoft.com/office/office365/howto/setup-development-environment#bk_CreateAzureSubscription)和**关联您的 Office 365 帐户和 Azure AD 以创建并管理应用**一节获取详细信息。
      
* 在 Azure 中注册的应用程序的客户端 id 和重定向 uri 值。必须向该示例应用程序授予**以用户身份发送邮件**权限以使用 **Microsoft Graph**。[在 Azure 中添加本机客户端应用程序](https://msdn.microsoft.com/office/office365/HowTo/add-common-consent-manually#bk_RegisterNativeApp)并向其[授予适当的权限](https://github.com/OfficeDev/O365-Android-Microsoft-Graph-Connect/wiki/Grant-permissions-to-the-Connect-application-in-Azure)。

## 打开使用 Android Studio 的示例

1. 安装 [Android Studio](http://developer.android.com/sdk/index.html) 并根据 developer.android.com 上的[说明](http://developer.android.com/sdk/installing/adding-packages.html)添加 Android SDK 程序包。
2. 下载或克隆该示例。
3. 启动 Android Studio。
	1. 关闭可能打开的任何项目，然后选择**打开一个现有的 Android Studio 项目**。
	2. 浏览您的本地存储库，然后选择 O365-Android-Microsoft-Graph-Connect 项目。单击**确定**。
	
	> 注意： Android Studio 可能会显示一个对话框，询问您是否要使用 Gradle 包装器。单击**确定**。
	> 
	> **检测到的框架****Android 支持存储库**
4. 打开 Constants.java 文件。
	1. 查找 CLIENT_ID 常数并将其字符串值设置为与您在 Azure Active Directory 中注册的客户端 id 相等。
	2. 查找 REDIRECT_URI 常数并将其字符串值设置为与您在 Azure Active Directory 中注册的重定向 URI 相等。![Office 365 Connect 示例](../readme-images/O365-Android-Connect-Constants.png "常量文件中的客户端 ID 和重定向 URI。")

    > 注意： 如果您没有 CLIENT_ID 和 REDIRECT_URI 值，请[在 Azure 中添加本机客户端应用程序](https://msdn.microsoft.com/zh-cn/library/azure/dn132599.aspx#BKMK_Adding)并记录 CLIENT_ID 和 REDIRECT_URI。

建立 Connect 示例后，您可以在仿真器或设备中运行。在**选择设备**对话中选择配备了 15 级或更高级别的设备。

要了解有关该示例的详细信息，请参阅[在 Android 应用中调用 Microsoft Graph](https://graph.microsoft.io/zh-cn/docs/platform/android)。

## 问题和意见

我们乐意倾听您有关 Office 365 Android Connect 项目的反馈。您可以在该存储库中的[问题](https://github.com/OfficeDev/O365-Android-Microsoft-Graph-Connect/issues)部分将问题和建议发送给我们。

与 Office 365 开发相关的问题一般应发布在[堆栈溢出](http://stackoverflow.com/questions/tagged/Office365+API)中。确保您的问题或意见使用了 [Office365] 和 [API] 标记。

## 后续步骤

该示例只显示您的应用需要使用 Office 365 的必要程序。应用可以使用 Office 365 API 进行的工作非常之多，例如，帮助用户使用日历管理工作日，在存储于 OneDrive 中的所有文件中查找用户需要的信息，或在他们的联系人列表中找出他们需要的人员。我们在 [适于 Android 的 Office 365 API 初学者项目](https://github.com/officedev/O365-Android-Start/)中与您共享了更多内容。我们认为这有助于激起您的创意。
  
## 其他资源

* [Office 365 API 平台概述](https://msdn.microsoft.com/office/office365/howto/platform-development-overview)
* [Office 365 API 入门](http://dev.office.com/getting-started/office365apis)
* [Office 365 Microsoft Graph 概述](http://graph.microsoft.io)
* [适于 Android 的 Office 365 SDK](https://github.com/OfficeDev/Office-365-SDK-for-Android)
* [Office 365 API 初学者项目和代码示例](https://msdn.microsoft.com/office/office365/howto/starter-projects-and-code-samples)
* [Office 365 Android Microsoft Graph 片段](https://github.com/OfficeDev/O365-Android-Microsoft-Graph-Snippets)
* [Android Office 365 API 初学者项目](https://github.com/OfficeDev/O365-Android-Start)
* [Android Office 365 Profile 示例](https://github.com/OfficeDev/O365-Android-Profile)


## 版权所有
版权所有 (c) 2015 Microsoft。保留所有权利。
