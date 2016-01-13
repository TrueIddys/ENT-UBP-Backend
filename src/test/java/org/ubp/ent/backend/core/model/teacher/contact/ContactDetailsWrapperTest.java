package org.ubp.ent.backend.core.model.teacher.contact;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 13/01/2016.
 */
public class ContactDetailsWrapperTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullType() {
        new ContactDetailsWrapper<>(null);
    }


    @Test
    public void shouldInstantiate() {
        DefaultType type = new DefaultType("name");
        ContactDetailsWrapper<DefaultType> wrapper = new ContactDetailsWrapper<>(type);
        assertThat(wrapper.getId()).isNull();
        assertThat(wrapper.getType()).isEqualTo(type);
    }

    @Test
    public void shouldBeEqualsBasedOnType() {
        DefaultType type = new DefaultType("name");
        ContactDetailsWrapper<DefaultType> wrapper1 = new ContactDetailsWrapper<>(type);
        ContactDetailsWrapper<DefaultType> wrapper2 = new ContactDetailsWrapper<>(type);
        assertThat(wrapper1).isEqualTo(wrapper2);
    }

    @Test
    public void shouldNotBeEqualsWithDifferentTypes() {
        ContactDetailsWrapper<DefaultType> wrapper1 = new ContactDetailsWrapper<>(new DefaultType("type 1"));
        ContactDetailsWrapper<DefaultType> wrapper2 = new ContactDetailsWrapper<>(new DefaultType("type 2"));
        assertThat(wrapper1).isNotEqualTo(wrapper2);
    }

    private static class DefaultType extends ContactDetailsType {
        public DefaultType(String name) {
            super(name);
        }
    }
}