package com.expense_tracker.UserService.Consumer;


import com.expense_tracker.UserService.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceConsumer {

    private final UserRepository userRepository;

    @KafkaListener(topics = "",groupId = "")
    public void listen(Object eventData){
        try {

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


}
