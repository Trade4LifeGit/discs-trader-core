package com.trade4life.discs.trader.core;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.trade4life.discs.trader.core");

        noClasses()
            .that()
                .resideInAnyPackage("com.trade4life.discs.trader.core.service..")
            .or()
                .resideInAnyPackage("com.trade4life.discs.trader.core.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.trade4life.discs.trader.core.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
