package com.microsoft.office365.connect;
import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.mime.TypedString;


public interface UnifiedAPIService {

    //See https://msdn.microsoft.com/office/office365/HowTo/examples-of-office-365-unified-api-calls#msg_eg_manage_me_send_mail_message_draft
    //for more information on creating and sending mail using the Unified API.

    @POST("/me/messages")
    void createDraftMail(@Header("Content-type") String contentTypeHeader,
                    @Body TypedString mail,
                    Callback<MailVO> callback );

    @POST("/me/messages/{messageID}")
    void SendDraftMail(
            @Path("messageID") String messageID,
            Callback<MailVO> callback);
}

