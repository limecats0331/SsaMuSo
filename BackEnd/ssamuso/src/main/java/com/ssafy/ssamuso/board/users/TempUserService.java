package com.ssafy.ssamuso.board.users;

import com.ssafy.ssamuso.domain.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TempUserService {

    private final TempUserRepository tempUserRepository;

    public Optional<User> findByUsername(String name){
        return tempUserRepository.findByUsername(name);
    }
}
