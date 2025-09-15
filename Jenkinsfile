pipeline {

    agent any
    tools {
        maven 'Maven'
    }

    stages {
        stage('Build') {
            steps {
                sh "mvn clean install -DskipTests"
            }
        }

        stage('Test') {

            steps {

                script{
                    catchError(buildResult:'UNSTABLE',stageResult:'FAILURE'){
                        withCredentials([userNamePassword(
                                credentialsId: 'REST_BOOKER_CREDS',
                                usernameVariable: 'RESTFULBOOKER_USERNAME',
                                passwordVariable: 'RESTFULBOOKER_PASSWORD'
                        )]) {
                            sh "mvn test -Pregression"
                        }

                    }
                }

            }
        }

        stage('Reports') {
            steps {
                script{
                    allure includeProperties: false, jdk: '', results: [[path: 'allure-results']]
                }

            }
        }

    }

}