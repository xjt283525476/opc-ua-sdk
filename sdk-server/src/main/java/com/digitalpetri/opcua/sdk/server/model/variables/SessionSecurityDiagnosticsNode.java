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

package com.digitalpetri.opcua.sdk.server.model.variables;

import java.util.Optional;

import com.digitalpetri.opcua.sdk.core.AttributeIds;
import com.digitalpetri.opcua.sdk.core.model.UaMandatory;
import com.digitalpetri.opcua.sdk.core.model.variables.SessionSecurityDiagnosticsType;
import com.digitalpetri.opcua.sdk.core.nodes.VariableNode;
import com.digitalpetri.opcua.sdk.server.api.UaNamespace;
import com.digitalpetri.opcua.sdk.server.util.UaVariableType;
import com.digitalpetri.opcua.stack.core.types.builtin.ByteString;
import com.digitalpetri.opcua.stack.core.types.builtin.DataValue;
import com.digitalpetri.opcua.stack.core.types.builtin.LocalizedText;
import com.digitalpetri.opcua.stack.core.types.builtin.NodeId;
import com.digitalpetri.opcua.stack.core.types.builtin.QualifiedName;
import com.digitalpetri.opcua.stack.core.types.builtin.Variant;
import com.digitalpetri.opcua.stack.core.types.builtin.unsigned.UByte;
import com.digitalpetri.opcua.stack.core.types.builtin.unsigned.UInteger;
import com.digitalpetri.opcua.stack.core.types.enumerated.MessageSecurityMode;
import com.digitalpetri.opcua.stack.core.types.structured.SessionSecurityDiagnosticsDataType;

@UaVariableType(name = "SessionSecurityDiagnosticsType")
public class SessionSecurityDiagnosticsNode extends BaseDataVariableNode implements SessionSecurityDiagnosticsType {

    public SessionSecurityDiagnosticsNode(UaNamespace namespace,
                                          NodeId nodeId,
                                          QualifiedName browseName,
                                          LocalizedText displayName,
                                          Optional<LocalizedText> description,
                                          Optional<UInteger> writeMask,
                                          Optional<UInteger> userWriteMask,
                                          DataValue value,
                                          NodeId dataType,
                                          Integer valueRank,
                                          Optional<UInteger[]> arrayDimensions,
                                          UByte accessLevel,
                                          UByte userAccessLevel,
                                          Optional<Double> minimumSamplingInterval,
                                          boolean historizing) {

        super(namespace, nodeId, browseName, displayName, description, writeMask, userWriteMask,
                value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);

    }

    @Override
    public DataValue getValue() {
        SessionSecurityDiagnosticsDataType value = new SessionSecurityDiagnosticsDataType(
                getSessionId(),
                getClientUserIdOfSession(),
                getClientUserIdHistory(),
                getAuthenticationMechanism(),
                getEncoding(),
                getTransportProtocol(),
                getSecurityMode(),
                getSecurityPolicyUri(),
                getClientCertificate()
        );

        return new DataValue(new Variant(value));
    }

    @Override
    public synchronized void setValue(DataValue value) {
        SessionSecurityDiagnosticsDataType v = (SessionSecurityDiagnosticsDataType) value.getValue().getValue();

        setSessionId(v.getSessionId());
        setClientUserIdOfSession(v.getClientUserIdOfSession());
        setClientUserIdHistory(v.getClientUserIdHistory());
        setAuthenticationMechanism(v.getAuthenticationMechanism());
        setEncoding(v.getEncoding());
        setTransportProtocol(v.getTransportProtocol());
        setSecurityMode(v.getSecurityMode());
        setSecurityPolicyUri(v.getSecurityPolicyUri());
        setClientCertificate(v.getClientCertificate());

        fireAttributeChanged(AttributeIds.Value, value);
    }

    @Override
    @UaMandatory("SessionId")
    public NodeId getSessionId() {
        Optional<VariableNode> node = getVariableComponent("SessionId");

        return node.map(n -> (NodeId) n.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    @UaMandatory("ClientUserIdOfSession")
    public String getClientUserIdOfSession() {
        Optional<VariableNode> node = getVariableComponent("ClientUserIdOfSession");

        return node.map(n -> (String) n.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    @UaMandatory("ClientUserIdHistory")
    public String[] getClientUserIdHistory() {
        Optional<VariableNode> node = getVariableComponent("ClientUserIdHistory");

        return node.map(n -> (String[]) n.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    @UaMandatory("AuthenticationMechanism")
    public String getAuthenticationMechanism() {
        Optional<VariableNode> node = getVariableComponent("AuthenticationMechanism");

        return node.map(n -> (String) n.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    @UaMandatory("Encoding")
    public String getEncoding() {
        Optional<VariableNode> node = getVariableComponent("Encoding");

        return node.map(n -> (String) n.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    @UaMandatory("TransportProtocol")
    public String getTransportProtocol() {
        Optional<VariableNode> node = getVariableComponent("TransportProtocol");

        return node.map(n -> (String) n.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    @UaMandatory("SecurityMode")
    public MessageSecurityMode getSecurityMode() {
        Optional<VariableNode> node = getVariableComponent("SecurityMode");
        return node.map(n -> {
            Integer value = (Integer) n.getValue().getValue().getValue();

            return MessageSecurityMode.from(value);
        }).orElse(null);
    }

    @Override
    @UaMandatory("SecurityPolicyUri")
    public String getSecurityPolicyUri() {
        Optional<VariableNode> node = getVariableComponent("SecurityPolicyUri");

        return node.map(n -> (String) n.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    @UaMandatory("ClientCertificate")
    public ByteString getClientCertificate() {
        Optional<VariableNode> node = getVariableComponent("ClientCertificate");

        return node.map(n -> (ByteString) n.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public synchronized void setSessionId(NodeId sessionId) {
        getVariableComponent("SessionId").ifPresent(n -> {
            n.setValue(new DataValue(new Variant(sessionId)));

            fireAttributeChanged(AttributeIds.Value, getValue());
        });
    }

    @Override
    public synchronized void setClientUserIdOfSession(String clientUserIdOfSession) {
        getVariableComponent("ClientUserIdOfSession").ifPresent(n -> {
            n.setValue(new DataValue(new Variant(clientUserIdOfSession)));

            fireAttributeChanged(AttributeIds.Value, getValue());
        });
    }

    @Override
    public synchronized void setClientUserIdHistory(String[] clientUserIdHistory) {
        getVariableComponent("ClientUserIdHistory").ifPresent(n -> {
            n.setValue(new DataValue(new Variant(clientUserIdHistory)));

            fireAttributeChanged(AttributeIds.Value, getValue());
        });
    }

    @Override
    public synchronized void setAuthenticationMechanism(String authenticationMechanism) {
        getVariableComponent("AuthenticationMechanism").ifPresent(n -> {
            n.setValue(new DataValue(new Variant(authenticationMechanism)));

            fireAttributeChanged(AttributeIds.Value, getValue());
        });
    }

    @Override
    public synchronized void setEncoding(String encoding) {
        getVariableComponent("Encoding").ifPresent(n -> {
            n.setValue(new DataValue(new Variant(encoding)));

            fireAttributeChanged(AttributeIds.Value, getValue());
        });
    }

    @Override
    public synchronized void setTransportProtocol(String transportProtocol) {
        getVariableComponent("TransportProtocol").ifPresent(n -> {
            n.setValue(new DataValue(new Variant(transportProtocol)));

            fireAttributeChanged(AttributeIds.Value, getValue());
        });
    }

    @Override
    public synchronized void setSecurityMode(MessageSecurityMode securityMode) {
        getVariableComponent("SecurityMode").ifPresent(n -> {
            Integer value = securityMode.getValue();

            n.setValue(new DataValue(new Variant(value)));

            fireAttributeChanged(AttributeIds.Value, getValue());
        });
    }

    @Override
    public synchronized void setSecurityPolicyUri(String securityPolicyUri) {
        getVariableComponent("SecurityPolicyUri").ifPresent(n -> {
            n.setValue(new DataValue(new Variant(securityPolicyUri)));

            fireAttributeChanged(AttributeIds.Value, getValue());
        });
    }

    @Override
    public synchronized void setClientCertificate(ByteString clientCertificate) {
        getVariableComponent("ClientCertificate").ifPresent(n -> {
            n.setValue(new DataValue(new Variant(clientCertificate)));

            fireAttributeChanged(AttributeIds.Value, getValue());
        });
    }

}
