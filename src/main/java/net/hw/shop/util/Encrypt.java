package net.hw.shop.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by howard on 2017/5/6.
 */
public class Encrypt {
    /**
     * 传入文本内容，返回SHA-256串
     *
     * @param strText
     * @return
     */
    public String SHA256(final String strText) {
        return SHA(strText, "SHA-256");
    }

    /**
     * 传入文本内容，返回SHA-512串
     *
     * @param strText
     * @return
     */
    public String SHA512(final String strText) {
        return SHA(strText, "SHA-512");
    }

    /**
     * 字符串SHA加密
     *
     * @param strText
     * @param strType
     * @return
     */
    private String SHA(final String strText, final String strType) {
        // 定义返回值
        String strResult = null;

        // 判断是否为有效字符串
        if (strText != null && strText.length() > 0) {
            try {
                // SHA 加密开始
                // 创建加密对象，并传入加密对象
                MessageDigest messageDigest = MessageDigest.getInstance(strType);
                // 传入待加密字符串
                messageDigest.update(strText.getBytes());
                // 得到加密字节数组
                byte byteBuffer[] = messageDigest.digest();

                // 创建字符串缓冲对象
                StringBuffer strHexString = new StringBuffer();
                // 遍历字节数组，将其元素放入字符串缓冲对象
                for (int i = 0; i < byteBuffer.length; i++) {
                    String hex = Integer.toHexString(0xff & byteBuffer[i]);
                    if (hex.length() == 1) {
                        strHexString.append('0');
                    }
                    strHexString.append(hex);
                }
                // 得到返回结果
                strResult = strHexString.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        return strResult;
    }
}
