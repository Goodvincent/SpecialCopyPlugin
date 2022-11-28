package com.wen.github.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Queues;

import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * @author cuiwl
 * @date 2022-11-19 22:07
 */
public class TemplateUtil {

    public static final String KEY_FIRST_LINE = "firstLineTemplate";

    public static final String KEY_OTHER_LINE = "otherLineTemplate";

    public static final String KEY_LAST_LINE = "lastLineTemplate";

    public static final String PLACEHOLDER_FIRST_LINE = "<line_1>";

    public static final String PLACEHOLDER_OTHER_LINE = "<line_*>";

    public static final String PLACEHOLDER_LAST_LINE = "<line_n>";

    public static Map<String, String> getLineTemplateMap(String template) {
        Map<String, String> templateMap = Maps.newHashMap();
        String[] lines = getTemplateLines(template);
        for (String line : lines) {
            if (line.indexOf(PLACEHOLDER_FIRST_LINE) >= 0) {
                templateMap.put(KEY_FIRST_LINE, line);
            } else if (line.indexOf(PLACEHOLDER_OTHER_LINE) >= 0) {
                templateMap.put(KEY_OTHER_LINE, line);
            } else if (line.indexOf(PLACEHOLDER_LAST_LINE) >= 0) {
                templateMap.put(KEY_LAST_LINE, line);
            }
        }
        return templateMap;
    }

    public static Queue<String> getTemplateQueue(String template) {
        Queue<String> templateQueue = Queues.newArrayDeque();
        String[] lines = getTemplateLines(template);
        for (String line : lines) {
            if (line.indexOf("<line_") >= 0) {
                if(templateQueue.contains("PLACEHOLDER")) {
                    continue;
                }
                line = "PLACEHOLDER";
            }
            templateQueue.add(line);
        }
        return templateQueue;
    }

    public static String[] getTemplateLines(String template) {
        List<String> lineList = Lists.newArrayList();
        String[] lines = template.split("\n");
        for (String line : lines) {
            if (line == null || "".equals(line)
                    || line.startsWith(";") || line.startsWith("#")) {
                continue;
            }
            lineList.add(line);
        }
        if(lineList == null || lineList.isEmpty()) {
            throw new RuntimeException("模板文件无效");
        }
        return lineList.toArray(new String[lineList.size()]);
    }
}
