node('master') {
    def WORKSPACE = env.WORKSPACE
    def BUILD = env.BUILD_NUMBER
    def SUITE_FILE = env.SUITE_FILE
    def JOB_NAME = env.JOB_NAME.toLowerCase()

    def IMAGE = "${JOB_NAME}_${BUILD}_image"
    def CONTAINER = "${JOB_NAME}_${BUILD}_container"
    def LOCATION = "${WORKSPACE}/reports"

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
        def exitCode = sh script: "docker run -t --network ${JOB_NAME}_default --name ${CONTAINER} ${IMAGE} mvn clean test -Dbuild.number=${BUILD} -Dtest.suite=${SUITE_FILE}", returnStatus: true
        if (exitCode == 1)
            currentBuild.result = "UNSTABLE"
    }

    stage("Reports Generate") {
        sh "mkdir ${LOCATION}"
        sh "docker cp ${CONTAINER}:/app/target/surefire-reports/. ${LOCATION}"
    }

    stage("Reports Publish") {
        publishHTML([
                allowMissing         : false,
                alwaysLinkToLastBuild: true,
                keepAll              : true,
                reportDir            : "${LOCATION}/cucumber-html-reports",
                reportFiles          : 'index.html',
                reportName           : 'HTML Report',
                reportTitles         : ''])
    }

    stage("Stop Selenium Grid Hub") {
        sh "docker-compose -p ${JOB_NAME} down --remove-orphans"
    }

    stage("Delete Docker Image & Container") {
        sh "docker rm  ${CONTAINER}"
        sh "docker rmi ${IMAGE} -f"
    }

}