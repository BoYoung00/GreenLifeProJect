package com.example.greenlifeproject.repository;

import com.example.greenlifeproject.entity.DepressionTestResults.DepressionTestResultEntity;
import com.example.greenlifeproject.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepressionTestResultRepository extends JpaRepository<DepressionTestResultEntity,Long>{
    List<DepressionTestResultEntity> findAllByMemberOrderByDateDesc(MemberEntity member);

}
