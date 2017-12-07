package com.tg.persisten.queue;

import java.util.ArrayList;
import java.util.List;

import com.tg.biz.impl.IBatchUpdateService;
import com.tg.model.IBatchAble;

public class PersistanceIntKeyMainLoop implements Runnable
{
	public static final long executorTime = 30 * 1000L;
	
	private List<IBatchAble> sqlvos = new ArrayList<>();
	
	private final IBatchUpdateService<IBatchAble> service;
	
	private ICheckable checkExcutor;
	
	private Thread thread = null;
	
	@SuppressWarnings("unchecked")
	public PersistanceIntKeyMainLoop(@SuppressWarnings("rawtypes") IBatchUpdateService service,ICheckable checkExcutor)
	{
		this.service = service;
		this.checkExcutor = checkExcutor;
		thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void run() 
	{
		
		while(true)
		{
			try
			{
				Thread.sleep(executorTime);
				List<IBatchAble> batchs = null;
				synchronized(sqlvos)
				{
					if (sqlvos.isEmpty())
					{
						continue;
					}
					batchs = new ArrayList<>(sqlvos.size());
					batchs.addAll(sqlvos);
					sqlvos.clear();
				}
				List<IBatchAble> tmp = null;
				for (int i = 0; i < batchs.size();i++)
				{
					if (i % 1000 == 0 || i == batchs.size() - 1)
					{
						if (i > 0 || i == batchs.size() - 1)
						{
							service.batchInsert(tmp, true);
						}
						tmp = new ArrayList<>();
					}
					tmp.add(batchs.get(i));
				}
				batchs = null;
			}
			catch(Throwable error)
			{
				
			}
		}
		
	}

	public boolean checkBatchable(IBatchAble vo)
	{
		boolean flag = false;
		synchronized (sqlvos) 
		{
			flag = checkExcutor.check(vo);
			if (flag)
			{
				sqlvos.add(vo);
			}
		}
		return flag;
	}

}
