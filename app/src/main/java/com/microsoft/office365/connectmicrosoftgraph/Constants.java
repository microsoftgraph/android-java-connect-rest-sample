/*
 * Copyright (c) Microsoft. All rights reserved. Licensed under the MIT license.
 * See LICENSE in the project root for license information.
 */
package com.microsoft.office365.connectmicrosoftgraph;

interface Constants {
    String CLIENT_ID = "ENTER_YOUR_CLIENT_ID";
    String REDIRECT_URI = "ENTER_YOUR_REDIRECT_URI";

    String AUTHORITY_URL = "https://login.microsoftonline.com/common";
    String MICROSOFT_GRAPH_API_ENDPOINT = "https://graph.microsoft.com/v1.0/";

    //Scopes your application will request

    //TODO: For GA, make these "short scopes". That is, removed the domain
    //qualifiers. Result is Mail.Send instead of "https... Mail.Send
    // See https://msdn.microsoft.com/office/office365/howto/authenticate-Office-365-APIs-using-v2
    // for a complete list of scopes
    String MAIL_SEND_SCOPE = "https://graph.microsoft.com/Mail.Send";
}
