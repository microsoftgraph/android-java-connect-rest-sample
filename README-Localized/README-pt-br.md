# Exemplo de conexão com o Office 365 para Android usando o Microsoft Graph

![Status da Compilação](https://ricalo.visualstudio.com/_apis/public/build/definitions/06256fa7-d8e5-4ca0-8639-7c00eb6f1fe9/7/badge)

[ ![Exemplo de conexão com o Office 365](../readme-images/O365-Android-Connect-video_play_icon.png)](https://www.youtube.com/watch?v=3IQIDFrqhY4 "Clique no exemplo para vê-lo em ação")

A primeira etapa para que os aplicativos Android comecem a funcionar com dados e serviços do Office 365 é estabelecer uma conexão com essa plataforma. Este exemplo mostra como conectar e chamar uma única API através do Microsoft Graph (antiga API unificada do Office 365).
> Observação: experimente a página [Introdução às APIs do Office 365](http://dev.office.com/getting-started/office365apis?platform=option-android#setup), que simplifica o registro para que você possa executar esse exemplo com mais rapidez.

## Requisitos do dispositivo

Para executar o exemplo de conexão, o dispositivo deve atender aos seguintes requisitos:

* Uma tela de tamanho 800 x 480 ou maior.
* API Android nível 15 ou superior.
 
## Pré-requisitos

Para usar o exemplo de conexão com o Office 365 para Android, é necessário o seguinte:

* [Android Studio](http://developer.android.com/sdk/index.html) versão 1.0 ou posterior.
* [JDK (Java Development Kit) 7](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html).
* Uma conta do Office 365. Inscreva-se para uma [Assinatura de Desenvolvedor do Office 365](https://profile.microsoft.com/RegSysProfileCenter/wizardnp.aspx?wizid=14b845d0-938c-45af-b061-f798fbb4d170), que inclui os recursos necessários para começar a criação de aplicativos do Office 365.

    > Observação: caso já tenha uma assinatura, o link anterior direciona você para uma página com a mensagem *Não é possível adicioná-la à sua conta atual*. Nesse caso, use uma conta de sua assinatura atual do Office 365.
* Um locatário do Microsoft Azure para registrar o seu aplicativo. O Azure Active Directory fornece serviços de identidade que os aplicativos usam para autenticação e autorização. Você pode adquirir uma assinatura de avaliação aqui: [Microsoft Azure](https://account.windowsazure.com/SignUp).

     > Importante: você também deve assegurar que a assinatura do Azure esteja vinculada ao locatário do Office 365. Para fazer isso, confira a postagem de blog da equipe do Active Directory, [Criando e gerenciando vários Active Directories do Microsoft Azure](http://blogs.technet.com/b/ad/archive/2013/11/08/creating-and-managing-multiple-windows-azure-active-directories.aspx). A seção **Adicionando um novo diretório** explica como fazer isso. Para saber mais, confira o artigo [Configurar o ambiente de desenvolvimento do Office 365](https://msdn.microsoft.com/office/office365/howto/setup-development-environment#bk_CreateAzureSubscription) e a seção **Associar uma conta do Office 365 ao AD do Azure para criar e gerenciar aplicativos**.
      
* Valores de uma ID do cliente e de um URI de redirecionamento de um aplicativo registrado no Azure. Esse exemplo de aplicativo deve ter a permissão **Enviar email como usuário** concedida para o **Microsoft Graph**. Para isso, [adicione um aplicativo cliente nativo no Microsoft Azure](https://msdn.microsoft.com/office/office365/HowTo/add-common-consent-manually#bk_RegisterNativeApp) e [conceda as permissões adequadas](https://github.com/OfficeDev/O365-Android-Microsoft-Graph-Connect/wiki/Grant-permissions-to-the-Connect-application-in-Azure).

## Abra o exemplo usando o Android Studio

1. Instale o [Android Studio](http://developer.android.com/sdk/index.html) e adicione os pacotes do SDK do Android de acordo com as [instruções](http://developer.android.com/sdk/installing/adding-packages.html) na página developer.android.com.
2. Baixe ou clone esse exemplo.
3. Inicie o Android Studio.
	1. Feche todos os projetos abertos e escolha **Abrir um projeto existente do Android Studio**.
	2. Navegue até o repositório local e escolha o projeto O365-Android-Microsoft-Graph-Connect. Clique em **OK**.
	
	> Observação: O Android Studio pode exibir uma caixa de diálogo perguntando se você deseja usar o wrapper Gradle. Clique em **OK**.
	> 
	> **Estruturas detectadas****Repositório de suporte do Android**
4. Abra o arquivo Constants.java.
	1. Localize a constante CLIENT_ID e defina o respectivo valor da cadeia de caracteres igual a ID do cliente registrada no Azure Active Directory.
	2. Localize a constante REDIRECT_URI e defina o respectivo valor da cadeia de caracteres igual à URI de redirecionamento registrada no Azure Active Directory. 
	![Exemplo de conexão com o Office 365](../readme-images/O365-Android-Connect-Constants.png "Valores de ID do cliente e URI de redirecionamento no arquivo Contantes")

    > Observação: caso não tenha os valores CLIENT_ID e REDIRECT_URI, [adicione um aplicativo cliente nativo no Azure](https://msdn.microsoft.com/pt-br/library/azure/dn132599.aspx#BKMK_Adding) e anote os valores do CLIENT_ID e do REDIRECT_URI.

Depois de criar o exemplo de conexão, você pode executá-lo em um emulador ou dispositivo. Escolha um dispositivo com API de nível 15 ou superior na caixa de diálogo **Escolher dispositivo**.

Para saber mais sobre o exemplo, confira o artigo [Chamar o Microsoft Graph em um aplicativo Android](https://graph.microsoft.io/pt-br/docs/platform/android).

## Perguntas e comentários

Gostaríamos de saber sua opinião sobre o projeto de conexão com o Office 365 para Android. Você pode enviar perguntas e sugestões na seção [Problemas](https://github.com/OfficeDev/O365-Android-Microsoft-Graph-Connect/issues) deste repositório.

Faça a postagem de perguntas sobre desenvolvimento do Office 365 em geral na página do [Stack Overflow](http://stackoverflow.com/questions/tagged/Office365+API). Não deixe de marcar as perguntas ou comentários com [Office365] e [API].

## Próximas etapas

Este exemplo mostra apenas os conceitos básicos necessários para que os aplicativos funcionem com o Office 365. O aplicativo pode fazer muito mais usando as APIs do Office 365, como ajudar os usuários a gerenciar os dias úteis com o calendário, localizar apenas as informações necessárias em todos os arquivos armazenados no OneDrive ou localizar uma pessoa específica na lista de contatos. Temos mais recursos para compartilhar com você no [Projeto inicial de APIs do Office 365 para Android](https://github.com/officedev/O365-Android-Start/). Esse conteúdo pode ajudar a fomentar suas ideias.
  
## Recursos adicionais

* [Visão geral da plataforma de APIs do Office 365](https://msdn.microsoft.com/office/office365/howto/platform-development-overview)
* [Introdução às APIs do Office 365](http://dev.office.com/getting-started/office365apis)
* [Visão geral do Microsoft Graph do Office 365](http://graph.microsoft.io)
* [SDK do Office 365 para Android](https://github.com/OfficeDev/Office-365-SDK-for-Android)
* [Exemplos de código e projetos iniciais de APIs do Office 365](https://msdn.microsoft.com/office/office365/howto/starter-projects-and-code-samples)
* [Trechos do Microsoft Graph do Office 365 para Android](https://github.com/OfficeDev/O365-Android-Microsoft-Graph-Snippets)
* [Projeto inicial de APIs do Office 365 para Android](https://github.com/OfficeDev/O365-Android-Start)
* [Exemplo de perfil do Office 365 para Android](https://github.com/OfficeDev/O365-Android-Profile)


## Direitos autorais
Copyright © 2015 Microsoft. Todos os direitos reservados.
