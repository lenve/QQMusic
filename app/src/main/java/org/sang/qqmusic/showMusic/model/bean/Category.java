package org.sang.qqmusic.showMusic.model.bean;

/**
 * Created by 王松 on 2016/10/19.
 */

public class Category {
    private String category;
    private int id;

    public Category() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category(String category, int id) {
        this.category = category;
        this.id = id;
    }
}
