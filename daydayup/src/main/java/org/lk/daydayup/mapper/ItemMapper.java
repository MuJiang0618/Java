package org.lk.daydayup.mapper;

import org.lk.daydayup.pojo.Item;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemMapper {
    void addItem(Item item);

    void giveLike(int itemId);
}
