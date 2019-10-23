#!/bin/bash
source ./framework.sh

####
## Week #3
####

TAG="version_04"

function build()
{
    maven    "clean package"
    if [ "$ERROR" = "1" ]
    then
	     echo "## Abort [project does not build]"
    else
	     echo "## \o/ Delivery OK \o/"
    fi
    ERROR=0
}

function check_env()
{
    git_tag_time  $TAG
    count_loc
    xml_key_value pom.xml groupId ca.uqam.inf5153.island
    xml_key_value pom.xml artifactId $1
    xml_key_value pom.xml version 0.1-SNAPSHOT
    PACKAGE=./src/main/java/ca/uqam/inf5153/island/$1
    exists_dir $PACKAGE
    exists_file $PACKAGE/Explorer.java
    exists_file ./journal.md
    if [ "$ERROR" = "1" ]
    then
	     echo "## Abort [bad environment]"
    else
	     build $1
    fi
    ERROR=0
}

function handle_repository()
{
    checkout_tag  $TAG
    if [ "$ERROR" = "1" ]
    then
	     echo "## Abort [missing tag]"
    else
	     check_env $1
    fi
    ERROR=0
}

main
