pipeline {
    agent {
        label 'common'
    }

    tools {
        jdk 'JDK_1_8'
    }

    options {
        skipStagesAfterUnstable()
        disableConcurrentBuilds()
        buildDiscarder(logRotator(numToKeepStr: '28'))
        timeout(time: 1, unit: 'HOURS')
    }

    triggers {
        // at least once a day
        cron('H 12 * * *')
        // every sixty minutes
        pollSCM('H/60 * * * *')
    }

    stages {
        stage("SCM Checkout") {
            steps {
                deleteDir()
                checkout scm
            }
        }

        stage("Maven") {
            steps {
                mavenbuild mavenArgs: "-DskipTests=true"
            }
        }

        stage("Nexus Lifecycle") {
            steps {
                mavenbuild mavenArgs: "-DskipTests=true dependency:copy-dependencies"
                nexusPolicyEvaluation failBuildOnNetworkError: false, 
                    iqApplication: 'com.baloise.open.stripe2paypal.stripe2paypal-csv', 
                    iqScanPatterns: [[scanPattern: 'target/dependency/*.jar']], 
                    iqStage: 'build', 
                    jobCredentialsId: ''
            }
        }
    }
}
