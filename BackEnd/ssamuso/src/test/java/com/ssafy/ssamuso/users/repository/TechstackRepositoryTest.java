package com.ssafy.ssamuso.users.repository;

import com.ssafy.ssamuso.common.repository.TechstackRepository;
import com.ssafy.ssamuso.domain.entity.enumtype.TechName;
import com.ssafy.ssamuso.common.domain.Techstack;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TechstackRepositoryTest {

    @Autowired
    TechstackRepository techstackRepository;

    @Test
    void save() throws Exception {
        //Given
        Techstack techstack = new Techstack();
        techstack.setTechName(TechName.Spring);

        //When
        Techstack saved = techstackRepository.save(techstack);

        //Then
        assertThat(saved.getTechName()).isEqualTo(TechName.Spring);
    }

    @Test
    void findById() throws Exception {
        //Given
        Techstack techstack = new Techstack();
        techstack.setTechName(TechName.Spring);

        //When
        Techstack saved = techstackRepository.save(techstack);
        long id = saved.getId();

        //Then
        assertThat(saved).isEqualTo(techstackRepository.findById(id).get());
    }
}