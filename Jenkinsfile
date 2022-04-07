pipeline {
    agent any
    environment {
        LT_BUILD_NAME = "test-lt-pipeline02"
    }


    stages {

         stage ('Setup') {
            steps {
                      sh './LT --user ${LT_USERNAME} --key ${LT_ACCESS_KEY}  &'
            }


        stage ('Git Clone') {
            steps {
                git branch: 'main', url: 'https://github.com/ltamit/sample_ecom.git'
            }
        stage ('Maven Compile') {
            steps {
                sh 'mvn clean install'

            }
        }
        stage ('Git Clone') {
            steps {
                sh 'mvn test'
            }

        }
    }
}
