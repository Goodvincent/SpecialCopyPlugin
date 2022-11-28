package com.wen.github.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.pom.Navigatable;
import com.wen.github.util.ClipboardUtil;
import com.wen.github.util.FileUitl;
import com.wen.github.util.StringUtil;

import javax.swing.*;

/**
 * 特殊复制按钮
 */
public final class SpecialCopyAction extends AnAction {

    private String templateFilePath;

    public SpecialCopyAction() {
    }

    public SpecialCopyAction(String templateFilePath, String text, String desc, Icon icon) {
        this(text, desc, icon);
        this.templateFilePath = templateFilePath;
    }

    public SpecialCopyAction(String text, String desc, Icon icon) {
        super(text, desc, icon);
    }

    @Override
    public void update(AnActionEvent event) {
        // Set the availability based on whether a project is open
        Editor requiredData = event.getRequiredData(CommonDataKeys.EDITOR);
        if(requiredData == null) {
            return;
        }
        String selectedText = requiredData.getSelectionModel().getSelectedText();
        Project currentProject = event.getProject();
        event.getPresentation().setEnabledAndVisible(currentProject != null && selectedText != null);
    }

    @Override
    public void actionPerformed(AnActionEvent event) {
        Editor requiredData = event.getRequiredData(CommonDataKeys.EDITOR);
        String selectedText = requiredData.getSelectionModel().getSelectedText();
        if(selectedText == null) {
            return;
        }
        String template = FileUitl.getFileContent(templateFilePath);
        String targetString = StringUtil.replaceByTemplate(template, selectedText);
        ClipboardUtil.setClipboardText(targetString);
//        System.out.println(selectedText);
        // Using the event, create and show a dialog
//        Project currentProject = event.getProject();
//        StringBuilder message =
//                new StringBuilder(event.getPresentation().getText() + " Selected!");
//        // If an element is selected in the editor, add info about it.
//        Navigatable selectedElement = event.getData(CommonDataKeys.NAVIGATABLE);
//        if (selectedElement != null) {
//            message.append("\nSelected Element: ").append(selectedElement);
//        }
//        String title = event.getPresentation().getDescription();
//        Messages.showMessageDialog(
//                currentProject,
//                message.toString(),
//                title,
//                Messages.getInformationIcon());
    }

    public String getTemplateFilePath() {
        return templateFilePath;
    }

    public void setTemplateFilePath(String templateFilePath) {
        this.templateFilePath = templateFilePath;
    }
}
