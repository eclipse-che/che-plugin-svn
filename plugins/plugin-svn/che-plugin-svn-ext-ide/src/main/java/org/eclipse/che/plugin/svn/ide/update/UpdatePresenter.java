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

import static com.google.common.base.Preconditions.checkState;
import static org.eclipse.che.ide.api.notification.StatusNotification.DisplayMode.FLOAT_MODE;
import static org.eclipse.che.ide.api.notification.StatusNotification.Status.FAIL;
import static org.eclipse.che.ide.api.notification.StatusNotification.Status.PROGRESS;
import static org.eclipse.che.ide.api.notification.StatusNotification.Status.SUCCESS;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.eclipse.che.api.promises.client.Operation;
import org.eclipse.che.api.promises.client.OperationException;
import org.eclipse.che.api.promises.client.Promise;
import org.eclipse.che.api.promises.client.PromiseError;
import org.eclipse.che.ide.api.app.AppContext;
import org.eclipse.che.ide.api.notification.NotificationManager;
import org.eclipse.che.ide.api.notification.StatusNotification;
import org.eclipse.che.ide.api.resources.Project;
import org.eclipse.che.ide.api.resources.Resource;
import org.eclipse.che.ide.api.user.AskCredentialsDialog;
import org.eclipse.che.ide.api.user.Credentials;
import org.eclipse.che.ide.processes.panel.ProcessesPanelPresenter;
import org.eclipse.che.ide.util.Arrays;
import org.eclipse.che.plugin.svn.ide.SubversionClientService;
import org.eclipse.che.plugin.svn.ide.SubversionExtensionLocalizationConstants;
import org.eclipse.che.plugin.svn.ide.common.StatusColors;
import org.eclipse.che.plugin.svn.ide.common.SubversionActionPresenter;
import org.eclipse.che.plugin.svn.ide.common.SubversionOutputConsoleFactory;
import org.eclipse.che.plugin.svn.shared.CLIOutputWithRevisionResponse;

/** Handler for the {@link import UpdateAction} action. */
@Singleton
public class UpdatePresenter extends SubversionActionPresenter {

  private final NotificationManager notificationManager;
  private final SubversionClientService service;
  private final SubversionExtensionLocalizationConstants constants;

  @Inject
  public UpdatePresenter(
      AppContext appContext,
      SubversionOutputConsoleFactory consoleFactory,
      SubversionClientService service,
      AskCredentialsDialog subversionCredentialsDialog,
      ProcessesPanelPresenter processesPanelPresenter,
      SubversionExtensionLocalizationConstants constants,
      NotificationManager notificationManager,
      StatusColors statusColors) {
    super(
        appContext,
        consoleFactory,
        processesPanelPresenter,
        statusColors,
        constants,
        notificationManager,
        subversionCredentialsDialog);

    this.constants = constants;
    this.notificationManager = notificationManager;
    this.service = service;
  }

  public void showUpdate() {
    doUpdate("HEAD", "infinity", false, null);
  }

  protected void doUpdate(
      final String revision,
      final String depth,
      final boolean ignoreExternals,
      final UpdateToRevisionView view) {

    final Project project = appContext.getRootProject();

    checkState(project != null);

    final Resource[] resources = appContext.getResources();

    checkState(!Arrays.isNullOrEmpty(resources));

    final StatusNotification notification =
        new StatusNotification(constants.updateToRevisionStarted(revision), PROGRESS, FLOAT_MODE);
    notificationManager.notify(notification);

    performOperationWithCredentialsRequestIfNeeded(
            new RemoteSubversionOperation<CLIOutputWithRevisionResponse>() {
              @Override
              public Promise<CLIOutputWithRevisionResponse> perform(Credentials credentials) {

                notification.setStatus(PROGRESS);
                notification.setTitle(constants.updateToRevisionStarted(revision));

                return service.update(
                    project.getLocation(),
                    toRelative(project, resources),
                    revision,
                    depth,
                    ignoreExternals,
                    "postpone",
                    credentials);
              }
            },
            notification)
        .then(
            new Operation<CLIOutputWithRevisionResponse>() {
              @Override
              public void apply(CLIOutputWithRevisionResponse response) throws OperationException {
                printResponse(
                    response.getCommand(),
                    response.getOutput(),
                    response.getErrOutput(),
                    constants.commandUpdate());

                notification.setTitle(
                    constants.updateSuccessful(Long.toString(response.getRevision())));
                notification.setStatus(SUCCESS);

                if (view != null) {
                  view.close();
                }
              }
            })
        .catchError(
            new Operation<PromiseError>() {
              @Override
              public void apply(PromiseError error) throws OperationException {
                notification.setTitle(constants.updateFailed());
                notification.setStatus(FAIL);
              }
            });
  }
}
