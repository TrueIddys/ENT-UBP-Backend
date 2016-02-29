package org.ubp.ent.backend.core.domains.teachingunit;

import org.junit.Test;
import org.ubp.ent.backend.core.domains.module.ModuleDomain;
import org.ubp.ent.backend.core.domains.module.ModuleDomainTest;
import org.ubp.ent.backend.core.model.module.ModuleTest;
import org.ubp.ent.backend.core.model.teachingunit.TeachingUnit;
import org.ubp.ent.backend.core.model.teachingunit.TeachingUnitTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Maxime on 28/02/2016.
 */
public class TeachingUnitDomainTest {

    public static TeachingUnitDomain createOne(String name, int moduleCount) {
        TeachingUnit teachingUnit = TeachingUnitTest.createOne();
        TeachingUnitDomain teachingUnitDomain = new TeachingUnitDomain(teachingUnit);

        for (int i = 0; i < moduleCount; i++) {
            teachingUnitDomain.addModule(ModuleDomainTest.createOne("module " + i));
        }

        return  teachingUnitDomain;
    }

    public static TeachingUnitDomain createOne() {
        return new TeachingUnitDomain(TeachingUnitTest.createOne());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullModel() {
        new TeachingUnitDomain(null);
    }

    @Test
    public void shouldCreateFromModel() {
        TeachingUnit model = TeachingUnitTest.createOne();
        model.setId(12L);
        TeachingUnitDomain domain = new TeachingUnitDomain(model);

        assertThat(domain.getId()).isEqualTo(model.getId());
        assertThat(domain.getName()).isEqualTo(model.getName());
        assertThat(domain.getModules()).isEmpty();
    }

    @Test
    public void shouldTransformToModel() {
        TeachingUnit model = TeachingUnitTest.createOne();
        model.setId(12L);
        TeachingUnitDomain domain = new TeachingUnitDomain(model);

        TeachingUnit newModel = domain.toModel();
        assertThat(newModel.getId()).isEqualTo(model.getId());
        assertThat(newModel.getName()).isEqualTo(model.getName());
    }

    @Test
    public void shouldBeEqualById() {
        TeachingUnitDomain first = TeachingUnitDomainTest.createOne();
        first.setId(1L);
        TeachingUnitDomain second = TeachingUnitDomainTest.createOne();
        second.setId(1L);

        assertThat(first).isEqualTo(second);
    }

    @Test
    public void shouldNotBeEqualWithDifferentIds() {
        TeachingUnitDomain first = TeachingUnitDomainTest.createOne();
        first.setId(1L);
        TeachingUnitDomain second = TeachingUnitDomainTest.createOne();
        second.setId(2L);

        assertThat(first).isNotEqualTo(second);
    }

    @Test
    public void shouldNotBeEqualWithNullIds() {
        TeachingUnitDomain first = TeachingUnitDomainTest.createOne();
        TeachingUnitDomain second = TeachingUnitDomainTest.createOne();

        assertThat(first).isNotEqualTo(second);
    }
    @Test
    public void shouldNotPopulateListOfModuleWhenCreatingDomainFromModel() {
        TeachingUnit model = TeachingUnitTest.createOne();
        model.addModule(ModuleTest.createOne());
        TeachingUnitDomain domain = new TeachingUnitDomain(model);

        assertThat(domain.getModules()).isEmpty();
    }

    @Test
    public void shouldNotPopulateListOfModuleWhenTransformingDomainFromModel() {
        TeachingUnit model = TeachingUnitTest.createOne();
        TeachingUnitDomain domain = new TeachingUnitDomain(model);
        domain.getModules().add(new ModuleDomain(ModuleTest.createOne()));

        TeachingUnit domainToModel = domain.toModel();
        assertThat(domainToModel.getModules()).isEmpty();
    }

}
