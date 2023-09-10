package com.example.sweater.repo;

import com.example.sweater.domain.Role;
import com.example.sweater.domain.UserInfo;
import org.springframework.data.repository.CrudRepository;

public interface UserInfoRepo extends CrudRepository<UserInfo, Long> {
   UserInfo findByUsername(String username);

   Iterable<UserInfo> findByRole(Role role);
}
