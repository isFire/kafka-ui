package com.manager.kafka.handler;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Data;
import org.springframework.core.io.Resource;

/**
 * <p>Description:  Kafka配置 </p>
 * <p>Company: 古尘工作室</p>
 * <p>Date：2023-07-20 15:25:41 </p>
 *
 * @author <a href="mailto:is_fire_subscribe@hotmail.com">古尘</a>
 * @version V1.0
 */
@Data
public class KafkaParams {


    /**
     * Comma-delimited list of host:port pairs to use for establishing the initial connections to
     * the Kafka cluster. Applies to all components unless overridden.
     */
    private List<String> bootstrapServers = new ArrayList<>(
            Collections.singletonList("localhost:9092"));


    /**
     * Additional properties, common to producers and consumers, used to configure the client.
     */
    private final Map<String, String> properties = new HashMap<>();

    private final Ssl ssl = new Ssl();

    private final Jaas jaas = new Jaas();

    private final Security security = new Security();

    private final Retry retry = new Retry();


    @Data
    public static class Ssl {

        /**
         * Password of the private key in either key store key or key store file.
         */
        private String keyPassword;

        /**
         * Certificate chain in PEM format with a list of X.509 certificates.
         */
        private String keyStoreCertificateChain;

        /**
         * Private key in PEM format with PKCS#8 keys.
         */
        private String keyStoreKey;

        /**
         * Location of the key store file.
         */
        private Resource keyStoreLocation;

        /**
         * Store password for the key store file.
         */
        private String keyStorePassword;

        /**
         * Type of the key store.
         */
        private String keyStoreType;

        /**
         * Trusted certificates in PEM format with X.509 certificates.
         */
        private String trustStoreCertificates;

        /**
         * Location of the trust store file.
         */
        private Resource trustStoreLocation;

        /**
         * Store password for the trust store file.
         */
        private String trustStorePassword;

        /**
         * Type of the trust store.
         */
        private String trustStoreType;

        /**
         * SSL protocol to use.
         */
        private String protocol;

    }

    @Data
    public static class Jaas {

        /**
         * Whether to enable JAAS configuration.
         */
        private boolean enabled;

        /**
         * Login module.
         */
        private String loginModule = "com.sun.security.auth.module.Krb5LoginModule";


        /**
         * Additional JAAS options.
         */
        private final Map<String, String> options = new HashMap<>();


    }

    @Data
    public static class Security {

        /**
         * Security protocol used to communicate with brokers.
         */
        private String protocol;

    }

    @Data
    public static class Retry {


        /**
         * Whether to enable topic-based non-blocking retries.
         */
        private boolean enabled;

        /**
         * Total number of processing attempts made before sending the message to the DLT.
         */
        private int attempts = 3;

        /**
         * Canonical backoff period. Used as an initial value in the exponential case, and as a
         * minimum value in the uniform case.
         */
        private Duration delay = Duration.ofSeconds(1);

        /**
         * Multiplier to use for generating the next backoff delay.
         */
        private double multiplier = 0.0;

        /**
         * Maximum wait between retries. If less than the delay then the default of 30 seconds is
         * applied.
         */
        private Duration maxDelay = Duration.ZERO;

        /**
         * Whether to have the backoff delays.
         */
        private boolean randomBackOff = false;

    }

}
