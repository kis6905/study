package com.leaf.http;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class HttpSession {
    private Map<String, Object> data;

    public HttpSession() {
        data = new HashMap<>();
    }

    public void setAttribute(String name, Object value) {
        data.put(name, value);
    }

    public Object getAttribute(String name) {
        return data.get(name);
    }

    @Override
    public String toString() {
        return data.keySet().stream()
            .map((key) -> {
                Object value = data.get(key);
                return "[" + key + "=" + value.toString() + "]";
            })
            .collect(Collectors.joining(","));
    }
}
