1. Define Dependencies:
Ensure your pom.xml or build.gradle includes the required dependencies like spring-boot-starter-data-jpa, spring-boot-starter-jdbc, and the JDBC driver for your databases.

2. Configure Data Sources:
Define connection properties for each data source in application.properties or application.yml. Ensure that the properties are prefixed correctly for each data source.

3. Create Configuration Classes:
Create separate configuration classes for each data source. Annotate these classes with @Configuration and define beans for data sources, entity managers, and transaction managers as necessary.
Define these 4 beans in each configuration class:
1. DatasourceProperties
2. define Datasource
3. EntityManagerFactory bean
4. TransactionManager bean

5. Define Entity Classes and Repositories:
Define entity classes and repositories for each data source. Make sure to annotate entities with @Entity and repositories with @Repository.

6. Use Multiple Transaction Managers (optional):
If you need to manage transactions separately for each data source, configure multiple transaction managers in your configuration classes. You can use @EnableTransactionManagement to enable transaction management and @Transactional to define transactional boundaries in your service methods.

By following these steps, you can effectively use two data sources in a single Spring Boot application. Each data source can have its own entity classes, repositories, and transaction management configuration. This allows you to interact with multiple databases within the same application seamlessly.
