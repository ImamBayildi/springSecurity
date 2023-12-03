package com.labreport.backendlabrep.dto;

import java.util.Set;

import com.labreport.backendlabrep.entity.Role;

import lombok.Builder;

/**
 * Kullanıcı oluşturma isteği için kullanılan DTO (Data Transfer Object) sınıfı.
 *
 * Bu sınıf, yeni bir kullanıcı oluşturma isteği için kullanılır. `name`, `username`,
 * `password` ve `authorities` alanlarını içerir ve bu alanlarla birlikte isteği temsil eder.
 */
@Builder
public record CreateUserRequest(
        String name,
        String username,
        String password,
        Set<Role> authorities
) {
    // Otomatik olarak getter metodları ve constructor oluşturulur.
    // Record sınıfı, basit veri taşıma amacıyla kullanılır ve otomatik olarak
    // equals, hashCode ve toString metotlarını sağlar.
}