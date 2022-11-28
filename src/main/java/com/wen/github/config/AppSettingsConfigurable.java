package com.wen.github.config;

import com.intellij.openapi.options.SearchableConfigurable;
import com.wen.github.component.AppSettingsComponent;
import com.wen.github.settings.AppSettingsState;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Provides controller functionality for application settings.
 * @author cuiwl
 */
public class AppSettingsConfigurable implements SearchableConfigurable {

  private AppSettingsComponent mySettingsComponent;

  public AppSettingsConfigurable() {

  }


  // A default constructor with no arguments is required because this implementation
  // is registered as an applicationConfigurable EP

  @Nls(capitalization = Nls.Capitalization.Title)
  @Override
  public String getDisplayName() {
    return "Special Copy";
  }

  @Override
  public JComponent getPreferredFocusedComponent() {
    return mySettingsComponent.getPreferredFocusedComponent();
  }

  @Nullable
  @Override
  public JComponent createComponent() {
    mySettingsComponent = new AppSettingsComponent();
    return mySettingsComponent.getPanel();
  }

  @Override
  public boolean isModified() {
    AppSettingsState settings = AppSettingsState.getInstance();
    boolean modified = !mySettingsComponent.getTemplatePathText().equals(settings.templatePath);
//    modified |= mySettingsComponent.getIdeaUserStatus() != settings.ideaStatus;
    return modified;
  }

  @Override
  public void apply() {
    AppSettingsState settings = AppSettingsState.getInstance();
    settings.templatePath = mySettingsComponent.getTemplatePathText();
  }

  @Override
  public void reset() {
    AppSettingsState settings = AppSettingsState.getInstance();
    mySettingsComponent.setTemplatePathText(settings.templatePath);
  }

  @Override
  public void disposeUIResources() {
    mySettingsComponent = null;
  }

  @Override
  public @NotNull
  @NonNls String getId() {
    return "com.wen.github.config.AppSettingsConfigurable";
  }

  @Override
  public @Nullable Runnable enableSearch(String option) {
    return SearchableConfigurable.super.enableSearch(option);
  }

  @Override
  public @NotNull Class<?> getOriginalClass() {
    return SearchableConfigurable.super.getOriginalClass();
  }
}