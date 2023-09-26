package com.utk.springbootkafka.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "wikimedia-recentchanges")
public class WikimediaData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(columnDefinition="LONGTEXT")
    private String eventData;
}
