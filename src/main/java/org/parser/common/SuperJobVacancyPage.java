package org.parser.common;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.parser.persistence.model.Vacancy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SuperJobVacancyPage {

    private String jsonStr = "";

    private boolean isMore;
    private Integer total;
    private List<JsonObject> vacancyJsonObject;

    public SuperJobVacancyPage(String str) {
        jsonStr = str;
        JsonElement element = new Gson().fromJson(str, JsonElement.class);
        vacancyJsonObject = new ArrayList();

        Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject().entrySet().iterator();
        Map.Entry<String, JsonElement> t = iter.next();

        JsonArray objects = t.getValue().getAsJsonArray();
        Iterator<JsonElement> iterVacancy = objects.iterator();
        while (iterVacancy.hasNext()) {
            vacancyJsonObject.add(iterVacancy.next().getAsJsonObject());
        }

        t = iter.next();
        this.total = t.getValue().getAsInt();
        t = iter.next();
        this.isMore = t.getValue().getAsBoolean();
    }

    public List<JsonObject> getVacancyJsonObject() {
        return vacancyJsonObject;
    }


    public Integer getTotal() {
        return total;
    }

    public boolean getMore() {
        return isMore;
    }
}
