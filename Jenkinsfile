node('master') {
    def WORKSPACE = env.WORKSPACE
    def BUILD = env.BUILD_NUMBER
    def BROWSER = env.BROWSER
    def TEST_SUITE = env.TEST_SUITE
    def JOB_NAME = "${env.JOB_NAME.replaceAll(/_|-|\\s|/, '').toLowerCase()}${BUILD}"

    def IMAGE = "${JOB_NAME}_image"
    def CONTAINER = "${JOB_NAME}_container"

    stage("Checkout Repository") {
        checkout scm
    }

    stage("Build Docker Image") {
        sh "docker build --tag ${IMAGE} --file '${WORKSPACE}/Dockerfile' '${WORKSPACE}' "
    }

    stage("Run Selenium Grid Hub") {
        sh "docker-compose -p ${JOB_NAME} up --remove-orphans -d"
    }

    stage("Run Tests") {
        def exitCode = sh script: "docker run -t --network=${JOB_NAME}_default --name ${CONTAINER} ${IMAGE} mvn clean test " +
                "-Dremote=true -Dbrowser=${BROWSER} -Dtest.suite=${TEST_SUITE} -q", returnStatus: true
        if (exitCode == 1)
            currentBuild.result = "UNSTABLE"
    }

    stage("Reports Generate") {
        sh "mkdir ${WORKSPACE}/reports"
        sh "docker cp ${CONTAINER}:/app/screenshots/. ${WORKSPACE}/screenshots"
        sh "docker cp ${CONTAINER}:/app/target/surefire-reports/. ${WORKSPACE}/reports"
    }

    stage("Reports Publish") {
        publishHTML([
                allowMissing         : false,
                alwaysLinkToLastBuild: true,
                keepAll              : true,
                reportDir            : "${WORKSPACE}/reports",
                reportFiles          : 'index.html',
                reportName           : 'HtmlReports',
                reportTitles         : 'Php Travels'])
    }

    stage("Stop Selenium Grid Hub") {
        sh "docker-compose -p ${JOB_NAME} down --remove-orphans"
    }

    stage("Delete Docker Image & Container") {
        sh "docker rm  ${CONTAINER}"
        sh "docker rmi ${IMAGE} -f"
    }
}