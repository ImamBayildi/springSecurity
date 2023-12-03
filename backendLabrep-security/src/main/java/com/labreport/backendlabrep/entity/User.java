package com.labreport.backendlabrep.entity;

import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Entity
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String username;
    private String password;

    /** Kullanıcının hesap süresi dolmuş mu?*/
    private boolean accountNonExpired;

    /** Kullanıcının hesabı etkin mi?*/
    private boolean isEnabled;

    /**Kullanıcının hesabı kilitli mi?*/
    private boolean accountNonLocked;

    /** Kullanıcının kimlik doğrulama bilgileri süresi dolmuş mu?  */
    private boolean credentialsNonExpired;

    /** * Kullanıcının rollerini içeren Set<Role> nesnesi. */
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @JoinTable(name = "authorities", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<Role> authorities;
    
}
