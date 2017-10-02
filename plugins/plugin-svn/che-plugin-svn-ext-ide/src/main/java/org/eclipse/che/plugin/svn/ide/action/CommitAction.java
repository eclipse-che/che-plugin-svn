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
package org.eclipse.che.plugin.svn.ide.action;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.eclipse.che.ide.api.action.ActionEvent;
import org.eclipse.che.ide.api.app.AppContext;
import org.eclipse.che.plugin.svn.ide.SubversionExtensionLocalizationConstants;
import org.eclipse.che.plugin.svn.ide.SubversionExtensionResources;
import org.eclipse.che.plugin.svn.ide.commit.CommitPresenter;

/** Extension of {@link SubversionAction} for implementing the "svn commit" command. */
@Singleton
public class CommitAction extends SubversionAction {

  private final CommitPresenter presenter;

  @Inject
  public CommitAction(
      AppContext appContext,
      CommitPresenter presenter,
      SubversionExtensionLocalizationConstants constants,
      SubversionExtensionResources resources) {
    super(
        constants.commitTitle(),
        constants.commitDescription(),
        resources.commit(),
        appContext,
        constants,
        resources);
    this.presenter = presenter;
  }

  @Override
  public void actionPerformed(final ActionEvent e) {
    presenter.show();
  }
}
