package com.manager.kafka.handler;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**

/**
 * <p>Description:  Kafka 接口 </p>
 * <p>Date：2023-07-20 15:13:27 </p>
 * @author <a href="mailto:lizhao@eelantech.com">李昭</a>
 * @version V1.0
 */
@RestController
@RequestMapping("/api/kafka")
public class KafkaHandler {

    @PostMapping("/kafka")
    public Mono<String> editKafka(@RequestBody KafkaParams params) {


            return Mono.empty();
    }

}
