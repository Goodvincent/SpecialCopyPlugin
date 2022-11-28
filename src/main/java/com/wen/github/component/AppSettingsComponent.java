package com.wen.github.component;

import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.ui.TextBrowseFolderListener;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.util.ui.FormBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Supports creating and managing a {@link JPanel} for the Settings Dialog.
 */
public class AppSettingsComponent {

  private final JPanel myMainPanel;
//  private final JBTextField myUserNameText = new JBTextField();
  private final TextFieldWithBrowseButton fieldWithBrowseButton =
          new TextFieldWithBrowseButton();

  public AppSettingsComponent() {
    myMainPanel = FormBuilder.createFormBuilder()
            .addComponent(fieldWithBrowseButton)
            .addComponentFillVertically(new JPanel(), 0)
            .getPanel();
  }

  public JPanel getPanel() {
    String projectFolder = "";
    myMainPanel.setPreferredSize(new Dimension(0, 0));

    /**
     * project folder
     */
    myMainPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    JLabel templateLabel = new JLabel("Template location:");
    myMainPanel.add(templateLabel);
    fieldWithBrowseButton.setTextFieldPreferredWidth(45);
    fieldWithBrowseButton.setText(projectFolder);
    fieldWithBrowseButton.addBrowseFolderListener(new TextBrowseFolderListener(
            FileChooserDescriptorFactory.createSingleFileOrFolderDescriptor()) {
      @Override
      public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        fieldWithBrowseButton.setText(fieldWithBrowseButton.getText().replaceAll("\\\\", "/"));
      }
    });
    myMainPanel.add(fieldWithBrowseButton);
    return myMainPanel;
  }

  public JComponent getPreferredFocusedComponent() {
    return fieldWithBrowseButton;
  }

  public String getTemplatePathText() {
    return fieldWithBrowseButton.getText();
  }

  public void setTemplatePathText(String newText) {
    fieldWithBrowseButton.setText(newText);
  }

//  public boolean getIdeaUserStatus() {
//    return myIdeaUserStatus.isSelected();
//  }
//
//  public void setIdeaUserStatus(boolean newStatus) {
//    myIdeaUserStatus.setSelected(newStatus);
//  }

}