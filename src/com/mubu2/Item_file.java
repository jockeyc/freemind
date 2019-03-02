package com.mubu2;

public class Item_file {
	/**
	 * @funtion:文件操作
	 */
	String name;
	String time;  //保存文件时修改
	String path;
	Item_file()
	{
		
	}
	Item_file(String name,String time,String path)
	{
		this.path=path;
		this.name=name;
		this.time=time;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj == null) return false;
		Item_file item = (Item_file) obj;
		
		if(name.equals(item.name) && time.equals(item.time) && path.equals(item.path)) {
			return true;
		}else {
			return false;
		}
	}
	void changePath(String path)
	{
		this.path=path;
	}
	void changeName(String name)
	{
		this.name=name;
	}
	void changeTime(String time)
	{
		this.time=time;
	}
	String getPath()
	{
		return path;
	}
	String getName()
	{
		return name;
	}
	String getTime()
	{
		return time;
	}
	public void show() {
		// TODO 自动生成的方法存根
		System.out.println(path);
		System.out.println(name);
		System.out.println(time);
	}
}
