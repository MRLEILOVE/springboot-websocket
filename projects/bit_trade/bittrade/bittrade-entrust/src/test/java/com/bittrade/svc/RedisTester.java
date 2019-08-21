package com.bittrade.svc;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.bittrade.svc.base.BaseTester;

import redis.clients.jedis.JedisCluster;

public class RedisTester extends BaseTester {

	@Autowired
	JedisCluster jedisCluster;

	@Test
	public void test() {
		System.out.println( "jedisCluster=" + jedisCluster );
		System.out.println( jedisCluster.get( "ab12" ) );
		jedisCluster.set( "ab12", "12" );
		System.out.println( jedisCluster.get( "ab12" ) );
	}

	@Autowired
	JedisCluster jc;
//	RedisTemplate<String, Object> redisTemplate;
	StringRedisTemplate redisTemplate;
	@Autowired
	public void setRT(StringRedisTemplate rt) {
		this.redisTemplate = rt;
	}
	
	@Test
	public void test_2() {
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
	public void test_3() {
		String str_key = "abc";
		System.out.println( "=" + jc.rpush( str_key, "a1", "a2" ) );
		System.out.println( "=" + jc.rpop( str_key ) );
		System.out.println( "=" + jc.rpop( str_key ) );
		System.out.println( "=" + jc.rpop( str_key ) );
	}

}
