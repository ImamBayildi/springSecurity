package com.labreport.backendlabrep.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.labreport.backendlabrep.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Kullanıcı adına göre bir kullanıcıyı arar.
     * @param userName Kullanıcı adı
     * @return Kullanıcıyı içeren bir Optional nesnesi
     */

     Optional<User> findByUsername(String userName);
    
}
