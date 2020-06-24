package org.lk.daydayup.service;

import org.lk.daydayup.pojo.Item;

public interface ItemService {
    public void addItem(Item item);

    public void giveLike(int itemId);
}
