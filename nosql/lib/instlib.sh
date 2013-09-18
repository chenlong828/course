#!/bin/bash

mvn install:install-file \
    -DgroupId=com.danga  \
    -DartifactId=memcached \
    -Dversion=2.6.6 \
    -Dfile=java_memcached-release_2.6.6.jar \
    -Dpackaging=jar \
    -DgeneratePom=true