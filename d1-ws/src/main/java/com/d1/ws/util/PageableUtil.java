package com.d1.ws.util;

import java.util.Map;

import org.springframework.data.domain.PageRequest;

public class PageableUtil {
	public static PageRequest pageable(Map<String,String> params) {
		PageRequest pageRequest = null;
		
		String page = params.get("page");
		String size = params.get("size");
		String sort = params.get("sort");
		
		if(sort == null) {
			if(page != null && size != null) {
				pageRequest = PageRequest.of(Integer.valueOf(page), Integer.valueOf(size));
			}
		}
		else {
			if(page != null && size != null) {
				pageRequest = PageRequest.of(Integer.valueOf(page), Integer.valueOf(size));
			}
		}
		return pageRequest;
	}
}
