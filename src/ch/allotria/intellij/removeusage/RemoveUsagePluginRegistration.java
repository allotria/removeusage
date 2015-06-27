package ch.allotria.intellij.removeusage;

import ch.allotria.intellij.removeusage.action.RemoveUsageAction;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.components.ApplicationComponent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RemoveUsagePluginRegistration implements ApplicationComponent {

    @Override
    public void initComponent() {
        ActionManager am = ActionManager.getInstance();

        RemoveUsageAction removeUsageAction = new RemoveUsageAction("Remove", "Remove this usage(s) from processing");
        ShortcutSet shortcutSet = buildShortcutSet(am, "EditorDeleteLine", "SafeDelete");
        removeUsageAction.registerCustomShortcutSet(shortcutSet, null);


        am.registerAction("UsageView.Remove", removeUsageAction);
        DefaultActionGroup usageViewPopup = (DefaultActionGroup) am.getAction("UsageView.Popup");


        Constraints constraints = new Constraints(Anchor.AFTER, "UsageView.Exclude");
        usageViewPopup.add(removeUsageAction, constraints);
    }

    private ShortcutSet buildShortcutSet(ActionManager am, String... actionIds) {
        List<Shortcut> shortcuts = new ArrayList<>(actionIds.length);

        for (String actionId : actionIds) {
            KeyboardShortcut shortcut = am.getKeyboardShortcut(actionId);
            if (shortcut != null) {
                shortcuts.add(shortcut);
            }
        }

        return new CustomShortcutSet(shortcuts.toArray(new Shortcut[shortcuts.size()]));
    }

    @Override
    public void disposeComponent() {

    }

    @NotNull
    @Override
    public String getComponentName() {
        return "RemoveUsagePlugin";
    }
}
