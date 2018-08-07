package gangemi.lorenzo.codicefiscaleutil;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Costanti {

    private static String[] Month_Codes = {
            "A",
            "B",
            "C",
            "D",
            "E",
            "H",
            "L",
            "M",
            "P",
            "R",
            "S",
            "T"
    };

    private static String[] SameName_Codes = {
            "L",
            "M",
            "N",
            "P",
            "Q",
            "R",
            "S",
            "T",
            "U",
            "V"
    };

    private static String[] Check_Codes = {
            "A",
            "B",
            "C",
            "D",
            "E",
            "F",
            "G",
            "H",
            "I",
            "J",
            "K",
            "L",
            "M",
            "N",
            "O",
            "P",
            "Q",
            "R",
            "S",
            "T",
            "U",
            "V",
            "W",
            "X",
            "Y",
            "Z"
    };

    private Map<String, Integer> Map_Conf_Odd;
    private Map<String, Integer> Map_Conf_Even;
    private Map<String, String> Map_Codici_Catastali;
    private List<String> CityList;

    //CONSTRUCTOR

    public Costanti(Context context) {
        init_Map_Conf_Even();
        init_Map_Conf_Odd();
        init_Map_Codici_Catastali(context);
        setCityList(new ArrayList<String>(Map_Codici_Catastali.keySet()));
    }

    //INIT EVEN AND ODD CODES

    private void init_Map_Conf_Odd() {
        Map_Conf_Odd= new HashMap<>();

        getMap_Conf_Odd().put("0",1);
        getMap_Conf_Odd().put("1",0);
        getMap_Conf_Odd().put("2",5);
        getMap_Conf_Odd().put("3",7);
        getMap_Conf_Odd().put("4",9);
        getMap_Conf_Odd().put("5",13);
        getMap_Conf_Odd().put("6",15);
        getMap_Conf_Odd().put("7",17);
        getMap_Conf_Odd().put("8",19);
        getMap_Conf_Odd().put("9",21);
        getMap_Conf_Odd().put("A",1);
        getMap_Conf_Odd().put("B",0);
        getMap_Conf_Odd().put("C",5);
        getMap_Conf_Odd().put("D",7);
        getMap_Conf_Odd().put("E",9);
        getMap_Conf_Odd().put("F",13);
        getMap_Conf_Odd().put("G",15);
        getMap_Conf_Odd().put("H",17);
        getMap_Conf_Odd().put("I",19);
        getMap_Conf_Odd().put("J",21);
        getMap_Conf_Odd().put("K",2);
        getMap_Conf_Odd().put("L",4);
        getMap_Conf_Odd().put("M",28);
        getMap_Conf_Odd().put("N",20);
        getMap_Conf_Odd().put("O",11);
        getMap_Conf_Odd().put("P",3);
        getMap_Conf_Odd().put("Q",6);
        getMap_Conf_Odd().put("R",8);
        getMap_Conf_Odd().put("S",12);
        getMap_Conf_Odd().put("T",14);
        getMap_Conf_Odd().put("U",16);
        getMap_Conf_Odd().put("V",10);
        getMap_Conf_Odd().put("W",22);
        getMap_Conf_Odd().put("X",25);
        getMap_Conf_Odd().put("Y",24);
        getMap_Conf_Odd().put("Z",23);
    }

    private void init_Map_Conf_Even() {
        Map_Conf_Even = new HashMap<>();

        getMap_Conf_Even().put("0",0);
        getMap_Conf_Even().put("1",1);
        getMap_Conf_Even().put("2",2);
        getMap_Conf_Even().put("3",3);
        getMap_Conf_Even().put("4",4);
        getMap_Conf_Even().put("5",5);
        getMap_Conf_Even().put("6",6);
        getMap_Conf_Even().put("7",7);
        getMap_Conf_Even().put("8",8);
        getMap_Conf_Even().put("9",9);
        getMap_Conf_Even().put("A",0);
        getMap_Conf_Even().put("B",1);
        getMap_Conf_Even().put("C",2);
        getMap_Conf_Even().put("D",3);
        getMap_Conf_Even().put("E",4);
        getMap_Conf_Even().put("F",5);
        getMap_Conf_Even().put("G",6);
        getMap_Conf_Even().put("H",7);
        getMap_Conf_Even().put("I",8);
        getMap_Conf_Even().put("J",9);
        getMap_Conf_Even().put("K",10);
        getMap_Conf_Even().put("L",11);
        getMap_Conf_Even().put("M",12);
        getMap_Conf_Even().put("N",13);
        getMap_Conf_Even().put("O",14);
        getMap_Conf_Even().put("P",15);
        getMap_Conf_Even().put("Q",16);
        getMap_Conf_Even().put("R",17);
        getMap_Conf_Even().put("S",18);
        getMap_Conf_Even().put("T",19);
        getMap_Conf_Even().put("U",20);
        getMap_Conf_Even().put("V",21);
        getMap_Conf_Even().put("W",22);
        getMap_Conf_Even().put("X",23);
        getMap_Conf_Even().put("Y",24);
        getMap_Conf_Even().put("Z",25);
    }

    private void init_Map_Codici_Catastali(Context context) {
        InputStream is = context.getResources().openRawResource(R.raw.codici);
        JsonReader reader = new JsonReader(new InputStreamReader(is));
        Map_Codici_Catastali = new Gson().fromJson(reader, HashMap.class);
    }


    //GET METHODS

    public String[] getMonth_Codes() {
        return Month_Codes;
    }

    public String[] getSameName_Codes() {
        return SameName_Codes;
    }

    public String[] getCheck_Codes() {
        return Check_Codes;
    }

    public Map<String, Integer> getMap_Conf_Odd() {
        return Map_Conf_Odd;
    }

    public Map<String, Integer> getMap_Conf_Even() {
        return Map_Conf_Even;
    }

    public Map<String, String> getMap_Codici_Catastali() {
        return Map_Codici_Catastali;
    }

    public List<String> getCityList() {
        return CityList;
    }

    public void setCityList(List<String> cityList) {
        CityList = cityList;
    }
}
