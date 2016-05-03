package com.sise.cwh.estate.service.impl;

import com.sise.cwh.estate.dao.CusLikeHosDao;
import com.sise.cwh.estate.entity.Cuslikehos;
import com.sise.cwh.estate.service.CusLikeHosService;


public class CusLikeHosServiceImpl implements CusLikeHosService {

	private CusLikeHosDao cusLikeHosDao;
	public void setCusLikeHosDao(CusLikeHosDao cusLikeHosDao) {
		this.cusLikeHosDao = cusLikeHosDao;
	}
	@Override
	public void saveCusLikeHos(Cuslikehos cuslikehos) {
		cusLikeHosDao.save(cuslikehos);
		
	}

}
