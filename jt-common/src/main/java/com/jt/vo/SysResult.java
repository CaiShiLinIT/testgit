package com.jt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**系统的返回值对象*/
@Data
@Accessors(chain=true)
@NoArgsConstructor
@AllArgsConstructor
public class SysResult {
	private Integer status; //200表示成功 201表示失败
	private String msg; //后台返回的数据提示信息
	private Object data;//后台返回任意数据
	
	public static SysResult ok() {
		return new SysResult(200,null,null);
	}
	public static SysResult ok(String msg,Object data) {
		return new SysResult(200,msg,data);
	}
	public static SysResult ok(Object data) {
		return new SysResult(200,null,data);
	}
	//这个方法不能加,如果用户调用时传入data的数据类型是String会默认调到这个方法                         
	/*public static SysResult ok(String msg) {
		return new SysResult(200,msg,null);
	}*/
	public static SysResult fail() {
		return new SysResult(200,null,null);
	}
	public static SysResult fail(String msg) {
		return new SysResult(201,msg,null);
	}
}
