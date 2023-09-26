package com.utk.springbootkafka.repositories;

import com.utk.springbootkafka.entities.WikimediaData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WikimediaDataRepository extends JpaRepository<WikimediaData, Long> {
}
