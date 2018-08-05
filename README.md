## Ncuhome-HackFeb-QandA
The backend of the app "Q&A"(com.ncuhome.QandA), which focuses on questioning  
yourself to help you think more deeper. It's a small project. *For study only.*
### Requirements
Java environment: JDK 1.8.0_152  
Kotlin environment: Kotlin runtime 1.2.41
### How to run
Use gradle to build the project.
> `gradle bootJar`

Use `java -jar <jar-filename>` to run the application.  
By default the location of `/static` where you store your pictures if the same as your execution path.  
**The database connection is for test. You should change it to your own.**
***
The project is under development, and the developers are striving to be better:)

#### Quick references for getting started with Springboot JPA
I'm quite doubtful that whether I'm using Springboot JPA or Hibernate at all.  
(Though it seems to be I'm told to use Hibernate at first...)
##### 1. Configuration  
Ways to configure Springboot JPA and hibernate are different. Database connection  
of Springboot JPA is configured in `application.properties` (or `application.yml`,  
and this is more recommended for its clear structure), while Hibernate loads its  
connection information from `hibernate.cfg.xml`.
##### 2.Data Mapping (ORM)
> `***Model.kt`

`@Entity` annonation declares a class as an entity.  
`@Table(name = "your_table_name")` binds the class with the table specified.  
These two annonations are to be used on the entity class.

`@Column(name = "your_column_name")` binds the property of the class with a column.  
The table needn't to be specified, as the `@Table` annonation on the class has  
determined the table, which means, the existing column will be mapped to the property,  
and if the column does not exist, Springboot JPA will create the column according to  
its data type definitions.  
`@ManyToOne(fetch = fetch_type)` annonation declares the mapping type between columns.  
A typical usage of this kind of relationship is between items and their category  
property. `Many` is the class in which you use this annonation. And `One` is the  
*?Object?* on which you put this annonation. *(The reason why I use ?Object? here is*  
*that the usage of this annonation is still confusing on me. Its usage on mathods,*  
*columns and entities can all be foun don the Internet, and I only managed to get*  
*the last one to work.)* `fetch_type` could be `FetchType.EAGER` or `FetchType.LAZY` .  
`EAGER` means loading the data on query, while `LAZY` means laoding on usage (may save  
server's resources).  
`@JoinColumn(name = "your_column_name")` defines the column in database which  
you are to use as the forengn key.

> `***Repo.kt`

`@Repository` annonation tells Springboot JPA that this is a interface for querying  
data via database.  
Your interface should inherit JpaRepository<>. `@Autowired` annonation used in the  
Controller will automatically realize this interface.  
Interestingly, the key on deciding the WHERE clause of SQL query is the method name.  
The format is like: `findOneByQid()`, `findAllByCategory()`. Springboot JPA will  
resolve the method name automatically and generate the query statement. You can  
use an instance of the entity class to receive a single result. If it is a result set,  
use Page<> to receive the result set. Don't forget to pass a `pageable` object to the  
function to meet the requirement.
***
Reference is allowed, and please at least indicate the source.  
This is a starter's studying note and may contain mistakes. Your kindly correcting them  
is always welcomed.  
Last modified: Aug 5, 2018 