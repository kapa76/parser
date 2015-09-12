package org.parser.common;

import org.jsoup.nodes.Document;

public class HtmlVacPage {
    private String url;
    private String html;
    private Document doc;

    public HtmlVacPage(String url, String html){
        this.html = html;
        this.url = url;

    }

    public HtmlVacPage(Document doc, String htmlLoaded, String link) {
        this.html = htmlLoaded;
        this.url = link;
        this.doc = doc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public Document getDoc() {
        return doc;
    }

    public void setDoc(Document doc) {
        this.doc = doc;
    }
}
