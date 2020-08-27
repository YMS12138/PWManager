package cn.ymslucky.pwmanager.tools;

import org.cryptonode.jncryptor.AES256JNCryptor;

/**
 * 加密解密工具
 * 加密方式：AES
 */
public class JNCryptorTool {
    public static final AES256JNCryptor JNCryptor = new AES256JNCryptor();
    public static final String SecretKey = "65av614d5sv46s4e5689cwec48as";
}
