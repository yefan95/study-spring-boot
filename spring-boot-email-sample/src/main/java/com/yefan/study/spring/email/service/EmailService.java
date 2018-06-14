package com.yefan.study.spring.email.service;

import javax.mail.MessagingException;

public interface EmailService {
    /**
     * 发送简单的邮件
     *
     * @param from
     * @param to
     * @param subject
     * @param content
     */
    void sendSimpleEmail(String from, String[] to, String subject, String content);

    /**
     * 发送html格式的邮件
     *
     * @param from
     * @param to
     * @param subject
     * @param content
     */
    void sendHtmlEmail(String from, String[] to, String subject, String content) throws MessagingException;

    /**
     * 发送带附件的邮件
     *
     * @param from
     * @param to
     * @param subject
     * @param content
     * @param filePath
     */
    void sendAttachmentsEmail(String from, String[] to, String subject, String content, String filePath) throws MessagingException;

    /**
     * 发送带静态资源的邮件
     *
     * @param from
     * @param to
     * @param subject
     * @param content
     * @param rscPath
     * @param rscId
     */
    void sendInlineResourceEmail(String from, String[] to, String subject, String content, String rscPath, String rscId) throws MessagingException;
}
