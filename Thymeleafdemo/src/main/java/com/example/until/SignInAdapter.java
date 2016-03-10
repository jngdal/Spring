package com.example.until;

import org.springframework.social.connect.Connection;
import org.springframework.web.context.request.NativeWebRequest;

public interface SignInAdapter {
    String signIn(String userId, Connection<?> connection, NativeWebRequest request);
}
