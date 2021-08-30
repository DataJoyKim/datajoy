package com.d2.dw.util;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CacheEventLogger implements CacheEventListener<Object, Object> {

    public void onEvent(CacheEvent<? extends Object, ? extends Object> cacheEvent) {
        log.info("=================>> getKey: {} / getOldValue: {} / getNewValue:{}", cacheEvent.getKey(), cacheEvent.getOldValue(), cacheEvent.getNewValue());
    }
}