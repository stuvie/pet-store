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

cf push $projectName -b https://github.com/cloudfoundry/nodejs-buildpack || error
slack -bot successful deploy of $projectName to PCF
