package com.labreport.backendlabrep.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * Kullanıcı rollerini temsil eden enum sınıfı.
 * 
 * Bu enum sınıfı, kullanıcı rollerini tanımlamak için kullanılır ve Spring Security
 * tarafından yetkilendirme işlemleri için kullanılır. Aynı zamanda `GrantedAuthority`
 * arayüzünü uygular.
 */
public enum Role implements GrantedAuthority {

    ROLE_USER("USER"),
    ROLE_ADMIN("ADMIN"),
    ROLE_MOD("MOD"),
    ROLE_FSK("FSK");

    private String value;

    /**
     * Role enumunun yapıcı metodu.
     * @param value Role değeri
     */
    Role(String value) {
        this.value = value;
    }

    /**
     * Role değerini döndürür.
     * @return Role değeri
     */
    public String getValue() {
        return this.value;
    }

    /**
     * GrantedAuthority arayüzünden gelen metodu uygular.
     * @return Kullanıcı rolünün yetki adı
     */
    @Override
    public String getAuthority() {
        return name();
    }
}