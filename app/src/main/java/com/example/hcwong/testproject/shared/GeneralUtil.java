package com.example.hcwong.testproject.shared;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hcwong.testproject.Constant.Constant;
import com.example.hcwong.testproject.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.RejectedExecutionException;
import java.util.regex.Matcher;

public class GeneralUtil {


    public static void loadImgToView(String url, ImageView img){
        Picasso.get().load(url).into(img);
    }

    public static String simpleDateToDateFormatString(Calendar c)
    {
        c= Calendar.getInstance();
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.HOUR_OF_DAY, 0);
//        c.set(Calendar.MILLISECOND, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy hh:mma");
        String date= sdf.format(c.getTime()).replace("am", "AM").replace("pm", "PM");
        return date;
    }

    public static Date dateFormatStringToSimpleDate(String date_string)
    {
//        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy hh:mm:ss.SSS ");
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy hh:mma");

        Date date= null;
        try {
            date = sdf.parse(date_string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /*
     * Toasts
     */

    public static void toastLong(String message) {
        try {
            Toast.makeText(Constant.context, GeneralUtil.getTranslation(message), Toast.LENGTH_LONG).show();
        } catch (RejectedExecutionException e) {
            e.printStackTrace();
        }

    }

    public static void toastLong(int message) {
        try {
            Toast.makeText(Constant.context, GeneralUtil.getTranslation(Constant.context.getString(message)), Toast.LENGTH_LONG).show();
        } catch (RejectedExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void toastShort(int message) {
        Toast.makeText(Constant.context, GeneralUtil.getTranslation(Constant.context.getString(message)), Toast.LENGTH_SHORT).show();
    }

    public static void toastShort(String message) {
        Toast.makeText(Constant.context, GeneralUtil.getTranslation(message), Toast.LENGTH_SHORT).show();
    }

    public static void toastGenericRestError() {
        Toast.makeText(Constant.context, Constant.context.getResources().getString(R.string.error_message_rest),
                Toast.LENGTH_SHORT).show();
    }

    /*
     *
     * Internet connection
     */
    public static boolean isInternetConnected() {
        return isInternetConnected(true);
    }

    public static boolean isInternetConnected(boolean showToast) {
        try {
            NetworkInfo info = ((ConnectivityManager) Constant.context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

            if (info == null) {
                if (showToast) {
                    GeneralUtil.toastShort("No Internet Connection");
                }
                return false;
            }
            return info.isConnected();
        } catch (Exception e) {
            if (showToast) {
                GeneralUtil.toastShort("Network error: " + e.getLocalizedMessage());
                Log.e("Micepad", "Network error: " + e.getLocalizedMessage());
            }
            return false;
        }
    }

    /**
     * Prefixes 'http://' to a link if it doesn't already exist
     *
     * @param weblink - Link to verify / format
     * @return formatted link
     */
    public static String formatWeblink(String weblink) {
        if (!weblink.contains("http://") && !weblink.contains("https://"))
            return "http://" + weblink;
        return weblink;
    }

    /**
     * @return Whether or not device is tablet
     */
    public static boolean isTablet(Context context) {
        boolean xlarge = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == 4);
        boolean large = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
        return (xlarge || large);
    }

    /*
     * Background
     */

    /**
     * Convenience to run in background without all the messy code
     *
     * @param r
     */
    public static void runInBackground(final Runnable r) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                r.run();
                return null;
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }



    /*
     * SharedPreferences
     */

    /**
     * Returns the SharedPreferences associated with this app
     *
     * @return
     */
    public static SharedPreferences getSharedPreferences() {
        return Constant.context.getSharedPreferences(Constant.context.getString(R.string.PREFS_NAME), Context.MODE_PRIVATE);
    }

    public static String getTranslation(String stringToBeTranslated) {
        if (stringToBeTranslated == null)
            return "";
        String placeholder = stringToBeTranslated.replace("\n", "\\n").trim();
        if (Constant.translation.size() > 0) {
            if (!Constant.translation.containsKey(placeholder)) {
                placeholder = stringToBeTranslated.replace("\n", "\\n").trim();
            }

            if (Constant.translation.containsKey(placeholder)) {
                stringToBeTranslated = Constant.translation.get(placeholder);
                stringToBeTranslated = stringToBeTranslated.replace("\\n", "\n");
            }
        }

        return stringToBeTranslated;
    }


    /**
     * Creating Initial of a user
     *
     * @param userName
     * @return the Initial of course!
     */
    public static String initialGenerator(String userName) {
        try {
            if (userName == null || userName.trim().length() == 0) return "";

            String[] words = userName.split(" ");

            // If only 1 word then get the first letter
            if (words.length == 1)
                return String.valueOf(Character.toUpperCase(words[0].charAt(0)));

                // Else if more than 1 word, get first letter of first and last word
            else if (words.length > 1) {
                char first = Character.toUpperCase(words[0].charAt(0));
                char second = Character.toUpperCase(words[words.length - 1].charAt(0));

                StringBuilder sb = new StringBuilder().append("").append(first).append(second);
                return sb.toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * Convert string in WebTextView to hyperlink by appending Anchor tag
     *
     * @param details the string
     * @return string with Anchor tags
     */
    public static String getClickableRichText(String details) {
        if (details.contains("https://") || details.contains("http://") || details.contains("www.")) {
            String cDetails = "";
            Matcher matcher = Patterns.WEB_URL.matcher(details);
            int start = 0, end;
            while (matcher.find()) {
                String urlLink = matcher.group();
                end = matcher.start();
                String previousString = details.substring(start, end);
                if (!previousString.trim().endsWith("=\"")
                        && !previousString.trim().endsWith("='")) {
                    urlLink = "<a href=\"" + urlLink + "\">" + urlLink + "</a>";
                }
                cDetails = cDetails.concat(previousString).concat(urlLink);
                start = matcher.end();
            }
            cDetails = cDetails.concat(details.substring(start, details.length()));
            return cDetails;
        }
        return details;
    }

    /**
     * Converts dp value to pixel so that it adjusts automatically to any
     * resolution
     *
     * @return the pixel value
     */
    public static int dipToPixels(float dipValue) {
        DisplayMetrics metrics = Constant.context.getResources().getDisplayMetrics();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics));
    }

    public static float pixelsToDip(float px) {
        DisplayMetrics metrics = Constant.context.getResources().getDisplayMetrics();
        return px / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    /**
     * Gets dimensional value in pixels
     *
     * @param dimenRes resource integer
     * @return pixels value of dimen
     */
    public static int getDimension(int dimenRes) {
        return Math.round(Constant.context.getResources().getDimension(dimenRes));
    }

    /*
     *
     * Timestamp updated
     */
    public static int getMaxTimestampUpdated(String appendString) {
        if ("GetSocial".equals(appendString)) {
            appendString = appendString.concat("_").concat(Constant.instanceId);
        }

        return GeneralUtil.getSharedPreferences().getInt("maxTimeStampUpdated_" + appendString, 0);
    }

    public static void saveMaxTimestampUpdated(String appendString, Class<?>[] entityClasses) {
        if ("GetSocial".equals(appendString)) {
            appendString = appendString.concat("_").concat(Constant.instanceId);
        }

        List<String> entityClassNames = new ArrayList<String>();
        for (Class<?> c : entityClasses) {
            entityClassNames.add(c.getSimpleName());
        }

        String overallKey = "maxTimeStampUpdated_" + appendString;
        SharedPreferences sp = GeneralUtil.getSharedPreferences();
        int overallMaxTimestampUpdated = 0;

        // Check any timestamps that correspond to any of the classes passed in
        for (Map.Entry<String, ?> e : sp.getAll().entrySet()) {
            if (!e.getKey().equals(overallKey) && e.getKey().startsWith("maxTimeStampUpdated_")
                    && entityClassNames.contains(e.getKey().replace("maxTimeStampUpdated_", ""))) {
                int maxTimestampUpdated = (Integer) e.getValue() + 1;
                if (maxTimestampUpdated > overallMaxTimestampUpdated) {
                    overallMaxTimestampUpdated = maxTimestampUpdated;
                }
            }
        }

        // Save
        sp.edit().putInt(overallKey, overallMaxTimestampUpdated).apply();
    }

    /**
     * To concatenate several JSONArrays together
     *
     * @param arrs ∂JSONArrays
     * @return result combined arrays
     * @throws JSONException
     */
    public static JSONArray concatArray(JSONArray... arrs) throws JSONException {
        JSONArray result = new JSONArray();
        for (JSONArray arr : arrs) {
            if (arr != null) {
                for (int i = 0; i < arr.length(); i++) {
                    result.put(arr.get(i));
                }
            }
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public static List removesDuplicateFromList(List list) {
        Set temp = new HashSet();
        temp.addAll(list);
        list.clear();
        list.addAll(temp);
        return list;
    }

    /**
     * Check for URL in a String
     *
     * @param message the URL
     * @return if there is any
     */
    public static boolean isUrlInString(String message) {
        try {
            getUrlInString(message);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }

    /**
     * Check for URL in a String
     *
     * @param message the subject string
     * @return the URL
     * @throws MalformedURLException if there is no URL found
     */
    public static String getUrlInString(String message) throws MalformedURLException {
        // separate input by spaces ( URLs don't have spaces )
        String[] parts = message.split("\\s+");

        // Attempt to convert each item into an URL.
        for (String item : parts) {
            try {
                if (item.startsWith("(") && item.endsWith(")")) {
                    item = item.substring(1, item.length() - 2);
                }

                URL url = new URL(item);

                return item;
            } catch (MalformedURLException e) {
                // Does nothing, it will be handled after the For loop
            }
        }

        // If it's not found, then it is a plain text
        throw new MalformedURLException("URL is not found within the String");
    }

    /**
     * To hide current keyboard using current active view reference
     *
     * @author Ray Chong
     */
    public static void hideKeyboard(Context mContext, View v) {
        try {
            if (v != null) {
                InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showKeyboard(Context mContext, View view) {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }


    /**
     * To check if user input is a valid email
     *
     * @author Ray Chong
     */
    public static boolean isValidEmail(String str) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(str).matches();
    }

    public static int getPowerOfTwoForSampleRatio(double ratio) {
        int k = Integer.highestOneBit((int) Math.floor(ratio));
        if (k == 0) return 1;
        else return k;
    }


    @SuppressLint("NewApi")
    public static void getScreenSize(Context mContext) {
        Point size = new Point();
        WindowManager w = (WindowManager) mContext
                .getSystemService(Context.WINDOW_SERVICE);
        w.getDefaultDisplay().getMetrics(new DisplayMetrics());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            w.getDefaultDisplay().getSize(size);
        } else {
            Display d = w.getDefaultDisplay();
            size.x = d.getWidth();
            size.y = d.getHeight();
        }

        Constant.ScreenSize.Width = size.x;
        Constant.ScreenSize.Height = size.y;
    }




}
