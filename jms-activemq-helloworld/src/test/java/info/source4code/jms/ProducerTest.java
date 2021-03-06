package info.source4code.jms;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.jms.JMSException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ProducerTest {

    private static Producer producer;
    private static Consumer consumer;

    @BeforeClass
    public static void setUpBeforeClass() throws JMSException {
        producer = new Producer();
        producer.create("helloworld.q");

        consumer = new Consumer();
        consumer.create("helloworld.q");
    }

    @AfterClass
    public static void tearDownAfterClass() throws JMSException {
        producer.close();
        consumer.close();
    }

    @Test
    public void testGetGreeting() {
        try {
            producer.sendName("John", "Doe");

            String greeting = consumer.getGreeting(1000);
            assertEquals("Hello John Doe!", greeting);

        } catch (JMSException e) {
            fail("a JMS Exception occurred");
        }
    }

    @Test
    public void testNoGreeting() {
        try {
            String greeting = consumer.getGreeting(1000);
            assertEquals("no greeting", greeting);

        } catch (JMSException e) {
            fail("a JMS Exception occurred");
        }
    }
}
