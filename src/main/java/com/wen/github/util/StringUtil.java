package com.wen.github.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Queues;

import java.util.*;

/**
 * @author cuiwl
 * @date 2022-11-19 21:56
 */
public class StringUtil {

    private static final String COPY_TEMPLATE = ";PL/SQL Developer SpecialCopy definition\n" +
            ";<LINE_1> for first line\n" +
            ";<LINE_*> for all other lines\n" +
            ";<LINE_N> for last line\n" +
            ";#define \" = \\\"\n" +
            "#define \\ = \\\\\n" +
            "#define char(9) = \\t\n" +
            "StringBuffer sql = new StringBuffer();\n" +
            "sql.append(\"<line_1>\");\n" +
            "sql.append(\"<line_*>\" );\n" +
            "sql.append(\"<line_n>\");";

    public static String replaceByTemplate(String template, String source) {
        List<String> targetLines = Lists.newArrayList();
        Map<String, String> lineTemplateMap = TemplateUtil.getLineTemplateMap(template);
        Queue<String> templateQueue = TemplateUtil.getTemplateQueue(template);
        List<String> sourceLines = getSourceLines(source);
        if (sourceLines == null || sourceLines.isEmpty()) {
            return null;
        }
        int sourceLineSize = sourceLines.size();
        while (!templateQueue.isEmpty()) {
            String templateLine = templateQueue.poll();
            if(templateLine != null && !"PLACEHOLDER".equals(templateLine)) {
                targetLines.add(templateLine);
            } else if("PLACEHOLDER".equals(templateLine)) {
                if (sourceLineSize == 1) {
                    String firstLineTemplate = lineTemplateMap.get(TemplateUtil.KEY_FIRST_LINE);
                    String firstLineString = firstLineTemplate.replace(TemplateUtil.PLACEHOLDER_FIRST_LINE, sourceLines.get(0));
                    targetLines.add(firstLineString);
                } else if (sourceLineSize == 2) {
                    String firstLineTemplate = lineTemplateMap.get(TemplateUtil.KEY_FIRST_LINE);
                    String firstLineString = firstLineTemplate.replace(TemplateUtil.PLACEHOLDER_FIRST_LINE, sourceLines.get(0));
                    targetLines.add(firstLineString);
                    String lastLineTemplate = lineTemplateMap.get(TemplateUtil.KEY_LAST_LINE);
                    String lastLineString = lastLineTemplate.replace(TemplateUtil.PLACEHOLDER_LAST_LINE, sourceLines.get(1));
                    targetLines.add(lastLineString);
                } else {
                    for (int i = 0; i < sourceLines.size(); i++) {
                        String lineString = "";
                        if(i == 0) {
                            String firstLineTemplate = lineTemplateMap.get(TemplateUtil.KEY_FIRST_LINE);
                            lineString = firstLineTemplate.replace(TemplateUtil.PLACEHOLDER_FIRST_LINE, sourceLines.get(0));
                        } else if(i == sourceLines.size() - 1) {
                            String lastLineTemplate = lineTemplateMap.get(TemplateUtil.KEY_LAST_LINE);
                            lineString = lastLineTemplate.replace(TemplateUtil.PLACEHOLDER_LAST_LINE, sourceLines.get(sourceLines.size() - 1));
                        } else {
                            String otherLineTemplate = lineTemplateMap.get(TemplateUtil.KEY_OTHER_LINE);
                            lineString = otherLineTemplate.replace(TemplateUtil.PLACEHOLDER_OTHER_LINE, sourceLines.get(i));
                        }
                        targetLines.add(lineString);
                    }
                }
            }
        }
        return String.join("\n", targetLines);
    }

    public static List<String> getSourceLines(String source) {
        List<String> sourceQueue = Lists.newArrayList();
        String[] lines = splitSourceLines(source);
        for (String line : lines) {
            if (line.indexOf("<line_") >= 0) {
                line = "PLACEHOLDER";
            }
            sourceQueue.add(line);
        }
        return sourceQueue;
    }

    private static String[] splitSourceLines(String source) {
        List<String> sourceLines = Lists.newArrayList();
        if(source == null || "".equals(source.trim())) {
            return null;
        }
        if(source.indexOf("\n") >= 0) {
            String[] splitLines = source.split("\n");
            sourceLines.addAll(Arrays.asList(splitLines));
        } else {
            sourceLines.add(source);
        }
        return sourceLines.toArray(new String[0]);
    }

    public static void main(String[] args) {
        String source = "select * from sm_user where dr = 0\n" +
                " and user_code like '%cuiwl%'\n " +
                " and user_name='崔文龙'\n" +
                " and name='jj'";
        String targetString = replaceByTemplate(COPY_TEMPLATE, source);
        ClipboardUtil.setClipboardText(targetString);
        System.out.println(targetString);
    }
}
