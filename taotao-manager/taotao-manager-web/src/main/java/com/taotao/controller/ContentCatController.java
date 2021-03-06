package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ContentCategoryService;

@Controller
@RequestMapping("/content/category")
public class ContentCatController {

	@Autowired
	private ContentCategoryService contentCategoryService;

	@RequestMapping("/list")
	@ResponseBody
	public List<EasyUITreeNode> getContentCatList(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
		return contentCategoryService.getContentCat(parentId);
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public TaotaoResult insertContentCat(Long parentId, String name) {
		return contentCategoryService.insertContentCat(parentId, name);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public TaotaoResult updateContentCatName(Long id, String name) {
		return contentCategoryService.updateContentCatName(id, name);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public TaotaoResult deleteContenCat(Long id) {
		return contentCategoryService.deleteContentCat(id);
	}
}
