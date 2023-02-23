package com.yy.springcloud.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    /**
     * 判断是否IP地址
     */
    public static boolean isIpAddress(String addr) {
        if (addr.length() < 7 || addr.length() > 15 || "".equals(addr)) {
            return false;
        }
        String rexp = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
        Pattern pat = Pattern.compile(rexp);
        Matcher mat = pat.matcher(addr);
        boolean ipAddress = mat.find();
        return ipAddress;
    }

    public static boolean isMobile(String mobile) {
        Pattern pattern = Pattern.compile("^[1][0-9]{10}$");
        Matcher m = pattern.matcher(mobile);
        return mobile != null && m.find();
    }

    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip == null || ip.length() == 0 || "0:0:0:0:0:0:0:1".equalsIgnoreCase(ip)) {
            ip = "127.0.0.1";
        }
        return ip;
    }

    public static String getClientType(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        if (userAgent == null) {
            return null;
        }
        Pattern pattern = Pattern.compile(";\\s?(\\S*?\\s?\\S*?)\\s?(Build)?/");
        Matcher matcher = pattern.matcher(userAgent);
        String model = null;
        if (matcher.find()) {
            model = matcher.group(1).trim();
        }
        return model;
    }

    /**
     * 检查指定的字符串是否为空。
     * <ul>
     * <li>SysUtils.isEmpty(null) = true</li>
     * <li>SysUtils.isEmpty("") = true</li>
     * <li>SysUtils.isEmpty("   ") = true</li>
     * <li>SysUtils.isEmpty("abc") = false</li>
     * </ul>
     *
     * @param value 待检查的字符串
     * @return true/false
     */
    public static boolean isEmpty(String value) {
        int strLen;
        if (value == null || (strLen = value.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(value.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检查对象是否为数字型字符串,包含负数开头的。
     */
    public static boolean isNumeric(Object obj) {
        if (obj == null) {
            return false;
        }
        char[] chars = obj.toString().toCharArray();
        int length = chars.length;
        if (length < 1)
            return false;

        int i = 0;
        if (length > 1 && chars[0] == '-')
            i = 1;

        for (; i < length; i++) {
            if (!Character.isDigit(chars[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检查指定的字符串列表是否不为空。
     */
    public static boolean areNotEmpty(String... values) {
        boolean result = true;
        if (values == null || values.length == 0) {
            result = false;
        } else {
            for (String value : values) {
                result &= !isEmpty(value);
            }
        }
        return result;
    }

    /**
     * 把通用字符编码的字符串转化为汉字编码。
     */
    public static String unicodeToChinese(String unicode) {
        StringBuilder out = new StringBuilder();
        if (!isEmpty(unicode)) {
            for (int i = 0; i < unicode.length(); i++) {
                out.append(unicode.charAt(i));
            }
        }
        return out.toString();
    }

    /**
     * 过滤不可见字符
     */
    public static String stripNonValidXMLCharacters(String input) {
        if (input == null || ("".equals(input)))
            return "";
        StringBuilder out = new StringBuilder();
        char current;
        for (int i = 0; i < input.length(); i++) {
            current = input.charAt(i);
            if ((current == 0x9) || (current == 0xA) || (current == 0xD)
                    || ((current >= 0x20) && (current <= 0xD7FF))
                    || ((current >= 0xE000) && (current <= 0xFFFD))
                    || ((current >= 0x10000) && (current <= 0x10FFFF)))
                out.append(current);
        }
        return out.toString();
    }

    /**
     * 将数组转换成string   1,2,3
     */
    public static <T> String copyValueOf(T array[]) {
        if (array == null || array.length == 0) {
            return "";
        }
        StringBuilder strBuilder = new StringBuilder();
        for (Object object : array) {
            strBuilder.append(object);
            strBuilder.append(",");
        }
        return strBuilder.deleteCharAt(strBuilder.length() - 1).toString();
    }

    /**
     * 获取指定位数的随机号
     *
     * @param length
     * @return SerialNumber
     */
    public static String getSerialNumber(int length) {
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
        for (int i = 1; i <= length; i++) {
            stringBuffer.append(random.nextInt(9) + 1);
        }
        return stringBuffer.toString();
    }

    /**
     * @param s
     * @return 获得图片
     */
    public static List<String> getImg(String s) {
        String regex;
        List<String> list = Lists.newArrayList();
        regex = "src=\"(.*?)\"";
        Pattern pa = Pattern.compile(regex, Pattern.DOTALL);
        Matcher ma = pa.matcher(s);
        while (ma.find()) {
            list.add(ma.group());
        }
        return list;
    }

    /**
     * @param s
     * @return 获得第一张图片
     */
    public static String getFirstImg(String s) {
        String regex;
        regex = "src=\"(.*?)\"";
        Pattern pa = Pattern.compile(regex, Pattern.DOTALL);
        Matcher ma = pa.matcher(s);
        while (ma.find()) {
            int begin = ma.group().indexOf("\"") + 1;
            int end = ma.group().lastIndexOf("\"");

            return ma.group().substring(begin, end);
        }
        return "";
    }

    /**
     * 去掉字符串中的html源码。<br>
     *
     * @param con    内容
     * @param length 截取长度
     * @param end    原始字符串超过截取长度时，后面增加字符
     * @return 去掉后的内容
     */
    public static String subStringHTML(String con, int length, String end) {
        String content = "";
        if (con != null) {
            content = con.replaceAll("</?[^>]+>", "");//剔出了<html>的标签
            content = content.replace("&nbsp;", "");
            content = content.replace(".", "");
            content = content.replace("\"", "‘");
            content = content.replace("'", "‘");
            content = content.replaceAll("\\s*|\t|\r|\n", "");//去除字符串中的空格
            if (content.length() > length) {
                content = content.substring(0, length) + end;
            }
        }
        return content.trim();
    }

    /**
     * 将对象转为xml字符串
     */
    public static String getXmlString(Map<String, String> map) {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("<xml>\n");
        for (Entry<String, String> entry : map.entrySet()) {
            if (entry.getValue() == null || entry.getValue().isEmpty()) {
                continue;
            }
            strBuilder.append("<").append(entry.getKey()).append(">");
            strBuilder.append("<![CDATA[");
            strBuilder.append(entry.getValue());
            strBuilder.append("]]>");
            strBuilder.append("</").append(entry.getKey()).append(">\n");
        }
        strBuilder.append("</xml>");
        return strBuilder.toString();
    }

    /**
     * 将xml数据流转为字符串
     */
    public static String inputStreamToString(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i;
        while ((i = is.read()) != -1) {
            baos.write(i);
        }
        return baos.toString();
    }

    /**
     * 根据xml字符串获取map对象
     */
    public static Map<String, String> getMapFromXML(String xmlString) throws SAXException, IOException, ParserConfigurationException {

        //这里用Dom的方式解析回包的最主要目的是防止API新增回包字段
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputStream inputStream = new ByteArrayInputStream(xmlString.getBytes("UTF-8"));
        Document document = builder.parse(inputStream);

        //获取到document里面的全部结点
        NodeList allNodes = document.getFirstChild().getChildNodes();
        Node node;
        Map<String, String> map = Maps.newHashMap();
        int i = 0;
        while (i < allNodes.getLength()) {
            node = allNodes.item(i);
            if (node instanceof Element) {
                map.put(node.getNodeName(), node.getTextContent());
            }
            i++;
        }
        return map;
    }

    public static String getSignContent(Map<String, String> sortedParams) {
        StringBuffer content = new StringBuffer();
        List<String> keys = new ArrayList<String>(sortedParams.keySet());
        Collections.sort(keys);
        int index = 0;
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = sortedParams.get(key);
            if (StringUtil.areNotEmpty(key, value)) {
                content.append((index == 0 ? "" : "&") + key + "=" + value);
                index++;
            }
        }
        return content.toString();
    }

    /**
     * 获取指定位数的随机数(纯数字)
     *
     * @param length 随机数的位数
     * @return String
     */
    public static String getRandomNum(int length) {
        if (length <= 0) {
            length = 1;
        }
        StringBuilder res = new StringBuilder();
        Random random = new Random();
        int i = 0;
        while (i < length) {
            res.append(random.nextInt(10));
            i++;
        }
        return res.toString();
    }

    /**
     * 获取9位随机数
     *
     * @param level
     * @return
     */
    public static Integer getSeriaNo(String level) {
        String serialNo = level + getRandomNum(8);
        return Integer.valueOf(serialNo);
    }

    /**
     * urlEncode
     *
     * @param url
     * @return
     */
    public static String getEncodeRedirectUrl(String url) {
        try {
            return URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {

        }
        return "";
    }

    /**
     * 获取文件后缀名
     */
    public static String getExtension(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    /**
     *  获取不带扩展名的文件名
     * @param filename
     * @return
     */
    public static String getFileNameNoEx(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }



    /**
     * 正则匹配
     *
     * @param text
     * @param pattern
     * @return
     */
    public static String getTextByPattern(String text, String pattern) {
        Pattern r = Pattern.compile(pattern);

        String result = "";
        Matcher m = r.matcher(text);
        if (m.find()) {
            result = m.group(1);
        }

        result = result.trim();

        if (StringUtils.isEmpty(result)) {
            result = "";
        }

        return result;
    }

    public static int stringCount(String text, String sub) {
        int count = 0, start = 0;
        while ((start = text.indexOf(sub, start)) >= 0) {
            start += sub.length();
            count++;
        }
        return count;
    }

    /**
     * 保留一位小数
     * @param s
     * @return
     */
    public static String formatDouble(double s) {
        DecimalFormat fmt = new DecimalFormat("##0.0");
        return fmt.format(s);
    }

    /**
     * 保留4位小数
     * @param s
     * @return
     */
    public static String formatDouble2(double s) {
        DecimalFormat fmt = new DecimalFormat("##0.0000");
        return fmt.format(s);
    }

    public static void main(String[] args) {
//        System.out.printf(getExtension("Bubble.22.3_Videvo.mp4"));
//        System.out.printf(getLocalIP());
        System.out.println(formatDouble2(0.24));
    }

}
