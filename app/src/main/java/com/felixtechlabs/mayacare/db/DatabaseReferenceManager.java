package com.felixtechlabs.mayacare.db;


import com.felixtechlabs.mayacare.BuildConfig;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Singleton DatabaseReference Manager class managing reference for all db nodes
 * Contains methods for getting individual node references.
 * Created by rohan on 24/5/17.
 */

public class DatabaseReferenceManager {

    private static DatabaseReferenceManager instance;

    /**
     * Root database node to be set from build.gradle
     */
    private final String DATABASE_ROOT;

    /**
     * Class containing all reference key constants
     */
    private final class ReferenceKeys {
        static final String CONSUMERS = "consumers";
        static final String REQUESTS = "requests";
        static final String ENQUIRIES = "enquiries";
    }

    /**
     * Instance method to get DatabaseReferenceManager instance
     *
     * @return DatabaseReferenceManager instance
     */
    public static DatabaseReferenceManager getInstance() {
        if (instance == null) {
            instance = new DatabaseReferenceManager();
        }
        return instance;
    }

    /**
     * Private constructor to make it singleton, initialise DATABASE_ROOT value
     */
    private DatabaseReferenceManager() {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        DATABASE_ROOT = BuildConfig.DATABASE_ROOT;
    }

    /**
     * Method to get root reference
     *
     * @return - Root database reference
     */
    private DatabaseReference getRoot() {
        return FirebaseDatabase.getInstance().getReference().child(DATABASE_ROOT);
    }

    /**
     * Method to get Consumers reference
     *
     * @return - Consumers database reference
     */
    public DatabaseReference getConsumersReference() {
        return getRoot().child(ReferenceKeys.CONSUMERS);
    }

    /**
     * Method to get Requests reference
     *
     * @return - Requests database reference
     */
    public DatabaseReference getRequestsReference() {
        return getRoot().child(ReferenceKeys.REQUESTS);
    }

    /**
     * Method to get Enquiries reference
     *
     * @return - Enquiries database reference
     */
    public DatabaseReference getEnquiriesReference() {
        return getRoot().child(ReferenceKeys.ENQUIRIES);
    }

}
