package com.lonecode.mymoviecatalogue3;

public class Globals {
    private static Globals instance;

    private String language = "en-US";

    // Restrict the constructor from being instantiated
    private Globals(){}

    public void setLanguage(String l) {
        this.language = l;
    }

    public String getLanguage() {
        return this.language;
    }

    public static synchronized Globals getInstance() {
        if (instance == null) {
            instance = new Globals();
        }

        return instance;
    }
}
