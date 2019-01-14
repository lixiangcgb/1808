package com.jt.manage.controller;

import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.GenericHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.po.Item;
import com.jt.common.po.ItemDesc;
import com.jt.common.vo.EasyUIResult;
import com.jt.common.vo.SysResult;
import com.jt.manage.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	
	//实现商品信息展现
	@RequestMapping("/query")
	@ResponseBody
	public EasyUIResult findItemByPage
	(Integer page,Integer rows){
		
		return itemService.findItemByPage(page,rows);
	}
	
	/**
	 * 通过itemcat的id查询商品分类的名称
	 * 规则:
	 * 	@ResponseBody
	 *  当返回数据为对象时(VO),则默认的字符集编码为UTF-8
	 *  public abstract class AbstractJackson2HttpMessageConverter extends AbstractHttpMessageConverter<Object>
		implements GenericHttpMessageConverter<Object> {
		public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
	 *  
	 *  如果返回值为String类型时,则默认对的字符集为ISO-8859-1
	 *  StringHttpMessageConverter extends AbstractHttpMessageConverter<String> {

		public static final Charset DEFAULT_CHARSET = Charset.forName("ISO-8859-1");
	 * @param itemId
	 * @return
	 */
	@RequestMapping(value="/cat/queryItemName",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findItemCatNameById(Long itemId){
		
		return itemService.findItemCatNameById(itemId);
	}
	
	//实现商品的新增        desc
	@RequestMapping("/save")     
	@ResponseBody      
	public SysResult saveItem(Item item,String desc){
		try {
			itemService.saveItem(item,desc);
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201,"新增商品失败");
	}
	
	
	//编辑商品信息
	@RequestMapping("/update")
	@ResponseBody
	public SysResult updateItem(Item item,String desc){
		try {
			itemService.updateItem(item,desc);
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SysResult.build(201, "修改失败");
	}
	
	
	//实现商品下架   ids=100,200,300
	@RequestMapping("/instock")
	@ResponseBody
	public SysResult instock(Long[] ids){
		try {
			int status = 2;	//表示下架
			itemService.updateState(ids,status);
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201,"用户下架失败");
	}
	
	//根据商品id查询商品详情
	@RequestMapping("/query/item/desc/{itemId}")
	@ResponseBody
	public SysResult findItemDescById(@PathVariable Long itemId){
		try {
			ItemDesc itemDesc = 
					itemService.findItemDescById(itemId);
			return SysResult.oK(itemDesc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201,"查询失败");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
