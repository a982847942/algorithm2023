package nuaa.zk.day;


import java.util.Locale;

/**
 * @author brain
 * @version 1.0
 * @date 2023/4/1 9:16
 */
public class MaskingPersonalInformationLC_831 {
    public String maskPII(String s) {
        StringBuilder builder = new StringBuilder();
        int index = s.indexOf('@');
        if (index > -1) {
            //邮箱
            s = s.toLowerCase(Locale.ENGLISH);
            builder.append(s.charAt(0));
            for (int i = 0; i < 5; i++) {
                builder.append('*');
            }
            for (int i = index - 1; i < s.length(); i++) {
                builder.append(s.charAt(i));
            }
            return builder.toString();
        } else {
            //电话号码
            s = removeChar(s);
            return resolve(s);
        }
    }

    private String resolve(String s) {
        StringBuilder builder = new StringBuilder();
        int len = s.length();
        if (len == 13) {
            builder.append('+');
            for (int i = 0; i < 3; i++) {
                appendStar(builder, 3);
            }
            appendX(builder,s);
        }else if (len == 12){
            builder.append('+');
            appendStar(builder,2);
            for (int i = 0; i < 2; i++) {
                appendStar(builder, 3);
            }
            appendX(builder,s);
        }else if (len == 11){
            builder.append('+');
            appendStar(builder,1);
            for (int i = 0; i < 2; i++) {
                appendStar(builder, 3);
            }
            appendX(builder,s);
        }else {
            for (int i = 0; i < 2; i++) {
                appendStar(builder, 3);
            }
            appendX(builder,s);
        }
        return builder.toString();
    }

    private void appendX(StringBuilder builder,String s) {
        int start = s.length() - 4;
        for (int i = 0; i < 4; i++) {
            builder.append(s.charAt(start + i));
        }
    }

    private void appendStar(StringBuilder builder, int len) {
        for (int i = 0; i < len; i++) {
            builder.append('*');
        }
        builder.append('-');
    }

    private String removeChar(String s) {
        StringBuilder builder = new StringBuilder();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char cur = s.charAt(i);
            if (cur == '(' || cur == ')' || cur == '+' || cur == '-' || cur == ' ') {
                continue;
            }
            builder.append(cur);
        }
        return builder.toString();
    }

    String[] country = {"", "+*-", "+**-", "+***-"};

    public String maskPII2(String s) {
        int at = s.indexOf("@");
        if (at > 0) {
            s = s.toLowerCase();
            return (s.charAt(0) + "*****" + s.substring(at - 1)).toLowerCase();
        }
        s = s.replaceAll("[^0-9]", "");
        return country[s.length() - 10] + "***-***-" + s.substring(s.length() - 4);
    }
}
