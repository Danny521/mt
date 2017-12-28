/**
 * Copyright: Copyright (c) 2016 
 * Company:东方网力科技股份有限公司
 * 
 * @author yuwenhao
 * @date 2016年11月23日 下午10:41:28
 * @version V1.0
 */
package com.danny.web.sevice;

/**
  * @ClassName: Header
  * @Description: 响应头
  * @author yuwenhao
  * @date 2016年11月23日 下午10:41:28
  *
  */
public class RespHeader {
    private String contentEncoding;
    private int contentLength = 0;
    public RespHeader(){};
    public RespHeader(String contentEncoding, int contentLength) {
        super();
        this.contentEncoding = contentEncoding;
        this.contentLength = contentLength;
    }
    public String getContentEncoding() {
        return contentEncoding;
    }
    public void setContentEncoding(String contentEncoding) {
        this.contentEncoding = contentEncoding;
    }
    public int getContentLength() {
        return contentLength;
    }
    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }
    
}
