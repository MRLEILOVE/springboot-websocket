package com.test.bittrade.svc;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.bittrade.svc.base.BaseTester;

import redis.clients.jedis.JedisCluster;

public class RedisTester extends BaseTester {

	private static final Logger LOG = LoggerFactory.getLogger(RedisTester.class);

//	@Autowired
//	private org.springframework.data.redis.core.StringRedisTemplate redisTemplate;
	@Autowired
	private JedisCluster jedisCluster;

	@Test
	public void test() {
//		redisTemplate.opsForValue().set("key", "value");
//		System.out.println(redisTemplate.opsForValue().get("key"));
		
		System.out.println("jedisCluster=" + jedisCluster);
		LOG.info("jedisCluster=" + jedisCluster);
//		jedisCluster.set("key", "value");
//		System.out.println(jedisCluster.get("key"));
//		for (int i = 0; i < 100; i++) {
//			jedisCluster.set("" + i, "value " + i);
//		}
//		jedisCluster.set("ab12", "value 101 ");
		System.out.println(jedisCluster.get("ab12"));
	}

}
