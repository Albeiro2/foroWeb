package com.albeirobaena.foroweb.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "reports")
public class ReportEntity {

    @Id
    private String id;
    private String userId;
    private String idPost;
    private String reason;
    private String explication;
}
