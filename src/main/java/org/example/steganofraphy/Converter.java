package org.example.steganofraphy;

public class Converter {

    public String convertTextToBinary(String text) {
        String binaryText = text.chars()
                .collect(StringBuilder::new,
                        (sb, c) -> sb.append(String.format("%16s", Integer.toBinaryString(c)).replace(' ', '0')).append(' '),
                        StringBuilder::append).toString();
        return binaryText;
    }
    public String convertBinaryToText(String text) {
        text = text.replaceAll(" ", "");
        StringBuilder decodedText = new StringBuilder();
        StringBuilder srb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            srb.append(text.charAt(i));
            if (srb.length() == 16) {
                int num = Integer.parseInt(srb.toString(), 2);
                char utf16Char = (char) num;
                decodedText.append(utf16Char);
                srb.setLength(0);
            }
        }
        return decodedText.toString();
    }
}
