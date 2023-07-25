package com.example.greenlifeproject.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor//매개변수를 전부다 받는 생성자
@NoArgsConstructor//디폴트 생성자
@MappedSuperclass//부모 클래스로만 사용하고 테이블은 따로 생성x
public class BaseEntity {

    @CreationTimestamp
    @Column(name = "created_at")//만든 시간
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createDataTime;

    @CreationTimestamp
    @Column(name = "updated_at")//수정 시간
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedTime;

}
