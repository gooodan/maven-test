package learn.spring.service.impl;

import learn.spring.service.MessageService;

public class MessageServiceImpl implements MessageService {

    @Override
    public String getMessage() {
        return "hello world";
    }
}
