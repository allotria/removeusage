/*
 * Copyright 2015 Manuel Stadelmann
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.allotria.intellij.removeusage.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.usages.Usage;
import com.intellij.usages.UsageView;
import org.jetbrains.annotations.Nullable;

/**
 * @author Manuel Stadelmann
 */
public class RemoveUsageAction extends AnAction {

    public RemoveUsageAction(@Nullable String text, @Nullable String description) {
        super(text, description, null);
    }

    protected void process(Usage[] usages, UsageView usageView) {
        for (Usage usage : usages) {
            usageView.removeUsage(usage);
        }
    }

    private static Usage[] getUsages(AnActionEvent context) {
        UsageView usageView = context.getData(UsageView.USAGE_VIEW_KEY);
        if (usageView == null) return Usage.EMPTY_ARRAY;
        Usage[] usages = context.getData(UsageView.USAGES_KEY);
        return usages == null ? Usage.EMPTY_ARRAY : usages;
    }

    @Override
    public void update(AnActionEvent e) {
        e.getPresentation().setEnabled(getUsages(e).length > 0);
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        process(getUsages(e), e.getData(UsageView.USAGE_VIEW_KEY));
    }

}