rootProject.name = "smartworker-backend-api"

include(":api-module")
include(":save-health-rate-module")
include(":alarm-module")
include(":document-db-module")
include(":common-messaging-module")
include(":common-messaging-module:kafka-config-data")
include(":common-messaging-module:kafka-consumer")
include(":common-messaging-module:kafka-model")
include(":common-messaging-module:kafka-producer")
