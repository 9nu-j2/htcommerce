package ht.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import ht.config.kafka.KafkaProcessor;
import ht.domain.*;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    Repository Repository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='PaymentApproved'"
    )
    public void wheneverPaymentApproved_UpdateAccount(
        @Payload PaymentApproved paymentApproved
    ) {
        PaymentApproved event = paymentApproved;
        System.out.println(
            "\n\n##### listener UpdateAccount : " + paymentApproved + "\n\n"
        );

        // Sample Logic //
        Account.updateAccount(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
