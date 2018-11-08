By default, tests are not run. To build an application and run tests as part of the build, override the default MAVEN_ARGS, as in the following command:

$ oc new-app redhat-openjdk18-openshift~<git_repo_URL> --context-dir=<context-dir> --build-env='MAVEN_ARGS=-e -Popenshift -Dcom.redhat.xpaas.repo.redhatga package'