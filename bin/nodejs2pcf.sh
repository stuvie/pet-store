#!/bin/sh

error() {
	slack -error failed deploy of $projectName to PCF && exit 1
}

usage() {
	echo usage: nodejs2pcf 'projectName' && exit 1
}

if test $# -lt 1; then
	usage
fi

projectName=$1

cp ../Staticfile  Staticfile
cf push $projectName -b staticfile_buildpack || error
slack -bot successful deploy of $projectName to PCF
