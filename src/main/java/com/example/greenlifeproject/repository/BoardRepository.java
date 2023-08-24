package com.example.greenlifeproject.repository;

import com.example.greenlifeproject.entity.MemberEntity;
import com.example.greenlifeproject.entity.boardEntitys.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<BoardEntity,Long> {

    @Modifying//UPDATE 나 DELETE 같은 쿼리 를 사용할때 붙혀주기
    @Query(value = "update BoardEntity b set b.boardHits = b.boardHits + 1 where b.id = :id")
    void updateHits(@Param("id") Long id);

    int countByMember(MemberEntity member);

    BoardEntity findBoardEntityById(Long id);
}
