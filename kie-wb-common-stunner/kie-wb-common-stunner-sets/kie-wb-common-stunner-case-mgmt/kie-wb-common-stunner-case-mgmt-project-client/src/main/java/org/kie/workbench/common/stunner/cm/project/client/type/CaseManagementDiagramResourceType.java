/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.workbench.common.stunner.cm.project.client.type;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
import org.guvnor.common.services.project.categories.Process;
import org.jboss.errai.ui.client.local.spi.TranslationService;
import org.kie.workbench.common.stunner.cm.client.resources.CaseManagementImageResources;
import org.kie.workbench.common.stunner.cm.project.client.resources.i18n.CaseManagementProjectClientConstants;
import org.kie.workbench.common.stunner.cm.resource.CaseManagementDefinitionSetResourceType;
import org.uberfire.client.workbench.type.ClientResourceType;

@ApplicationScoped
public class CaseManagementDiagramResourceType extends CaseManagementDefinitionSetResourceType implements ClientResourceType {

    private static final Image ICON = new Image(CaseManagementImageResources.INSTANCE.cmicon());

    private final TranslationService translationService;

    protected CaseManagementDiagramResourceType() {
        this(null, null);
    }

    @Inject
    public CaseManagementDiagramResourceType(final Process category,
                                             final TranslationService translationService) {
        super(category);
        this.translationService = translationService;
    }

    @Override
    public IsWidget getIcon() {
        return ICON;
    }

    @Override
    public String getShortName() {
        return translationService.getTranslation(CaseManagementProjectClientConstants.CaseManagementDiagramResourceTypeShortName);
    }

    @Override
    public String getDescription() {
        return translationService.getTranslation(CaseManagementProjectClientConstants.CaseManagementDiagramResourceTypeDescription);
    }
}
