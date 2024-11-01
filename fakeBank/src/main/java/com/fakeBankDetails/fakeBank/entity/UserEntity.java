package com.fakeBankDetails.fakeBank.entity;

import com.fakeBankDetails.fakeBank.enums.Roles;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class UserEntity implements UserDetails {

    @Id
    private String email;

    private long mobile;

    private String otp ;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING )
    private Set<Roles> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true ,fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<AccountHoldersDetails> accountHoldersDetailsList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return this.otp;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

}
