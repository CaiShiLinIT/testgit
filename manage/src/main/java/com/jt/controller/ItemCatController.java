package com.jt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.service.ItemCatService;
import com.jt.vo.EasyUITree;

@RequestMapping("/item/cat")
@Controller
public class ItemCatController {
	@Autowired
	private ItemCatService itemCatService;
	@RequestMapping("/queryItemName")
	@ResponseBody
	public String findItemCatNameById(Long itemCatId) {
		System.out.println(itemCatService.findItemCatNameById(itemCatId));
		return itemCatService.findItemCatNameById(itemCatId);
	}
	//新增商品类型列表展示
	@RequestMapping("/list")
	@ResponseBody
	public List<EasyUITree> findItemCatByParentId(//页面点击后请求携带着自身的id
			@RequestParam(name="id",defaultValue="0")Long parentId){
		return itemCatService.findItemCatByParentId(parentId);
	}
}
