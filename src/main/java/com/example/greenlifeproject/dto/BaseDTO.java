package com.example.greenlifeproject.dto;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class BaseDTO {
    private LocalDateTime createDataTime;

    private LocalDateTime updatedTime;
}
