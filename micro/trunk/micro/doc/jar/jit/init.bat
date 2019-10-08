mvn install:install-file
-Dfile=jit_pkitool-V2.2.0.1.jar
-DgroupId=com.jit
-DartifactId=jit_pkitool
-Dversion=V2.2.0.1
-Dpackaging=jar

mvn install:install-file
-Dfile=jit-cinas-authen-1000-jdk15.jar
-DgroupId=com.jit
-DartifactId=jit-cinas-authen
-Dversion=1000-jdk15
-Dpackaging=jar

mvn install:install-file
-Dfile=jit-cinas-commons-1000-jdk13.jar
-DgroupId=com.jit
-DartifactId=jit-cinas-commons
-Dversion=1000-jdk13
-Dpackaging=jar