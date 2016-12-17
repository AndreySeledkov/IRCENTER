package org.ircenter.server.bean.forum;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class MainPart implements Serializable {
    private long id;
    private int orderIndex;
    private String name;
    private List<Part> parts = new LinkedList<Part>();

    public MainPart() {
    }

    public MainPart(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }


    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MainPart)) return false;
        if (!super.equals(o)) return false;

        MainPart mainPart = (MainPart) o;

        return orderIndex == mainPart.orderIndex && name.equals(mainPart.name);

    }

    @Override
    public int hashCode() {
        int result = orderIndex;
        result = 31 * result + name.hashCode();
        return result;
    }
}
