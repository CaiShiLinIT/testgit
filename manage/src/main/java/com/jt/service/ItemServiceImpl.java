package com.jt.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jt.mapper.ItemDescMapper;
import com.jt.mapper.ItemMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EasyUIData;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private ItemDescMapper itemDescMapper;
	@Override
	public EasyUIData findItemByPage(Integer page, Integer rows) {
		QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
		Integer total = itemMapper.selectCount(queryWrapper);
		Integer start = (page-1)*20;
		List<Item> list = itemMapper.findItemByPage(start,rows);
		return new EasyUIData().setTotal(total).setRows(list);
	}

	//修改商品信息
	//根据主键更新
	@Transactional
	@Override
	public void updateItem(Item item) {
		item.setUpdated(new Date());
		itemMapper.updateById(item);
	}

	@Override
	public void deleteItem(Long[] ids) {
		//1.手动删除
		//itemMapper.deleteItem(ids);
		//2.自动删除
		List<Long> itemList = Arrays.asList(ids);
		itemMapper.deleteBatchIds(itemList);

	}
	//上架 下架
	@Override
	public Item updateStock(Long[] ids,Integer status) {
		//update tb_item set status=${status},updated=${updated} 
		//where id in (1,21,5,21,2)
		Item item = new Item();
		item.setStatus(status).setUpdated(new Date());
		UpdateWrapper<Item> updateWrapper = new UpdateWrapper<>();
		List<Long> longList = Arrays.asList(ids);
		updateWrapper.in("id", longList);
		itemMapper.update(item, updateWrapper);
		return item;
	}
	@Transactional
	@Override 
	public void saveItem(Item item, ItemDesc itemDesc) {
		//同时插入两个表
		item.setStatus(1)
		.setUpdated(new Date())
		.setCreated(item.getUpdated());
		itemMapper.insert(item);
		
		itemDesc.setItemId(item.getId())
		.setCreated(new Date())
		.setUpdated(new Date());
		itemDescMapper.insert(itemDesc);
	}


}
