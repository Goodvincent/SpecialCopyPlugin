package com.wen.github.action;

import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.wen.github.settings.AppSettingsState;
import com.wen.github.util.FileUitl;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileFilter;

/**
 * @author cuiwl
 * @date 2022-11-18 10:46
 */
public class SpecialCopyActionGroup extends ActionGroup {

    @Override
    public AnAction[] getChildren(@Nullable AnActionEvent event) {
        String templatePath = AppSettingsState.getInstance().getState().templatePath;
        File[] templateFiles = FileUitl.getCopyTemplates(templatePath);
        if(templateFiles == null || templateFiles.length == 0) {
            return new AnAction[0];
        }
        AnAction[] actions = new AnAction[templateFiles.length];
        for (int i = 0; i < actions.length; i++) {
            String filePath = templateFiles[i].getAbsolutePath();
            String fileName = templateFiles[i].getName();
            String actionName = fileName.substring(0, fileName.lastIndexOf(FileUitl.TEMPLATE_FILETYPE));
            actions[i] = new SpecialCopyAction(filePath, actionName, "", null);
        }
        return actions;
    }
}
