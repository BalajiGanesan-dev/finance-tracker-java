pipeline {
    agent any

    environment {
        DBPASSWORD = credentials('local_db_password')
        EC2_HOST   = '18.60.46.165'
    }

    stages {
        stage('Checkout') {
            steps { checkout scm }
        }

        stage('Build & Test') {
            steps {
                sh 'chmod +x mvnw'
                sh './mvnw -B clean package'
            }
        }

        stage('Archive') {
            steps { archiveArtifacts artifacts: 'target/*.jar', fingerprint: true }
        }

        stage('Deploy to AWS') {
            steps {
                sshagent(credentials: ['ec2-ssh-key']) {
                    sh '''
                      scp -o StrictHostKeyChecking=accept-new target/*.jar ubuntu@$EC2_HOST:/home/ubuntu/app/finance-tracker.jar.new
                      ssh -o StrictHostKeyChecking=accept-new ubuntu@$EC2_HOST \
                        "mv /home/ubuntu/app/finance-tracker.jar.new /home/ubuntu/app/finance-tracker.jar && sudo systemctl restart finance-tracker"
                    '''
                }
            }
        }

        stage('Verify') {
            steps {
                sh '''
                  sleep 10
                  for i in $(seq 1 12); do
                    if curl -fs http://$EC2_HOST:8080/transactions > /dev/null; then
                      echo "App is up"; exit 0
                    fi
                    echo "Waiting for app... ($i)"; sleep 5
                  done
                  echo "App did not come up"; exit 1
                '''
            }
        }
    }

    post {
        success { echo 'Build and deploy succeeded.' }
        failure { echo 'Pipeline failed: check the console output.' }
    }
}