package com.tg.persisten.queue;

import java.util.Calendar;

import com.tg.model.IShardable;

public class GetTimeCheck implements ICheckable
{
	private int getTime; 
	@Override
	public void setCheckableFlag() 
	{
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.getMaximum(Calendar.MONTH) + 1;
		getTime = year * 100 + month;
	}

	@Override
	public boolean check(Object obj) 
	{
		//IShardable<Integer> checkVO = (IShardable<Integer>) obj;
		//即时入库
		return  false;
		//return checkVO.getShardFlag() == getTime;
	}
}
