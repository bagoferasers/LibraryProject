#!/bin/bash
cd "$(dirname "$0")"
export JAVA_HOME=`/usr/libexec/java_home -v 1.8.0_261`
chmod u+x Library.jar
java -jar Library.jar