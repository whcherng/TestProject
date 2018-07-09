package com.example.hcwong.testproject.Constant;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constant {
    public static Context context;

    public static final int MAX_TAB = 5;
    public static final int PROFILE_RED = Color.rgb(199, 78, 78);
    public static final int PROFILE_BLUE = Color.rgb(81, 184, 227);
    public static final int PROFILE_GREEN = Color.rgb(44, 168, 82);
    public static final int PROFILE_ORANGE = Color.rgb(215, 102, 72);
    public static final int PROFILE_YELLOW = Color.rgb(216, 180, 36);
    public static final int PROFILE_PURPLE = Color.rgb(150, 78, 195);
    public static final int[] PROFILE_COLOR = new int[] {PROFILE_RED, PROFILE_BLUE, PROFILE_GREEN, PROFILE_ORANGE, PROFILE_YELLOW, PROFILE_PURPLE};

    public static final int PROFILE_BW1 = Color.rgb(126, 140, 144);
    public static final int PROFILE_BW2 = Color.rgb(159, 165, 167);
    public static final int PROFILE_BW3 = Color.rgb(109, 113, 115);
    public static final int PROFILE_BW4 = Color.rgb(82, 84, 85);
    public static final int PROFILE_BW5 = Color.rgb(118, 127, 126);
    public static final int PROFILE_BW6 = Color.rgb(148, 148, 146);
    public static final int[] PROFILE_BW = new int[] {PROFILE_BW1, PROFILE_BW2, PROFILE_BW3, PROFILE_BW4, PROFILE_BW5, PROFILE_BW6};

    /*
     *  This is the current activity on top of the main task stack
     *  This is updated by CLifecycleHandler
     */
    public static Activity topActivity;

    public static String ImageDir = "";

    public static class ScreenSize {
        public static int Width, Height;
    }

    public static String apikey = "";
    public static String instanceId = "";
    public static String qrcode = "";
    public static String email = "";
    public static int languageid = 0;
    public static int linkedInstanceId = 0;
    public static int userid = 0;
    public static int groupid = 0;
    public static int channelid = 0;
    public static int parentInstancesNo = 0;
    public static int currentActiveChannelId = -1;
    public static int selectedBmId = -1;

    public static int sessionActivity = 0;
    public static String assetFolder;
    public static int Tabmode;

    public static int serverUnixTime = 0;
    public static long timeSinceLastGetSocialInMillis = 0;

    public static String firebaseToken;
    public static Intent v7Intent;
    public static boolean postponeUpdate = false;
    public static boolean isOrganizationLevel = false;

    public static List<Integer> groups = new ArrayList<>();
    public static Map<String, String> translation = new HashMap<>();

    // Let's Start from 31630, 31631, and so on
    public static final int REQUEST_CODE_PASSWORD = 31630;
    public static final int CORRECT_PASSWORD = 31631;

    // Business Matching
    public static boolean hasBmProfile = true;

    public static void clear() {
        qrcode = "";
        email = "";
        apikey = "";
        instanceId = "";
        sessionActivity = 0;
        Tabmode = 0;
        languageid = 0;
        linkedInstanceId = 0;
        groups.clear();
        translation.clear();
    }

    public static Bundle saveConstant(Bundle outstate) {
        // TODO: Saves Bundles in onSaveInstanceState
        return outstate;
    }

    public static void loadConstant(Bundle bundle) {
        // TODO: Repopulate Constant from Bundle
    }

    public static final String TAG_SEARCH = "TAG_SEARCH";
    public static final String TAG_SEARCH_RESULT = "TAG_SEARCH_RESULT";
    public static final String TAG_FEATURED = "TAG_FEATURED";
    public static final String TAG_PROFILE = "TAG_PROFILE";
    public static final String TAG_PROFILE_UPDATE_PASSWORD = "TAG_PROFILE_UPDATE_PASSWORD";
    public static final String TAG_LOGIN = "TAG_LOGIN";
    public static final String TAG_ORG_PROFILE = "TAG_ORG_PROFILE";
    public static final String TAG_EVENT_DETAILS= "TAG_EVENT_DETAILS";
    public static final String TAG_SEARCH_RESULT_SORT= "TAG_SEARCH_RESULT_SORT";
    public static final String TAG_PREENTER_EVENT = "TAG_PREENTER_EVENT";

    public static final String TAG_NOTIFICATION_LIST= "TAG_NOTIFICATION_LIST";
    public static final String TAG_SIGN_UP = "TAG_SIGN_UP";
    public static final String TAG_SIGN_UP_DETAILS = "TAG_SIGN_UP_DETAILS";
    public static final String TAG_EDIT_PROFILE_FRAGMENT = "TAG_EDIT_PROFILE_FRAGMENT";
    public static final String TAG_BM_PROFILE_INTEREST= "TAG_BM_PROFILE_INTEREST";

    public static final int EMAIL_CONTENT = 1;
    public static final int EMAIL_CANVAS = 2;
    public static final int EMAIL_ALL_PROJECTS = 3;
    public static final int EMAIL_SCANNED_ITEMS = 4;
    public static final int EMAIL_NOTE = 5;

}
