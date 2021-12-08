# testng-error-notifier

Error reporter for TestNG.

If you put this on Maven project test classpath:
```
		<dependency>
			<groupId>io.github.solf</groupId>
			<artifactId>testng-error-notifier</artifactId>
			<version>${VERSION}</version>
			<scope>test</scope>
		</dependency>
```		
(replace version reference with any available versions from here:
https://repo.maven.apache.org/maven2/io/github/solf/testng-error-notifier/ )

then TestNG errors (exceptions) will be reported immediately to System.err
and slf4j (if available) under 'testng-error-notifier' logger 
along with the information about the test and timestamp -- this makes tracing
logs much easier if you use multi-threaded tests execution.

To easier find failure messages you can search for TESTNGFAIL
