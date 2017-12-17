package SWProject.Model;

import java.io.Serializable;

public class Suggest implements Serializable
{
    private int suggestID;
    private String userID;
    private String type;
    private String suggestion;


    public int getSuggestID() {
        return suggestID;
    }

    public void setSuggestID(int suggestID) {
        this.suggestID = suggestID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }
}
