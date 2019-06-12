package com.jt.service;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EasyUIData;

public interface ItemService {

	EasyUIData findItemByPage(Integer page, Integer rows);

	void updateItem(Item item);

	void deleteItem(Long[] ids);

	Item updateStock(Long[] ids,Integer status);

	void saveItem(Item item, ItemDesc itemDesc);
	
}
