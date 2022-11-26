package com.example.demo.cache;

import com.example.demo.annotation.Cache;
import com.example.demo.domain.Message;
import com.example.demo.repository.MessageRepository;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

@Cache
public class MessageCache {
    private final static Logger logger = LoggerFactory.getLogger(MessageCache.class);

    private final static long CACHE_BY_FROM_PERSON_ID_EXPIRE_TIME = Long.getLong("cache.byfromperson.expire.time", 10);
    private final static long CACHE_BY_TO_PERSON_ID_EXPIRE_TIME = Long.getLong("cache.bytoperson.expire.time", 10);
    private final LoadingCache<Long, Collection<Message>> cacheByFromPersonId;
    private final LoadingCache<Long, Collection<Message>> cacheByToPersonId;

    public MessageCache(MessageRepository messageRepository) {
        this.cacheByFromPersonId = Caffeine.newBuilder()
                .expireAfterWrite(CACHE_BY_FROM_PERSON_ID_EXPIRE_TIME, TimeUnit.MINUTES)
                .build(id -> {
                    Collection<Message> result = messageRepository.getByFromPersonId(id);
                    logger.debug("Invoke cacheByFromPersonId({}) result {}", id, result);
                    return result;
                });
        this.cacheByToPersonId = Caffeine.newBuilder()
                .expireAfterWrite(CACHE_BY_TO_PERSON_ID_EXPIRE_TIME, TimeUnit.MINUTES)
                .build(id -> {
                    Collection<Message> result = messageRepository.getByToPersonId(id);
                    logger.debug("Invoke cacheByToPersonId({}) result {}", id, result);
                    return result;
                });
    }

    public Collection<Message> getByFromPersonId(long id) {
        return cacheByFromPersonId.get(id);
    }

    public Collection<Message> getByToPersonId(long id) {
        return cacheByToPersonId.get(id);
    }

    public void invalidateCacheByFromPersonId(long id) {
        cacheByFromPersonId.invalidate(id);
    }

    public void invalidateCacheByToPersonId(long id) {
        cacheByToPersonId.invalidate(id);
    }
}
