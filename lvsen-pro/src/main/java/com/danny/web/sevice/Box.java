/**
 * Copyright: Copyright (c) 2016 
 * Company:东方网力科技股份有限公司
 * 
 * @author yuwenhao
 * @date 2016年11月23日 下午4:47:13
 * @version V1.0
 */
package com.danny.web.sevice;

/**
  * @ClassName: Box
  * @Description: 盒子实体
  * @author yuwenhao
  * @date 2016年11月23日 下午4:47:13
  *
  */
public class Box {
    private String id;
    private String name;
    
    public Box(String id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "{id:"+this.id+","+"name:"+this.name+"}";
    }
}
