package com.tg.persisten.queue;

/**
 * @author liyu
 * 对数据是否需要进行批量操作的判断
 * @param <T> 判断的类
 */
public interface ICheckable
{
	/**
	 * 设置判断基准
	 */
	void setCheckableFlag();
	
	/**
	 * 判断是否需要进行批量操作
	 * @param checkVO 判断对象
	 * @return true:是，false:否
	 */
	boolean check(Object checkVO);
}
