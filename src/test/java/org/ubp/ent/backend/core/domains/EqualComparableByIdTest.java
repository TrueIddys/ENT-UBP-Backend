package org.ubp.ent.backend.core.domains;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 13/01/2016.
 */
public class EqualComparableByIdTest {

    @Test
    public void shouldBeEqualsTwoInstanceWithSameId() {
        DefaultClass first = new DefaultClass().setId(12L);
        DefaultClass second = new DefaultClass().setId(12L);

        assertThat(first).isEqualTo(second);
    }

    @Test
    public void shouldNotBeEqualsTwoInstanceWitDifferentIds() {
        DefaultClass first = new DefaultClass().setId(12L);
        DefaultClass second = new DefaultClass().setId(13L);

        assertThat(first).isNotEqualTo(second);
    }

    @Test
    public void shouldNotBeEqualsIfOneHasANullId() {
        DefaultClass first = new DefaultClass().setId(null);
        DefaultClass second = new DefaultClass().setId(13L);
        assertThat(first).isNotEqualTo(second);

        first.setId(12L);
        second.setId(null);
        assertThat(first).isNotEqualTo(second);

        first.setId(null);
        second.setId(null);
        assertThat(first).isNotEqualTo(second);
    }

    @Test
    public void shouldNotBeEqualsTwoInstancesWithSameIdButFalseConstraints() {
        DefaultClassWithConstraint first = new DefaultClassWithConstraint("anthony").setId(12L);
        DefaultClassWithConstraint second = new DefaultClassWithConstraint("joe").setId(12L);

        assertThat(first).isNotEqualTo(second);
    }

    @Test
    public void shouldBeEqualsTwoInstancesWithSameIdAndTrueConstraints() {
        DefaultClassWithConstraint first = new DefaultClassWithConstraint("anthony").setId(12L);
        DefaultClassWithConstraint second = new DefaultClassWithConstraint("anthony").setId(12L);

        assertThat(first).isEqualTo(second);
    }

    @Test
    public void shouldWorkWhenCallingEqualsOnChildClassEvenIfOnlySuperclassExtendsOnEqualComparableById() {
        //Not equals, since checkAdditionalEqualsConstraint returns always false.
        assertThat(new SuperClass().setId(12L)).isNotEqualTo(new SuperClass().setId(12L));
        assertThat(new SuperClass().setId(12L)).isNotEqualTo(new SuperClass().setId(13L));

        // Equals, since checkAdditionalEqualsConstraint returns always true.
        assertThat(new ChildClass().setId(12L)).isEqualTo(new ChildClass().setId(12L));
        assertThat(new ChildClass().setId(12L)).isNotEqualTo(new ChildClass().setId(13L));
    }

    @Test
    public void shouldNotBeEqualsTwoInstanceOfTwoDifferentChildClasses() {
        assertThat(new FirstNoConstraintChildClass().setId(12L)).isNotEqualTo(new SecondNoConstraintChildClass().setId(12L));
        assertThat(new SecondNoConstraintChildClass().setId(12L)).isNotEqualTo(new FirstNoConstraintChildClass().setId(12L));
    }


    private static class DefaultClass extends EqualComparableById<DefaultClass, Long> {

        private Long id;

        @Override
        public Long getId() {
            return id;
        }

        public DefaultClass setId(Long id) {
            this.id = id;
            return this;
        }
    }

    private static class DefaultClassWithConstraint extends EqualComparableById<DefaultClassWithConstraint, Long> {

        private Long id;
        private String name;

        public DefaultClassWithConstraint(String name) {
            this.name = name;
        }

        @Override
        public Long getId() {
            return id;
        }

        public DefaultClassWithConstraint setId(Long id) {
            this.id = id;
            return this;
        }

        @Override
        protected boolean checkAdditionalEqualsConstraint(DefaultClassWithConstraint other) {
            return name.equals(other.name);
        }
    }

    private static class SuperClass extends EqualComparableById<SuperClass, Long> {
        private Long id;

        @Override
        public Long getId() {
            return id;
        }

        public SuperClass setId(Long id) {
            this.id = id;
            return this;
        }

        @Override
        protected boolean checkAdditionalEqualsConstraint(SuperClass other) {
            return false;
        }
    }

    private static class ChildClass extends SuperClass {
        @Override
        protected boolean checkAdditionalEqualsConstraint(SuperClass other) {
            return true;
        }
    }

    private static class NoConstraintSuperClass extends EqualComparableById<NoConstraintSuperClass, Long> {
        private Long id;

        @Override
        public Long getId() {
            return id;
        }

        public NoConstraintSuperClass setId(Long id) {
            this.id = id;
            return this;
        }
    }

    private static class FirstNoConstraintChildClass extends SuperClass {
    }

    private static class SecondNoConstraintChildClass extends SuperClass {
    }

}