package org.parser.common;


import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.Iterator;
import java.util.Map;

public class SuperJobVacancyPage {

    private String jsonStr = "";

    public SuperJobVacancyPage(String str){
        jsonStr = str;
        JsonElement element = new Gson().fromJson(str, JsonElement.class);

        //Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet().iterator();



    }

    public boolean isMore(){
       return true;
    }
}
