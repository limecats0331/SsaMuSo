package com.ssafy.ssamuso.users.dto;

import com.ssafy.ssamuso.users.domain.Portfolios;
import com.ssafy.ssamuso.domain.entity.enumtype.TechName;
import com.ssafy.ssamuso.users.domain.User;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class UserMyPage {
    private Long id;
    private String username;
    private String area;
    private Integer term;
    private String track;
    private Integer classNum;
    private String profileImg;
    private List<TechName> techstacks;
    private String portfolios;

    /**
     * 마이페이지용 DTO
     *
     * @param user       반환하려고 하는 유저
     * @param portfolios userID로 검색한 결과
     * @return
     */
    public static UserMyPage createUserMyPage(User user, Portfolios portfolios) {
        UserMyPage userMyPage = UserMyPage.builder()
                .id(user.getId())
                .username(user.getUsername())
                .area(user.getArea())
                .term(user.getTerm())
                .track(user.getTrack())
                .classNum(user.getClassNum())
                .profileImg(user.getProfileImg())
                .portfolios(portfolios.getLink())
                .build();

        userMyPage.setTechstacks(new ArrayList<>());
        if (user.getUserTechstacks() != null) {
            user.getUserTechstacks()
                    .forEach(userTechstack -> userMyPage.getTechstacks().add(userTechstack.getTechstack().getTechName()));
        }
        return userMyPage;
    }

}
