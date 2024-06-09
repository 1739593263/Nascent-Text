package com.nascent.models;

import lombok.Data;

@Data
public class DataModel {
    /**
     * is loop existed
     */
    private boolean loop = false;

    /**
     * main template
     */
    private MainTemplate mainTemplate = new MainTemplate();

    @Data
    public static class MainTemplate {
        /**
         * Code Author
         */
        public String author = "aNDre";

        /**
         * Output Text
         */
        public String outputText = "Code holder result: ";
    }

//    public String getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(String author) {
//        this.author = author;
//    }
//
//    public String getOutputText() {
//        return outputText;
//    }
//
//    public void setOutputText(String outputText) {
//        this.outputText = outputText;
//    }
//
//    public boolean isLoop() {
//        return loop;
//    }
//
//    public void setLoop(boolean loop) {
//        this.loop = loop;
//    }
}
