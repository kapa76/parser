package org.parser;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.parser.persistence.model.Vacancy;
import org.parser.util.CommonUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonPrimitive;
import com.google.gson.internal.LinkedTreeMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class DetailTestClass {

    private static final String SJ_LOAD_PROPERTIES = "https://api.superjob.ru/2.0/references/";

    public static void MaritalStatusResumeGenderServiceInit(JsonElement element, String value) {

        Set<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet();
        for(Map.Entry<String, JsonElement> mp : iter){
            JsonObject o = mp.getValue().getAsJsonObject();
            for( Entry<String, JsonElement> o1 : o.entrySet() ){
                String aa = o1.getValue().getAsString();
            }
        }

    }

    public static void main(String[] args) throws IOException {

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(SJ_LOAD_PROPERTIES);
        HttpResponse response = client.execute(request);

        int returnCode = response.getStatusLine().getStatusCode();
        System.out.println("Response Code : " + returnCode);  //200

        if (returnCode == 200) {
            String result = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
            JsonElement element = new Gson().fromJson(result, JsonElement.class);
            MaritalStatusResumeGenderServiceInit(element, "maritalstatus_resume_gender");

        }


    }

}
