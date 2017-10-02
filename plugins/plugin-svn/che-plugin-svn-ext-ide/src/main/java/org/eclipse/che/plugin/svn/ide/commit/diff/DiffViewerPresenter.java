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
package org.eclipse.che.plugin.svn.ide.commit.diff;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.eclipse.che.ide.api.app.AppContext;
import org.eclipse.che.ide.api.notification.NotificationManager;
import org.eclipse.che.ide.api.user.AskCredentialsDialog;
import org.eclipse.che.ide.processes.panel.ProcessesPanelPresenter;
import org.eclipse.che.plugin.svn.ide.SubversionExtensionLocalizationConstants;
import org.eclipse.che.plugin.svn.ide.common.StatusColors;
import org.eclipse.che.plugin.svn.ide.common.SubversionActionPresenter;
import org.eclipse.che.plugin.svn.ide.common.SubversionOutputConsoleFactory;

/**
 * Presenter for the {@link DiffViewerView}.
 *
 * @author Vladyslav Zhukovskyi
 */
@Singleton
public class DiffViewerPresenter extends SubversionActionPresenter
    implements DiffViewerView.ActionDelegate {

  private DiffViewerView view;

  @Inject
  protected DiffViewerPresenter(
      AppContext appContext,
      SubversionOutputConsoleFactory consoleFactory,
      AskCredentialsDialog credentialsDialog,
      SubversionExtensionLocalizationConstants constants,
      NotificationManager notificationManager,
      ProcessesPanelPresenter processesPanelPresenter,
      DiffViewerView view,
      StatusColors statusColors) {
    super(
        appContext,
        consoleFactory,
        processesPanelPresenter,
        statusColors,
        constants,
        notificationManager,
        credentialsDialog);
    this.view = view;
    this.view.setDelegate(this);
  }

  public void showDiff(String content) {
    view.showDiff(content);
    view.onShow();
  }

  /** {@inheritDoc} */
  @Override
  public void onCloseClicked() {
    view.onClose();
  }
}
