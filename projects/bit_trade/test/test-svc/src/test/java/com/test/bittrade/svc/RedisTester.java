package com.test.bittrade.svc;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

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

//	RedisTemplate<String, Object> redisTemplate;
	StringRedisTemplate redisTemplate;
	@Autowired
	public void setRT(StringRedisTemplate rt) {
		this.redisTemplate = rt;
	}
	
	@Test
	public void testList_1() {
		String redisKey = "abc";
		BoundListOperations<String, String> unreadMsg = redisTemplate.boundListOps( redisKey );
		Long unreadCount = unreadMsg.size();
		for (int i = 0; i <= unreadCount; i++) {
			Object message = unreadMsg.rightPop();
			// 推送消息
			System.out.println( "message=" + message );
		}
		
		unreadMsg.leftPush( "a1" );
		unreadMsg.leftPush( "a2" );
		
		System.out.println( "unreadMsg.size()=" + unreadMsg.size() );
		
		System.out.println( "unreadMsg.leftPop()=" + unreadMsg.leftPop() );
		System.out.println( "unreadMsg.leftPop()=" + unreadMsg.leftPop() );
	}
	
	@Test
	public void testList_2() {
		String str_key = "abc";
		System.out.println( "=" + jedisCluster.rpush( str_key, "a1", "a2" ) );
		System.out.println( "=" + jedisCluster.rpop( str_key ) );
		System.out.println( "=" + jedisCluster.rpop( str_key ) );
		System.out.println( "=" + jedisCluster.rpop( str_key ) );
	}

}
