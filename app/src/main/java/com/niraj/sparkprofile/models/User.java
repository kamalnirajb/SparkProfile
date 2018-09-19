package com.niraj.sparkprofile.models;

import com.google.gson.annotations.SerializedName;

public class User {

    public User(String birthday, String figure, String occupation, String gender, String ethnicity, String realName, String profilePicture, String displayName, String religion, String aboutme, String maritalStatus, String location, String height) {
        this.birthday = birthday;
        this.figure = figure;
        this.occupation = occupation;
        this.gender = gender;
        this.ethnicity = ethnicity;
        this.realName = realName;
        this.profilePicture = profilePicture;
        this.displayName = displayName;
        this.religion = religion;
        this.aboutme = aboutme;
        this.maritalStatus = maritalStatus;
        this.location = location;
        this.height = height;
    }

    @SerializedName("birthday")
    private String birthday;

    @SerializedName("figure")
    private String figure;

    @SerializedName("occupation")
    private String occupation;

    @SerializedName("gender")
    private String gender;

    @SerializedName("ethnicity")
    private String ethnicity;

    @SerializedName("real_name")
    private String realName;

    @SerializedName("profile_picture")
    private String profilePicture;

    @SerializedName("display_name")
    private String displayName;

    @SerializedName("religion")
    private String religion;

    @SerializedName("aboutme")
    private String aboutme;

    @SerializedName("marital_status")
    private String maritalStatus;

    @SerializedName("location")
    private String location;

    @SerializedName("height")
    private String height;

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }

    public String getFigure() {
        return figure;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRealName() {
        return realName;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getReligion() {
        return religion;
    }

    public void setAboutme(String aboutme) {
        this.aboutme = aboutme;
    }

    public String getAboutme() {
        return aboutme;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return
                "User{" +
                        "birthday = '" + birthday + '\'' +
                        ",figure = '" + figure + '\'' +
                        ",occupation = '" + occupation + '\'' +
                        ",gender = '" + gender + '\'' +
                        ",ethnicity = '" + ethnicity + '\'' +
                        ",real_name = '" + realName + '\'' +
                        ",profile_picture = '" + profilePicture + '\'' +
                        ",display_name = '" + displayName + '\'' +
                        ",religion = '" + religion + '\'' +
                        ",aboutme = '" + aboutme + '\'' +
                        ",marital_status = '" + maritalStatus + '\'' +
                        ",location = '" + location + '\'' +
                        ",height = '" + height + '\'' +
                        "}";
    }
}