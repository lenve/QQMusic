package org.sang.qqmusic.showMusic.model;

import org.sang.qqmusic.showMusic.model.bean.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王松 on 2016/10/19.
 */

public class ILeftDataImpl implements ILeftData {
    @Override
    public List<Category> getCategory() {
        List<Category> list = new ArrayList<>();
        list.add(new Category("欧美",3));
        list.add(new Category("内地",5));
        list.add(new Category("港台",6));
        list.add(new Category("韩国",16));
        list.add(new Category("日本",17));
        list.add(new Category("民谣",18));
        list.add(new Category("摇滚",19));
        list.add(new Category("销量",23));
        list.add(new Category("热歌",26));
        return list;
    }
}
