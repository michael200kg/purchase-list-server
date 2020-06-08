package com.michael200kg.purchaseserver.configuration;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

import java.util.Objects;

public class CamelCaseToSnakeCaseNamingStrategy extends PhysicalNamingStrategyStandardImpl {

    private static final String CAMEL_CASE_REGEX = "([a-z]+)([A-Z]+)";
    private static final String SNAKE_CASE_PATTERN = "$1\\_$2";

    @Override
    public Identifier toPhysicalCatalogName(
        Identifier name, JdbcEnvironment context) {
        return formatIdentifier(super.toPhysicalCatalogName(name, context));
    }

    @Override
    public Identifier toPhysicalSchemaName(
        Identifier name, JdbcEnvironment context) {
        return formatIdentifier(super.toPhysicalSchemaName(name, context));
    }

    @Override
    public Identifier toPhysicalTableName(
        Identifier name, JdbcEnvironment context) {
        return formatIdentifier(super.toPhysicalTableName(name, context));
    }

    @Override
    public Identifier toPhysicalSequenceName(
        Identifier name, JdbcEnvironment context) {
        return formatIdentifier(super.toPhysicalSequenceName(name, context));
    }

    @Override
    public Identifier toPhysicalColumnName(
        Identifier name, JdbcEnvironment context) {
        return formatIdentifier(super.toPhysicalColumnName(name, context));
    }

    private Identifier formatIdentifier(Identifier identifier) {
        if (Objects.nonNull(identifier)) {
            String name = identifier.getText();
            String formattedName = name
                .replaceAll("Entity", "")
                .replaceAll(CAMEL_CASE_REGEX, SNAKE_CASE_PATTERN)
                .toUpperCase();

            return Identifier.toIdentifier(formattedName, identifier.isQuoted());
        } else {
            return null;
        }
    }
}
