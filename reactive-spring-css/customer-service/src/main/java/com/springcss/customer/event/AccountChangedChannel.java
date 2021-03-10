package com.springcss.customer.event;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface AccountChangedChannel {
	
	String ACCOUNT_CHANGED = "accountChangedChannel";
	
    @Input(AccountChangedChannel.ACCOUNT_CHANGED)
    SubscribableChannel accountChangedChannel();
}
