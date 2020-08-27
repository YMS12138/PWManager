import cn.ymslucky.pwmanager.tools.JNCryptorTool;
import org.cryptonode.jncryptor.CryptorException;

public class Test2 {
    public static void main(String[] args) throws CryptorException {
        String cip = "\u0003\u0001`��4J�\u001BI���Kx@R��+��N��[�1�����.R�$Hv@?��?^$.���\\�F�g�\u0004\u0011%�hmʼ`W��S\f�\u00177Ճ�t4>r";

        byte[] bytes = JNCryptorTool.JNCryptor.decryptData(cip.getBytes(), JNCryptorTool.SecretKey.toCharArray());
        System.out.println(new String(bytes));
    }
}
