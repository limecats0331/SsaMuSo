package com.ssafy.ssamuso.service;

import com.amazonaws.services.ec2.model.UserData;
import com.ssafy.ssamuso.domain.dto.TeammateInfoDTO;
import com.ssafy.ssamuso.domain.entity.Portfolios;
import com.ssafy.ssamuso.domain.entity.User;
import com.ssafy.ssamuso.domain.entity.UserDelete;
import com.ssafy.ssamuso.domain.entity.UserTechstack;
import com.ssafy.ssamuso.repository.PortfoliosRepository;
import com.ssafy.ssamuso.repository.UserDeleteRepository;
import com.ssafy.ssamuso.repository.UserRepository;
import com.ssafy.ssamuso.repository.UserTechstackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImlp {
    private final UserRepository userRepository;
    private final PortfoliosRepository portfoliosRepository;
    private final UserTechstackRepository userTechstackRepository;
    private final UserDeleteRepository userDeleteRepository;

    Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    public Optional<User> findByUsername(String name) {
        return userRepository.findByUsername(name);
    }

    public Optional<TeammateInfoDTO> findTeammateByUsername(String username) {
        Optional<User> findUser = userRepository.findByUsername(username);
        if (findUser.isEmpty()) {
            return Optional.empty();
        }

        Optional<Portfolios> findPortfolios = portfoliosRepository.findByUser(findUser.get());
        Optional<List<UserTechstack>> findUserTeckstacks = userTechstackRepository.findAllByUser(findUser.get());

        TeammateInfoDTO teammateInfoDTO = new TeammateInfoDTO();

        teammateInfoDTO.setUsername(findUser.get().getUsername());
        if (findPortfolios.isPresent()) {
            teammateInfoDTO.setPortfoliosLink(findPortfolios.get().getLink());
        }
        if (findUserTeckstacks.isPresent()) {
            for (UserTechstack userTechstack : findUserTeckstacks.get()) {
                teammateInfoDTO.getTechstacks().add(userTechstack.getTechstack().getTechName());
            }
        }

        return Optional.of(teammateInfoDTO);
    }

    private void deleteUser(User user) {
        UserDelete userDelete = makeDeleteUser(user);
        userDeleteRepository.save(userDelete);
        userRepository.deleteById(user.getId());
    }

    private UserDelete makeDeleteUser(User user) {
         return UserDelete.builder()
                .id(user.getId())
                .area(user.getArea())
                .track(user.getTrack())
                .password(user.getPassword())
                .userTechstacks(user.getUserTechstacks())
                .email(user.getEmail())
                .profileImg(user.getProfileImg())
                .term(user.getTerm())
                .classNum(user.getClassNum())
                .role(user.getRole())
                .name(user.getName())
                .username(user.getUsername())
                .build();
    }
}
