package com.cinema.cinema.service.classes;

import com.cinema.cinema.database.dao.interfaces.LanguageDAO;
import com.cinema.cinema.database.entity.Language;
import com.cinema.cinema.service.interfaces.LanguageService;

public class LanguageServiceImpl extends MainServiceImpl<Language> implements LanguageService {
    private final LanguageDAO dao;
    public LanguageServiceImpl(LanguageDAO dao) {
        super(dao);
        this.dao = dao;
    }
}
