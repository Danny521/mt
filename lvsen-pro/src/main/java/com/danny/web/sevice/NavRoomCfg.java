/**
 * Copyright: Copyright (c) 2016 
 * Company:东方网力科技股份有限公司
 * 
 * @author yuwenhao
 * @date 2016年11月23日 下午3:55:58
 * @version V1.0
 */
package com.danny.web.sevice;
public class NavRoomCfg {
    private String ip;
    private int port;
    private String pid;
    public NavRoomCfg(String ip, int port, String pid) {
        this.ip = ip;
        this.port = port;
        this.pid = pid;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public int getPort() {
        return port;
    }
    public void setPort(int port) {
        this.port = port;
    }
    public String getPid() {
        return pid;
    }
    public void setPid(String pid) {
        this.pid = pid;
    }

   
    
}
