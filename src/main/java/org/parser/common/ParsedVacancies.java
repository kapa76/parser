package org.parser.common;


import org.jsoup.nodes.Document;

public class ParsedVacancies {
    private Document doc;
    private Integer size;
    private Integer qty;

    public ParsedVacancies(Document doc) {
        this.doc = doc;
    }

    public Document getDoc() {
        return doc;
    }

    public void setDoc(Document doc) {
        this.doc = doc;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
}
