package com.tg.persisten.queue;

public class NoCheck implements ICheckable
{

	@Override
	public void setCheckableFlag() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean check(Object checkVO) {
		return false;
	}

}
