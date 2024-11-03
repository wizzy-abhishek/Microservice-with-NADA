package com.fakeBankDetails.fakeBank.service;

import com.fakeBankDetails.fakeBank.dto.SignUpDTO;
import com.fakeBankDetails.fakeBank.dto.UserDTO;
import com.fakeBankDetails.fakeBank.entity.UserEntity;
import com.fakeBankDetails.fakeBank.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final ModelMapper modelMapper ;
    private final UserRepo userRepo ;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByEmailIgnoreCase(username)
                .orElseThrow(() -> new BadCredentialsException("Account not found with email + " + username));
    }

    @Transactional
    public UserDTO signUp(SignUpDTO signUpDTO){

        Optional<UserEntity> user = userRepo.findByEmailIgnoreCase(signUpDTO.getEmail());
        if (user.isPresent()){
            throw new BadCredentialsException("Email already present " + signUpDTO.getEmail());
        }
        UserEntity toBeCreatedUser = modelMapper.map(signUpDTO , UserEntity.class);

        UserEntity savingUser = userRepo.save(toBeCreatedUser);
        return modelMapper.map(savingUser , UserDTO.class);
    }


}
