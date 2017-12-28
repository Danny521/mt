package com.danny.web.sevice;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NavroomClient {
    
    /**
     * 日志
     */
    private static final Logger LOG = LoggerFactory.getLogger(NavroomClient.class);
    /**
     * 配置信息
     */
    private NavRoomCfg cfg;
    
    @SuppressWarnings("unused")
    private NavroomClient(){};
    
    public NavroomClient(NavRoomCfg cfg) {
        this.cfg = cfg;
    }
    
    /**
     * 实时数据socket
     */
    private Socket realDataSocket;
    
    /**
     * 回车换行符
     */
    static final String CRLF = "\r\n";
    /**
     * 读取字节后,编码
     */
    static final String CS_UTF8 = "UTF-8";
    /**
     * 可接受编码
     */
    static final String DEFAULT_ACCEPT_ENCODING="utf-8";
    
    
    /**
     * 
      * @Title: getBoxList
      * @Description: 获取盒子列表
      * @return
     * @throws Exception 
     * @throws IOException 
     * @throws UnknownHostException 
     */
    public Box[] getBoxList() throws UnknownHostException,IOException {
        Map<String,String> params=new HashMap<String,String>();
        params.put("cmd", "lb");
        
        Box[] boxs=null;
        Socket s=null;
        try{
            s = new Socket(cfg.getIp(), cfg.getPort());
            InputStream is = s.getInputStream();
            //发送请求
            sendMsg(s,params);
            //获取响应头
            RespHeader respHeader=getResponseHeader(is);
            //读取body部分
            if(respHeader!=null){
                InputStream isContent =getInputStream(is, respHeader.getContentEncoding());
                if (respHeader.getContentLength() > 0) {
                   String body=getResponseBody(isContent, respHeader.getContentLength());
                   String [] arr=body.split("\r\n");
                   if(arr!=null){
                       boxs=new Box[arr.length]; 
                       for(int i=0;i<arr.length;i++){
                           String str=arr[i];
                           String[] data=str.split(":");
                           if(data!=null){
                               boxs[i]=new Box(data[0].trim(), data[1].trim());
                           }
                       }
                   }
                }
            }   
        }catch (UnknownHostException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }finally{
            close(s);
        }
        return boxs;
    }
    
    /**
     * 
      * @Title: getRealData
      * @Description: 获取实时数据
      * @param dataProcess
      * @throws IOException
     */
    public void realDataListener(DataProcess dataProcess){
        Map<String,String> params=new HashMap<String,String>();
        params.put("cmd", "l");
        try {
            realDataSocket = new Socket(cfg.getIp(), cfg.getPort());
            InputStream is = realDataSocket.getInputStream();
            //发送请求
            sendMsg(realDataSocket,params);

            //获取响应头
            RespHeader respHeader=getResponseHeader(is);
            //读取body部分
            if(respHeader!=null){
                InputStream isContent =getInputStream(is, respHeader.getContentEncoding());
                if (respHeader.getContentLength() > 0) {
                   getResponseBody(isContent, respHeader.getContentLength());
                } else {
                    WifiRecord data=readOneRecord(isContent);
                    while (true) {
                        if(data!=null){
                            dataProcess.process(data);
                        }
                        data=readOneRecord(isContent);
                    }
                }
            }
        } catch (IOException e) {
            LOG.error("接收wifi实时数据出现错误:"+e.getMessage()+",将关闭连接,5s后自动重连...",e);
        }finally{
            try {
                close(realDataSocket);
                Thread.sleep(5000);
            } catch (IOException | InterruptedException e) {
                LOG.error(e.getMessage(),e);
            }
            LOG.info("接收wifi实时数据,重新连接...");
            realDataListener(dataProcess);
        }
    }
    
    /**
     * 
      * @Title: closeConn4RealData
      * @Description: 关闭实时数据连接
      * @throws IOException
     */
    public void closeConn4RealData() throws IOException{
        close(realDataSocket);
    }
    
    
    /**
     * 
      * @Title: getHisDate
      * @Description: 获取历史数据
      * @param boxId
      * @return
     * @throws Exception 
     */
    public WifiRecord[] getHisData(int boxId,int offset,int size) throws UnknownHostException,IOException{
        Map<String,String> params=new HashMap<String,String>();
        params.put("cmd", "dh");
        params.put("box", String.valueOf(boxId));
        params.put("offset", String.valueOf(offset));
        
        WifiRecord[] wifiRecords=null;
        Socket s=null;
        try{
            s = new Socket(cfg.getIp(), cfg.getPort());
            InputStream is = s.getInputStream();
            //发送请求
            sendMsg(s,params);
            //获取响应头
            RespHeader respHeader=getResponseHeader(is);
            //读取body部分
            if(respHeader!=null){
                InputStream isContent =getInputStream(is, respHeader.getContentEncoding());
                if (respHeader.getContentLength() == 0) {
                    wifiRecords=new WifiRecord[size];
                    for (int i = 0; i < size; i++) {
                        WifiRecord data = readOneRecord(isContent);
                        if (data != null) {
                            wifiRecords[i]=data;
                        }else{
                            break;
                        }
                    }
                } 
            }   
        }catch (UnknownHostException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }finally{
            close(s);
        }
        return wifiRecords;
    }
    
    
    void close(Socket s) throws IOException{
        if(s!=null){
            if(!s.isClosed()){
                s.getOutputStream().close();
            }
            if(!s.isClosed()){
                s.getInputStream().close();
            }
            if(!s.isClosed()){
                s.close();
            }
        }
    }
    
     
    
     /**
      * 
       * @Title: getResponseBody
       * @Description: 获取响应body
       * @param isContent
       * @param contentLength
       * @return
       * @throws IOException
      */
    String getResponseBody(InputStream isContent,int contentLength ) throws IOException{
        String respContent=null;
        if (contentLength > 0) {
            int off = 0;
            byte[] buf = new byte[contentLength];
            while (off < contentLength) {
                off += isContent.read(buf, off, contentLength - off);
            }
            respContent = new String(buf, CS_UTF8);
            LOG.debug(respContent);
        }
        return respContent;
        
    }
    
    /**
     *  
      * @Title: readOneRecord
      * @Description: 读一条记录
      * @param isContent
      * @return
      * @throws IOException
     */
    WifiRecord readOneRecord(InputStream isContent) throws IOException{
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        final int recordLength = 16;
        byte[] buf = new byte[recordLength];
        //读一条记录
        if(readUntil(isContent, buf, 0, recordLength) == recordLength){
            boolean end=true;
            for(int i=0;i<buf.length;i++){
                if(buf[0]!=0){
                    end=false;
                    break;
                }
            }
            if(!end){
                int offset = 0;
                int id = ByteUtils.readInt32HighLast(buf, offset);
                offset += 4;
                String clientmac = ByteUtils.hex2Str(buf, offset, 6);
                offset += 6;
                int seconds = ByteUtils.readInt32HighLast(buf, offset);
                offset += 4;
                c.set(1970, 0, 1, 0, 0, 0);
                c.add(Calendar.SECOND, seconds);
                Date stime = c.getTime();
                short rssi = ByteUtils.readInt16HighLast(buf, offset);
                // offset += 2;
                LOG.debug(String.format("%d %s@%s %d", id, clientmac, sdf.format(stime), rssi));
                
                return new WifiRecord(id,clientmac,stime);
            }
        }
        return null;
    }
    
    
    /**
     * 
      * @Title: sendMsg
      * @Description: 发送信息
      * @param s
      * @param params
      * @throws IOException
     */
    void sendMsg(Socket s,Map<String,String> params) throws IOException{
        StringBuilder sendMsg = new StringBuilder();
        StringBuilder reqBody = new StringBuilder();
        
        reqBody.append("pid="+cfg.getPid()+"&");
        for(Iterator<String> iter=params.keySet().iterator();iter.hasNext();){
            String key=iter.next();
            reqBody.append(key+"="+params.get(key)+"&");
        }
        reqBody.append(CRLF);
        
        sendMsg.append("NDTP/1.0 HELLO").append(CRLF);
        sendMsg.append("Accept-Encoding: ").append(DEFAULT_ACCEPT_ENCODING).append(CRLF);
        sendMsg.append("Content-Length: ").append(reqBody.length()).append(CRLF).append(CRLF);
        sendMsg.append(reqBody);
        
        LOG.debug(">>>>"+"\n"+sendMsg.toString());
        
        OutputStream os = s.getOutputStream();
        send(os,sendMsg.toString());
    }
    
    
    /**
     * 
      * @Title: getResponseHandler
      * @Description: 获取响应头
      * @param is
      * @return
      * @throws IOException
     */
    RespHeader getResponseHeader(InputStream is) throws IOException{
        boolean valid = true;
        String line = readLine(is, CS_UTF8);
        
        StringBuilder respHeaderSb=new StringBuilder();
        respHeaderSb.append(line+"\n");
//        System.out.println("<<<<");
//        System.out.println(line);
        valid = line.startsWith("NDTP/1.0");
        
        String contentEncoding = null ;
        int contentLength = 0;
        
        //读取请求头
        while (valid) {
            line = readLine(is, CS_UTF8);
//            System.out.println(line);
            respHeaderSb.append(line+"\n");
            if (isStringNullOrEmpty(line))
                break;

            String[] items = parseHeadLine(line);
            if (items != null) {
                String val = items[1];
                if(items[0].equals("Content-Encoding")){
                    contentEncoding = val;
                }else if(items[0].equals("Content-Length")){
                    contentLength = Integer.parseInt(val);
                }
            } else {
                valid = false;
                break;
            }
        }
        
        LOG.debug("<<<<"+"\n"+respHeaderSb);
        
        if(valid){
            return new RespHeader(contentEncoding,contentLength);
        }
        return null;
    }
     
    /**
     * 
      * @Title: getInputStream
      * @Description: 获取输入流
      * @param is
      * @param contentEncoding
      * @return
      * @throws IOException
     */
    InputStream getInputStream(InputStream is,String contentEncoding) throws IOException{
        InputStream isContent = is;
        if (isStringNullOrEmpty(contentEncoding) == false) {
            if (contentEncoding.equals("gzip"))
                isContent = new java.util.zip.GZIPInputStream(is);
            else if (contentEncoding.equalsIgnoreCase("deflate"))
                isContent = new java.util.zip.DeflaterInputStream(is);
        }
        return isContent;
        
    }
    
    
    static boolean isStringNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }
    
    /**
     * 
      * @Title: readLine
      * @Description: 读一行
      * @param is
      * @param enc
      * @return
      * @throws IOException
     */
    static String readLine(InputStream is, String enc) throws IOException {
        byte[] buf = new byte[4096];
        int i = 0;
        while (i < buf.length) {
            if (is.read(buf, i, 1) == 0 || buf[i] == '\n')
                break;
            i++;
        }

        byte[] bytes = new byte[i];
        System.arraycopy(buf, 0, bytes, 0, i);

        return new String(bytes, enc).trim();
    }
    
    static void send(OutputStream s, String content) throws IOException {
        byte[] buf = content.getBytes(CS_UTF8);
        s.write(buf);
    }

    static int readUntil(InputStream s, byte[] buf, int offset, int len) throws IOException {
        int count = 0;
        while (len > 0) {
            int n = s.read(buf, offset, len);
            count += n;
            if (n == 0 || n == len)
                break;
            offset += n;
            len -= n;
        }
        return count;
    }
    
    static String[] parseHeadLine(String line) {
        int pos = line.indexOf(':');
        if (pos <= 0)
            return null;
        String name = line.substring(0, pos).trim();
        String val = line.substring(pos + 1).trim();
        return new String[] { name, val };
    }

     
}
