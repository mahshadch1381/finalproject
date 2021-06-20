package model;

import java.util.List;

public class Person {
    public String username;
    public String country;
    public String name;
    public String password;
    public String profilePath="-";
    public String accidentalQuestion;
    public List<String> followers;
    public List<String> followings;
   public Person(String user,String password){
       username=user;
       this.password=password;
   }

    public void setAccidentalQuestion(String accidentalQuestion) {
        this.accidentalQuestion = accidentalQuestion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
