package com.zhowin.study.voide.bean;

/**
 * author Z_B
 * date :2019/11/23 11:58
 * description:
 */
public class UserStatus {
    public String accessToken;
    public int userStatus;
    public boolean userStatusChecked = false;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    public boolean isUserStatusChecked() {
        return userStatusChecked;
    }

    public void setUserStatusChecked(boolean userStatusChecked) {
        this.userStatusChecked = userStatusChecked;
    }

    @Override
    public String toString() {
        return "UserStatus{" +
                "accessToken='" + accessToken + '\'' +
                ", userStatus=" + userStatus +
                ", userStatusChecked=" + userStatusChecked +
                '}';
    }
}
