# Hibernate Caching

<!--![Caching](caching.png)-->

## Second Level Cache
## Dependency
```xml
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-ehcache</artifactId>
</dependency>
```

## Configuration
- Enable second level cache
```spring.jpa.properties.hibernate.cache.use_second_level_cache = true```

- Specify the caching framework - EhCache
```spring.jpa.properties.hibernate.cache.region.factory_class = org.hibernate.cache.ehcache.internal.EhcacheRegionFactory```

- Only cache those entity which have @Cacheable(true)
```spring.jpa.properties.javax.persistence.sharedCache.mode = ENABLE_SELECTIVE```

- Enable ehcache debug logging
```logging.level.net.sf.ehcache=debug```