pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Checkout the code from GitHub
                git branch: 'main', url: 'https://github.com/WSMaan/RestAssured.git'
            }
        }

        stage('Build') {
            steps {
                // Compile the code
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                // Run the tests using TestNG
                sh 'mvn test'
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
