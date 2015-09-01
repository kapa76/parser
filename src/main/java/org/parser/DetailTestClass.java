package org.parser;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.parser.common.SuperJobVacancyPage;
import org.parser.persistence.model.Vacancy;
import org.parser.util.CommonUtils;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class DetailTestClass {

    public static void main(String[] args) throws IOException {

        String url = "https://api.superjob.ru/2.0/" + "r00e72d6106234c3e2bd3d644c726c4aaeafd6483e52ae3cfc217329676e9de33710117a5" + "/vacancies?";


        List<NameValuePair> params = new LinkedList<NameValuePair>();

        params.add(new BasicNameValuePair("period", "0"));
        String paramString = URLEncodedUtils.format(params, "utf-8");
        url = url + paramString;

        //url сохранить в history на тему делали не делали ? ....

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        HttpResponse response = client.execute(request);

        int returnCode = response.getStatusLine().getStatusCode();
        System.out.println("Response Code : " + returnCode);  //200

        if (returnCode == 200) {
            String result = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
            SuperJobVacancyPage sPage = new SuperJobVacancyPage(result);

            saveVacancy(sPage.getVacancyJsonObject());


        }


    }

    private static void saveVacancy(List<JsonObject> vacancyObj) {
            for (JsonObject t : vacancyObj) {
                Vacancy v = new Vacancy();

                v.setId_client(t.get("id_client").getAsLong());
                v.setPayment(t.get("payment_to").getAsDouble());
                v.setDatePublished(t.get("date_published").getAsLong());
                v.setWork(CommonUtils.isNullNull(t.get("work")));
                v.setCandidat(CommonUtils.isNullNull(t.get("candidat")));
                //v.setCurrency(currencyService.findOne(t.get("currency").getAsString().replaceAll("\"", "")));
                v.setCompensation(CommonUtils.isNullNull(t.get("compensation")));
                v.setProfession(t.get("profession").getAsString());
                v.setAddress(CommonUtils.isNullNull(t.get("address")));
                v.setDate_pub_to(t.get("date_pub_to").getAsLong());
                v.setPayment_from(t.get("payment_from").getAsDouble());
                v.setInternal_id(t.get("id").getAsLong());
                if(t.get("moveable").isJsonObject()){
                    // t.get("moveable").getAsJsonObject()
                }
                /*
                moveable: false
                agreement: false
                anonymous: false
                is_archive: false
                is_storage: false
                  */
                /*
                v.setType_of_work(typeOfWorkService.findOne(t.get("type_of_work")));
                type_of_work: {
                id: 12
                title: "Сменный график работы"
                }-
                place_of_work: {
                id: 3
                title: "Разъездного характера"
                }-
                education: {
                id: 0
                title: "Не имеет значения"
                }-
                experience: {
                id: 1
                title: "Не требуется"
                }-
                maritalstatus: {
                id: 0
                title: "Не имеет значения"
                }-
                children: {
                id: 0
                title: "Не имеет значения"
                }
                */
    /*





    * */


            }
        }
}
