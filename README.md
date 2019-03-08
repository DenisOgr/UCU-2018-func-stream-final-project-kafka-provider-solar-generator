# UCU-2018-func-stream-final-project-kafka-provider-solar-generator
This is a solar panel sensor provider(Kafka-connect) for scala  final project.
Your task build jar connect. To do this:

Build Docker image (first time only)
```
make build
```
Build fat jar
```
make assembly
```
Copy `{root}/kafka-connector-solar-panel.jar` to kafka plugins