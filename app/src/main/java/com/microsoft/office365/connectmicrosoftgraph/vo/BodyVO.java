/*
 * Copyright (c) Microsoft. All rights reserved. Licensed under the MIT license.
 * See LICENSE in the project root for license information.
 */
package com.microsoft.office365.connectmicrosoftgraph.vo;

import com.google.gson.annotations.SerializedName;

public class BodyVO {

    @SerializedName("ContentType")
    public String mContentType;

    @SerializedName("Content")
    public String mContent;

}