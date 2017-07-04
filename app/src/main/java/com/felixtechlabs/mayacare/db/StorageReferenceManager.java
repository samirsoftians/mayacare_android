package com.felixtechlabs.mayacare.db;


import com.felixtechlabs.mayacare.BuildConfig;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Singleton StorageReference Manager class managing references for all storage nodes
 * Contains methods for getting individual storage node references.
 * Created by rohan on 1/6/17.
 */

public class StorageReferenceManager {

    private static StorageReferenceManager instance;

    /**
     * Root storage node to be set from build.gradle
     */
    private final String STORAGE_ROOT;

    /**
     * Class containing all reference key constants
     */
    private final class ReferenceKeys {
        static final String USER_PROFILE_PHOTOS = "user_profile_pictures";
    }

    /**
     * Instance method to get StorageReferenceManager instance
     *
     * @return StorageReferenceManager instance
     */
    public static StorageReferenceManager getInstance() {
        if (instance == null) {
            instance = new StorageReferenceManager();
        }
        return instance;
    }

    /**
     * Private constructor to make it singleton, initialise STORAGE_ROOT value
     */
    private StorageReferenceManager() {
        STORAGE_ROOT = BuildConfig.DATABASE_ROOT;
    }

    /**
     * Method to get root reference
     *
     * @return - Root database reference
     */
    private StorageReference getRoot() {
        return FirebaseStorage.getInstance().getReference().child(STORAGE_ROOT);
    }

    /**
     * Method to get profile photos reference
     *
     * @return - Profile photos reference
     */
    public StorageReference getProfilePhotosReference() {
        return getRoot().child(ReferenceKeys.USER_PROFILE_PHOTOS);
    }

}
