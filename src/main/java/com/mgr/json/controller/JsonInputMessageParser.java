package com.mgr.json.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Klasa odpowiadajaca za sparsowanie JSONa do formatu HashMapy
 *
 * @author michal
 */
public class JsonInputMessageParser {

    private final ObjectMapper objectMapper;

    private static final Logger LOG = LoggerFactory
            .getLogger(JsonInputMessageParser.class);

    public JsonInputMessageParser(ObjectMapper objectMapper) {
        this.objectMapper = Preconditions.checkNotNull(objectMapper);
    }

    /**
     * Metoda parsujaca otrzymana wiadomosc
     *
     * @param json
     * @param eventName
     * @return
     */
    public HashMap<String, String> parse(String json, String eventName) {
        JsonNode jsonNode = parse(objectMapper, json);
        HashMap<String, String> headEntries = parseHead(jsonNode.get("event"));
        headEntries.put("EVENT_NAME", eventName);
        return headEntries;
    }

    private JsonNode parse(ObjectMapper om, String json) {
        try {
            return om.reader().readTree(json);
        } catch (IOException e) {
            LOG.info("Could not parse message.", e);
            return null;
        }
    }

    private static HashMap<String, String> parseHead(JsonNode headNode) {
        HashMap<String, String> headEntries = new HashMap<String, String>();
        Iterator<String> headItr = headNode.fieldNames();
        while (headItr.hasNext()) {
            String key = headItr.next();
            headEntries.put(key, headNode.get(key).asText());
        }
        return headEntries;
    }

}
