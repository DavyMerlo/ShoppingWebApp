package com.davy.restapi.authetication.email;

import com.davy.restapi.user.entity.User;

public interface EmailSender {

    void send(String to, String email);
}
