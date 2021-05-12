package com.easy;

import com.segi.uhomecp.utils.ASCUtil;
import org.jasypt.util.text.BasicTextEncryptor;

public class AscUtil {
    public static void main(String[] args) throws Exception {
//        		ASCUtil des = new ASCUtil();
//		System.out.println("--------------");
//		System.out.println("input----->>:"+args[0]);
//		String result = des.encryption(args[0]);
//		System.out.println("encryp----->>:"+result);
//		System.out.println("decrypt----->>:"+decryption(result));
//		jdbc.username=NjekwJ99+po=
//		jdbc.password=+RTYb/VgcvMYvBsivIz/tw==
//        jdbc.username=Or8i3XboPqc=
//                jdbc.password=BQ+OPspqTqpyE/b88Ya6kQ==
        ASCUtil des = new ASCUtil();
        System.out.println("--------------");
        System.out.println(ASCUtil.decryption("Xm2Y5AOHeZI="));
        System.out.println(ASCUtil.decryption("RzPHK4KXpL63m09dWL2i6w=="));
        System.out.println("medApp--->"+des.encryption("knowApp"));
        System.out.println("732_Rsrxr3su7--->"+des.encryption("aXrXU3c83q"));
//        jdbc.username=EHBTwYwr8jg=
//                jdbc.password=/Whxdgv680Qrprpp6WqXGg==
//		result = decryption("k+IRHtpG8hI=");
//		System.out.println("encryp----->>:"+result);
//		result = decryption("0SE0jRMPz/Pit2QB2dBUZw==");
//		System.out.println("encryp----->>:"+result);
//		result = decryption("k+IRHtpG8hI=");
//		System.out.println("encryp----->>:"+result);
//		result = decryption("0SE0jRMPz/Pit2QB2dBUZw==");
//		System.out.println("encryp----->>:"+result);

        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        //加密所需的salt(盐)
        textEncryptor.setPassword("EbfYkitulv73I2p0mXI50JMXoaxZTKJ0");
        //要加密的数据（数据库的用户名或密码）
//        String username = textEncryptor.encrypt("uhomeb");
//        String password = textEncryptor.encrypt("bsvrvto0320dt");


        String username = textEncryptor.decrypt("HVqufEpwxfj6A4f3EOAPkg0IcQWfyQ6B");
        String password = textEncryptor.decrypt("bnoXwX2tMwNP4bj6vtXqWAUZQvnGfFSTrhMVmk3iktk=");
        System.out.println("username:"+username);
        System.out.println("password:"+password);
    }
}
