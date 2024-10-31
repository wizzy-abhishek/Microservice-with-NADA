package com.fakeBankDetails.fakeBank.service;

import com.fakeBankDetails.fakeBank.dto.LoginInitialDTO;
import com.fakeBankDetails.fakeBank.dto.LoginInitialResponseDTO;
import com.fakeBankDetails.fakeBank.dto.LoginResponseDTO;
import com.fakeBankDetails.fakeBank.repository.UserRepo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.SecureRandom;
import java.util.Base64;

@Service
public class AuthLoginService {

    private final AuthenticationManager authenticationManager ;
    private final UserRepo userRepo;
    private final EmailService emailService ;

    public AuthLoginService(AuthenticationManager authenticationManager,
                            UserRepo userRepo, EmailService emailService) {
        this.authenticationManager = authenticationManager;
        this.userRepo = userRepo;
        this.emailService = emailService;
    }

/*    @Transactional
    public LoginResponseDTO loginFinal(LoginDTO loginDTO){
        Authentication  authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail() , loginDTO.getOtp()));

        return new LoginResponseDTO("authenticated");
    }*/

    public String generateAESOTP() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128); // AES-128
        SecretKey secretKey = keyGen.generateKey();

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        SecureRandom random = new SecureRandom();
        byte[] randomBytes = new byte[16]; // 128 bits
        random.nextBytes(randomBytes);

        byte[] encryptedBytes = cipher.doFinal(randomBytes);

        return Base64.getEncoder().encodeToString(encryptedBytes).substring(0, 6);
    }

    public LoginInitialResponseDTO loginInitial(String email) {
        try {
            if (userRepo.existsByEmailIgnoreCase(email)) {
                String otp = generateAESOTP() ;
                emailService.sendOTPinMail(email, "OTP", otp );
                return new LoginInitialResponseDTO("OTP SENT") ;
            }
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        System.out.println(userRepo.existsById(email));
        System.out.println(email);
        System.out.println(userRepo.findById(email));
        return new LoginInitialResponseDTO("NO EMAIL");
    }
}
