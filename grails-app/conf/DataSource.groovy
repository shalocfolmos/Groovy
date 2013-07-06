dataSource {
    pooled = true
    driverClassName = "org.h2.Driver"
    username = "sa"
    password = ""
}


mysqlDataSource {
    driverClassName = "com.mysql.jdbc.Driver"
    pooled = "true"
//    dbCreate = "create-drop"
    dbCreate = "update"
    username = "root"
    password = "123456"
    url = "jdbc:mysql://127.0.0.1:3306/cms_email?useUnicode=yes&characterEncoding=UTF-8"
}

hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
    development {
        dataSource = mysqlDataSource
    }
    test {
        dataSource =  mysqlDataSource
    }
    production {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:prodDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
            pooled = true
            properties {
               maxActive = -1
               minEvictableIdleTimeMillis=1800000
               timeBetweenEvictionRunsMillis=1800000
               numTestsPerEvictionRun=3
               testOnBorrow=true
               testWhileIdle=true
               testOnReturn=true
               validationQuery="SELECT 1"
            }
        }
    }
}
