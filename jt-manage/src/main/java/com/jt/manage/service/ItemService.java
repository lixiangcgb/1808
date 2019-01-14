package com.jt.manage.service;

import com.jt.common.po.Item;
import com.jt.common.po.ItemDesc;
import com.jt.common.vo.EasyUIResult;

public interface ItemService {

	EasyUIResult findItemByPage(Integer page, Integer rows);

	String findItemCatNameById(Long itemId);

	void saveItem(Item item, String desc);

	void updateItem(Item item, String desc);

	void updateState(Long[] ids, int state);
	
	ItemDesc findItemDescById(Long itemId);

	Item findItemById(Long itemId);

}
