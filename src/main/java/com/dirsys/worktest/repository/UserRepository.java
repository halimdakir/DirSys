package com.dirsys.worktest.repository;

import com.dirsys.worktest.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findUsersByEmail(String email);
}
