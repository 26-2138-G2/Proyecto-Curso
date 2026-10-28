pipeline {

    // Cualquier agente disponible: en el aula, el propio Jenkins.
    agent any

    // Los nombres entre comillas son los de Manage Jenkins > Tools (S13-2) y deben
    // coincidir letra por letra con ellos.
    tools {
        jdk 'jdk-21'
        maven 'maven-3.9'
    }

    // Los mismos dos parámetros de la Demo 1 (S15-1).
    parameters {
        booleanParam(name: 'EJECUTAR_PRUEBAS', defaultValue: true, description: 'Marcar para ejecutar las pruebas. Desmarcar para saltarlas.')
        booleanParam(name: 'EMPAQUETAR', defaultValue: true, description: 'Marcar para generar y guardar el JAR. Desmarcar para omitir la etapa Empaquetar.')
    }

    stages {

        stage('Compilar') {
            steps {
                // clean borra target/ antes de empezar: así no quedan informes de una construcción anterior.
                bat 'mvn -B clean compile'
            }
        }

        stage('Probar') {
            environment {
                POSTGRES_PASSWORD = credentials('postgres-password')
            }
            steps {
                // Si EJECUTAR_PRUEBAS está marcado, Maven ejecuta las pruebas con el agente de JaCoCo
                // activado (lo declara el pom.xml) y deja el informe en target/site/jacoco/jacoco.xml.
                bat "mvn -B test -DskipTests=${!params.EJECUTAR_PRUEBAS}"
            }
            post {
                always {
                    // Publica los resultados de las pruebas (S13-2 y S14-1).
                    junit testResults: 'target/surefire-reports/*.xml', allowEmptyResults: true

                    // recordCoverage publica la cobertura. tools indica de qué herramienta es el informe:
                    // parser 'JACOCO' le dice que el formato es el de JaCoCo. Sin indicar pattern, el plugin
                    // busca en el espacio de trabajo **/jacoco.xml, que es donde lo deja Maven.
                    recordCoverage(tools: [[parser: 'JACOCO']])
                }
            }
        }

        stage('Empaquetar') {
            when {
                expression { return params.EMPAQUETAR }
            }
            steps {
                bat 'mvn -B package -DskipTests'
                archiveArtifacts artifacts: 'target/*.jar'
            }
        }
    }

    // post fuera de stages: se evalúa una sola vez, cuando termina todo el pipeline (S14-2).
    post {

        // failure: se ejecuta solo si la construcción ha terminado en rojo.
        failure {
            echo "FALLO: la construccion ${env.BUILD_NUMBER} de ${env.JOB_NAME} ha fallado. Revisar la etapa en rojo."
        }

        // success: se ejecuta solo si la construcción ha terminado en verde.
        success {
            echo "OK: la construccion ${env.BUILD_NUMBER} de ${env.JOB_NAME} ha terminado bien."
        }

        // fixed: esta construcción termina bien y la anterior no.
        fixed {
            echo "RECUPERADA: ${env.JOB_NAME} vuelve a funcionar desde la construccion ${env.BUILD_NUMBER}."
        }
    }
}
