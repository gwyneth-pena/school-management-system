package com.example.Login.Service;

import com.example.Login.User.User;
import com.example.Login.Web.RegisterDataTransferObj;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService{
    User save(RegisterDataTransferObj registerDataTransferObj);
}