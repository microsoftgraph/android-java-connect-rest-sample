# Ejemplo Connect de Office 365 para Android con Microsoft Graph

[ ![Estado de la compilación](https://travis-ci.org/OfficeDev/O365-Android-Microsoft-Graph-Connect.svg?branch=master)](https://travis-ci.org/OfficeDev/O365-Android-Microsoft-Graph-Connect)

[ ![Ejemplo Connect de Office 365](../readme-images/O365-Android-Connect-video_play_icon.png)](https://www.youtube.com/watch?v=3IQIDFrqhY4 "Haga clic para ver el ejemplo en funcionamiento")

Conectarse a Office 365 es el primer paso que debe realizar cada aplicación Android para empezar a trabajar con los datos y servicios de Office 365. Este ejemplo muestra cómo conectar y cómo llamar después a una API a través de Microsoft Graph (anteriormente denominada API unificada de Office 365).
> Nota: Consulte [Introducción a las API de Office 365](http://dev.office.com/getting-started/office365apis?platform=option-android#setup), que simplifica el registro para que este ejemplo se ejecute más rápidamente.

## Requisitos del dispositivo

Para ejecutar el ejemplo Connect, el dispositivo debe cumplir los siguientes requisitos:

* Una pantalla de tamaño de 800 x 480 o superior.
* Nivel de API de Android 15 o superior.
 
## Requisitos previos

Para usar el ejemplo Connect de Office 365 para Android, necesita lo siguiente:

* [Android Studio](http://developer.android.com/sdk/index.html) versión 1.0 o posterior.
* [Kit de desarrollo Java (JDK) 7](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html).
* Una cuenta de Office 365. Puede registrarse en [una suscripción a Office 365 Developer](https://portal.office.com/Signup/Signup.aspx?OfferId=6881A1CB-F4EB-4db3-9F18-388898DAF510&DL=DEVELOPERPACK&ali=1#0), que incluye los recursos que necesita para comenzar a crear aplicaciones de Office 365.

    > Nota: Si ya dispone de una suscripción, el vínculo anterior le dirige a una página con el mensaje *No se puede agregar a su cuenta actual*. En ese caso, use una cuenta de su suscripción actual a Office 365.
* Un inquilino de Microsoft Azure para registrar la aplicación. Azure Active Directory proporciona servicios de identidad que las aplicaciones usan para autenticación y autorización. Puede adquirir una suscripción de prueba aquí: [Microsoft Azure](https://account.windowsazure.com/SignUp).

     > Importante: También necesitará asegurarse de que su suscripción a Azure esté enlazada a su inquilino de Office 365. Para ello, consulte la publicación del blog del equipo de Active Directory, [Crear y administrar varios directorios de Windows Azure Active Directory](http://blogs.technet.com/b/ad/archive/2013/11/08/creating-and-managing-multiple-windows-azure-active-directories.aspx). La sección **Agregar un nuevo directorio** le explicará cómo hacerlo. Para obtener más información, también puede consultar [Configurar el entorno de desarrollo de Office 365](https://msdn.microsoft.com/office/office365/howto/setup-development-environment#bk_CreateAzureSubscription) y la sección **Asociar su cuenta de Office 365 con Azure AD para crear y administrar aplicaciones**.
      
* Los valores de identificador de cliente y URI de redireccionamiento de una aplicación registrada en Azure. A esta aplicación de ejemplo se le debe conceder el permiso **Enviar correo como usuario** para **Microsoft Graph**. [Agregar una aplicación de cliente nativa en Azure](https://msdn.microsoft.com/office/office365/HowTo/add-common-consent-manually#bk_RegisterNativeApp) y [concederle los permisos adecuados](https://github.com/OfficeDev/O365-Android-Microsoft-Graph-Connect/wiki/Grant-permissions-to-the-Connect-application-in-Azure).

## Abrir el ejemplo con Android Studio

1. Instale [Android Studio](http://developer.android.com/sdk/index.html) y agregue los paquetes del SDK de Android según las [instrucciones](http://developer.android.com/sdk/installing/adding-packages.html) de developer.android.com.
2. Descargue o clone este ejemplo.
3. Inicie Android Studio.
	1. Cierre todos los proyectos que tenga abiertos y, a continuación, elija **Open an existing Android Studio project** (Abrir un proyecto existente de Android Studio).
	2. Examine su repositorio local y elija el proyecto O365-Android-Microsoft-Graph-Connect. Haga clic en **OK** (Aceptar).
	
	> Nota: Android Studio podría mostrar un cuadro de diálogo preguntándole si desea usar el contenedor Gradle. Haga clic en **OK** (Aceptar).
	> 
	> **Frameworks detected****Android Support Repository**
4. Abra el archivo Constants.java.
	1. Busque la constante CLIENT_ID y establezca el mismo valor de cadena que el valor del identificador de cliente que registró en Azure Active Directory.
	2. Busque la constante REDIRECT_URI y establezca el mismo valor de cadena que el valor del URI de redireccionamiento que registró en Azure Active Directory. ![Ejemplo Connect de Office 365](../readme-images/O365-Android-Connect-Constants.png "Valores de identificador de cliente y de URI de redireccionamiento en el archivo Constants")

    > Nota: Si no dispone de los valores CLIENT_ID y REDIRECT_URI, [agregue una aplicación de cliente nativa en Azure](https://msdn.microsoft.com/es-es/library/azure/dn132599.aspx#BKMK_Adding) y anote los valores CLIENT_ID y REDIRECT_URI.

Una vez creado el ejemplo Connect, puede ejecutarlo en un emulador o en un dispositivo. Elija un dispositivo con un nivel de API 15 o superior desde el cuadro de diálogo **Choose device** (Elegir dispositivo).

Para obtener más información acerca del ejemplo, consulte [Llamar a Microsoft Graph en una aplicación Android](https://graph.microsoft.io/es-es/docs/platform/android).

## Preguntas y comentarios

Nos encantaría recibir sus comentarios acerca del ejemplo Connect de Android de Office 365. Puede enviarnos sus preguntas y sugerencias a través de la sección [Problemas](https://github.com/OfficeDev/O365-Android-Microsoft-Graph-Connect/issues) de este repositorio.

Las preguntas generales sobre desarrollo en Office 365 deben publicarse en [Stack Overflow](http://stackoverflow.com/questions/tagged/Office365+API). Asegúrese de que sus preguntas o comentarios se etiquetan con [Office365] y [API].

## Pasos siguientes

Este ejemplo muestra solo los elementos esenciales que las aplicaciones necesitan para funcionar en Office 365. Sus aplicaciones pueden hacer muchas más cosas con las API de Office 365, como ayudar a sus usuarios a administrar su día de trabajo con el calendario, encontrar la información que necesitan en todos los archivos que almacenan en OneDrive o encontrar la persona exacta que necesitan de la lista de contactos. Tenemos más información que compartir en [Proyecto de inicio de las API de Office 365 para Android](https://github.com/officedev/O365-Android-Start/). Creemos que puede ayudarle a alimentar sus ideas.
  
## Recursos adicionales

* [Información general sobre la plataforma de las API de Office 365](https://msdn.microsoft.com/office/office365/howto/platform-development-overview)
* [Introducción a las API de Office 365](http://dev.office.com/getting-started/office365apis)
* [Información general de Microsoft Graph de Office 365](http://graph.microsoft.io)
* [SDK de Office 365 para Android](https://github.com/OfficeDev/Office-365-SDK-for-Android)
* [Proyectos de inicio de las API de Office 365 y ejemplos de código](https://msdn.microsoft.com/office/office365/howto/starter-projects-and-code-samples)
* [Fragmentos de código de Microsoft Graph de Office 365 para Android](https://github.com/OfficeDev/O365-Android-Microsoft-Graph-Snippets)
* [Proyecto de inicio de las API de Office 365 para Android](https://github.com/OfficeDev/O365-Android-Start)
* [Ejemplo de perfil de Office 365 para Android](https://github.com/OfficeDev/O365-Android-Profile)


## Copyright
Copyright (c) 2015 Microsoft. Todos los derechos reservados.
