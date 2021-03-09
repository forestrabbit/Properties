package com.fr;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;

public class Properties {
    private Map<String, List<String>> map;

    public Properties() {
        map = new HashMap<>();
    }

    public List<String> getValue(String key) {
        return map.get(key);
    }

    public void putValue(String key, String value) {
        if (map.containsKey(key)) {
            map.get(key).add(value);
        } else {
            List<String> list = new ArrayList<>();
            list.add(value);
            map.put(key, list);
        }
    }

    public void write(BufferedWriter writer) throws IOException {
        for (var entry: map.entrySet()) {
            for (var value: entry.getValue()) {
                writer.write(entry.getKey() + " = " + value + "\n");
            }
        }
        writer.flush();
    }

    public void load(BufferedReader reader) throws IllegalArgumentException, IOException {
        int cnt = 0;

        String data = null;
        while ((data = reader.readLine()) != null) {
            cnt++;
            int status = 0;
            var key = new StringBuilder();
            var value = new StringBuilder();
            char x = ' ';

            parse: for (int i = 0; i < data.length(); i++) {
                x = data.charAt(i);
                if (x == '#') {
                    break;
                }
                if (x == ' ' || x == '\t' || x == '\n') {
                    continue;
                }
                switch (status) {
                    case 0:
                        status = 1;
                        if (x == '=') {
                            break parse;
                        }
                        key.append(x);
                        break;
                    case 1:
                        if (x != '=') {
                            key.append(x);
                        } else {
                            status = 2;
                        }
                        break;
                    case 2:
                        value.append(x);
                        break;
                }
            }

            if (status != 2 && status != 0) {
                throw new IllegalArgumentException("第" + cnt + "行出错，出错字符：" + x);
            }
            if (status == 0 && x == ' ') {
                continue;
            }
            if (map.containsKey(key.toString())) {
                map.get(key.toString()).add(value.toString());
            } else {
                List<String> list = new ArrayList<>();
                list.add(value.toString());
                map.put(key.toString(), list);
            }
        }
    }

    @Override
    public String toString() {
        var builder = new StringBuilder();
        for (var entry: map.entrySet()) {
            builder.append("Key: " + entry.getKey() + " Value: " + entry.getValue() + "\n");
        }
        return builder.toString();
    }
}