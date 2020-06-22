package com.home.redditclone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotoficationEmail {
    private String subject;
    private String recipient;
    private String body;
}
