node {
  stage('Checkout') {
      checkout scm
  }
	stage('Backend Build') {
	  dir('pet-services') {
      sh './mvnw clean compile'
    }
	}
	stage('Backend Unit Tests') {
    dir('pet-services') {
      sh './mvnw test'
    }
	}
  stage('Backend Integration Tests') {
    dir('pet-services') {
      sh './mvnw org.jacoco:jacoco-maven-plugin:prepare-agent install -Dmaven.test.failure.ignore=false'
    }
	}
  stage('Backend Code Metrics') {
    dir('pet-services') {
      sh './mvnw checkstyle:checkstyle pmd:pmd sonar:sonar'
    }
	}
  stage('Backend Deploy to PCF') {
    dir('pet-services') {
      sh 'cf push'
      sh 'slack -bot successful backend deploy to PCF'
    }
	}
	stage('Frontend Build') {
	  dir('storefront') {
      sh 'yarn install'
      sh 'yarn ng -- build --prod --aot --no-progress'
    }
	}
  stage('Frontend Deploy to PCF') {
    sh 'pwd'
    sh 'whoami'
    sh 'node --version'
    sh './bin/nodejs2pcf.sh storefront'
	}
}
