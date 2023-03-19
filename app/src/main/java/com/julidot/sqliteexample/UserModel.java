package com.julidot.sqliteexample;

public class UserModel {

    private final int idUser;
    private final String userName;
    private final int userAge;
    private final boolean isActive;

    // constructors
    public UserModel(int idUser, String userName, int userAge, boolean isActive) {
        this.idUser = idUser;
        this.userName = userName;
        this.userAge = userAge;
        this.isActive = isActive;
    }

    // toSting is needed for printing the contents of a class object
    @Override
    public String toString() {
        return "UserModel{" +
                "idUser=" + idUser +
                ", userName='" + userName + '\'' +
                ", age=" + userAge +
                ", isActive=" + isActive +
                '}';
    }

    public int getIdUser() {
        return idUser;
    }

    public String getUserName() {
        return userName;
    }


    public int getUserAge() {
        return userAge;
    }


    public boolean isActive() {
        return isActive;
    }

}
