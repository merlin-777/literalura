package com.x.literalura.infra.repository;

import com.x.literalura.domain.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LanguageRepository  extends JpaRepository<Language,Long> {

    Optional<Language> findByCode(String code);

}
