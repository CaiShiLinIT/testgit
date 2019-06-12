package com.jt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.service.ItemService;
import com.jt.vo.EasyUIData;
import com.jt.vo.SysResult;
@RequestMapping("/item")
@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/query")
	@ResponseBody
	public EasyUIData findItemByPage(Integer page,Integer rows) {
		EasyUIData easyUIData = itemService.findItemByPage(page,rows);
		return easyUIData;
	}
	//修改商品信息
	@RequestMapping("/update")
	@ResponseBody
	public SysResult updateItem(Item item) {
		try {
			itemService.updateItem(item);
			return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.fail("商品修改失败");
		}
	}
	//删除商品信息
	@RequestMapping("/delete")
	@ResponseBody
	public SysResult deleteItem(Long[] ids) { //前端虽然传的是1,2,3,4,5串 但spring帮我们拆成了数组
		try {
			itemService.deleteItem(ids);
			return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.fail();
		}
	}
	//下架
	@RequestMapping("/instock")
	@ResponseBody
	public SysResult instockStock(Long[] ids) {
		try {
			Integer status =2;
			itemService.updateStock(ids,status);
			return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.fail();
		}
	}
	
	//上架
	@RequestMapping("/reshelf")
	@ResponseBody
	public SysResult reshelfStock(Long[] ids) {
		try {
			Integer status =1;
			itemService.updateStock(ids, status);
			return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.fail();
		}
	}
	
	//商品详情添加
	@RequestMapping("/save")
	@ResponseBody
	public SysResult saveItem(Item item,ItemDesc itemDesc) {
		try {
			itemService.saveItem(item,itemDesc);
			return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.fail();
		}
	}
	
	@RequestMapping("/query/item/desc/{itemId}")
	@ResponseBody
	public SysResult findItemDescById(@PathVariable Long itemId) {
		//itemService.findItemDescById(itemId);
		return null;
	}
}
