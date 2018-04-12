node {
  stage('Checkout') {
      checkout scm
  }
	stage('Backend Build') {
	  dir('pet-services') {
      sh 'printenv'
      sh 'whoami'
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
      sh '/usr/local/bin/cf push'
      sh '/Users/steve/bin/slack -bot successful deploy of pet-services to PCF'
    }
	}
	stage('Frontend Build') {
	  dir('storefront') {
      sh 'yarn install'
      sh 'yarn ng -- build --prod --aot --no-progress'
    }
	}
  stage('Frontend Deploy to PCF') {
    dir('storefront/dist') {
      sh '/Users/steve/bin/nodejs2pcf.sh steve-petstore'
    }
	}
}
