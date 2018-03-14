这篇会讲得比较基础，力求通过一个简单的Spring MVC 例子，让读者都了解SpringBoot 是怎么一回事。

项目构建我们会使用gradle。如果你更熟悉或喜欢maven，也是没任何问题的。IDE 笔者用习惯[Eclipse](http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/oxygen2)了。

> 如果你对gradle不了解，可以查看[官网](https://gradle.org/)。当然，你也可以先不了解，只是[安装个gradle](https://gradle.org/install/)就可以。

好，先创建一个gradle的项目目录。P.S. 跟maven项目目录是一样的。
```
hello-world
    ∟ src
		    ∟ main
				    ∟ java
						∟ resources
				∟ test
				    ∟ java
						∟ resources
```

接着，在根目录（即hello-world 目录下）创建一个`build.gradle`文件。  
**build.gradle**
```
buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:2.0.0.RELEASE")
    }
}

apply plugin: 'java'
apply plugin: 'eclipse'
apply plugin: 'idea'
apply plugin: 'org.springframework.boot'
apply plugin: 'io.spring.dependency-management'

jar {
    baseName = 'hello-world'
    version =  '0.1.0'
}

repositories {
    mavenCentral()
}

sourceCompatibility = 1.8
targetCompatibility = 1.8

dependencies {
    compile("org.springframework.boot:spring-boot-starter-web")
    compile("org.springframework.boot:spring-boot-starter-thymeleaf")
    testCompile("org.springframework.boot:spring-boot-starter-test")
}
```
在根目录下执行`gradle eclipse` 命令（如果使用IDEA，应该是`gradle idea`），gradle 会生成一个Eclipse 可导入的项目。这个过程可能会比较费时，因为gradle 会拉取maven 拉取依赖包到本地。然后就可以在Eclipse 中improt 了。

这时候Eclipse 中看到的应该是这样的项目结构。  
![hello-world](http://static.youbenzi.com/374654df8e873e733ec56a3861ed8024.png?imageView2/1/format/webp)

接下来创建两个类。  
**src/main/java/com/youbenzi/Application.java**
```
package com.youbenzi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
}
```
**src/main/java/com/youbenzi/HelloController.java**
```
package com.youbenzi;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@RequestMapping(path = "/hello")
	public String hello(@RequestParam(defaultValue = "World", required = false, name = "name") String name,
			Model model) {
		return "Hello, " + name;
	}

}
```

运行一下`src/main/java/com/youbenzi/Application.java`的`main`函数。访问[http://localhost:8080/hello](http://localhost:8080/hello)。应该可以看到如下内容：
![hello-world](http://static.youbenzi.com/68b89d42face35b36d6b4dc909ff2161.png?imageView2/1/format/webp)

就这么简单，Spring Boot 内置了一个Tomcat，一运行Spring Boot Application，就自动打包部署了。比起以前我们写web简单太多！

这就是一个基于Spring Boot 的Spring MVC 例子。我们来解释下这两个类。

在`Application.java` 中，`@SpringBootApplication` 注解表明这个类是一个SrpingBoot Application，然后用`SpringApplication.run(Application.class, args);` 运行起来这个Application。基本上所有的SpringBoot Application 都是这么启动。

至于`HelloController.java`，熟悉Spring MVC 的读者应该看起来很熟悉了。`@RestController` 表明这是一个直接返回响应内容的Controller，`@RequestMapping(path = "/hello")` 指定`hello()` 响应`/hello`的Get请求（默认是Get请求，可以通过RequestMapping 的method 字段来修改，比如`@RequestMapping(path = "/hello", method = RequestMethod.POST)`）。`@RequestParam(defaultValue = "World", required = false, name = "name") String name` 说明参数`name` 是一个请求参数，对应的请求参数名为`name`，非必要参数，默认值为`World`。最后，retrun 的内容即我们访问`/hello` 时见到的内容。

写到这里，读者可能会有个疑问：那我要响应一个页面的内容怎么办？来，我们改造下`HelloController.java`类。如下修改
**src/main/java/com/youbenzi/HelloController.java**
```
package com.youbenzi;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@RestController
@Controller
public class HelloController {

	@RequestMapping(path = "/hello")
	public String hello(@RequestParam(defaultValue = "World", required = false, name = "name") String name,
			Model model) {
		model.addAttribute("name", name);
//		return "Hello, " + name;
		return "hello";
	}

}
```
主要有三个修改：注解换成`@Controller`；增加`model.addAttribute("name", name);`，这是把name 值传给我们的页面模版；return 的值为页面模版的文件名，这里说明我们的页面模版为`hello.html`。

接着创建模版文件，在`src/main/resources` 下创建一个文件夹`templates`，然后新建一个`hello.html` 文件。
**src/main/resources/templates/hello.html**
```
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Getting Started: Serving Web Content</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
    <p th:text="'Hello, ' + ${name} + '!'" />
</body>
</html>
```

再运行一下`src/main/java/com/youbenzi/Application.java`的`main`函数。访问[http://localhost:8080/hello](http://localhost:8080/hello)。可以发现已经能访问到我们刚定义的模版页了，试下访问[http://localhost:8080/hello?name=Jack](http://localhost:8080/hello?name=Jack)。

读者现在应该有很多疑问，先解释下。SpringBoot 默认使用的模版引擎是[Thymeleaf](http://www.thymeleaf.org/doc/tutorials/2.1/thymeleafspring.html)，下面是关于Thymeleaf 在SpringBoot 中的默认配置：
```
# THYMELEAF (ThymeleafAutoConfiguration)
spring.thymeleaf.cache=true # Whether to enable template caching.
spring.thymeleaf.check-template=true # Whether to check that the template exists before rendering it.
spring.thymeleaf.check-template-location=true # Whether to check that the templates location exists.
spring.thymeleaf.enabled=true # Whether to enable Thymeleaf view resolution for Web frameworks.
spring.thymeleaf.enable-spring-el-compiler=false # Enable the SpringEL compiler in SpringEL expressions.
spring.thymeleaf.encoding=UTF-8 # Template files encoding.
spring.thymeleaf.excluded-view-names= # Comma-separated list of view names (patterns allowed) that should be excluded from resolution.
spring.thymeleaf.mode=HTML # Template mode to be applied to templates. See also Thymeleaf's TemplateMode enum.
spring.thymeleaf.prefix=classpath:/templates/ # Prefix that gets prepended to view names when building a URL.
spring.thymeleaf.reactive.chunked-mode-view-names= # Comma-separated list of view names (patterns allowed) that should be the only ones executed in CHUNKED mode when a max chunk size is set.
spring.thymeleaf.reactive.full-mode-view-names= # Comma-separated list of view names (patterns allowed) that should be executed in FULL mode even if a max chunk size is set.
spring.thymeleaf.reactive.max-chunk-size=0 # Maximum size of data buffers used for writing to the response, in bytes.
spring.thymeleaf.reactive.media-types= # Media types supported by the view technology.
spring.thymeleaf.servlet.content-type=text/html # Content-Type value written to HTTP responses.
spring.thymeleaf.suffix=.html # Suffix that gets appended to view names when building a URL.
spring.thymeleaf.template-resolver-order= # Order of the template resolver in the chain.
spring.thymeleaf.view-names= # Comma-separated list of view names (patterns allowed) that can be resolved.
```
相信看完之后，关于这个页面模版就没啥疑问了。

SpringBoot 也支持其它模版引擎，需要的读者可以自行查找相关资料。

看到这里，你可能会发现，为啥都没用过配置文件？上面讲到的Thymeleaf 配置如果我们要修改，又应该怎么修改呢？

其实SpringBoot 的配置文件默认是`application.properties`，路径位于`src/main/resources`。如果我们要修改默认配置，可以在`src/main/resources` 下新建一个`application.properties` 文件，添加我们要修改的配置属性即可。例如，修改下SpringBoot Application 的服务器端口。
**src/main/resources/application.properties**
```
server.port=8081
```
再启动，会发现端口已经修改为8081了。

配置文件可以是properties文件，也可以是yaml文件。比如上面这个文件可以改成
**src/main/resources/application.yml**
```
server:
  port: 8081
```

两种文件类型随你喜欢，就我个人而言，还是更喜欢properties文件。

关于Spring Boot的配置属性，可以查看[官网的文档](https://docs.spring.io/spring-boot/docs/2.0.0.RELEASE/reference/htmlsingle/#common-application-properties)。
