package com.danny.web.sevice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyService {

    private static final Logger LOG = LoggerFactory.getLogger(MyService.class);
    private static NavroomClient navroomClient = null;

    public static void testWifiService() {
        String host = "120.26.74.75";
        String port = "88";
        String pid = "10010";
        try {
            LOG.info("----------->try to create navroomClient Object");
            NavRoomCfg cfg = new NavRoomCfg(host, Integer.parseInt(port), pid);
            navroomClient = new NavroomClient(cfg);
            LOG.info("----------->create navroomClient Object successful");
            Thread wifiThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    navroomClient.realDataListener(new DataProcess() {
                        @Override
                        public void process(WifiRecord res) {
                            LOG.info("----------->" + res.toString());
                        }
                    });
                }
            });
            wifiThread.setName("wifi");
            wifiThread.start();
        } catch (Exception e) {
            LOG.error("----------->connect wifi failed", e);
        }
    }
    
//    public static void kafkaTest() throws UnsupportedEncodingException {
//        // 刷新点位缓存
//        KafkaClient kafkaClient = null;
//        kafkaClient = KafkaClient.getInstance("peopleface");
//        kafkaClient.startConsumer();
//        kafkaClient.subscriptionTopic();
//        System.out.println("===开始获取数据=====");
//        while (true) {
//            ConsumerRecords<String, byte[]> records = null;
//            try {
//                KafkaConsumer<String, byte[]> consumer = kafkaClient.getConsumer();
//                records = consumer.poll(100);
//            } catch (Exception e) {
//                System.out.println("consumer出错:" + e.getMessage());
//                kafkaClient.reconnect();
//                kafkaClient.subscriptionTopic();
//            }
//            if (null != records) {
//                System.out.println("consumer信息:" + records);
//                for (ConsumerRecord<String, byte[]> record : records) {
//                    ByteBuffer buffer = ByteBuffer.wrap(record.value());
//                    int len = buffer.getInt();
//                    byte[] jsonBytes = new byte[len];
//                    buffer.get(jsonBytes);
//                    String res = new String(jsonBytes, "UTF-8");
//                    if (!StringUtils.isEmpty(res)) {
//                        JSONObject resJson = JSON.parseObject(res);
//                        System.out.println("获取kafka中数据为---->：" + resJson);
//                        if (resJson.containsKey("alarm")) {
//                            System.out.println("alarm-->" + resJson.getJSONObject("alarm"));
//                        }
//                    }
//                }
//            }
//        }
//    }


//    public void testService() {
//        String faceMqAddress = "tcp://10.46.0.207:61616";
//        String faceTopicStr = "alarm";
//        LOG.error("------------创建人脸报警");
//        System.out.println("-----66-------创建人脸报警");
//        try {
//            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD,
//                    faceMqAddress);
//            Connection connection = factory.createConnection();
//            connection.start();
//            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//            Topic faceTopic = session.createTopic(faceTopicStr);
//            LOG.error("------------------------>创建人脸报警：" + faceTopicStr);
//            MessageConsumer faceConsumer = session.createConsumer(faceTopic);
//            faceConsumer.setMessageListener(new MessageListener() {
//                @Override
//                public void onMessage(Message message) {
//                    LOG.error("------------------------>收到人脸报警信息");
//                    TextMessage textMessage = (TextMessage) message;
//                    String info = "";
//                    try {
//                        info = textMessage.getText();
//                        FaceMqMessage msg = JSON.parseObject(textMessage.getText(), FaceMqMessage.class);
//                        LOG.error("------------------------>detail=" + JSON.toJSONString(msg));
//                    } catch (JSONException e) {
//                        LOG.error("接收非法消息:" + info);
//                    } catch (JMSException e) {
//                        LOG.error("接收消息error", e);
//                    } catch (Exception e) {
//                        LOG.error("消息处理error", e);
//                    }
//                }
//            });
//        } catch (Exception e) {
//            LOG.error("------------------------连接服务失败" + e);
//        }
//    }

    @Scheduled(fixedRate = 1000)
    public void mySchedule() {
        // LOG.error("xxxxxxxxxxxx");
    }
}
