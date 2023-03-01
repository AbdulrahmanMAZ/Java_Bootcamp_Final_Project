package com.abdulrahman.final_Project.MyUser;

import com.abdulrahman.final_Project.Advisor.Advisor;
import com.abdulrahman.final_Project.Start_up.StartUp;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Pattern(regexp = "^.+@.+\\.\\w{2,}$" , message = "must be in this form XXX@XX.XX")
    @Column(columnDefinition = "varchar(40) unique not null")
    private String username;
    @Column(nullable = false)
    private String password;
    @Pattern(regexp = "^STARTUP||ADVISOR||ADMIN$",message = "Role must be user only")
    @Column(columnDefinition = "varchar(10) not null check (role='STARTUP' || role='ADVISOR'|| role='ADMIN'  )")
    private String role;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Advisor advisor;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
    @PrimaryKeyJoinColumn
    private StartUp startUp;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
