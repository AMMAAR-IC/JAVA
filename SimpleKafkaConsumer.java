// SimpleKafkaConsumer.java
import org.apache.kafka.clients.consumer.*;
import java.time.Duration;
import java.util.*;

public class SimpleKafkaConsumer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "demo-group");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        try (KafkaConsumer<String,String> c = new KafkaConsumer<>(props)) {
            c.subscribe(List.of("my-topic"));
            while (true) {
                var recs = c.poll(Duration.ofSeconds(1));
                recs.forEach(r -> System.out.println(r.offset() + ": " + r.value()));
            }
        }
    }
}
