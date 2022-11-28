package com.wen.github.settings;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;

/**
 * @author cuiwl
 * @date 2022-11-18 17:00
 */
@State(
        name = "com.wen.github.settings.AppSettingsState",
        storages = @Storage("SpecialCopy.xml")
)
public class AppSettingsState implements PersistentStateComponent<AppSettingsState> {

//    public String userId = "John Q. Public";
//    public boolean ideaStatus = false;

    public String templatePath = System.getProperty("user.dir");


    public static AppSettingsState getInstance() {
        return ApplicationManager.getApplication().getService(AppSettingsState.class);
    }

    @Override
    public AppSettingsState getState() {
        return this;
    }

    @Override
    public void loadState(AppSettingsState state) {
//        XmlSerializerUtil.copyBean(state, this);
    }
}
