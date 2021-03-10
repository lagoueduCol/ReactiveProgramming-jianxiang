package com.springcss.account.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.stream.reactive.FluxSender;
import org.springframework.cloud.stream.reactive.StreamEmitter;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.springcss.account.domain.Account;
import com.springcss.message.AccountChangedEvent;
import com.springcss.message.AccountMessage;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;

@Component
public class ReactiveAccountChangedSource {

    private static final Logger logger = LoggerFactory.getLogger(ReactiveAccountChangedSource.class);
  
	private FluxSink<Message<AccountChangedEvent>> eventSink;
	private Flux<Message<AccountChangedEvent>> flux;
	
	public ReactiveAccountChangedSource() {
		this.flux = Flux.<Message<AccountChangedEvent>>create(sink -> this.eventSink = sink).publish().autoConnect();
	}
	
    private Mono<Void> publishAccountChangedEvent(String operation, Account account){
    	logger.debug("Sending message for Account Id: {}", account.getId());    	
    	
    	AccountMessage accountMessage = new AccountMessage(account.getId(), account.getAccountCode(), account.getAccountName());
    	
    	AccountChangedEvent originalevent =  new AccountChangedEvent(
    			AccountChangedEvent.class.getTypeName(),
        		operation,
        		accountMessage);

        Mono<AccountChangedEvent> monoEvent = Mono.just(originalevent);
        
        return monoEvent.map(event -> eventSink.next(MessageBuilder.withPayload(event).build())).then();
    }
    
    @StreamEmitter
	public void emit(@Output(Source.OUTPUT) FluxSender output) {
		output.send(this.flux);
	}
    
    public Mono<Void> publishAccountUpdatedEvent(Account account) {
    	return publishAccountChangedEvent("UPDATE", account);
    }
    
    public Mono<Void> publishAccountDeletedEvent(Account account) {
    	return publishAccountChangedEvent("DELETE", account);
    }
}
