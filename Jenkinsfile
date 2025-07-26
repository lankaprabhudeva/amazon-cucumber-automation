pipeline {
    agent any

   tools {
    maven 'Maven 3.9.9'
    jdk 'JDK17'
}


    stages {
        stage('Checkout Code') {
            steps {
                git url: 'https://github.com/lankaprabhudeva/amazon-cucumber-automation.git', branch: 'main'
            }
        }

        stage('Build Project') {
            steps {
                bat 'mvn clean install'
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Archive Reports') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }
    }

    post {
        success {
            echo 'Build Successful'
        }
        failure {
            echo ' Build Failed'
        }
    }
}
