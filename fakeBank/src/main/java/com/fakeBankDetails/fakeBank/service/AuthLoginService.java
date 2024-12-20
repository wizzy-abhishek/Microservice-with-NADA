package com.fakeBankDetails.fakeBank.service;

import com.fakeBankDetails.fakeBank.dto.AccountHoldersDetailsDTO;
import com.fakeBankDetails.fakeBank.dto.LoginInitialResponseDTO;
import com.fakeBankDetails.fakeBank.dto.FinalLoginResponseDTO;
import com.fakeBankDetails.fakeBank.dto.UserLoginFinalDTO;
import com.fakeBankDetails.fakeBank.entity.UserEntity;
import com.fakeBankDetails.fakeBank.repository.UserRepo;
import com.fakeBankDetails.fakeBank.service.interfaces.AuthLoginServiceInterface;
import com.fakeBankDetails.fakeBank.service.interfaces.EmailServiceInterface;
import com.fakeBankDetails.fakeBank.service.interfaces.JWTServiceInterface;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.InvalidTimeoutException;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthLoginService implements AuthLoginServiceInterface {

    private final AuthenticationManager authenticationManager ;
    private final UserRepo userRepo;
    private final EmailServiceInterface emailService ;
    private final PasswordEncoder passwordEncoder ;
    private final PasswordEncoder passwordEncoderBCrypt ;
    private final JWTServiceInterface jwtService ;
    private final ModelMapper modelMapper ;

    public AuthLoginService(AuthenticationManager authenticationManager,
                            UserRepo userRepo, EmailServiceInterface emailService, PasswordEncoder passwordEncoder, JWTServiceInterface jwtService, ModelMapper modelMapper) {
        this.authenticationManager = authenticationManager;
        this.userRepo = userRepo;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.modelMapper = modelMapper;
        this.passwordEncoderBCrypt = new BCryptPasswordEncoder() ;
    }

    @Transactional
    public FinalLoginResponseDTO loginFinal(UserLoginFinalDTO loginDTO){

        Authentication  authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken( loginDTO.getEmail() , loginDTO.getOtp()));

        UserEntity user = (UserEntity) authentication.getPrincipal();
        user.setOtp(passwordEncoderBCrypt.encode(generateAESOTP()));
        userRepo.save(user);

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        List<AccountHoldersDetailsDTO> getAccountDTO = user.getAccountHoldersDetailsList().stream()
                .map(accountHolderEntity -> modelMapper.map(accountHolderEntity , AccountHoldersDetailsDTO.class))
                .toList();

        return new FinalLoginResponseDTO(user.getEmail() , accessToken , refreshToken , getAccountDTO);
    }

    private String generateAESOTP() {

        KeyGenerator keyGen = null;
        try {
            keyGen = KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        keyGen.init(128);
        SecretKey secretKey = keyGen.generateKey();

        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException(e);
        }
        try {
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }

        SecureRandom random = new SecureRandom();
        byte[] randomBytes = new byte[16];
        random.nextBytes(randomBytes);

        byte[] encryptedBytes = null;
        try {
            encryptedBytes = cipher.doFinal(randomBytes);
        } catch (BadPaddingException | IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        }
        return Base64.getEncoder().encodeToString(encryptedBytes).substring(0, 6);
    }

    public LoginInitialResponseDTO loginInitial(String email)  {
        try {
            if (userRepo.existsByEmailIgnoreCase(email)) {
                String otp = generateAESOTP() ;
                UserEntity user = userRepo.findById(email).orElseThrow();
                emailService.sendOTPinMail(email, "OTP", otp );
                System.out.println(otp);
                user.setOtp(passwordEncoder.encode(otp));
                userRepo.save(user);
                return new LoginInitialResponseDTO("OTP SENT") ;
            }
        }catch (Exception exception){
           throw new RuntimeException(exception);
        }
        return new LoginInitialResponseDTO("NO EMAIL");
    }
}
