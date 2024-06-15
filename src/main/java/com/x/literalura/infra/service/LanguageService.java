package com.x.literalura.infra.service;

import com.x.literalura.domain.Language;
import com.x.literalura.infra.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {

    @Autowired
    private LanguageRepository languageRepository;

    public Optional<Language> getLanguageByCode(String languageName) {
        return languageRepository.findByCode(languageName);
    }

    public Language saveLanguage(Language language) {
        return languageRepository.save(language);
    }

    public List<Language> findAll() {
        return languageRepository.findAll();
    }
}
