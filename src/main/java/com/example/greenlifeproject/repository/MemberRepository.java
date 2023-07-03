package com.example.greenlifeproject.repository;

import com.example.greenlifeproject.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity,Long> {
    MemberEntity findByEmail(String email);


}
