pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Checkout the code from GitHub
                git branch: 'master', url: 'https://github.com/WSMaan/RestAssured.git'
            }
        }

        stage('Build') {
            steps {
                // Compile the code using Maven
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                // Run the tests using TestNG
                bat 'mvn test'
            }
        }
    }

    post {
        success {
            echo 'Pipeline succeeded!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}
