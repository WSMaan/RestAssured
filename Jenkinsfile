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
                // Run the tests and generate Allure results
                bat 'mvn test'
            }
        }

        stage('Allure Report') {
            steps {
                // Generate and publish Allure report
                allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
            }
        }
    }

    post {
        always {
            // Clean up workspace after the build
            cleanWs()
        }
    }
}
