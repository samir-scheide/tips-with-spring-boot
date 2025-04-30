package dev.samir.draft;

import org.springframework.data.redis.core.RedisHash;

/**
 * Represents a draft tip.
 * This class is used to store draft tips in Redis.
 * It contains a UUID and a message.
 */
@RedisHash("drafts")
public record Draft(Long id, String message, String uuid) {}