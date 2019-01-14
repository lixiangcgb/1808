package com.jt.manage.service;

import java.util.Date;
import java.util.List;

import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.common.po.Item;
import com.jt.common.po.ItemDesc;
import com.jt.common.vo.EasyUIResult;
import com.jt.manage.mapper.ItemDescMapper;
import com.jt.manage.mapper.ItemMapper;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private ItemDescMapper itemDescMapper;
	
	//实现商品查询
	@Override
	public EasyUIResult findItemByPage(Integer page, Integer rows) {
		
		//1.获取商品记录总数
		//int total = itemMapper.findItemCount();
		
		//使用通用mapper查询  
		//sql:select count(*) from tb_item 
		int total = itemMapper.selectCount(null);
		
		//2.获取商品列表信息使用分页
		/**
		 * SELECT * FROM tb_item LIMIT 0,20   第1页
		   SELECT * FROM tb_item LIMIT 20,20 第2页
		   SELECT * FROM tb_item LIMIT 40,20 第3页
		   SELECT * FROM tb_item LIMIT (n-1)*rows,rows
		 */
		int start = (page - 1) * rows;
		List<Item> itemList = 
				itemMapper.findItemByPage(start,rows);
		return new EasyUIResult(total, itemList);
	}

	@Override
	public String findItemCatNameById(Long itemId) {
		
		return itemMapper.findItemCatNameById(itemId);
	}
	
	/**
	 * 数据库主键自增
	 * id: 1,2,3,4,6,7 线程一旦获取主键后,其他线程将不能获取相同的主键
	 * 思路:	
	 * 		insert into ........
	 * 		select last_insert_id()
	 */
	@Override
	public void saveItem(Item item,String desc) {
		//同时入库2张表
		item.setStatus(1);	//1.上架状态  2下架
		item.setCreated(new Date());
		item.setUpdated(item.getCreated());
		itemMapper.insert(item);//新增完成后一定有id
		/**
		 * 通用Mapper动态获取主键
		 * Executing: SELECT LAST_INSERT_ID() 
		 */
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(item.getId()); 
		//必须和item中Id一致
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(item.getCreated());
		itemDesc.setUpdated(item.getCreated());
		itemDescMapper.insert(itemDesc);
		
	}

	@Override
	public void updateItem(Item item,String desc) {
		//添加时间
		item.setUpdated(new Date());
		itemMapper.updateByPrimaryKeySelective(item);
		
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(item.getId());
		itemDesc.setItemDesc(desc);
		itemDesc.setUpdated(item.getUpdated());
		itemDescMapper.updateByPrimaryKeySelective(itemDesc);
	}

	@Override
	public void updateState(Long[] ids, int status) {
		//效率太低了
		/*for (Long itemId : ids) {
			Item item = new Item();
			item.setId(itemId);
			item.setStatus(state);
			itemMapper.updateByPrimaryKeySelective(item);
		}*/
		
		itemMapper.updateStatus(ids,status);
		
	}

	@Override
	public ItemDesc findItemDescById(Long itemId) {
		
		return itemDescMapper.selectByPrimaryKey(itemId);
	}

	
	@Override
	public Item findItemById(Long itemId) {
		
		return itemMapper.selectByPrimaryKey(itemId);
	}

	
	
	
	
	
	
	
	
}
