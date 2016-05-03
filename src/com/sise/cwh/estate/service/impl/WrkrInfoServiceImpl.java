package com.sise.cwh.estate.service.impl;

import java.util.List;
import java.util.Map;

import com.sise.cwh.estate.dao.WrkrInfoDao;
import com.sise.cwh.estate.entity.Wrkrinfo;
import com.sise.cwh.estate.service.WrkrInfoService;
import com.sise.cwh.estate.util.Pagination;

public class WrkrInfoServiceImpl implements WrkrInfoService {

	private WrkrInfoDao wrkrInfoDao;
	public void setWrkrInfoDao(WrkrInfoDao wrkrInfoDao) {
		this.wrkrInfoDao = wrkrInfoDao;
	}

	@Override
	public List<Wrkrinfo> queryAllWrkrinfos() {
		// TODO Auto-generated method stub
		return wrkrInfoDao.getAll();
	}

	@Override
	public List<Map<String, Object>> queryWrkrInfoBy(String wrkrNo,
			String wrkrNm, String wrkrPst, String roleId,Pagination pg) {
		return wrkrInfoDao.queryWrkrInfoBy(wrkrNo, wrkrNm, wrkrPst, roleId, pg);
	}

	@Override
	public String getWrkrNo() {
		// TODO Auto-generated method stub
		return wrkrInfoDao.getWrkrNo();
	}

	@Override
	public void saveWrkrInfo(Wrkrinfo wrkrinfo) {
		 wrkrInfoDao.save(wrkrinfo);
	}

	@Override
	public void deleteWrkrInfo(String wrkrNos) {
		String []wrkrNo = wrkrNos.split(",");
		for(int i=0;i<wrkrNo.length;i++){
		wrkrInfoDao.delete(wrkrNo[i]);
		}
		
	}

}
