package com.jt.vo;

import java.io.Serializable;
import java.util.List;

import com.jt.pojo.Item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**该类是展现表格数据的*/
@Data
@Accessors(chain=true)
@NoArgsConstructor
@AllArgsConstructor
public class EasyUIData implements Serializable{
	private static final long serialVersionUID = -7593775376565877096L;
	private Integer total; //记录总数
	private List<Item> rows; //展现数据集合
}
