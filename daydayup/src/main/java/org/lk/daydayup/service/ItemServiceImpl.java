package org.lk.daydayup.service;

import org.lk.daydayup.mapper.ItemMapper;
import org.lk.daydayup.pojo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService{
    @Autowired ItemMapper itemMapper;

    @Override
    public void addItem(Item item) {
        itemMapper.addItem(item);
    }

    // 点赞+1
    @Override
    public void giveLike(int itemId) {
        itemMapper.giveLike(itemId);
    }
}
