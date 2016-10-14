/*
 * Copyright (c) Microsoft. All rights reserved. Licensed under the MIT license.
 * See LICENSE in the project root for license information.
 */
package com.microsoft.office365.connectmicrosoftgraph;

interface Constants {
    String AUTHORITY_URL = "https://login.microsoftonline.com/common";
    // Update these two constants with the values for your application:
    String CLIENT_ID = "ENTER_YOUR_CLIENT_ID";
    String REDIRECT_URI = "https://login.microsoftonline.com/common/oauth2/nativeclient";
    String MICROSOFT_GRAPH_API_ENDPOINT_RESOURCE_ID = "https://graph.microsoft.com/";
    String SCOPES = "openid profile Mail.Send offline_access";
}
