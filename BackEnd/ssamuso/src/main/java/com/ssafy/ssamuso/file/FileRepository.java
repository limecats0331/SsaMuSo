package com.ssafy.ssamuso.file;


import com.ssafy.ssamuso.domain.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface FileRepository extends CrudRepository<File, Long> {

    File save(File file);
    File findByChangedName(String fileName);
    ArrayList<File> findByBoardId(long brdId);
}
