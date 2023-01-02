package com.ssafy.ssamuso.service;

import com.ssafy.ssamuso.domain.dto.IdentificationDto;
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
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PortfoliosRepository portfoliosRepository;
    private final UserTechstackRepository userTechstackRepository;
    private final UserDeleteRepository userDeleteRepository;

    @Override
    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> findByUsername(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
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

    @Override
    public void deleteUser(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        user.orElseThrow(() -> new IllegalArgumentException("No User"));

        UserDelete userDelete = makeDeleteUser(user.get());

        userDeleteRepository.save(userDelete);
        userRepository.deleteById(user.get().getId());
    }

    @Override
    public boolean identify(IdentificationDto identificationDto) {
        List<String> keyList = userRepository.findRoutingKeyByUsername(identificationDto.getUsername());
        for (String key : keyList) {
            if (key.equals(identificationDto.getRoutingKey())) {
                return true;
            }
        }
        return false;
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
