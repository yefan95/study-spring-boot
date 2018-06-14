package com.yefan.study.spring.email.service.impl;

import com.yefan.study.spring.email.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Slf4j(topic = "EmailServiceImpl")
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendSimpleEmail(String from, String[] to, String subject, String content) {
        //创建简单邮件消息
        SimpleMailMessage message = new SimpleMailMessage();
        //设置发送人
        message.setFrom(from);
        //设置收件人
        message.setTo(to);
        //设置主题
        message.setSubject(subject);
        //设置内容
        message.setText(content);

        mailSender.send(message);
    }

    @Override
    public void sendHtmlEmail(String from, String[] to, String subject, String content) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        //true表示需要创建一个multipart message
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(message);
    }


    @Override
    public void sendAttachmentsEmail(String from, String[] to, String subject, String content, String filePath) throws MessagingException {
        //创建一个MINE消息
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        // true表示这个邮件是有附件的
        helper.setText(content, true);

        FileSystemResource file = new FileSystemResource(filePath);
        String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
        //添加附件
        helper.addAttachment(fileName, file);

        mailSender.send(message);
    }

    @Override
    public void sendInlineResourceEmail(String from, String[] to, String subject, String content, String rscPath, String rscId) throws MessagingException {
        //创建一个MINE消息
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        // true表示这个邮件是有附件的
        helper.setText(content, true);

        FileSystemResource res = new FileSystemResource(new File(rscPath));
        //添加内联资源，一个id对应一个资源，最终通过id来找到该资源
        helper.addInline(rscId, res);

        mailSender.send(message);
    }
}
