package com.wapple.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import redis.clients.jedis.Jedis;

public class RedisUtil {
	private static final Logger log = LoggerFactory.getLogger(RedisUtil.class);

	/**
	 * 设置redis已经存储的 key的有效期，单位是秒
	 * 
	 * @param key
	 * @param exTime
	 * @return
	 */
	public static Long expire(String key, int exTime) {
		Jedis jedis = null;
		Long result = null;
		try {
			jedis = RedisPool.getJedis();
			result = jedis.expire(key, exTime);
		} catch (Exception e) {
			log.error("expire key:{} error", key, e);
			RedisPool.returnBrokenResource(jedis);
			return result;
		}
		RedisPool.returnResource(jedis);
		return result;
	}

	/**
	 * 向reids中写入数据 带过期时间
	 * 
	 * @param key
	 * @param value
	 * @param exTime
	 * @return
	 */
	public static String setEx(String key, String value, int exTime) {
		Jedis jedis = null;
		String result = null;
		try {
			jedis = RedisPool.getJedis();
			result = jedis.setex(key, exTime, value);
		} catch (Exception e) {
			log.error("setex key:{} value:{} error", key, value, e);
			RedisPool.returnBrokenResource(jedis);
			return result;
		}
		RedisPool.returnResource(jedis);
		return result;
	}

	/**
	 * redis中存入 永不超时
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static String set(String key, String value) {
		Jedis jedis = null;
		String result = null;

		try {
			jedis = RedisPool.getJedis();
			result = jedis.set(key, value);
		} catch (Exception e) {
			log.error("set key:{} value:{} error", key, value, e);
			RedisPool.returnBrokenResource(jedis);
			return result;
		}
		RedisPool.returnResource(jedis);
		return result;
	}

	/**
	 * redis中取出值
	 * 
	 * @param key
	 * @return
	 */
	public static String get(String key) {
		Jedis jedis = null;
		String result = null;
		try {
			jedis = RedisPool.getJedis();
			result = jedis.get(key);
		} catch (Exception e) {
			log.error("get key:{} error", key, e);
			RedisPool.returnBrokenResource(jedis);
			return result;
		}
		RedisPool.returnResource(jedis);
		return result;
	}

	/**
	 * 移除值
	 * 
	 * @param key
	 * @return
	 */
	public static Long del(String key) {
		Jedis jedis = null;
		Long result = null;
		try {
			jedis = RedisPool.getJedis();
			result = jedis.del(key);
		} catch (Exception e) {
			log.error("del key:{} error", key, e);
			RedisPool.returnBrokenResource(jedis);
			return result;
		}
		RedisPool.returnResource(jedis);
		return result;
	}

}
