/*
 * Copyright (c) 2012-2017 Red Hat, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Red Hat, Inc. - initial API and implementation
 */
package org.eclipse.che.plugin.svn.ide.update;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.eclipse.che.ide.api.app.AppContext;
import org.eclipse.che.ide.api.notification.NotificationManager;
import org.eclipse.che.ide.api.user.AskCredentialsDialog;
import org.eclipse.che.ide.processes.panel.ProcessesPanelPresenter;
import org.eclipse.che.plugin.svn.ide.SubversionClientService;
import org.eclipse.che.plugin.svn.ide.SubversionExtensionLocalizationConstants;
import org.eclipse.che.plugin.svn.ide.common.StatusColors;
import org.eclipse.che.plugin.svn.ide.common.SubversionOutputConsoleFactory;

/** Handler for the {@link org.eclipse.che.plugin.svn.ide.action.UpdateToRevisionAction} action. */
@Singleton
public class UpdateToRevisionPresenter extends UpdatePresenter
    implements UpdateToRevisionView.ActionDelegate {

  private final UpdateToRevisionView view;

  @Inject
  public UpdateToRevisionPresenter(
      AppContext appContext,
      NotificationManager notificationManager,
      SubversionOutputConsoleFactory consoleFactory,
      SubversionClientService service,
      AskCredentialsDialog credentialsDialog,
      SubversionExtensionLocalizationConstants constants,
      ProcessesPanelPresenter processesPanelPresenter,
      UpdateToRevisionView view,
      StatusColors statusColors) {
    super(
        appContext,
        consoleFactory,
        service,
        credentialsDialog,
        processesPanelPresenter,
        constants,
        notificationManager,
        statusColors);

    this.view = view;

    this.view.setDelegate(this);
  }

  /** Displays the dialog and resets its state. */
  public void showWindow() {
    view.setDepth("infinity");
    view.setRevision("");
    view.setIgnoreExternals(false);
    view.setIsCustomRevision(false);
    view.setIsHeadRevision(true);

    view.setEnableUpdateButton(true);
    view.setEnableCustomRevision(false);

    view.showWindow();
  }

  @Override
  public void onCancelClicked() {
    view.close();
  }

  @Override
  public void onUpdateClicked() {
    doUpdate(view.getRevision(), view.getDepth(), view.ignoreExternals(), view);
  }

  @Override
  public void onRevisionTypeChanged() {
    handleFormChange();
  }

  @Override
  public void onRevisionChanged() {
    handleFormChange();
  }

  /** Helper method to enable/disable form fields based on form state changes. */
  private void handleFormChange() {
    view.setEnableCustomRevision(view.isCustomRevision());

    if (view.isCustomRevision() && view.getRevision().isEmpty()) {
      view.setEnableUpdateButton(false);
    } else {
      view.setEnableUpdateButton(true);
    }
  }
}
