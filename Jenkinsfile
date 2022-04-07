pipeline {
    agent any
    stages {

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
