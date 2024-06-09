package com.nascent.models;

import lombok.Data;


@Data
public class DataModel {
    /**
     * if generating .gitignore file
     */
    public boolean gitNeeded = true;
    /**
     * Loop or not
     */
    public boolean loop = false;
    /**
     * Main Template
     */
    public MainTemplate mainTemplate ;

    /**
     * Leverage to generate the core template for clients
     */
    @Data
    public static class MainTemplate {
        /**
         * Author annotation
         */
        public String author = "aNDre";
        /**
         * output text
         */
        public String outputText = "Code result: ";
    }
}
