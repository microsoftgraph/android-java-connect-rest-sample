/*
 *  Copyright (c) Microsoft. All rights reserved. Licensed under the MIT license. See full license at the bottom of this file.
 */
package com.microsoft.office365.connectmicrosoftgraph;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * This activity handles the send mail operation of the app.
 * The app must be connected to Office 365 before this activity can send an email.
 * It also uses the MSGraphAPIController to send the message.
 */
public class SendMailActivity extends AppCompatActivity implements Callback<Void> {

    private static final String TAG = "SendMailActivity";

    private TextView mTitleTextView;
    private TextView mDescriptionTextView;
    private EditText mEmailEditText;
    private ImageButton mSendMailButton;
    private ProgressBar mSendMailProgressBar;
    private TextView mConclusionTextView;
    private String mGivenName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_mail);
        initializeViews();
        // Extract the givenName and displayableId and use it in the UI.
        mGivenName = getIntent().getStringExtra("givenName");
        mTitleTextView.append(mGivenName + "!");
        mEmailEditText.setText(getIntent()
                .getStringExtra("displayableId"));
    }

    private void initializeViews() {
        mTitleTextView = (TextView) findViewById(R.id.titleTextView);
        mDescriptionTextView = (TextView) findViewById(R.id.descriptionTextView);
        mEmailEditText = (EditText) findViewById(R.id.emailEditText);
        mSendMailButton = (ImageButton) findViewById(R.id.sendMailButton);
        mSendMailProgressBar = (ProgressBar) findViewById(R.id.sendMailProgressBar);
        mConclusionTextView = (TextView) findViewById(R.id.conclusionTextView);
    }

    /**
     * Handler for the onclick event of the send mail button. It uses the MSGraphAPIController to
     * send an email. When the call is completed, the call will return to either the success()
     * or failure() methods in this class which will then take the next steps on the UI.
     * This method sends the email using the address stored in the mEmailEditText view.
     * The subject and body of the message is stored in the strings.xml file.
     *
     * @param v The view.
     */
    public void onSendMailButtonClick(View v) {
        resetUIForSendMail();

        //Prepare body message and insert name of sender
        String body = getResources().getString(R.string.mail_body_text);
        body = body.replace("{0}", mGivenName);

        MSGraphAPIController
                .getInstance()
                .sendMail(
                        mEmailEditText.getText().toString(),
                        getResources().getString(R.string.mail_subject_text),
                        body,
                        this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.send_mail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        try {
            switch (item.getItemId()) {
                case R.id.disconnectMenuitem:
                    AuthenticationManager.getInstance().disconnect();
                    showDisconnectSuccessUI();
                    Intent connectIntent = new Intent(this, ConnectActivity.class);
                    startActivity(connectIntent);
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }

        } catch (Throwable t) {
            if (t.getMessage() == null) {
                Log.e(TAG, " ");
            } else {
                Log.e(TAG, t.getMessage());
            }
        }
        return true;
    }

    private void resetUIForSendMail() {
        mSendMailButton.setVisibility(View.GONE);
        mConclusionTextView.setVisibility(View.GONE);
        mSendMailProgressBar.setVisibility(View.VISIBLE);
    }

    private void showSendMailSuccessUI() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mSendMailProgressBar.setVisibility(View.GONE);
                mSendMailButton.setVisibility(View.VISIBLE);
                mConclusionTextView.setText(R.string.conclusion_text);
                mConclusionTextView.setVisibility(View.VISIBLE);
                Toast.makeText(
                        SendMailActivity.this,
                        R.string.send_mail_toast_text,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showSendMailErrorUI() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mSendMailProgressBar.setVisibility(View.GONE);
                mSendMailButton.setVisibility(View.VISIBLE);
                mConclusionTextView.setText(R.string.sendmail_text_error);
                mConclusionTextView.setVisibility(View.VISIBLE);
                Toast.makeText(
                        SendMailActivity.this,
                        R.string.send_mail_toast_text_error,
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showDisconnectSuccessUI() {
        mTitleTextView.setVisibility(View.GONE);
        mDescriptionTextView.setVisibility(View.GONE);
        mEmailEditText.setVisibility(View.GONE);
        mSendMailButton.setVisibility(View.GONE);
        mConclusionTextView.setVisibility(View.GONE);

        Toast.makeText(
                SendMailActivity.this,
                R.string.disconnect_toast_text,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void success(Void _void, Response response) {
        Log.i(TAG, "sendMailToRecipient - Mail sent");
        showSendMailSuccessUI();
    }

    @Override
    public void failure(RetrofitError error) {
        Log.e(TAG, "onSendMailButtonClick - " + error.getMessage());
        showSendMailErrorUI();
    }
}

// *********************************************************
//
// O365-Android-Microsoft-Graph-Connect, https://github.com/OfficeDev/O365-Android-Microsoft-Graph-Connect
//
// Copyright (c) Microsoft Corporation
// All rights reserved.
//
// MIT License:
// Permission is hereby granted, free of charge, to any person obtaining
// a copy of this software and associated documentation files (the
// "Software"), to deal in the Software without restriction, including
// without limitation the rights to use, copy, modify, merge, publish,
// distribute, sublicense, and/or sell copies of the Software, and to
// permit persons to whom the Software is furnished to do so, subject to
// the following conditions:
//
// The above copyright notice and this permission notice shall be
// included in all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
// EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
// MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
// NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
// LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
// OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
// WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
//
// *********************************************************
