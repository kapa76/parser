package org.parser;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.CookieSpecFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
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


        String content = "";
        DefaultHttpClient client = new DefaultHttpClient();
        client.getCookieSpecs().register("lenient", new CookieSpecFactory() {
            public CookieSpec newInstance(HttpParams params) {
                return new LenientCookieSpec();
            }
        });
        HttpClientParams.setCookiePolicy(client.getParams(), "lenient");

        HttpGet request = new HttpGet("http://hh.ru/search/vacancy?items_on_page=100&industry=51.675");
        //List<NameValuePair> params = new LinkedList<NameValuePair>();

//        HttpParams params=new BasicHttpParams();

//        params.setParameter("Set-Cookie", "_xsrf=1de7669a35596983f198be3343ec3450; Domain=.hh.ru; Path=/");
//        params.setParameter("Set-Cookie", "_xsrf=1de7669a35596983f198be3343ec3450; Path=/");
//        params.setParameter("Set-Cookie", "crypted_id=; Domain=.hh.ru; expires=Fri, 12 Sep 2014 21:41:38 GMT; Path=/");
//        params.setParameter("Set-Cookie", "crypted_id=; expires=Fri, 12 Sep 2014 21:41:38 GMT; Path=/");
//        params.setParameter("Set-Cookie", "regions=1; expires=Sun, 11 Sep 2016 21:41:38 GMT; Path=/");
//        params.setParameter("Set-Cookie", "hhrole=anonymous; Path=/");
//        params.setParameter("Set-Cookie", "unique_banner_user=1442094098.22446796855321; expires=Sun, 13 Sep 2015 21:41:38 GMT; Path=/");
//        params.setParameter("Expires", "Sat, 12 Sep 2015 21:41:37 GMT");
//        params.setParameter("Cache-Control", "no-cache");
//        params.setParameter("X-Request-ID", "1442094098169624c04150f21df2e4f2");
//        params.setParameter("X-Frame-Options", "SAMEORIGIN");
//        params.setParameter("X-Content-Type-Options", "nosniff");

//        request.setParams(params);


        HttpResponse response = client.execute(request);

        int returnCode = response.getStatusLine().getStatusCode();
        System.out.println("Response Code : " + returnCode);  //200

        if (returnCode == 200) {
            content = IOUtils.toString(response.getEntity().getContent(), "UTF-8");


            int len = content.length();

        }
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


