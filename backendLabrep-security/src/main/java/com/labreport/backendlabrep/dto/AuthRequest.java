package com.labreport.backendlabrep.dto;

/**
 * Kimlik doğrulama isteği için kullanılan DTO (Data Transfer Object) sınıfı.
 * 
 * Bu sınıf, kullanıcı kimlik doğrulama isteği için kullanılır. `username` ve `password`
 * alanlarını içerir ve bu alanlarla birlikte isteği temsil eder.
 */
public record AuthRequest(
        String username,
        String password
) {
    // Otomatik olarak getter metodları ve constructor oluşturulur.
    // Record sınıfı, basit veri taşıma amacıyla kullanılır ve otomatik olarak
    // equals, hashCode ve toString metotlarını sağlar.
}