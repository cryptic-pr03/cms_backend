package com.example.cms;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class Constants {
    public final HashMap<Integer, String> roleValue;

    public Constants() {
        roleValue = new HashMap<>();
        roleValue.put(1, "USER");
        roleValue.put(2, "MANAGER");
    }
}
