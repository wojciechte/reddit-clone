package com.home.redditclone.service;

import com.home.redditclone.dto.RegisterRequest;
import com.home.redditclone.exceptions.InvalidTokenException;
import com.home.redditclone.exceptions.RedditCloneException;
import com.home.redditclone.model.NotoficationEmail;
import com.home.redditclone.model.User;
import com.home.redditclone.model.VerificationToken;
import com.home.redditclone.repository.UserRepository;
import com.home.redditclone.repository.VerificationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;

    @Transactional
    public void signup(RegisterRequest registerRequest) {
        User user = createUser(registerRequest);
        userRepository.save(user);

        String token = generateVerificationToken(user);

        String ACTIVATION_URL = "http://localhost:8080/api/auth/accountVerification/";
        mailService.sendMail(
                new NotoficationEmail(
                        "Please Activate your Account",
                        user.getEmail(),
                        "please click on the below url to activate your accout : " +
                                ACTIVATION_URL + token)
        );
    }

    private User createUser(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreated(Instant.now());
        user.setEnabled(false);

        return user;
    }

    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationTokenRepository.save(verificationToken);

        return token;
    }

    @Transactional
    public void verifyAccount(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token)
                .orElseThrow(() -> new InvalidTokenException("Invalid Token"));
        fetchUserAndEnable(verificationToken);
    }

    private void fetchUserAndEnable(VerificationToken verificationToken) {
        String username = verificationToken.getUser().getUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RedditCloneException("User not found with name: " + username));
        user.setEnabled(true);
        userRepository.save(user);
    }
}
