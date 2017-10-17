package com.chikeandroid.retrofittutorial.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Chike on 12/4/2016.
 */

public class Owner implements Parcelable {


    @SerializedName("reputation")
    @Expose
    private Integer reputation;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("user_type")
    @Expose
    private String userType;
    @SerializedName("profile_image")
    @Expose
    private String profileImage;
    @SerializedName("display_name")
    @Expose
    private String displayName;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("accept_rate")
    @Expose
    private Integer acceptRate;

    protected Owner(Parcel in) {
        userType = in.readString();
        profileImage = in.readString();
        displayName = in.readString();
        link = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userType);
        dest.writeString(profileImage);
        dest.writeString(displayName);
        dest.writeString(link);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Owner> CREATOR = new Creator<Owner>() {
        @Override
        public Owner createFromParcel(Parcel in) {
            return new Owner(in);
        }

        @Override
        public Owner[] newArray(int size) {
            return new Owner[size];
        }
    };

    /**
     *
     * @return
     * The reputation
     */
    public Integer getReputation() {
        return reputation;
    }

    /**
     *
     * @param reputation
     * The reputation
     */
    public void setReputation(Integer reputation) {
        this.reputation = reputation;
    }

    /**
     *
     * @return
     * The userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     *
     * @param userId
     * The user_id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     *
     * @return
     * The userType
     */
    public String getUserType() {
        return userType;
    }

    /**
     *
     * @param userType
     * The user_type
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     *
     * @return
     * The profileImage
     */
    public String getProfileImage() {
        return profileImage;
    }

    /**
     *
     * @param profileImage
     * The profile_image
     */
    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    /**
     *
     * @return
     * The displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     *
     * @param displayName
     * The display_name
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     *
     * @return
     * The link
     */
    public String getLink() {
        return link;
    }

    /**
     *
     * @param link
     * The link
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     *
     * @return
     * The acceptRate
     */
    public Integer getAcceptRate() {
        return acceptRate;
    }

    /**
     *
     * @param acceptRate
     * The accept_rate
     */
    public void setAcceptRate(Integer acceptRate) {
        this.acceptRate = acceptRate;
    }
}
