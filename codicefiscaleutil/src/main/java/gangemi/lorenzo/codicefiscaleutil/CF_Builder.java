package gangemi.lorenzo.codicefiscaleutil;

import android.util.Log;

public class CF_Builder {

    private static final String TAG = "CF_BUILDER";

    private static void e(String message) {
        Log.e(TAG,message);
    }

    private static void d(String message) {
        Log.d(TAG,message);
    }


    public static String build() {
        return "Codice Fiscale";
    }
}
