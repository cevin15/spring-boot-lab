#!/bin/bash

project="$1"
case "$project" in
"") echo "You need a project name"
;;
*)
mkdir "$project"
cd "$project"
mkdir src
mkdir src/main src/test
mkdir src/main/java src/main/resources src/test/java src/test/resources
echo "Create project $project success."
;;
esac
