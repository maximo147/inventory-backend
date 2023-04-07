package com.company.inventory.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ResponseGenerico {
    private ArrayList<Map<String, String>> metadata = new ArrayList<>();

    public ArrayList<Map<String, String>> getMap() {
        return metadata;
    }

    public ResponseGenerico(String type, String code, String date) {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("type", type);
        metadata.put("code", code);
        metadata.put("date", date);

        this.metadata.add(metadata);
    }
}
