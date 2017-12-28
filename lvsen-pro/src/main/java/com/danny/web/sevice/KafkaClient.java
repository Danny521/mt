package com.danny.web.sevice;

/**
 * @ClassName: KafkaClient
 * @Description: kafka消费端
 * @author wangshang
 * @date 2016年11月24日 下午5:36:39
 *
 */
//public class KafkaClient {
//
//    /**
//     * 日志
//     */
//    private static final Logger LOG = LoggerFactory.getLogger(KafkaClient.class);
//    /**
//     * 
//     */
//    private String bootstrapServers;
//
//    /**
//     * 
//     */
//    private String groupId;
//
//    /**
//     * 
//     */
//    private String enableAutoCommit = "true";
//
//    /**
//     * 
//     */
//    private String autoCommitInterval = "1000";
//
//    /**
//     * 
//     */
//    private String sessionTimeoutMs = "30000";
//
//    /**
//     * 主题
//     */
//    private String topic = "";
//
//    /**
//     * kafka消费者
//     */
//    private KafkaConsumer<String, byte[]> consumer = null;
//
//    /**
//     * @Fields INSTANCE : 实例
//     **/
//    protected static KafkaClient INSTANCE = null;
//
//    /**
//     * 配置信息
//     */
//    protected Properties conProperties = new Properties();
//
//    /**
//     * 创建一个新的实例 KafkaClient.
//     * <p>
//     * Title:
//     * </p>
//     * <p>
//     * Description:
//     * </p>
//     */
//    private KafkaClient() {
//
//    }
//
//    /**
//     * 创建一个新的实例 KafkaClient.
//     * <p>
//     * Title:
//     * </p>
//     * <p>
//     * Description:
//     * </p>
//     * 
//     * @param origProp
//     * @param producter
//     *            生产者标识 用于区分多个kafka服务配置
//     */
//    private KafkaClient(String producter) {
//        setPropValue(producter);
//    }
//
//    /**
//     * 创建一个新的实例 KafkaClient.
//     * @param origProp
//     * @param producter
//     * @param topic
//     */
//    private KafkaClient(String producter, String topic) {
//        setPropValue(producter);
//        this.topic = topic;
//    }
//
//    /**
//     * @Title: getInstance
//     * @Description: 获取实例
//     * @param origProp
//     * @param producter
//     * @return
//     */
//    public static KafkaClient getInstance(String producter) {
//        if (INSTANCE == null) {
//            synchronized (KafkaClient.class) {
//                if (INSTANCE == null) {
//                    INSTANCE = new KafkaClient(producter);
//                }
//            }
//        }
//        return INSTANCE;
//    }
//
//    /**
//     * @Title: getInstance
//     * @Description: 获取实例
//     * @param origProp
//     * @param producter
//     * @param topic
//     * @return
//     */
//    public static KafkaClient getInstance(String producter, String topic) {
//        if (INSTANCE == null) {
//            synchronized (KafkaClient.class) {
//                if (INSTANCE == null) {
//                    INSTANCE = new KafkaClient(producter, topic);
//                }
//            }
//        }
//        return INSTANCE;
//    }
//
//    /**
//     * @Title: createConsumerConfig
//     * @Description: 初始化kafka配置
//     * @return
//     */
//    protected void createConsumerConfig() {
//        conProperties.put("bootstrap.servers", this.bootstrapServers);
//        conProperties.put("group.id", this.groupId);
//        conProperties.put("enable.auto.commit", this.enableAutoCommit);
//        conProperties.put("auto.commit.interval.ms", this.autoCommitInterval);
//        conProperties.put("session.timeout.ms", this.sessionTimeoutMs);
//        conProperties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        conProperties.put("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");
//    }
//
//    /**
//     * @Title: setPropValue
//     * @Description: 设置客户端连接参数值
//     * @param origProp
//     *            原始配置信息
//     * @param producter
//     *            生产者名称，用于标识多kafka情况
//     */
//    private void setPropValue(String producter) {
////        peopleface.bootstrap.servers=10.3.24.75:9092
////                peopleface.group.id=pbd_zt
////                peopleface.topic=face_data
////                peopleface.enable.auto.commit=true
////                peopleface.auto.commit.interval.ms=1000
////                peopleface.session.timeout.ms=30000
//
//        this.bootstrapServers = "10.3.24.75:9092";
//        this.groupId = "pbd_zt";
//        this.topic = "face_data";
//        this.enableAutoCommit = "true";
//        this.autoCommitInterval = "1000";
//        this.sessionTimeoutMs = "30000";
//    }
//
//    /**
//     * @Title: startConsumer
//     * @Description: 开启消费者
//     */
//    public void startConsumer() {
//        createConsumerConfig();
//        if (null == consumer) {
//            LOG.info("------->" + conProperties.entrySet());
//            consumer = new KafkaConsumer<>(conProperties);
//        }
//    }
//
//    /**
//     * @Title: stopConsumer
//     * @Description: 关闭消费者
//     */
//    public void stopConsumer() {
//        consumer = null;
//    }
//
//    /**
//     * 
//     * @Title: reconnect
//     * @Description: 重新连接kafka
//     */
//    public void reconnect() {
//        try {
//            LOG.info("重新连接kafka");
//            Thread.sleep(5 * 1000);
//            consumer = null;
//            consumer = new KafkaConsumer<>(conProperties);
//        } catch (InterruptedException e) {
//            LOG.error("连接kafka失败", e);
//        }
//    }
//
//    /**
//     * @Title: subscriptionTopic
//     * @Description: 订阅主题
//     * @param topic
//     *            主题标识
//     */
//    public void subscriptionTopic() {
//        consumer.subscribe(Arrays.asList(topic));
//    }
//
//    public String getTopic() {
//        return topic;
//    }
//
//    public void setTopic(String topic) {
//        this.topic = topic;
//    }
//
//    public KafkaConsumer<String, byte[]> getConsumer() {
//        return consumer;
//    }
//
//}
