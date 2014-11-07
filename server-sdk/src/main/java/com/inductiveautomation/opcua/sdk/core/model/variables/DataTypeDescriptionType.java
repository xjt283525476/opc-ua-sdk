package com.inductiveautomation.opcua.sdk.core.model.variables;

import com.inductiveautomation.opcua.sdk.core.model.UaOptional;
import com.inductiveautomation.opcua.stack.core.types.builtin.ByteString;

public interface DataTypeDescriptionType extends BaseDataVariableType {

    @UaOptional("DataTypeVersion")
    String getDataTypeVersion();

    @UaOptional("DictionaryFragment")
    ByteString getDictionaryFragment();

    void setDataTypeVersion(String dataTypeVersion);

    void setDictionaryFragment(ByteString dictionaryFragment);

}
