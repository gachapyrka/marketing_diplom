package com.example.sweater.repo;

import com.example.sweater.domain.Profile;
import com.example.sweater.domain.UserInfo;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepo extends CrudRepository<Profile, Long> {
}
