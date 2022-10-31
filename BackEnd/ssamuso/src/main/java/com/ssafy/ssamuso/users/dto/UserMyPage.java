package com.ssafy.ssamuso.users.dto;

import com.ssafy.ssamuso.users.domain.Portfolios;
import com.ssafy.ssamuso.domain.entity.enumtype.TechName;
import com.ssafy.ssamuso.users.domain.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
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

    private UserMyPage() {
    }

    /**
     * 마이페이지용 DTO
     *
     * @param user       반환하려고 하는 유저
     * @param portfolios userID로 검색한 결과
     * @return
     */
    public static UserMyPage createUserMyPage(User user, Portfolios portfolios) {
        UserMyPage userMyPage = new UserMyPage();
        userMyPage.setId(user.getId());
        userMyPage.setUsername(user.getUsername());
        userMyPage.setArea(user.getArea());
        userMyPage.setTerm(user.getTerm());
        userMyPage.setTrack(user.getTrack());
        userMyPage.setClassNum(user.getClassNum());
        userMyPage.setProfileImg(user.getProfileImg());

        userMyPage.setTechstacks(new ArrayList<>());
        if (user.getUserTechstacks() != null) {
            user.getUserTechstacks()
                    .forEach(userTechstack -> userMyPage.getTechstacks().add(userTechstack.getTechstack().getTechName()));
        }
        userMyPage.setPortfolios(portfolios.getLink());

        return userMyPage;
    }

}
