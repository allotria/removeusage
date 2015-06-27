package ch.allotria.intellij.removeusage;

import ch.allotria.intellij.removeusage.action.RemoveUsageAction;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.components.ApplicationComponent;
import org.jetbrains.annotations.NotNull;

public class RemoveUsagePluginRegistration implements ApplicationComponent {

    @Override
    public void initComponent() {
        ActionManager am = ActionManager.getInstance();
        AnAction deleteLineAction = am.getAction("DeleteLine");

        RemoveUsageAction removeUsageAction = new RemoveUsageAction("Remove", "Remove this usage(s) from processing");
        removeUsageAction.copyShortcutFrom(deleteLineAction);

        am.registerAction("UsageView.Remove", removeUsageAction);
        DefaultActionGroup usageViewPopup = (DefaultActionGroup) am.getAction("UsageView.Popup");


        Constraints constraints = new Constraints(Anchor.AFTER, "UsageView.Exclude");
        usageViewPopup.add(removeUsageAction, constraints);
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
