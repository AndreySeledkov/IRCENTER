package org.ircenter.server.service.user;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Seledkov Kostyantyn
 * Date: 14.02.12
 * Time: 10:09
 */
public enum Gender {
    NONE(2, "Не выбран"), FEMALE(0, "Женский"), MALE(1, "Мужской");

    private int genderId;
    private String genderName;

    private Gender(int id, String name) {
        this.genderId = id;
        this.genderName = name;
    }

    public static Gender getGender(int id) {
        if (id == FEMALE.genderId) {
            return FEMALE;
        } else if (id == MALE.genderId) {
            return MALE;
        }
        return NONE;
    }
    
    public static List<Gender> getList(){
        Gender[] g = values();
        List<Gender> lst = new ArrayList<Gender>(g.length);
        for (Gender f : g) {
            lst.add(f);
        }
        return lst;
    }

    public String getGenderName() {
        return genderName;
    }

    public int getGenderId() {
        return genderId;
    }
}
