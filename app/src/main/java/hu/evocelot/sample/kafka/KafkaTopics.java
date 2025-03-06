package hu.evocelot.sample.kafka;

/**
 * Utility class for defining constants related to Kafka topics.
 * <p>
 * This class contains the keys for various Kafka topics used within the
 * application. Kafka topics are used to organize messages within a Kafka
 * broker, and the keys defined here are used to specify the topic when sending
 * or consuming messages.
 * </p>
 * 
 * @author mark.danisovszky
 */
public class KafkaTopics {
    /**
     * The Kafka topic key for notifying that a sample entity has been saved.
     * <p>
     * This topic is used by related modules to trigger actions that need to
     * be performed after a sample entity has been successfully saved.
     * </p>
     */
    public static final String SAMPLE_SAVED = "sample-saved";
}