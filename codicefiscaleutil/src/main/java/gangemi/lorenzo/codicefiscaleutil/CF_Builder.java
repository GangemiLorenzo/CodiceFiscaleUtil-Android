package gangemi.lorenzo.codicefiscaleutil;

import android.content.Context;

import java.util.List;

public class CF_Builder {

    private static final String TAG = "CF_BUILDER";
    private static final int FemDays = 40;

    private static Costanti C;

    public static void init(Context context)
    {
        C = new Costanti(context);
    }

    public static String build(PersonalData personalData) {
        String CF = getSurnameCode(personalData.getSurname());
        CF += getNameCode(personalData.getName());
        CF += getDateCode(
                personalData.getDd(),
                personalData.getMm(),
                personalData.getYy(),
                personalData.isGender()
        );
        CF += getCodiceCatastale(personalData.getBirthplace());
        CF += getCheckCode(CF);
        return CF;
    }

    //Return the first three consonants
    private static String getSurnameCode(String surname) {
        String SurnameCode = remove_vowels(surname.toUpperCase());
        return SurnameCode.substring(0,3);
    }

    //Return the first, the third and the fourth consonants
    private static String getNameCode(String name) {
        String NameCode = remove_vowels(name.toUpperCase());
        if (NameCode.length() >= 4)
            NameCode = NameCode.substring(0,1) + NameCode.substring(2,3) + NameCode.substring(3,4);
        else {
            NameCode += remove_consonants(name);
            NameCode = NameCode.substring(0, 3);
        }
        return NameCode;
    }

    // Day, Month, Year,  gender (boolean) Male: true, Female: false
    private static String getDateCode(String dd, String mm, String yy, boolean gender) {
        String DateCode = getYearCode(yy);
        DateCode += getMonthCode(mm);
        DateCode += getDayCode(dd, gender);
        return DateCode;
    }

    private static String getDayCode(String dd, boolean gender) {
        //Padding to avoid type errors   ex. day 4 --> day 04
        dd += "00" + dd;
        dd = dd.substring(dd.length()-2,dd.length());

        //Add 40 if Female
        if(!gender) dd = (Integer.parseInt(dd) + FemDays) + "";

        return dd;
    }

    //mm --> Month number so it goes from 1 to 12
    private static String getMonthCode(String mm) {
        return C.getMonth_Codes()[Integer.parseInt(mm) - 1];
    }

    private static String getYearCode(String yy) {
        //Padding to avoid type errors
        yy += "00" + yy;
        yy = yy.substring(yy.length()-2,yy.length());
        return yy;
    }

    private static String getCodiceCatastale(String birthplace) {
        String code = C.getMap_Codici_Catastali().get(birthplace);
        return code;
    }

    private static String getCheckCode(String CF) {
        int sum_odd = 0;
        int sum_even = 0;
        for (int i = 1; i < 16; i++) {
            String c = CF.charAt(i-1) + "";
            if(i%2 == 0) sum_even += C.getMap_Conf_Even().get(c);
            else sum_odd += C.getMap_Conf_Odd().get(c);
        }
        int sum = sum_even + sum_odd;
        sum = sum % 26;
        return C.getCheck_Codes()[sum];
    }

    //UTILS

    //Remove Vowels from parameter String
    private static String remove_vowels(String message) {
        return message.replaceAll("[^BCDFGHJKLMNPQRSTVWXYZ]","");
    }

    //Remove Consonants from parameter String
    private static String remove_consonants(String message) {
        return message.replaceAll("[^AEIOU]","");
    }

    public static List<String> getCityList()
    {
        return C.getCityList();
    }
}
