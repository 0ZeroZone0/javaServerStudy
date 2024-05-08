package com.group.libraryapp.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByName(String name);

    boolean existsByName(String name); //존재 여부에 따른 값

    long countByAge(Integer age); //해당 이름을 가진 유저의 명수가 반환

}
