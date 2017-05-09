#mybatis分页工具   
##项目环境
SSM:springMVC3.3.8+springIOC3.3.8+Mybatis3.4.4
##官方示例
[https://github.com/abel533/Mybatis-Spring](https://github.com/abel533/Mybatis-Spring)   
[https://github.com/pagehelper/Mybatis-PageHelper/blob/master/wikis/zh/HowToUse.md](https://github.com/pagehelper/Mybatis-PageHelper/blob/master/wikis/zh/HowToUse.md "使用方法")   
参考资料：   
[http://www.cnblogs.com/itjcw/p/6206888.html](http://www.cnblogs.com/itjcw/p/6206888.html "mybatis的两种分页方式:RowBounds和PageHelper")
##使用步骤   
1、	添加gradle依赖   
```xml
//Mybatis分页
// https://mvnrepository.com/artifact/com.github.pagehelper/pagehelper
compile group: 'com.github.pagehelper', name: 'pagehelper', version: '5.0.1'
```
由于使用了sql 解析工具，你还需要下载 jsqlparser.jar：
```xml
    // https://mvnrepository.com/artifact/com.github.jsqlparser/jsqlparser
    compile group: 'com.github.jsqlparser', name: 'jsqlparser', version: '1.0'
```
2、在spring配置文件的mybatis部分添加代码
```xml
<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
    <property name="dataSource" ref="dataSource"/>
    <property name="mapperLocations">
        <array>
            <value>classpath:mapper/*.xml</value>
        </array>
    </property>
    <property name="typeAliasesPackage" value="com.isea533.mybatis.model"/>
    <property name="plugins">
        <array>
            <bean class="com.github.pagehelper.PageInterceptor">
                <!-- 这里的几个配置主要演示如何使用，如果不理解，一定要去掉下面的配置 -->
                <property name="properties">
                    <value>
                        helperDialect=mysql
                        reasonable=true
                        supportMethodsArguments=true
                        params=count=countSql
                        autoRuntimeDialect=true
                    </value>
                </property>
            </bean>
        </array>
    </property>
</bean>
```
上面是官方的例子，下面是我的spring.xml中mybatis相关的所有配置
```xml
    <!-- 注入数据库配置资源文件 -->
    <util:properties id="dbCfg" location="classpath:db.properties"></util:properties>
    <!-- 加载资源文件  其中包含变量信息，必须在Spring配置文件的最前面加载，即第一个加载-->
    <!--<context:property-placeholder location="classpath:db.properties" />-->
    <!-- 1定义dbcp数据库连接池 -->
    <bean id="dbcp" class="org.apache.commons.dbcp2.BasicDataSource">
        <!--<property name="username" value="${jdbc.username}"></property>-->
        <!--<property name="password" value="${jdbc.password}"></property>-->
        <!--<property name="driverClassName"-->
                  <!--value="${jdbc.driverClassName}"></property>-->
        <!--<property name="url"-->
                  <!--value="${jdbc.url}"></property>-->
        <property name="username" value="#{dbCfg.username}"></property>
        <property name="password" value="#{dbCfg.password}"></property>
        <property name="driverClassName"
                  value="#{dbCfg.driverClassName}"></property>
        <property name="url"
                  value="#{dbCfg.url}"></property>
    </bean>
    <!-- 2创建SqlSessionFactory -->
    <bean id="ssf" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dbcp" />
        <!-- 加载mapper目录下所有的SQL定义文件 -->
        <property name="mapperLocations"
                  value="classpath:com/zbkblog/mapper/*.xml">
        </property>
        <!-- 分页插件相关 -->
        <!--可能是实体类<property name="typeAliasesPackage" value="com.isea533.mybatis.model"/>-->
        <property name="plugins">
            <array>
                <bean class="com.github.pagehelper.PageInterceptor">
                    <!-- 这里的几个配置主要演示如何使用，如果不理解，一定要去掉下面的配置 -->
                    <property name="properties">
                        <value>
                            <!--数据库使用什么数据库-->
                            helperDialect=mysql
                            <!-- 默认false,将 RowBounds 中的 offset 参数当成 pageNum 使用
                            可以用页码和页面大小两个参数进行分页。-->
                            offsetAsPageNum=true
                            <!--默认值为false，该参数对使用 RowBounds 作为分页参数时有效。
                            当该参数设置为true时，使用 RowBounds 分页会进行 count 查询-->
                            rowBoundsWithCount=true
                            <!--分页合理化参数，默认值为false。当该参数设置为 true 时，
                            pageNum<=0 时会查询第一页， pageNum>pages（超过总数时），
                            会查询最后一页。默认false 时，直接根据参数进行查询-->
                            reasonable=true
                            supportMethodsArguments=true
                            params=count=countSql
                            autoRuntimeDialect=true
                        </value>
                    </property>
                </bean>
            </array>
        </property>
        <!-- 分页插件相关 end -->

    </bean>
    <!-- 3定义MapperScannerConfigurer，将mapper(Dao)接口生成实现对象
        使用接口名首字母小写做对象的id，所以要使用就直接使用接口名首字母小写就行 -->
    <bean id="mapperScanner"
          class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <!-- 3.1注入SqlSessionFactory -->
        <property name="sqlSessionFactory" ref="ssf">
        </property>
        <!-- 3.2注入mapper接口所在位置,将dao包下所有接口生成实现对象(和SQL定义文件对应的Dao接口) -->
        <property name="basePackage" value="com.zbkblog.dao">
        </property>
    </bean>
```

3、mapper.xml配置文件
```xml
    <!-- 分页查询 -->
    <select id="findAllBlogDocPaging" parameterType="com.zbkblog.medo.BlogDoc" resultMap="ResultBlogDoc">
        SELECT *
        FROM blogdoc
        WHERE 1 = 1
        <if test="blogClass!=null">
            AND blogClass=#{blogClass}
        </if>
        <if test="blogTag!=null">
            AND blogTag=#{blogTag}
        </if>
        ORDER BY updataTime
    </select>
```
注意：resultMap要定义好
```xml
    <!--BlogDoc对应的映射-->
    <resultMap id="ResultBlogDoc" type="com.zbkblog.medo.BlogDoc" >
        <id column="blogId" property="blogId" jdbcType="INTEGER" />
        <result column="blogTitle" property="blogTitle" jdbcType="VARCHAR" />
        <result column="blogClass" property="blogClass" jdbcType="VARCHAR" />
        <result column="blogTag" property="blogTag" jdbcType="VARCHAR" />
        <result column="blogMd" property="blogMd" jdbcType="VARCHAR" />
        <result column="updataTime" property="updataTime" jdbcType="INTEGER" />
        <result column="updataTimeStr" property="updataTimeStr" jdbcType="VARCHAR" />
        <result column="openNumber" property="openNumber" jdbcType="INTEGER" />
        <result column="supportNumber" property="supportNumber" jdbcType="INTEGER" />
    </resultMap>
```

4、Dao接口添加
```java
    /**
     * 分页查询所有
     * @param blogDoc
     * @return
     */
    List<BlogDoc> findAllBlogDocPaging(BlogDoc blogDoc);
```

5、Service接口添加
```java
    /**
     * 分页查询文章，传入一个BlogDoc对象，根据非空属性匹配
     * 传入一个页码和页大小实现分页
     * @param blogDoc
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageInfo<BlogDoc> findAllBlogDocPaging(BlogDoc blogDoc, Integer pageNo, Integer pageSize);
```

6、实现Service接口中添加的方法
```java
    @Override
    public PageInfo<BlogDoc> findAllBlogDocPaging(BlogDoc blogDoc, Integer pageNo, Integer pageSize) {
        pageNo = pageNo == null?1:pageNo;
        pageSize = pageSize == null?10:pageSize;
        //应该也能用
        //PageHelper.offsetPage(pageNo,pageSize);//未测试
        PageHelper.startPage(pageNo, pageSize);
        List<BlogDoc> list = blogDao.findAllBlogDocPaging(blogDoc);//.selectUserByUserName(userName);
        //用PageInfo对结果进行包装
        PageInfo<BlogDoc> page = new PageInfo(list);
        //测试PageInfo全部属性
        System.out.println(page.getPageNum());
        System.out.println(page.getPageSize());
        System.out.println(page.getStartRow());
        System.out.println(page.getEndRow());
        System.out.println(page.getTotal());
        System.out.println(page.getPages());
        System.out.println(page.getFirstPage());
        System.out.println(page.getLastPage());
        System.out.println(page.isHasPreviousPage());
        System.out.println(page.isHasNextPage());
        return page;
    }
```

7、调用Service方法
>3.7、单元测试类添加分页查询测试方法
>UserServiceTest.java文件添加方法：
>   
>1   
>2   
>3   
>4   
>5   
>```java
    @Test 
    public void queryByPageTest(){  
        PageInfo<User> page =  userService.queryByPage(null, 1, 1);
        System.out.println(page);
    }
```

上面是网上的测试

在Controller中调用

```java
    @Autowired
    private BlogService blogService;
      ...
    PageInfo<BlogDoc> pageInfo =blogService.findAllBlogDocPaging(null,1,3);
```
