package com.example.sweater.service;

import com.example.sweater.domain.Profile;
import com.example.sweater.domain.UserInfo;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RegistrationService {
    private static Profile user;
    private static String key;

    public String getKey(Profile user){
        this.user = user;

        Random r = new Random();

        key = "";
        for(int i=0; i<8; ++i){
            key += (char) (r.nextInt() % 26 + 'a');
        }

        return key;
    }

    public Boolean checkKey(String key){
        return this.key.equals(key);
    }

    public Profile getProfile(){
        return user;
    }
}
