/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.workbench.common.screens.examples.client.wizard.pages.organizationalunit;

import java.util.List;
import java.util.Set;

import org.gwtbootstrap3.client.ui.constants.ValidationState;
import org.kie.workbench.common.screens.examples.model.ExampleOrganizationalUnit;
import org.kie.workbench.common.screens.examples.model.ExampleTargetRepository;
import org.uberfire.client.mvp.UberView;

public interface OUPageView extends UberView<OUPage> {

    interface Presenter {

        void setOrganizationalUnits( final Set<ExampleOrganizationalUnit> organizationalUnits );

        void setTargetRepository( final ExampleTargetRepository repository );

        void setTargetOrganizationalUnit( final ExampleOrganizationalUnit organizationalUnit );

    }

    void initialise();

    void setTargetRepositoryPlaceHolder( final String placeHolder );

    void setOrganizationalUnitsPlaceHolder( final String placeHolder );

    void setOrganizationalUnits( final List<ExampleOrganizationalUnit> organizationalUnits );

    void setOrganizationalUnit( final ExampleOrganizationalUnit organizationalUnit );

    void setTargetRepositoryGroupType( final ValidationState state );

    void showTargetRepositoryHelpMessage( final String message );

    void hideTargetRepositoryHelpMessage();

    void setTargetOrganizationalUnitGroupType( final ValidationState state );

    void showTargetOrganizationalUnitHelpMessage( final String message );

    void hideTargetOrganizationalUnitHelpMessage();

}