package com.yefan.study.spring.email.controller;

import com.yefan.study.spring.email.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;

@Slf4j(topic = "MailController")
@Controller
@RequestMapping(value = "/mail")
public class MailController {

    @Autowired
    private EmailService emailService;

    @Value("${mail.fromMail.address}")
    private String from;

    @Value("${mail.toMail.address}")
    private String[] to;

    @GetMapping(value = "/sendSimple")
    @ResponseBody
    public String sendSimpleEmail() {

        String subject = "测试简单邮件";

        String content = "测试邮件内容";

        emailService.sendSimpleEmail(from, to, subject, content);

        return "success";
    }


    @GetMapping(value = "/sendHtml")
    @ResponseBody
    public String sendHtmlEmail() {

        String subject = "测试HTML邮件";

        String content = "<html>\n" +
                "<body>\n" +
                "\n" +
                "<p>这是段落。</p>\n" +
                "<p>这是段落。</p>\n" +
                "<p>这是段落。</p>\n" +
                "\n" +
                "<p>段落元素由 p 标签定义。</p> \n" +
                "\n" +
                "</body>\n" +
                "</html>";

        try {
            emailService.sendHtmlEmail(from, to, subject, content);
        } catch (MessagingException e) {
            return "fail";
        }

        return "success";
    }

    @GetMapping(value = "/sendAttach")
    @ResponseBody
    public String sendAttachmentsEmail() {

        String subject = "测试带附件的邮件";

        String content = "测试邮件内容";

        String filePath = "/Users/yefan/Documents/WorkSpace/IDEAProjects/study-spring-boot/spring-boot-email-sample/src/main/resources/测试附件.txt";

        try {
            emailService.sendAttachmentsEmail(from, to, subject, content, filePath);
        } catch (MessagingException e) {
            return "fail";
        }

        return "success";
    }

    @GetMapping(value = "/sendInline")
    @ResponseBody
    public String sendInlineResourceEmail() {

        String subject = "测试带静态资源的邮件";

        String content = "测试邮件内容";

        String filePath = "/Users/yefan/Documents/WorkSpace/IDEAProjects/study-spring-boot/spring-boot-email-sample/src/main/resources/内联附件.JPG";

        try {
            emailService.sendInlineResourceEmail(from, to, subject, content, filePath, "111");
        } catch (MessagingException e) {
            return "fail";
        }

        return "success";
    }

}


