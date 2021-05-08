package utils;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static utils.AESUtil.getKeyFromPassword;

public class CSVUtil {
    private static List<Property> properties;
    public static List<Property> readCSV(String filename) throws IOException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        String password = "bGlhb2NoYW5nbGlu";
        String salt = "c2FsdDEyMw==";
        SecretKey key = getKeyFromPassword(password,salt);
        String algorithm = "AES/CBC/PKCS5Padding";
        IvParameterSpec ivParameterSpec = AESUtil.generateIv();
        File encryptedFile = new File(filename);
        File decryptedFile = new File("data/property.decrypted");
        AESUtil.decryptFile(algorithm, key, ivParameterSpec, encryptedFile, decryptedFile);
        properties = new LinkedList<>();
        FileReader fileReader = new FileReader(decryptedFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        decryptedFile.delete();// 删除解密文件
        String line = bufferedReader.readLine(); // first line
        while((line = bufferedReader.readLine())!=null){
            line = line.replace('"',' ');
            String[] split = line.split(",");
            if(split.length<8){
                for(String str: split)
                    System.out.println(str);
            }
            Property property = null;
            try {
                String area = split[0];//区域名称
                String position = split[1];//存放地点
                String code = split[2];//资产
                String name = split[3];//资产名称
                String mode = split[4];//规格型号
                Integer number = parseInt(split[5]);//数量
                Double price = parseDouble(split[6]);//单价
                LocalDate buyDate = generateDate(split[8]);
                property = new Property(area,position,code,name,mode,number,price,buyDate);
            }catch (Exception e){
                for(int i = 0;i<split.length;++i){
                    System.out.println(split[i]);
                }
                e.printStackTrace();
            }

            properties.add(property);
        }
        return properties;
    }
    public static void encryptFile(File inputFile) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, IOException, BadPaddingException, InvalidKeyException {
        String password = "bGlhb2NoYW5nbGlu";
        String salt = "c2FsdDEyMw==";
        SecretKey key = getKeyFromPassword(password,salt);
        String algorithm = "AES/CBC/PKCS5Padding";
        IvParameterSpec ivParameterSpec = AESUtil.generateIv();
        File encryptedFile = new File("data/property.encrypted");
        AESUtil.encryptFile(algorithm, key, ivParameterSpec, inputFile, encryptedFile);
    }
    public static void summarize(List<Property> properties){
        for(int i =0;i<properties.size(); ++i) {
            for (int j = i+1; j < properties.size();) {
                Property p1 = properties.get(i);
                Property p2 = properties.get(j);
                if(p1.equalsName(p2)){
                    p1.number += p2.number;
                    p1.totalPrice += p2.totalPrice;
                    properties.remove(p2);
                } else
                    ++j;
            }

        }

    }
    private static LocalDate generateDate(String date){
       date = date.trim();
       String[] dates = date.split("/");
       int year = parseInt(dates[0]);
       int month = parseInt(dates[1]);
       int day = parseInt(dates[2]);
       LocalDate localDate = LocalDate.of(year,month,day);
       return localDate;
    }
    public static void writeCSV(String filename, List<Property> properties) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("区域名称,存放地点,资产编码,资产名称,规格型号,数量,单价,总价,购置日期\n");
        for(Property property: properties){
            stringBuilder.append(property.toString());
        }
        FileWriter fileWriter = new FileWriter(new File(filename));
        fileWriter.write(stringBuilder.toString());
        fileWriter.close();
    }
    public static void writeCSV(File file, List<Property> properties) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("区域名称,存放地点,资产名称,规格型号,数量,单价,总价,购置日期\n");
        for(Property property: properties){
            stringBuilder.append(property.toString3());
        }
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(stringBuilder.toString());
        fileWriter.close();
    }
    public static void writeCSV2(File file, List<Property> properties) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("区域名称,资产名称,规格型号,数量,单价,总价,购置日期\n");
        for(Property property: properties){
            stringBuilder.append(property.toString2());
        }
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(stringBuilder.toString());
        fileWriter.close();
    }
    public static List<Property> filter(String area, String position, String propertyName){
        List<Property> ret = new LinkedList<>();
        for(Property property: properties){
            if(property.getArea().equals(area)){
                if(position==null || property.getPosition().equals(position))
                    ret.add(property);
            }
        }
        if(propertyName!=null){
            for (int i = 0; i < ret.size(); ) {
                Property property = ret.get(i);
                if(!property.getName().equals(propertyName))
                    ret.remove(property);
                else
                    ++i;
            }
//            for(Property property: ret){
//                if(!property.getName().equals(propertyName))
//                   ret.remove(property);
//            }
        }
        return ret;
    }
    public static List<Property> filter(String area, String position){
        return filter(area,position,null);
    }
    public static List<Property> filter(String area){
        return filter(area,null,null);
    }
    public static Object[][] list2Array(List<Property> properties){
        Object[][] objects = new Object[properties.size()][];
        int i =0;
        for(Property property:properties) {
            Object[] o = property.toArray();
            objects[i++] = o;
        }

        return objects;
    }
    public static Object[][] list2Array2(List<Property> properties){
        Object[][] objects = new Object[properties.size()][];
        int i =0;
        for(Property property:properties) {
            Object[] o = property.toArray2();
            objects[i++] = o;
        }

        return objects;
    }
}
