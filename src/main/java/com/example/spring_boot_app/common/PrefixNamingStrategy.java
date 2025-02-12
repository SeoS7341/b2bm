package com.example.spring_boot_app.common;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

import com.example.spring_boot_app.common.util.CommonUtil;

public class PrefixNamingStrategy extends PhysicalNamingStrategyStandardImpl{

    private static final long serialVersionUID = 1L;
    private static final ImprovedNamingStrategy STRATEGY_INSTANCE = new ImprovedNamingStrategy();

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
        return new Identifier(classToTableName(name.getText()), name.isQuoted());
    }

    protected String classToTableName(String className) {
        return STRATEGY_INSTANCE.classToTableName(CommonUtil.getTablePrefix() + className);
    }

}