package com.ssafy.ssamuso.domain.entity.enumtype;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TechName {
    React("React"), Spring("Spring"), JPA("JPA");

    private final String description;
}
