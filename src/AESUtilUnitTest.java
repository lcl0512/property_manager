import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.AESUtil;
import utils.CSVUtil;
import utils.Property;

import javax.annotation.Resource;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import static utils.AESUtil.getKeyFromPassword;

class AESUtilUnitTest {

    @Test
    void givenPassword_whenEncrypt_thenSuccess()
        throws InvalidKeySpecException, NoSuchAlgorithmException, IllegalBlockSizeException,
        InvalidKeyException, BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException {
        // given
        String plainText = "www.baeldung.com";
        String password = "baeldung";
        String salt = "12345678";
        IvParameterSpec ivParameterSpec = AESUtil.generateIv();
        SecretKey key = getKeyFromPassword(password, salt);

        // when
        String cipherText = AESUtil.encryptPasswordBased(plainText, key, ivParameterSpec);
        String decryptedCipherText = AESUtil.decryptPasswordBased(cipherText, key, ivParameterSpec);

        // then
        Assertions.assertEquals(plainText, decryptedCipherText);
    }
    @Test
    void givenFile_whenEncrypt_thenSuccess()
            throws NoSuchAlgorithmException, IOException, IllegalBlockSizeException,
            InvalidKeyException, BadPaddingException, InvalidAlgorithmParameterException,
            NoSuchPaddingException, InvalidKeySpecException {

        String password = "bGlhb2NoYW5nbGlu";
        String salt = "c2FsdDEyMw==";
        SecretKey key = getKeyFromPassword(password,salt);
        String algorithm = "AES/CBC/PKCS5Padding";
        IvParameterSpec ivParameterSpec = AESUtil.generateIv();
        File inputFile = new File("data/property.csv");
        File encryptedFile = new File("data/property.encrypted");
        File decryptedFile = new File("data/property.decrypted");
//        AESUtil.encryptFile(algorithm, key, ivParameterSpec, inputFile, encryptedFile);
        AESUtil.decryptFile(algorithm, key, ivParameterSpec, encryptedFile, decryptedFile);
        List<Property> properties1 = CSVUtil.readCSV("data/property.decrypted");
        System.out.println(properties1.size());
        decryptedFile.delete();
        List<Property> properties = CSVUtil.filter("清华楼");
        for(Property property: properties){
            System.out.println(property);
        }

    }


}
