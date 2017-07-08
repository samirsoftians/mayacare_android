package com.felixtechlabs.mayacare.util;

import com.felixtechlabs.mayacare.R;

/**
 * Constants class containing all public static final constants required for app
 * Created by rohan on 20/6/17.
 */

public class MCConstants {


    public static final String KEY_REQUEST_OBJECT = "request.object";

    public static final int LAUNCH_CONTROL_DELAY_MILLIS = 800;
    public static final int[] DASHBOARD_TAB_ICONS = new int[]{

            R.drawable.ic_home_selector,
            R.drawable.ic_myrequest_selector,
            R.drawable.ic_profile_selector,

    };
    public static final String[] DASHBOARD_TAB_TITLES = new String[]{
            "Home",
            "My Requests",
            "Profile"

    };

    public static final class RequestStatusInt {
        public static final int PENDING = 0;
        public static final int ASSIGNED = 1;
        public static final int COMPLETED = 2;
    }

    public static final class RequestStatus {
        public static final String PENDING = "Pending";
        public static final String ASSIGNED = "Assigned";
        public static final String COMPLETED = "Completed";
    }


}
