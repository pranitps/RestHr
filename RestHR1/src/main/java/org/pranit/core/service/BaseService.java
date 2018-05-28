package org.pranit.core.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Path;

import org.pranit.core.dao.BaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;



@Transactional
public abstract class BaseService<T extends Persistable<? extends Serializable>, ID extends Serializable> {

	private final Logger logger = LoggerFactory.getLogger(BaseService.class);

    /**
     * The Entity class.
     */
    protected Class<T> entityClass;

    /**
     * Gets entity dao.
     *
     * @return the entity dao
     */
    abstract protected BaseDao<T, ID> getEntityDao();

    /**
     * Instantiates a new Lms base service.
     */
    public BaseService() {}


    /**
     * Pre insert.
     *
     * @param entity the entity
     */
    protected void preInsert(T entity) {
    }

    /**
     * Pre update.
     *
     * @param entity the entity
     */
    protected void preUpdate(T entity) {
    }

    /**
     * Save t.
     *
     * @param entity the entity
     * @return the t
     */
    public T save(T entity) {
        if (entity.isNew()) {
            preInsert(entity);
        } else {
            preUpdate(entity);
        }
        return getEntityDao().save(entity);
    }

    /**
     * Save list.
     *
     * @param entities the entities
     * @return the list
     */
    public List<T> save(Iterable<T> entities) {
        List<T> result = new ArrayList<T>();
        if (entities == null) {
            return result;
        }
        for (T entity : entities) {
            result.add(save(entity));
        }
        return result;
    }

    /**
     * Find one t.
     *
     * @param id the id
     * @return the t
     */
    @Transactional(readOnly = true)
    public T findOne(ID id) {
        Assert.notNull(id);
        return getEntityDao().findOne(id);
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    @Transactional(readOnly = true)
    public List<T> findAll() {
        return getEntityDao().findAll();
    }

    /**
     * Find all list.
     *
     * @param ids the ids
     * @return the list
     */
    @Transactional(readOnly = true)
    public List<T> findAll(final ID... ids) {
        Assert.isTrue(ids != null && ids.length > 0, "No IDs list is empty");
        Specification<T> spec = (root, query, builder) -> {
            Path expression = root.get("id");
            return expression.in(ids);
        };
        return this.getEntityDao().findAll(spec);
    }

    /**
     * Delete.
     *
     * @param entity the entity
     */
    public void delete(T entity) {
        getEntityDao().delete(entity);
    }

    /**
     * Delete.
     *
     * @param entities the entities
     */
    public void delete(Iterable<T> entities) {
        for (T entity : entities) {
            delete(entity);
        }
    }

    /**
     * Find by property t.
     *
     * @param property the property
     * @param value    the value
     * @return the t
     */
    @Transactional(readOnly = true)
    public T findByProperty(final String property, final Object value) {
        Specification<T> spec = (root, query, builder) -> {
            Path expression = root.get(property);
            return builder.equal(expression, value);
        };

        List<T> entities = this.getEntityDao().findAll(spec);
        if (CollectionUtils.isEmpty(entities)) {
            return null;
        } else {
            Assert.isTrue(entities.size() == 1);
            return entities.get(0);
        }
    }

    /**
     * Find all by property list.
     *
     * @param property the property
     * @param value    the value
     * @return the list
     */
    @Transactional(readOnly = true)
    public List<T> findAllByProperty(final String property, final Object value) {
        Specification<T> spec = (root, query, builder) -> {
            Path expression = root.get(property);
            return builder.equal(expression, value);
        };

        List<T> entities = this.getEntityDao().findAll(spec);
        if (CollectionUtils.isEmpty(entities)) {
            return null;
        } else {
            return entities;
        }
    }

    /**
     * Find first by property t.
     *
     * @param property the property
     * @param value    the value
     * @return the t
     */
    @Transactional(readOnly = true)
    public T findFirstByProperty(final String property, final Object value) {
        Specification<T> spec = (root, query, builder) -> {
            @SuppressWarnings("rawtypes")
            Path expression = root.get(property);
            return builder.equal(expression, value);
        };

        List<T> entities = this.getEntityDao().findAll(spec);
        if (CollectionUtils.isEmpty(entities)) {
            return null;
        } else {
            return entities.get(0);
        }
    }

    /**
     * Exists by property boolean.
     *
     * @param property the property
     * @param value    the value
     * @return the boolean
     */
    @Transactional(readOnly = true)
    public boolean existsByProperty(final String property, final Object value) {
        Specification<T> spec = (root, query, builder) -> {
            @SuppressWarnings("rawtypes")
            Path expression = root.get(property);
            return builder.equal(expression, value);
        };
        return getEntityDao().count(spec)> 0;
    }


    /**
     * Count long.
     *
     * @param spec the spec
     * @return the long
     */
    @Transactional(readOnly = true)
    public long count(Specification<T> spec) {
        return getEntityDao().count(spec);
    }
}
