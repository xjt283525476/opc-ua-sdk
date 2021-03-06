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

package com.digitalpetri.opcua.sdk.client.nodes.attached;

import java.util.concurrent.CompletableFuture;

import com.digitalpetri.opcua.sdk.client.OpcUaClient;
import com.digitalpetri.opcua.sdk.client.api.nodes.attached.UaVariableTypeNode;
import com.digitalpetri.opcua.sdk.core.AttributeId;
import com.digitalpetri.opcua.stack.core.types.builtin.DataValue;
import com.digitalpetri.opcua.stack.core.types.builtin.NodeId;
import com.digitalpetri.opcua.stack.core.types.builtin.StatusCode;

public class AttachedVariableTypeNode extends AttachedNode implements UaVariableTypeNode {

    public AttachedVariableTypeNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    @Override
    public CompletableFuture<DataValue> readValue() {
        return readAttribute(AttributeId.VALUE);
    }

    @Override
    public CompletableFuture<DataValue> readDataType() {
        return readAttribute(AttributeId.DATA_TYPE);
    }

    @Override
    public CompletableFuture<DataValue> readValueRank() {
        return readAttribute(AttributeId.VALUE_RANK);
    }

    @Override
    public CompletableFuture<DataValue> readArrayDimensions() {
        return readAttribute(AttributeId.ARRAY_DIMENSIONS);
    }

    @Override
    public CompletableFuture<DataValue> readIsAbstract() {
        return readAttribute(AttributeId.IS_ABSTRACT);
    }

    @Override
    public CompletableFuture<StatusCode> writeValue(DataValue value) {
        return writeAttribute(AttributeId.VALUE, value);
    }

    @Override
    public CompletableFuture<StatusCode> writeDataType(DataValue value) {
        return writeAttribute(AttributeId.DATA_TYPE, value);
    }

    @Override
    public CompletableFuture<StatusCode> writeValueRank(DataValue value) {
        return writeAttribute(AttributeId.VALUE_RANK, value);
    }

    @Override
    public CompletableFuture<StatusCode> writeArrayDimensions(DataValue value) {
        return writeAttribute(AttributeId.ARRAY_DIMENSIONS, value);
    }

    @Override
    public CompletableFuture<StatusCode> writeIsAbstract(DataValue value) {
        return writeAttribute(AttributeId.IS_ABSTRACT, value);
    }

}
