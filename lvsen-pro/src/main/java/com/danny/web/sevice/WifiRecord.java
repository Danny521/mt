/**
 * Copyright: Copyright (c) 2016 
 * Company:东方网力科技股份有限公司
 * 
 * @author yuwenhao
 * @date 2016年11月23日 下午10:24:15
 * @version V1.0
 */
package com.danny.web.sevice;

import java.util.Date;

/**
  * @ClassName: Data
  * @Description: 盒子采集到的数据
  * @author yuwenhao
  * @date 2016年11月23日 下午10:24:15
  *
  */
public class WifiRecord {
    /**
     * 若是实时数据,则是盒子id, 若是历史数据,则是记录id
     */
    private int id;
    /**
     * 客户端mac地址
     */
    private String clientMac;
    /**
     * 采集时间
     */
    private Date date;
    
    public WifiRecord(int id, String clientMac, Date date) {
        this.id = id;
        this.clientMac = clientMac;
        this.date = date;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getClientMac() {
        return clientMac;
    }
    public void setClientMac(String clientMac) {
        this.clientMac = clientMac;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    
    @Override
    public String toString() {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "{id=" + id + ", clientMac=" + clientMac + ", date=" + sdf.format(date) + "}";
    }
    
    
}
