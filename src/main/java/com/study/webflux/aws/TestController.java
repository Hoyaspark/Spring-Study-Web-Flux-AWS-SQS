package com.study.webflux.aws;

import com.study.webflux.aws.sqs.SqsMessageSender;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final SqsMessageSender sqsMessageSender;

    @GetMapping("/send")
    public String sendSQS() {
        sqsMessageSender.sendMessage();
        return "Success";
    }
}
