// SimpleKafkaProducer.java
import org.apache.kafka.clients.producer.*;
import java.util.*;

public class SimpleKafkaProducer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        try (Producer<String,String> producer = new KafkaProducer<>(props)) {
            ProducerRecord<String,String> rec = new ProducerRecord<>("my-topic", "key", "hello-from-java");
            producer.send(rec).get();
            System.out.println("Sent message");
        } catch (Exception e) { e.printStackTrace(); }
    }
}
