package net.contrapt.auction.model;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.io.Serializable;

/**
 * Created by msimmons on 11/12/14.
 */
public class SpecificationHelper<T> {

    public Specification<T> joinCollections(final String... collectionNames) {
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                for ( String collectionName : collectionNames ) {
                    root.fetch(collectionName, JoinType.LEFT);
                }
                return null;
            }
        };
    }

    public Specification<T> findBy(final String attributeName, final Object attributeValue) {
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                query.where(cb.equal(root.get(attributeName), attributeValue));
                return null;
            }
        };
    }

    public Specification<T> findByWithCollections(final String attributeName, final Object attributeValue, final String... collectionNames) {
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                query.where(cb.equal(root.get(attributeName), attributeValue));
                for ( String collectionName : collectionNames ) {
                    root.fetch(collectionName, JoinType.LEFT);
                }
                return null;
            }
        };
    }

}
