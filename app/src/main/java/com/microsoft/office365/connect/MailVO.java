package com.microsoft.office365.connect;


/**
 * Mail Value Object for holding values in an email
 */
public class MailVO {
    public String subject;
    public Body body;
    public ToRecipients[] toRecipients;

    public class Body{
        public String contentType;
        public String content;
    }

    public class ToRecipients{
        public EmailAddress emailAddress;

        public class EmailAddress{
            public String address;
        }
    }
}


