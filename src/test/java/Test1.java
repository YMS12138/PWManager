import cn.ymslucky.pwmanager.tools.JNCryptorTool;
import org.cryptonode.jncryptor.AES256JNCryptor;
import org.cryptonode.jncryptor.CryptorException;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class Test1 {
    public static void main(String[] args) throws CryptorException, UnsupportedEncodingException {
        AES256JNCryptor jnCryptor = new AES256JNCryptor();

        String plaintext = "怎么飞也飞也飞不高嗷嗷嗷啊！";
        String ciphertext;

        byte[] bytes = jnCryptor.encryptData(plaintext.getBytes(), JNCryptorTool.SecretKey.toCharArray());
        ciphertext = new String(bytes, StandardCharsets.UTF_8);
        System.out.println(ciphertext);

        byte[] bytes1 = jnCryptor.decryptData(bytes, JNCryptorTool.SecretKey.toCharArray());

        System.out.println(new String(bytes1));
    }
}
