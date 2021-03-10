package com.springcss.customer.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.springcss.customer.client.AccountMapper;
import com.springcss.customer.repository.redis.AccountRedisRepository;
import com.springcss.message.AccountChangedEvent;
import com.springcss.message.AccountMessage;

import reactor.core.publisher.Mono;

@EnableBinding(AccountChangedChannel.class)
public class ReactiveAccountChangedSink {

	@Autowired
	AccountRedisRepository accountRedisRepository;

    private static final Logger logger = LoggerFactory.getLogger(ReactiveAccountChangedSink.class);

    @StreamListener(AccountChangedChannel.ACCOUNT_CHANGED)
    public void handleAccountChangedEvent(AccountChangedEvent accountChangedEvent) {
    	
        logger.debug("Received a message of type " + accountChangedEvent.getType()); 
    	logger.debug("Received a {} event from the account service for account id {}", 
    			accountChangedEvent.getOperation(), 
    			accountChangedEvent.getAccountMessage().getId());
        
    	System.out.println("Received a account message of id " + accountChangedEvent.getAccountMessage().getId());
    	
    	AccountMessage accountMessage = accountChangedEvent.getAccountMessage();
    	AccountMapper accountMapper = 
    			new AccountMapper(accountMessage.getId(), 
    					accountMessage.getAccountCode(), 
    					accountMessage.getAccountName());
    	
        if(accountChangedEvent.getOperation().equals("UPDATE")) {
        	accountRedisRepository.updateAccount(accountMapper).subscribe(); 	
        } else if(accountChangedEvent.getOperation().equals("DELETE")) {
        	accountRedisRepository.deleteAccount(accountMapper.getId()).subscribe();
        } else {            
            logger.error("The operations {} is undefined ", accountChangedEvent.getOperation());
        }
    }    
}
