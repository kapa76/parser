package org.parser.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;


public class CommonParserSJ {
    public static long getCountVacancy(Document html) {
        String countValue = html.select("span.VacancySearchResult_ctrl_result").first().text();
        return Long.getLong(countValue.replaceAll("\\D", ""));
    }

    public static List<String> getEducationList(Document html) {
        List<String> eList = new ArrayList<>();
        String part = html.select("div.VacancySearchForm_form_content").html();
        Document htmlPart = Jsoup.parse(part);

        Elements elements = Jsoup.parse(htmlPart.childNodes().get(0).childNodes().get(1).childNode(20).toString()).select("div.sj_select_option");
        for (Element e : elements) {
            eList.add(e.text());
        }
        return eList;
    }

    public static List<String> getExperienceList(Document html) {
        List<String> eList = new ArrayList<>();
        String part = html.select("div.VacancySearchForm_form_content").html();
        Document htmlPart = Jsoup.parse(part);

        Elements elements = Jsoup.parse(htmlPart.childNodes().get(0).childNodes().get(1).childNode(10).toString()).select("div.sj_select_option");
        for (Element e : elements) {
            eList.add(e.text());
        }
        return eList;
    }
}
