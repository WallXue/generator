package org.mybatis.generator.ext.codegen;

import org.mybatis.generator.codegen.mybatis3.IntrospectedTableMyBatis3Impl;
import org.mybatis.generator.ext.codegen.intf.IntrospectedTableMixin;

import java.util.HashMap;
import java.util.Map;

/**
 */
public class IntrospectedTableMixinImpl extends IntrospectedTableMyBatis3Impl implements IntrospectedTableMixin {

    private Map<String, String> internalAttributes = new HashMap<String, String>();
    public static final String DAO_INTF_TYPE = "DAO_INTF_TYPE";
    public static final String DAO_IMPL_TYPE = "DAO_IMPL_TYPE";

    public String getExtDaoIntfType() {
        if (internalAttributes.containsKey(DAO_INTF_TYPE)) {
            String originDaoIntf = getDAOInterfaceType();

        }

        return internalAttributes.get(DAO_INTF_TYPE);
    }

    public String getExtDaoImplType() {
        if (internalAttributes.containsKey(DAO_IMPL_TYPE)) {
            String originDaoIntf = getDAOInterfaceType();

        }

        return internalAttributes.get(DAO_IMPL_TYPE);
    }
}
