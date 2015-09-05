package org.parser;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class DetailTestClass {

    private static final String START_PAGE =
            // "http://www.superjob.ru/vacancy/search/?period=0&catalogues=&pay1=&pay0=0&experience=0&type_of_work=0&place_of_work=0&age=&pol=0&education=0&lng=0&lnlev=0&lang0=0&agency=0&moveable=0&active=0&detail_search=1&sbmit=1&extended=1&c%5B%5D=1";

            "http://hh.ru/search/vacancy?text=&salary=&currency_code=RUR&experience=doesNotMatter&order_by=relevance&search_period=30&items_on_page=20&no_magic=true";

    private static String get_html_by_link(String url) throws IOException {
        String content = "";
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        HttpResponse response = client.execute(request);

        int returnCode = response.getStatusLine().getStatusCode();
        System.out.println("Response Code : " + returnCode);  //200

        if (returnCode == 200) {
            content = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
        }
        return content;
    }

    public static void main(String[] args) throws IOException {

        BuildVocabulary();
        /*String content = "";
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(START_PAGE);
        HttpResponse response = client.execute(request);

        int returnCode = response.getStatusLine().getStatusCode();
        System.out.println("Response Code : " + returnCode);  //200

        if (returnCode == 200) {
            content = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
            Document doc = Jsoup.parse(content);

            Elements elems = doc.select("div.search-result-item");

            int countOnPage = elems.size();
            Iterator<Element> iterator = elems.iterator();
            while (iterator.hasNext()) {
                Element elem = iterator.next();

                String vacancyPageUrl = elem.select("div.search-result-item__head").first().getElementsByAttribute("href").first().attr("href");
                String contentPage = get_html_by_link(vacancyPageUrl);
                parse_html_page(contentPage);
            }
        }  */
    }

    private static String parse_html_page(String htmlContent) {
        // разбор целиком страницы, поиск вакансий на странице и отделно забрать контент только по вакансии
        String nextPageUrl = "";

        Document doc = Jsoup.parse(htmlContent);
        String vacancy_name = doc.select("h1.VacancyView_title").text();

        String Salary = doc.select("div.VacancyView_salary").select("span.h_color_green").text();
        String Experience = doc.select("div.VacancyView_salary").select("b.h_color_gray_dk").text();

        Elements ee = doc.select("div.VacancyDetails_section");
        Object obj[] = ee.toArray();
        String need_make = ((Element) obj[0]).text();
        String needs = ((Element) obj[1]).text();
        String predlagaem = ((Element) obj[2]).text();

        String company_name = doc.select("div.VacancyView_location").first().getElementsByAttribute("href").first().text();
        String company_addr = doc.select("div.VacancyView_location").first().select("span.h_color_gray").first().text();

        String vacancy_internal_number = doc.select("div.VacancyView_number").first().text();
        String[] splt = vacancy_internal_number.split(" ");
        Long vacancy_internale_id = Long.parseLong(splt[2]);

        return "";
    }

    private static void BuildVocabulary() throws IOException {
        //получение списка отраслей для фильтрации

        String content = "";
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet("https://api.hh.ru/industries");
        HttpResponse response = client.execute(request);

        int returnCode = response.getStatusLine().getStatusCode();
        System.out.println("Response Code : " + returnCode);  //200

        if (returnCode == 200) {
            content = IOUtils.toString(response.getEntity().getContent(), "UTF-8");

            Gson gson = new Gson();
            JsonElement obj = gson.fromJson(content, JsonElement.class);

            JsonArray ar = obj.getAsJsonArray();
            List<String> listNumberIndustry = new ArrayList<>();
            Iterator<JsonElement> itemRoot = ar.iterator();
            while (itemRoot.hasNext()) {
                JsonElement elem = itemRoot.next();
                listNumberIndustry.add(elem.getAsJsonObject().get("id").getAsString());  //корень забрали


                JsonArray subRoot = elem.getAsJsonObject().get("industries").getAsJsonArray();
                Iterator<JsonElement> item = subRoot.iterator();
                while (item.hasNext()) {
                    JsonElement e = item.next();

                    listNumberIndustry.add(e.getAsJsonObject().get("id").getAsString());

                }
            }

            int count = listNumberIndustry.size();
        }


    }
}


