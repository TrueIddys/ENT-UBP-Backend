package org.ubp.ent.backend.core.domains;

/**
 * Created by Anthony on 22/11/2015.
 */
public interface ModelTransformable<T, PK> {

    PK getId();

    T toModel();

}
