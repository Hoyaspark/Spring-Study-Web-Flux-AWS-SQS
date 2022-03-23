package com.study.webflux.aws.sqs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.Acknowledgment;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SqsMessageReceiver {


    @SqsListener(value = "index.fifo", deletionPolicy = SqsMessageDeletionPolicy.NEVER)
    public void listen(String params, Acknowledgment acknowledgment) {
        String hello = params.replaceAll("[\\[\\]]", "");

        List<Integer> seqList = Arrays.stream(hello.split(","))
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        seqList.forEach((seq) -> {
            log.info("seq: {}", seq);
        });

        log.info("SQS Message Receive : {}", seqList);
        acknowledgment.acknowledge();
    }
}
