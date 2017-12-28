//package com.danny.web.sevice;
//
//import java.util.Properties;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import netposa.realtime.kafka.utils.DirectKafkaConsumer;
//import netposa.realtime.kafka.utils.RowMessage;
//import scala.collection.mutable.Buffer;
//
//public class PVDKafkaConsumer {
//    /**
//     * 日志
//     */
//    private static final Logger LOG = LoggerFactory.getLogger(PVDKafkaConsumer.class);
//
//    /**
//     * 
//     */
//    private DirectKafkaConsumer consumer;
//
//    /**
//     * 
//     */
//    private static Properties conProperties = new Properties();
//
//    /**
//     * @Fields INSTANCE : 实例
//     **/
//    protected static PVDKafkaConsumer INSTANCE = null;
//
//    /**
//     * 
//     * @Title: getInstance
//     * @Description: 获取实例
//     * @return
//     */
//    public static PVDKafkaConsumer getInstance() {
//        if (INSTANCE == null) {
//            synchronized (PVDKafkaConsumer.class) {
//                if (INSTANCE == null) {
//                    INSTANCE = new PVDKafkaConsumer();
//                    createConsumerConfig();
//                    LOG.info("--------------->conProperties=" + conProperties.entrySet());
//                }
//            }
//        }
//        return INSTANCE;
//    }
//
//    /**
//     * @Title: startConsumer
//     * @Description: 开启消费者
//     */
//    public void startConsumer() {
//        try {
//            try {
//                consumer = new DirectKafkaConsumer(conProperties);
//            } catch (Exception e) {
//                System.out.println("---->"+e);
//                LOG.error("connect kafka failure, exeption :", e);
//            }
//            if (consumer != null) {
//                LOG.info(" kafka consumer start... ... ...");
//            } else {
//                LOG.error(" kafka consumer start error! consumer = null");
//            }
//        } catch (Exception ex) {
//            LOG.error(" kafka consumer start error... ... ...", ex);
//        }
//    }
//
//    /**
//     * @Title: stopConsumer
//     * @Description: 关闭消费者
//     */
//    public void stopConsumer() {
//        LOG.info("-------stop kafka consumer...");
//        consumer = null;
//    }
//
//    /**
//     * 
//     * @Title: createConsumerConfig
//     * @Description: 创建config
//     */
//    private static void createConsumerConfig() {
//        conProperties.put("server.total.nodes", "");
//        conProperties.put("current.node.id", "");
//        conProperties.put("metadata.broker.list", "");
//        conProperties.put("group.id", "");
//        conProperties.put("service.receive.topic.name", "");
//        conProperties.put("auto.offset.reset", "smallest");
//        conProperties.put("socket.connect.max.retry", "6");
//        conProperties.put("refresh.leader.backoff.ms", "200");
//        conProperties.put("rebalance.backoff.ms", "1200");
//    }
//
//    /**
//     *
//     * @Title: getRowMsgList
//     * @Description: 获取消息
//     * @return
//     */
//    public Buffer<RowMessage> getRowMsgList() {
//        try {
//            if (consumer != null) {
//                return consumer.fetchMessages();
//            }
//        } catch (Exception ex) {
//            LOG.warn(" kafka consumer javaFetchMessage error...: ", ex);
//            try {
//                Thread.sleep(10 * 60 * 1000);
//                consumer = null;
//                consumer = new DirectKafkaConsumer(conProperties);
//            } catch (InterruptedException e) {
//                LOG.error("重连kafka失败！", e);
//            }
//        }
//        return null;
//    }
//
//    public DirectKafkaConsumer getConsumer() {
//        return consumer;
//    }
//
//}
