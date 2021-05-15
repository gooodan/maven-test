package learn.spring;

import learn.spring.service.MessageService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DemoMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:application.xml");
        System.out.println("context 启动成功");
        MessageService messageService = applicationContext.getBean(MessageService.class);

        System.out.println(messageService.getMessage());
    }
}
