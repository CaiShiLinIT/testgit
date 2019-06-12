package com.jt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.ItemCat;
import com.jt.vo.EasyUITree;

@Service
public class ItemCatServiceImpl implements ItemCatService{
	@Autowired
	private ItemCatMapper itemCatMapper;
	
	@Override
	public String findItemCatNameById(Long itemCatId) {
		ItemCat itemCat = itemCatMapper.selectById(itemCatId);
		return itemCat.getName();
	}
	//新增商品类型列表展示
	//select id,name,status from tb_item_cat where parent_id=parentId
	@Override
	public List<EasyUITree> findItemCatByParentId(Long parentId) {
		List<ItemCat> list=findItemCat(parentId);//findItemCatByParentId(parentId)
		List<EasyUITree> easyUITree = new ArrayList<>();
		for (ItemCat itemCat : list) {
			EasyUITree easyTree = new EasyUITree();
			easyTree.setId(itemCat.getId())
			.setText(itemCat.getName());
			String status = itemCat.getIsParent()?"closed":"open";
			easyTree.setState(status);
			easyUITree.add(easyTree);
		}
		return easyUITree;
	}
	private List<ItemCat> findItemCat(Long parentId) {
		QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<ItemCat>();
		queryWrapper.eq("parent_id", parentId);
		return itemCatMapper.selectList(queryWrapper);
	}
	
	
}
