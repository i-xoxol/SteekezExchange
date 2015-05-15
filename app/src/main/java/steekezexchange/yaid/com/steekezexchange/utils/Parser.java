package steekezexchange.yaid.com.steekezexchange.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import steekezexchange.yaid.com.steekezexchange.entity.SteekezItem;

/**
 * Created by Игорь on 16.05.2015.
 */
public class Parser {

    private static final String EMAIL = "EMAIL";
    private static final String COLLECTION = "COLLECTION";
    private static final String NAME = "NAME";
    private static final String ID = "ID";
    private static final String QUANTITY = "QUANTITY";

    public static ArrayList<SteekezItem> parseFile(String data)
    {
        ArrayList<SteekezItem> items = new ArrayList<SteekezItem>();

        try {
            JSONObject record = new JSONObject(data);
            JSONArray itemsArray = record.getJSONArray(COLLECTION);
            for (int i=0; i<itemsArray.length(); i++)
                items.add(getItem(itemsArray.getJSONObject(i)));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return items;
    }

    private static SteekezItem getItem(JSONObject obj)
    {
        SteekezItem item = new SteekezItem();

        try {
            item.setName(obj.getString(NAME));
            item.setId(obj.getInt(ID));
            item.setQuantity(obj.getInt(QUANTITY));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return item;
    }

    public static String getStringData(String email, ArrayList<SteekezItem> items)
    {
        JSONObject data = new JSONObject();
        JSONArray collection = new JSONArray();
        try {
            data.put(EMAIL,email);
            for (int i=0; i<items.size(); i++)
            {
                JSONObject obj = new JSONObject();
                obj.put(NAME,items.get(i).getName());
                obj.put(ID,items.get(i).getId());
                obj.put(QUANTITY,items.get(i).getQuantity());
                collection.put(obj);
            }
            data.put(COLLECTION,collection);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return data.toString();
    }
}
