package com.x.literalura.models;

public enum Language {
    EN("en","Ingles"),
    ES("es","Español");

    private String languageGutendex;
    private String languageDisplay;

    Language(String languageGutendex, String languageDisplay){
        this.languageGutendex=languageGutendex;
        this.languageDisplay=languageDisplay;
    }
    public static Language fromString(String text) {
        for (Language language : Language.values()) {
            if (language.languageGutendex.equalsIgnoreCase(text)) {
                return language;
            }
        }
        throw new IllegalArgumentException("Ningun idioma encontrado: " + text);
    }
    public static Language fromEspanol(String text) {
        for (Language language : Language.values()) {
            if (language.languageDisplay.equalsIgnoreCase(text)) {
                return language;
            }
        }
        throw new IllegalArgumentException("Ningun idioma encontrado: " + text);
    }
}