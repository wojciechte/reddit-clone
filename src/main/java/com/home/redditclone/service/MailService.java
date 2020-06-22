package com.home.redditclone.service;

import com.home.redditclone.exceptions.RedditCloneMailException;
import com.home.redditclone.model.NotoficationEmail;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class MailService {

    private final JavaMailSender mailSender;
    private final MailContentBuilder mailContentBuilder;

    public void sendMail(NotoficationEmail email) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("wojtur91@gmail.com");
            messageHelper.setTo(email.getRecipient());
            messageHelper.setSubject(email.getSubject());
            messageHelper.setText(mailContentBuilder.build(email.getBody()));
        };

        try {
            mailSender.send(messagePreparator);
            log.info("Email sent");
        } catch (MailException e) {
            log.error(e.getLocalizedMessage());
            throw new RedditCloneMailException("Exception occurred when sending mail to " + email.getRecipient());
        }
    }
}
