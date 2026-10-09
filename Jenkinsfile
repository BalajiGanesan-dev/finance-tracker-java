pipeline {
    agent any
    environment {
            DBPASSWORD = credentials('local_db_password')
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                sh 'chmod +x mvnw'
                sh './mvnw -B clean package'
            }
        }

        stage('Archive') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
    }

    post {
        success { echo 'CI finished: build succeeded.' }
        failure { echo 'CI failed: check the console output.' }
    }
}