/*
 * digitalpetri OPC-UA SDK
 *
 * Copyright (C) 2015 Kevin Herron
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.digitalpetri.opcua.sdk.client.fsm.states;

import java.util.concurrent.CompletableFuture;

import com.digitalpetri.opcua.sdk.client.api.UaSession;
import com.digitalpetri.opcua.sdk.client.fsm.SessionState;
import com.digitalpetri.opcua.sdk.client.fsm.SessionStateContext;
import com.digitalpetri.opcua.sdk.client.fsm.SessionStateEvent;
import com.digitalpetri.opcua.stack.core.StatusCodes;
import com.digitalpetri.opcua.stack.core.UaException;

public class Inactive implements SessionState {

    private final CompletableFuture<UaSession> future = new CompletableFuture<>();

    public Inactive() {
        future.completeExceptionally(new UaException(StatusCodes.Bad_SessionClosed, "session inactive"));
    }

    @Override
    public void activate(SessionStateEvent event, SessionStateContext context) {

    }

    @Override
    public SessionState transition(SessionStateEvent event, SessionStateContext context) {
        switch (event) {
            case CREATE_AND_ACTIVATE_REQUESTED:
                return new CreateAndActivate(new CompletableFuture<>());
        }

        return this;
    }

    @Override
    public CompletableFuture<UaSession> getSessionFuture() {
        return future;
    }

}
