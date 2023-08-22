package com.quan.framework.spring.mvc.crypto.decrypt;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import com.quan.framework.spring.mvc.crypto.CryptoHelper;
import com.quan.framework.spring.mvc.crypto.annotation.RSADecrypt;

/**
 * RSA 解密实现
 *
 * @author Force-oneself
 * @date 2023-08-21
 */
public class RSADecryptor implements CustomizableAdviceDecryptor {

    public static final String PRI = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBALxyT/DXInUZdpTuHTVEYNw9exLv7WeUht9KwkxRMDvJxEwVsf+0Wn1DHMFsRrR6mOonNG0vnCJ57yfkTYfuUTwioer8t5puUWmHbmG145uB8Fqu8ZsxXCeXhyYnuB2vLR3OzJfw3TWPvuuBcBe3EWV8Cz466yIaBTLjkR/PkL7VAgMBAAECgYEAh0S681NKLao4f5EosCrEKHONW7uTu01XPwNH00ZYDCK54C+WlMkeR1ocUw9o9XYnO7UmodWSCwFh1U3BBXjhH8CU4VR8Bna3UEdtRcxMD28N6G5pIn2iTMYkEo/UdM+67NKsuy1Cmgg8V9Me/KuOJVLpiRTs0iE+teRfeYKcSuECQQDoxjb6gYN6+gucmcy7AlSCGSz5o2C+O7h7yxmmkFodJpbqiEo7dDglqIzRuEOlF+/3B+pBTgWPQVKDcMT1+dLdAkEAzz/T7xIISUZAmR+u9Mkxee/gWdNJezbigWC988VjdK9A11bmKr+ApR+uNfVKILTcKb5PMG4HSpXHKb0eSZEwWQJBAONKUPrQpgrZx6bVQuTERLJ4VMM59cgrVluCmPxcbbGTMb+27w0Y0Y5sA3zwuRSmyAjl8KZtwd39Ac4YuioZE/0CQQDFR0zGVbPtjCIRoEc6XAnsuJom5ykbULNr1Fr8oPh7Khfe46lVJ9WdWPrMAiH94Cty0OUwhIwMQ2bCJHOgM8SBAkAN5Mqq+kSlXNQdjS25YnwQKxmUjJo/0UcNIx2KYqxJzjovU51Dg9yQpwYGVZe2Pctpod7MfO5dlke8s7fVR57x\n";
    public static final String PUB = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyDr2mhpDhq1jp82sohNM\n" +
            "6E3C/6gv/wZ0AIdsazcUTxXfdsTZAFxkjcopJdPCQP80V9Dm1ugCJTAHO9fffhGE\n" +
            "Qcxyz1YEv5JQHXbHszD4F3ZLtR8TegGQ1ZKOn3ZEkr5H3u98+JJsYs4y/bVwqVHa\n" +
            "38yPwKJ0xn+9eSS7koqLd2zId1CRV89UZ4uaAwDFipGx/eCPStUsDCzQSnybhvz9\n" +
            "xfo7c8s+WoraFMEWeiKakWqGBSEG95yy/jzEhVfiKWDx3LEJoRVW7YMCwM85+2Gv\n" +
            "cJZ1QEtCg0YNI1roOykYM7S+HAvVYdoER0FHq52pzfyJ4jrlTD23evVm1vKRO45o\n" +
            "EwIDAQAB";

    @Override
    public boolean support(DecryptAdviceHolder holder) {
        return CryptoHelper.isAnnotated(holder.getParameter(), RSADecrypt.class);
    }

    @Override
    public byte[] decryptor(DecryptAdviceHolder holder, byte[] ciphertext) {
        return SecureUtil.rsa(PRI, null).decrypt(Base64.decode(ciphertext), KeyType.PrivateKey);
    }

}
