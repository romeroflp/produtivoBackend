package com.example.produtivoBackend.domain.users.services;

import org.springframework.stereotype.Service;

@Service
public class UserService {

//    @Autowired
//    private UserRepository userRepo;
//
//
//    public Page<AuthenticationUserResponseDTO> findAll(Pageable pageable){
//        Page<Users> users = userRepo.findAll(pageable);
//        return new PageImpl<>(users.stream().map(AuthenticationUserResponseDTO::new).toList());
//
//    }
//
//    public UserResponseDTO updateNameUser(String email,
//                                          String newName){
//        Users user = userRepo.findByEmail(email);
//
//        if(newName.length() <= 2 || newName.length() > 30 ||
//                newName.matches("^[A-ZÀ-Úa-zà-ú][A-ZÀ-Úa-zà-ú]*(?:[ '-][A-ZÀ-Úa-zà-ú]+)*$") ||
//                newName.isBlank()){
//            throw new InvalidCredentialsException("O nome informado não é permitido. Tente novamente.");
//        }
//        user.setName(newName.trim());
//        return new UserResponseDTO(userRepo.save(user));
//
//    }
//
//    public void deleteUser(String email){
//        userRepo.delete(userRepo.findByEmail(email));
//    }


}
