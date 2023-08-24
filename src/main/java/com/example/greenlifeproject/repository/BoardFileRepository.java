package com.example.greenlifeproject.repository;

import com.example.greenlifeproject.entity.boardEntitys.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardFileRepository extends JpaRepository<FileEntity,Long> {

}
