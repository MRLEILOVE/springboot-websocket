//package com.core.web.common.config.oauth2;
//
//import org.springframework.security.crypto.password.PasswordEncoder;
//
///**
// * 〈自定义无加密密码验证〉
// *
// * @author 
// * @create 
// * @since 1.0.0
// */
//public class NoEncryptPasswordEncoder__ implements PasswordEncoder {
//
//    @Override
//    public String encode(CharSequence charSequence) {
//        return (String) charSequence;
//    }
//
//    @Override
//    public boolean matches(CharSequence charSequence, String s) {
//        return s.equals((String) charSequence);
//    }
//}