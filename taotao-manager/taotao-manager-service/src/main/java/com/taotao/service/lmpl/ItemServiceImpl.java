package com.taotao.service.lmpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper tbItemMapper;
	@Autowired
	private TbItemDescMapper tbItemDescMapper;
	
	/**
	 * 根据商品ID查询商品
	 */
	@Override
	public TbItem itemsQuery(Long id) {
		TbItem tbItem = tbItemMapper.selectByPrimaryKey(id);
		return tbItem;
	}

	/**
	 * 分页查询商品列表
	 */
	@Override
	public EasyUIDataGridResult getItemsList(int page, int size) {
		TbItemExample example = new TbItemExample();
		PageHelper.startPage(page, size);
		List<TbItem> list = tbItemMapper.selectByExample(example);
		
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		EasyUIDataGridResult easyUIDataGridResult = new EasyUIDataGridResult();
		easyUIDataGridResult.setTotal((int)pageInfo.getTotal());
		easyUIDataGridResult.setRows(list);
		return easyUIDataGridResult;
	}

	/**
	 * 添加商品
	 */
	@Override
	public TaotaoResult createItem(TbItem item, String desc) {
		//补全商品信息，并插入商品表
		Long itemId = IDUtils.genItemId();
		item.setId(itemId);
		//商品状态：1-正常、2-下架
		item.setStatus((byte) 1);
		//添加创建日期和修改日期
		Date date = new Date();
		item.setCreated(date);
		item.setUpdated(date);
		tbItemMapper.insert(item);
		
		//补全商品描述信息，并插入商品描述表
		TbItemDesc tbItemDesc = new TbItemDesc();
		tbItemDesc.setItemId(itemId);
		tbItemDesc.setItemDesc(desc);
		tbItemDesc.setCreated(date);
		tbItemDesc.setUpdated(date);
		tbItemDescMapper.insert(tbItemDesc);
		return TaotaoResult.ok();
	}

}
