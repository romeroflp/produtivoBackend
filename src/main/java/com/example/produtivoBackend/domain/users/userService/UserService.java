package com.example.produtivoBackend.domain.users.userService;

import com.example.produtivoBackend.domain.users.DTOs.UserRequestDTO;
import com.example.produtivoBackend.domain.users.DTOs.UserResponseDTO;
import com.example.produtivoBackend.domain.users.Users;
import com.example.produtivoBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public UserResponseDTO registerUser(UserRequestDTO request){
        if(userRepo.findByEmail(request.email()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user already exists.");
        }

        Users newUser = new Users(request);
        newUser.setPassword(new BCryptPasswordEncoder().encode(request.password()));

        
        return new UserResponseDTO(userRepo.save(newUser));


    }


}
