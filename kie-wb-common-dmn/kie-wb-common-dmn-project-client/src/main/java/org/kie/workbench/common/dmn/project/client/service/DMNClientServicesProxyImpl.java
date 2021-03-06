/*
 * Copyright 2019 Red Hat, Inc. and/or its affiliates.
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

package org.kie.workbench.common.dmn.project.client.service;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.guvnor.common.services.project.client.context.WorkspaceProjectContext;
import org.guvnor.common.services.project.model.WorkspaceProject;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.ErrorCallback;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.kie.workbench.common.dmn.api.definition.v1_1.ItemDefinition;
import org.kie.workbench.common.dmn.api.editors.included.DMNIncludedModel;
import org.kie.workbench.common.dmn.api.editors.included.DMNIncludedModelsService;
import org.kie.workbench.common.dmn.api.editors.included.DMNIncludedNode;
import org.kie.workbench.common.dmn.api.editors.types.DMNParseService;
import org.kie.workbench.common.dmn.api.editors.types.DMNSimpleTimeZone;
import org.kie.workbench.common.dmn.api.editors.types.DMNValidationService;
import org.kie.workbench.common.dmn.api.editors.types.RangeValue;
import org.kie.workbench.common.dmn.api.editors.types.TimeZoneService;
import org.kie.workbench.common.dmn.client.service.DMNClientServicesProxy;
import org.kie.workbench.common.stunner.core.client.service.ClientRuntimeError;
import org.kie.workbench.common.stunner.core.client.service.ServiceCallback;

@Dependent
public class DMNClientServicesProxyImpl implements DMNClientServicesProxy {

    private final WorkspaceProjectContext projectContext;
    private final Caller<DMNIncludedModelsService> includedModelsService;
    private final Caller<DMNParseService> parserService;
    private final Caller<DMNValidationService> validationService;
    private final Caller<TimeZoneService> timeZoneService;

    @Inject
    public DMNClientServicesProxyImpl(final WorkspaceProjectContext projectContext,
                                      final Caller<DMNIncludedModelsService> includedModelsService,
                                      final Caller<DMNParseService> parserService,
                                      final Caller<DMNValidationService> validationService,
                                      final Caller<TimeZoneService> timeZoneService) {
        this.projectContext = projectContext;
        this.includedModelsService = includedModelsService;
        this.parserService = parserService;
        this.validationService = validationService;
        this.timeZoneService = timeZoneService;
    }

    @Override
    public void loadModels(final ServiceCallback<List<DMNIncludedModel>> callback) {
        includedModelsService.call(onSuccess(callback), onError(callback)).loadModels(getWorkspaceProject());
    }

    @Override
    public void loadNodesFromImports(final List<DMNIncludedModel> includedModels,
                                     final ServiceCallback<List<DMNIncludedNode>> callback) {
        includedModelsService.call(onSuccess(callback), onError(callback)).loadNodesFromImports(getWorkspaceProject(), includedModels);
    }

    @Override
    public void loadItemDefinitionsByNamespace(final String modelName,
                                               final String namespace,
                                               final ServiceCallback<List<ItemDefinition>> callback) {
        includedModelsService.call(onSuccess(callback), onError(callback)).loadItemDefinitionsByNamespace(getWorkspaceProject(), modelName, namespace);
    }

    @Override
    public void parseFEELList(final String source,
                              final ServiceCallback<List<String>> callback) {
        parserService.call(onSuccess(callback), onError(callback)).parseFEELList(source);
    }

    @Override
    public void parseRangeValue(final String source,
                                final ServiceCallback<RangeValue> callback) {
        parserService.call(onSuccess(callback), onError(callback)).parseRangeValue(source);
    }

    @Override
    public void isValidVariableName(final String source,
                                    final ServiceCallback<Boolean> callback) {
        validationService.call(onSuccess(callback), onError(callback)).isValidVariableName(source);
    }

    @Override
    public void getTimeZones(final ServiceCallback<List<DMNSimpleTimeZone>> callback) {
        timeZoneService.call(onSuccess(callback), onError(callback)).getTimeZones();
    }

    <T> RemoteCallback<T> onSuccess(final ServiceCallback<T> callback) {
        return callback::onSuccess;
    }

    <T> ErrorCallback<Boolean> onError(final ServiceCallback<T> callback) {
        return (message, throwable) -> {
            callback.onError(new ClientRuntimeError(throwable));
            return false;
        };
    }

    private WorkspaceProject getWorkspaceProject() {
        return projectContext.getActiveWorkspaceProject().orElse(null);
    }
}
