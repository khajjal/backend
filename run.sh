/System/Library/Java/JavaVirtualMachines/1.6.0.jdk/Contents/Home/bin/java -Dclassworlds.conf=/usr/share/java/maven-3.0.3/bin/m2.conf -Dmaven.home=/usr/share/java/maven-3.0.3 -classpath /usr/share/java/maven-3.0.3/boot/plexus-classworlds-2.4.jar org.codehaus.classworlds.Launcher --no-plugin-registry --fail-fast --no-plugin-updates --strict-checksums --update-snapshots -DskipTests=true -f pom.xml install -P local;
/System/Library/Java/JavaVirtualMachines/1.6.0.jdk/Contents/Home/bin/java -Dclassworlds.conf=/usr/share/java/maven-3.0.3/bin/m2.conf -Dmaven.home=/usr/share/java/maven-3.0.3 -Dfile.encoding=UTF-8 -classpath /usr/share/java/maven-3.0.3/boot/plexus-classworlds-2.4.jar org.codehaus.classworlds.Launcher --no-plugin-registry --fail-fast --no-plugin-updates --strict-checksums --update-snapshots -DskipTests=true -f pom.xml org.apache.tomcat.maven:tomcat7-maven-plugin:2.0:run -P local
