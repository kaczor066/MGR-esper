package com.mgr.json.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mgr.common.queue.PassEventQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * Kontroler rejestrowany w kontenerze springa obslugujacy odbieranie
 * komunikatow w formacie JSON
 *
 * @author michal
 */
@Controller
public class JsonMessageController {

    private static final Logger LOG = LoggerFactory
            .getLogger(JsonMessageController.class);

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    PassEventQueue queue;
    JsonInputMessageParser mapper;

    /**
     * Metoda odbierajaca wiadomosci na adresie host/events/nazwaEventu
     *
     * @param name
     * @param json
     * @param request
     * @param response
     * @return
     * @throws JsonProcessingException
     * @throws InterruptedException
     */
    @RequestMapping(value = "/events/{name}", method = {RequestMethod.POST,
            RequestMethod.PUT}, consumes = {"application/json"})
    @ResponseBody
    public DeferredResult<HttpEntity<String>> addJson(
            @PathVariable("name") String name, @RequestBody String json,
            HttpServletRequest request, HttpServletResponse response)
            throws JsonProcessingException, InterruptedException {

        LOG.debug("Received a new message.");
        LOG.debug(json.toString());

        HashMap<String, String> map = mapper.parse(json.toString(), name);
        queue.put(map);
        return null;
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public
    @ResponseBody
    String error(Exception e) {
        LOG.error("Unhandled exception", e);
        MDC.clear();
        return "";
    }

    @PostConstruct
    public void init() {
        mapper = new JsonInputMessageParser(objectMapper);
    }
}
