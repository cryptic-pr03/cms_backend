package com.example.cms;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class Constants {
    public final HashMap<Integer, String> typeUserCode;

    public final String FRONTEND_URL;

    public Constants() {
        typeUserCode = new HashMap<>();
        typeUserCode.put(1, "AUDIENCE");
        typeUserCode.put(2, "ARTIST_MANAGER");
        typeUserCode.put(3, "STAFF");
        typeUserCode.put(4, "VENUE_MANAGER");
        typeUserCode.put(5, "ADMIN");

        FRONTEND_URL = "http://localhost:3000";
    }
}
