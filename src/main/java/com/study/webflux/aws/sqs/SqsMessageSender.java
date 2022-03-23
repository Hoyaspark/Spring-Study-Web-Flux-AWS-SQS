package com.study.webflux.aws.sqs;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.UUID;

@Service
@Slf4j
public class SqsMessageSender {
    private final AmazonSQS sqs;
    private final Environment env;
    private final String SQS_URL;
    private final ObjectMapper objectMapper;

    public SqsMessageSender(AmazonSQS amazonSQS, Environment env, ObjectMapper objectMapper) {
        this.sqs = amazonSQS;
        this.env = env;
        this.SQS_URL = env.getProperty("aws.sqs.url");
        this.objectMapper = objectMapper;
    }

    public void sendMessage() {
        try {
            String json = objectMapper.writeValueAsString(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9,10));

            SendMessageRequest request =
                    new SendMessageRequest()
                            .withQueueUrl(SQS_URL)
                            .withMessageBody(json)
                            .withMessageGroupId("test")
                            .withMessageDeduplicationId(UUID.randomUUID().toString());

            SendMessageResult result = sqs.sendMessage(request);

            if(ObjectUtils.isEmpty(result) || "".equals(result.getMessageId())){
                throw new RuntimeException();
            }

            log.info("Send SQS Result : {}", result);
        } catch (Exception e) {
            log.error("{}", e);
        }
    }
}
